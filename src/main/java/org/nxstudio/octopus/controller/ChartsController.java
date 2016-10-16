package org.nxstudio.octopus.controller;

import java.util.LinkedList;
import java.util.List;

import org.nxstudio.octopus.mybatis.model.Prediction;
import org.nxstudio.octopus.mybatis.model.RawDataYahooDividend;
import org.nxstudio.octopus.mybatis.model.RawDataYahooHistorical;
import org.nxstudio.octopus.service.CompanyService;
import org.nxstudio.octopus.service.PredictionService;
import org.nxstudio.octopus.service.PredictionService.PREDICTION_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/charts")
public class ChartsController {
	@Autowired
	private CompanyService		companyService;
	@Autowired
	private PredictionService	predictionService;

	/**
	 * getStockData
	 *
	 * @param symbol
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("data/v0.1/{symbol}")
	public List<Object[]> getStockData(@PathVariable("symbol") String symbol) {
		final List<Object[]> result = new LinkedList<>();
		for (RawDataYahooHistorical record : companyService.getHistoricalDaily(symbol)) {
			result.add(new Object[] { record.getTimestamp().getTime(), record.getOpen(), record.getHigh(), record.getLow(),
			                record.getClose(), record.getVolume() });
		}
		return result;
	}

	/**
	 * getStockDividend
	 *
	 * @param symbol
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("data/v0.1/{symbol}/dividend")
	public List<Object[]> getStockDividend(@PathVariable("symbol") String symbol) {
		final List<Object[]> result = new LinkedList<>();
		for (RawDataYahooDividend record : companyService.getAllDividends(symbol.toUpperCase())) {
			result.add(new Object[] { record.getTimestamp().getTime(), record.getDividend() });
		}
		return result;
	}

	/**
	 * getPrediction
	 *
	 * @param symbol
	 * @param type
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("prediction/v0.1/{symbol}/{type}")
	public List<Object[]> getPrediction(@PathVariable("symbol") String symbol, @PathVariable("type") String type) {
		final PREDICTION_TYPE predictionType = PREDICTION_TYPE.valueOf(type.toUpperCase());
		final List<Object[]> result = new LinkedList<>();
		for (Prediction record : predictionService.getAllOpen(symbol.toUpperCase(), predictionType)) {
			result.add(new Object[] { record.getTimestamp().getTime(), record.getD1(), record.getD2(), record.getD3(), record.getD4(),
			                record.getD5() });
		}
		return result;
	}

}
