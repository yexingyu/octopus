package org.nxstudio.octopus.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.nxstudio.octopus.mybatis.mapper.PredictionMapper;
import org.nxstudio.octopus.mybatis.model.Prediction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {
	private final static Logger	l	= LoggerFactory.getLogger(PredictionService.class);
	@Autowired
	private SqlSessionFactory	factory;

	public PredictionService() {}

	/**
	 * getAllOpen
	 *
	 * @param symbol
	 * @return
	 */
	public List<Prediction> getAllOpen(String symbol, PREDICTION_TYPE type) {
		final SqlSession session = factory.openSession();
		final PredictionMapper mapper = session.getMapper(PredictionMapper.class);

		try {
			if (type == PREDICTION_TYPE.OPEN) {
				return mapper.getAllOpen(symbol);
			} else if (type == PREDICTION_TYPE.HIGH) {
				return mapper.getAllHigh(symbol);
			} else if (type == PREDICTION_TYPE.LOW) {
				return mapper.getAllLow(symbol);
			} else {
				return mapper.getAllClose(symbol);
			}
		} catch (Exception e) {
			l.error("PredictionService.getAllOpen: Exception", e);
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * @author TimoYe
	 */
	public enum PREDICTION_TYPE {
		OPEN, HIGH, LOW, CLOSE;
	}
}
