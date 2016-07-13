package org.nxstudio.octopus;

import org.nxstudio.octopus.fetcher.NasdaqFetcher;
import org.nxstudio.octopus.fetcher.YahooFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJob {
	private final static Logger	l	= LoggerFactory.getLogger(ScheduledJob.class);
	//	private final static long	MS_1S	= 1000L;
	//	private final static long	MS_1M	= 60 * MS_1S;
	//	private final static long	MS_5M	= 5 * MS_1M;
	//	private final static long	MS_15M	= 15 * MS_1M;
	//	private final static long	MS_30M	= 30 * MS_1M;
	//	private final static long	MS_1H	= 60 * MS_1M;
	//	private final static long	MS_3H	= 3 * MS_1H;
	//	private final static long	MS_6H	= 6 * MS_1H;
	//	private final static long	MS_12H	= 12 * MS_1H;
	//	private final static long	MS_1D	= 24 * MS_1H;
	@Autowired
	private NasdaqFetcher		nasdaqFetcher;
	@Autowired
	private YahooFetcher		yahooFetcher;

	/**
	 * fetchNasdaqCompany
	 */
	@Scheduled(cron = "0 0 1 * * MON-FRI")
	public void fetchNasdaqCompany() {
		try {
			nasdaqFetcher.fetchCompany();
		} catch (Exception e) {
			l.error("ScheduledJob.fetchNasdaqCompany: Error", e);
		}
	}

	/**
	 * fetchYahooMinutely
	 */
	@Scheduled(cron = "0 0 0-9,17-23 * * MON-FRI")
	public void fetchYahooMinutely() {
		try {
			yahooFetcher.fetchMinutely();
		} catch (Exception e) {
			l.error("ScheduledJob.fetchYahooMinutely: Error", e);
		}
	}

	/**
	 * fetchYahooDividend
	 */
	@Scheduled(cron = "0 0 0 * * MON-FRI")
	public void fetchYahooDividend() {
		try {
			yahooFetcher.fetchDividend();
		} catch (Exception e) {
			l.error("ScheduledJob.fetchYahooDividend: Error", e);
		}
	}

	/**
	 * fetchYahooHistorical
	 */
	@Scheduled(cron = "0 0 17,18 * * MON-FRI")
	public void fetchYahooHistorical() {
		try {
			yahooFetcher.fetchHistorical();
		} catch (Exception e) {
			l.error("ScheduledJob.fetchYahooHistorical: Error", e);
		}
	}

	/**
	 * fetchYahooQuote
	 */
	//	@Scheduled(initialDelay = 0, fixedRate = MS_1M)
	//	public void fetchYahooQuote() {
	//		try {
	//			yahooFetcher.fetchQuote();
	//		} catch (Exception e) {
	//			l.error("ScheduledJob.fetchYahooQuote: Error", e);
	//		}
	//	}

}
