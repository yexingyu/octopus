package org.nxstudio.octopus.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooDividendMapper;
import org.nxstudio.octopus.mybatis.mapper.RawDataYahooHistoricalMapper;
import org.nxstudio.octopus.mybatis.model.RawDataYahooDividend;
import org.nxstudio.octopus.mybatis.model.RawDataYahooHistorical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
	private final static Logger	l	= LoggerFactory.getLogger(StockService.class);
	@Autowired
	private SqlSessionFactory	factory;

	public StockService() {}

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
			l.error("StockService.getHistoricalDaily: Exception", e);
			return null;
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
			l.error("StockService.getAllDividends: Exception", e);
			return null;
		} finally {
			session.close();
		}
	}
}
