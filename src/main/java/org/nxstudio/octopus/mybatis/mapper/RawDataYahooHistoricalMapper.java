/**
 *
 */
package org.nxstudio.octopus.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.nxstudio.octopus.mybatis.model.RawDataYahooHistorical;

/**
 * @author TimoYe
 */
public interface RawDataYahooHistoricalMapper {

	/**
	 * insert
	 *
	 * @param object
	 * @return
	 */
	@Insert("REPLACE INTO `raw_data_yahoo`.`historical` (`symbol`,`timestamp`,`close`,`high`,`low`,`open`,`volume`,`adj_close`) "
	                + "VALUES (#{symbol},#{timestamp},#{close},#{high},#{low},#{open},#{volume},#{adjClose})")
	public int insert(RawDataYahooHistorical object);

	/**
	 * getHistoricalYears
	 *
	 * @param symbol
	 * @return
	 */
	@Select("SELECT DISTINCT YEAR(timestamp) FROM `raw_data_yahoo`.`historical` WHERE symbol=#{symbol}")
	public List<Integer> getHistoricalYears(@Param("symbol") String symbol);

	/**
	 * getAll
	 *
	 * @param symbol
	 * @return
	 */
	@Select("SELECT symbol, timestamp, open, high, low, close, volume, adj_close as adjClose, modified FROM `raw_data_yahoo`.`historical` "
	                + "WHERE symbol=#{symbol} order by timestamp")
	public List<RawDataYahooHistorical> getAll(@Param("symbol") String symbol);
}
