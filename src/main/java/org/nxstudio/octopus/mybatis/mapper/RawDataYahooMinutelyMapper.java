/**
 *
 */
package org.nxstudio.octopus.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.nxstudio.octopus.mybatis.model.RawDataYahooMinutely;

/**
 * @author TimoYe
 */
public interface RawDataYahooMinutelyMapper {

	@Select("SELECT symbol, timestamp, close, high, low, open, volume FROM raw_data_yahoo.minutely")
	List<RawDataYahooMinutely> list();

	@Insert("INSERT INTO `raw_data_yahoo`.`minutely` (`symbol`,`timestamp`,`close`,`high`,`low`,`open`,`volume`) "
	                + "VALUES (#{symbol},#{timestamp},#{close},#{high},#{low},#{open},#{volume})")
	int insert(RawDataYahooMinutely object);

	@Delete("DELETE FROM `raw_data_yahoo`.`minutely` WHERE symbol=#{symbol} AND timestamp BETWEEN #{timestamp1} AND #{timestamp2}")
	int delete(@Param("symbol") String symbol, @Param("timestamp1") Date timestamp1,
	                @Param("timestamp2") Date timestamp2);
}
