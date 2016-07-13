/**
 *
 */
package org.nxstudio.octopus.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.nxstudio.octopus.mybatis.model.RawDataYahooQuote;

/**
 * @author TimoYe
 */
public interface RawDataYahooQuoteMapper {

	@Insert("INSERT INTO `raw_data_yahoo`.`quote` (`symbol`,`timestamp`,`avg_daily_volume`,`change`,`days_low`,`days_high`,`year_low`,`year_high`,`market_capitalization`,`last_trade_price_only`,`days_range`,`name`,`volume`,`stock_exchange`) "
	                + "VALUES (#{symbol},#{timestamp},#{avgDailyVolume},#{change},#{days_low},#{days_high},#{year_low},#{year_high},#{marketCapitalization},#{lastTradePriceOnly},#{days_range},#{name},#{volume},#{stockExchange})")
	int insert(RawDataYahooQuote object);

}
