package org.nxstudio.octopus.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.nxstudio.octopus.mybatis.mapper.RawDataNasdaqCompanyMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooDividendMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooHistoricalMapper;
import org.nxstudio.octopus.mybatis.model.RawDataNasdaqCompany;
import org.nxstudio.octopus.mybatis.model.RawDataYahooDividend;
import org.nxstudio.octopus.mybatis.model.RawDataYahooHistorical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

@Service
public class CompanyService {
	private final static Logger	l	= LoggerFactory.getLogger(CompanyService.class);
	@Autowired
	private SqlSessionFactory	factory;

	public CompanyService() {}

	/**
	 * getHistoricalDaily
	 *
	 * @param symbol
	 * @return
	 */
	public List<RawDataYahooHistorical> getHistoricalDaily(String symbol) {
		final SqlSession session = factory.openSession();
		final RawDataYahooHistoricalMapper mapper = session.getMapper(RawDataYahooHistoricalMapper.class);

		try {
			return mapper.getAll(symbol);
		} catch (Exception e) {
			l.error("CompanyService.getHistoricalDaily: Exception", e);
			return ImmutableList.of();
		} finally {
			session.close();
		}
	}

	/**
	 * getAllDividends
	 *
	 * @param symbol
	 * @return
	 */
	public List<RawDataYahooDividend> getAllDividends(String symbol) {
		final SqlSession session = factory.openSession();
		final RawDataYahooDividendMapper mapper = session.getMapper(RawDataYahooDividendMapper.class);

		try {
			return mapper.getAll(symbol);
		} catch (Exception e) {
			l.error("CompanyService.getAllDividends: Exception", e);
			return ImmutableList.of();
		} finally {
			session.close();
		}
	}

	/**
	 * getAvailableCompanies
	 *
	 * @return
	 */
	public List<RawDataNasdaqCompany> getAvailableCompanies() {
		final SqlSession session = factory.openSession();
		final RawDataNasdaqCompanyMapper mapper = session.getMapper(RawDataNasdaqCompanyMapper.class);
		try {
			return mapper.getAvailableCompanies();
		} catch (Exception e) {
			l.error("CompanyService.getAvailableCompanies: Exception", e);
			return ImmutableList.of();
		} finally {
			session.close();
		}
	}

	/**
	 * getAllCompanies
	 *
	 * @return
	 */
	public List<RawDataNasdaqCompany> getAllCompanies() {
		final SqlSession session = factory.openSession();
		final RawDataNasdaqCompanyMapper mapper = session.getMapper(RawDataNasdaqCompanyMapper.class);
		try {
			return mapper.getAllCompanies();
		} catch (Exception e) {
			l.error("CompanyService.getAllCompanies: Exception", e);
			return ImmutableList.of();
		} finally {
			session.close();
		}
	}
}
