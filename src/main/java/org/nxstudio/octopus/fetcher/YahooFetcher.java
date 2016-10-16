/**
 *
 */
package org.nxstudio.octopus.fetcher;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooDividendMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooHistoricalMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooMinutelyMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooQuoteMapper;
import org.nxstudio.octopus.mybatis.model.RawDataYahooDividend;
import org.nxstudio.octopus.mybatis.model.RawDataYahooHistorical;
import org.nxstudio.octopus.mybatis.model.RawDataYahooMinutely;
import org.nxstudio.octopus.mybatis.model.RawDataYahooQuote;
import org.nxstudio.octopus.service.SymbolService;
import org.nxstudio.octopus.utils.GenericUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.common.net.UrlEscapers;

/**
 * YahooFetcher
 *
 * @author TimoYe
 */
@Component
public class YahooFetcher {
	private final static Logger	l					= LoggerFactory.getLogger(YahooFetcher.class);
	private final static String	URL_TPL_MINUTELY	= "https://chartapi.finance.yahoo.com/instrument/1.0/%s/chartdata;type=quote;range=1d/csv";
	//private final static String	URL_TPL_YEARLY		= "https://chartapi.finance.yahoo.com/instrument/1.0/%s/chartdata;type=quote;ys=%s;yz=0/csv";
	private final static String	URL_YQL				= "https://query.yahooapis.com/v1/public/yql?q=%s";
	private final static String	URL_YQL_TAIL		= "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
	@Autowired
	private SymbolService		symbolService;
	@Autowired
	private SqlSessionFactory	factory;

	/**
	 * fetchDividend
	 */
	public void fetchDividend() {
		l.info("YahooFetcher.fetchDividend: Starting...");
		List<String> symbols = symbolService.getAllSymbols();
		for (final String symbol : symbols) {
			try {
				l.info("YahooFetcher.fetchDividend: Fetching symbol {}", symbol);
				saveDividend(symbol, getDividends(symbol));
				l.info("YahooFetcher.fetchDividend: Finish fetching symbol {}", symbol);
			} catch (Exception e) {
				l.error("YahooFetcher.fetchDividend: Error", e);
			}
		}
		l.info("YahooFetcher.fetchDividend: Done.");
	}

	public void fetchHistorical() {
		l.info("YahooFetcher.fetchHistorical: Starting...");
		final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		final List<String> symbols = symbolService.getAllSymbols();
		for (final String symbol : symbols) {
			try {
				final Set<Integer> years = new HashSet<>(symbolService.getHistoricalYears(symbol));
				final int ipoYear = symbolService.getIpoYear(symbol);
				for (int year = currentYear; year >= ipoYear; year--) {
					if (year != currentYear && years.contains(year)) {
						continue;
					}
					l.info("YahooFetcher.fetchHistorical: Fetching symbol {} on year {}", symbol, year);
					List<RawDataYahooHistorical> quotes = getHistorical(symbol, year);
					if (year != currentYear && quotes.isEmpty()) {
						l.info("YahooFetcher.fetchHistorical: Break symbol {} on year {}", symbol, year);
						break;
					}
					l.info("YahooFetcher.fetchHistorical: Saving symbol {} on year {}", symbol, year);
					saveHistorical(quotes);
					l.info("YahooFetcher.fetchHistorical: Finish fetching symbol {} on year {}", symbol, year);
				}
			} catch (Exception e) {
				l.error("YahooFetcher.fetchHistorical: Error", e);
			}
		}
		l.info("YahooFetcher.fetchHistorical: Done.");
	}

	/**
	 * fetchMinutely
	 */
	public void fetchMinutely() {
		l.info("YahooFetcher.fetchMinutely: Starting...");

		final ExecutorService pool = Executors.newFixedThreadPool(8, new ThreadFactory() {
			private int id = 1;

			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "YahooFetcher-Minutely-" + id++);
			}
		});

		List<String> symbols = symbolService.getAllSymbols();
		for (final String symbol : symbols) {
			pool.submit(new Runnable() {

				@Override
				public void run() {
					try {
						l.info("YahooFetcher.fetchMinutely: Fetching symbol {}", symbol);
						saveMinutely(symbol, getMinutely(symbol));
						l.info("YahooFetcher.fetchMinutely: Finish fetching symbol {}", symbol);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			});
		}

		pool.shutdown();
		do {
			l.info("YahooFetcher.fetchMinutely: Waiting for terminated.");
			GenericUtils.sleep(1000);
		} while (!pool.isTerminated());

		l.info("YahooFetcher.fetchMinutely: Done.");
	}

	/**
	 * fetchQuote
	 */
	public void fetchQuote() {
		l.info("YahooFetcher.fetchQuote: Starting...");
		try {
			saveQuote(getQuote(symbolService.getAllSymbols()));
		} catch (Exception e) {
			l.error("YahooFetcher.fetchQuote: Error", e);
		}
		l.info("YahooFetcher.fetchQuote: Done.");
	}

	/**
	 * saveDividend
	 *
	 * @param symbol
	 * @param objects
	 * @throws Exception
	 */
	private void saveDividend(String symbol, List<RawDataYahooDividend> objects) throws Exception {
		if (objects == null || objects.isEmpty()) { return; }
		final SqlSession session = factory.openSession(ExecutorType.BATCH, false);
		final RawDataYahooDividendMapper mapper = session.getMapper(RawDataYahooDividendMapper.class);
		try {
			for (RawDataYahooDividend object : objects) {
				mapper.replace(object);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * saveMinutely
	 *
	 * @param symbol
	 * @param objects
	 * @throws Exception
	 */
	private void saveMinutely(String symbol, List<RawDataYahooMinutely> objects) throws Exception {
		if (objects == null || objects.isEmpty()) { return; }
		final SqlSession session = factory.openSession(ExecutorType.BATCH, false);
		final RawDataYahooMinutelyMapper mapper = session.getMapper(RawDataYahooMinutelyMapper.class);
		try {
			mapper.delete(symbol, objects.get(0).getTimestamp(), objects.get(objects.size() - 1).getTimestamp());
			for (RawDataYahooMinutely object : objects) {
				mapper.insert(object);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * saveQuote
	 *
	 * @param objects
	 * @throws Exception
	 */
	private void saveQuote(List<RawDataYahooQuote> objects) throws Exception {
		if (objects == null || objects.isEmpty()) { return; }
		final SqlSession session = factory.openSession(ExecutorType.BATCH, false);
		final RawDataYahooQuoteMapper mapper = session.getMapper(RawDataYahooQuoteMapper.class);
		try {
			for (RawDataYahooQuote object : objects) {
				mapper.insert(object);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * saveHistorical
	 *
	 * @param objects
	 * @throws Exception
	 */
	private void saveHistorical(List<RawDataYahooHistorical> objects) throws Exception {
		if (objects == null || objects.isEmpty()) { return; }
		final SqlSession session = factory.openSession(ExecutorType.BATCH, false);
		final RawDataYahooHistoricalMapper mapper = session.getMapper(RawDataYahooHistoricalMapper.class);
		try {
			for (RawDataYahooHistorical object : objects) {
				mapper.insert(object);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private byte[] getContentFromYQL(final String yql) throws Exception {
		final String urlString = String.format(URL_YQL, UrlEscapers.urlPathSegmentEscaper().escape(yql)) + URL_YQL_TAIL;
		final URL url = new URL(urlString);
		return Resources.asByteSource(url).read();
	}

	private List<RawDataYahooQuote> getQuote(List<String> symbols) throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final List<RawDataYahooQuote> result = new ArrayList<>();
		final String sqlString = "select * from yahoo.finance.quote where symbol in (%s)";
		final String sql = String.format(sqlString, "\"" + StringUtils.join(symbols, "\",\"") + "\"");
		byte[] content = getContentFromYQL(sql);
		JsonNode rootNode = mapper.readTree(content);

		JsonNode queryNode = rootNode.get("query");
		if (queryNode == null) { return result; }
		String created = queryNode.get("created").asText();
		if (Strings.isNullOrEmpty(created)) { return result; }
		final DateTime date = ISODateTimeFormat.dateTimeParser().parseDateTime(created);
		final Date timestamp = date.toDate();

		JsonNode quotes = rootNode.get("query").get("results").get("quote");
		if (quotes == null || !quotes.isArray()) { return result; }
		for (JsonNode quoteNode : quotes) {
			RawDataYahooQuote quote = new RawDataYahooQuote();
			quote.setTimestamp(timestamp);
			quote.setSymbol(quoteNode.get("symbol").asText());
			quote.setAvgDailyVolume(quoteNode.get("AverageDailyVolume").asDouble());
			quote.setChange(quoteNode.get("Change").asDouble());
			quote.setDays_low(quoteNode.get("DaysLow").asDouble());
			quote.setDays_high(quoteNode.get("DaysHigh").asDouble());
			quote.setYear_low(quoteNode.get("YearLow").asDouble());
			quote.setYear_high(quoteNode.get("YearHigh").asDouble());
			quote.setMarketCapitalization(quoteNode.get("MarketCapitalization").asText());
			quote.setLastTradePriceOnly(quoteNode.get("LastTradePriceOnly").asDouble());
			quote.setDays_range(quoteNode.get("DaysRange").asText());
			quote.setName(quoteNode.get("Name").asText());
			quote.setVolume(quoteNode.get("Volume").asDouble());
			quote.setStockExchange(quoteNode.get("StockExchange").asText());
			result.add(quote);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		YahooFetcher fetcher = new YahooFetcher();
		fetcher.getHistorical("AAPL", 2016);
	}

	private List<RawDataYahooHistorical> getHistorical(final String symbol, final int year) throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		final List<RawDataYahooHistorical> result = new ArrayList<>();
		final String sqlString = "select * from yahoo.finance.historicaldata where symbol = \"%s\" and startDate = \"%d-01-01\" and endDate = \"%d-12-31\"";
		final String sql = String.format(sqlString, symbol, year, year);
		byte[] content = getContentFromYQL(sql);
		JsonNode rootNode = mapper.readTree(content);
		JsonNode quotes = rootNode.get("query").get("results").get("quote");
		if (quotes == null || !quotes.isArray()) { return result; }
		for (JsonNode quoteNode : quotes) {
			RawDataYahooHistorical quote = new RawDataYahooHistorical();
			quote.setSymbol(symbol);
			quote.setTimestamp(formatter.parse(quoteNode.get("Date").asText()));
			quote.setOpen(quoteNode.get("Open").asDouble());
			quote.setHigh(quoteNode.get("High").asDouble());
			quote.setLow(quoteNode.get("Low").asDouble());
			quote.setClose(quoteNode.get("Close").asDouble());
			quote.setVolume(quoteNode.get("Volume").asLong());
			quote.setAdjClose(quoteNode.get("Adj_Close").asDouble());
			result.add(quote);
		}
		return result;
	}

	private List<RawDataYahooDividend> getDividends(final String symbol) throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		final List<RawDataYahooDividend> result = new ArrayList<>();
		final String sqlString = "select * from yahoo.finance.dividendhistory where symbol = \"%s\" and startDate = \"1900-01-01\" and endDate = \"2099-12-31\"";
		final String sql = String.format(sqlString, symbol);
		byte[] content = getContentFromYQL(sql);
		JsonNode rootNode = mapper.readTree(content);
		JsonNode quotes = rootNode.get("query").get("results").get("quote");
		if (quotes == null || !quotes.isArray()) { return result; }

		for (JsonNode quoteNode : quotes) {
			RawDataYahooDividend quote = new RawDataYahooDividend();
			quote.setSymbol(symbol);
			quote.setTimestamp(formatter.parse(quoteNode.get("Date").asText()));
			quote.setDividend(quoteNode.get("Dividends").asDouble());
			result.add(quote);
		}
		return result;
	}

	private List<RawDataYahooMinutely> getMinutely(final String symbol) throws Exception {
		final String urlString = String.format(URL_TPL_MINUTELY, symbol);
		final URL url = new URL(urlString);
		ImmutableList<String> lines = Resources.asCharSource(url, Charsets.ISO_8859_1).readLines();
		List<RawDataYahooMinutely> result = new ArrayList<>();
		if (lines != null) {
			for (String line : lines) {
				if (isValid(line)) {
					result.add(new RawDataYahooMinutely(symbol, line));
				}
			}
		}
		return result;
	}

	private boolean isValid(String line) {
		String[] fs = StringUtils.splitByWholeSeparator(line, ",");
		if (fs.length != 6) { return false; }
		if (StringUtils.startsWithIgnoreCase(line, "values:")) { return false; }
		return true;
	}

}
