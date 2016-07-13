package org.nxstudio.octopus.fetcher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.nxstudio.octopus.mybatis.mapper.RawDataNasdaqCompanyMapper;
import org.nxstudio.octopus.mybatis.model.RawDataNasdaqCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;

@Component
public class NasdaqFetcher {
	private final static Logger	l						= LoggerFactory.getLogger(NasdaqFetcher.class);
	private final static String	URL_FULL_COMPANY_LIST	= "http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NASDAQ&render=download";
	@Autowired
	private SqlSessionFactory	factory;

	public NasdaqFetcher() {}

	private List<RawDataNasdaqCompany> getContent() throws Exception {
		URL url = new URL(URL_FULL_COMPANY_LIST);
		ImmutableList<String> lines = Resources.asCharSource(url, Charsets.ISO_8859_1).readLines();
		List<RawDataNasdaqCompany> comps = new ArrayList<>();
		for (String line : lines) {
			if (StringUtils.startsWith(line, "\"Symbol")) {
				continue;
			}
			RawDataNasdaqCompany company = new RawDataNasdaqCompany(line);
			comps.add(company);
		}
		return comps;
	}

	private void save(List<RawDataNasdaqCompany> objects) {
		if (objects == null) { return; }
		final SqlSession session = factory.openSession(ExecutorType.BATCH, false);
		final RawDataNasdaqCompanyMapper mapper = session.getMapper(RawDataNasdaqCompanyMapper.class);

		try {
			for (RawDataNasdaqCompany company : objects) {
				mapper.insert(company);
			}
			l.info("NasdaqFetcher.save: Commiting..");
			Stopwatch stopwatch = Stopwatch.createStarted();
			session.commit();
			l.info("NasdaqFetcher.save: Committed, takes {} seconds.", stopwatch.elapsed(TimeUnit.SECONDS));
			stopwatch.reset();
		} catch (Exception e) {
			l.error("NasdaqFetcher.save: Error", e);
		} finally {
			session.close();
		}
	}

	/**
	 * fetchCompany
	 */
	public void fetchCompany() {
		l.info("NasdaqFetcher.fetchCompany: starting...");
		try {
			save(getContent());
		} catch (Exception e) {
			l.error("NasdaqFetcher.fetchCompany: Error", e);
		}
		l.info("NasdaqFetcher.fetchCompany: done.");
	}
}
