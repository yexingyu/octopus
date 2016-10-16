package org.nxstudio.octopus.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.nxstudio.octopus.mybatis.mapper.OctopusSymbolMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataNasdaqCompanyMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooHistoricalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

@Service
public class SymbolService {
	private final static Logger	l	= LoggerFactory.getLogger(SymbolService.class);
	@Autowired
	private SqlSessionFactory	factory;

	public SymbolService() {}

	/**
	 * getAllSymbols
	 *
	 * @return
	 */
	public List<String> getAllSymbols() {
		final SqlSession session = factory.openSession();
		final OctopusSymbolMapper mapper = session.getMapper(OctopusSymbolMapper.class);

		try {
			return mapper.getAllSymbols();
		} catch (Exception e) {
			l.error("SymbolService.getAllSymbols: Exception", e);
			return ImmutableList.of();
		} finally {
			session.close();
		}
	}

	/**
	 * getIpoYear
	 *
	 * @param symbol
	 * @return
	 */
	public int getIpoYear(String symbol) {
		final SqlSession session = factory.openSession();
		final RawDataNasdaqCompanyMapper mapper = session.getMapper(RawDataNasdaqCompanyMapper.class);

		try {
			return mapper.getIpoYear(symbol);
		} catch (Exception e) {
			l.warn("SymbolService.getIpoYear: Error on query ipo year for '{}'", symbol);
			return 0;
		} finally {
			session.close();
		}
	}

	/**
	 * getHistoricalYears
	 *
	 * @param symbol
	 * @return
	 */
	public List<Integer> getHistoricalYears(String symbol) {
		final SqlSession session = factory.openSession();
		final RawDataYahooHistoricalMapper mapper = session.getMapper(RawDataYahooHistoricalMapper.class);

		try {
			return mapper.getHistoricalYears(symbol);
		} catch (Exception e) {
			l.error("SymbolService.getHistoricalYears: Exception", e);
			return ImmutableList.of();
		} finally {
			session.close();
		}
	}
}
