/**
 *
 */
package org.nxstudio.octopus.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.nxstudio.octopus.mybatis.model.RawDataYahooDividend;

/**
 * @author TimoYe
 */
public interface RawDataYahooDividendMapper {

	@Insert("REPLACE INTO `raw_data_yahoo`.`dividend` (`symbol`,`timestamp`,`dividend`) VALUES (#{symbol},#{timestamp},#{dividend})")
	public int replace(RawDataYahooDividend object);

	@Select("SELECT symbol, timestamp, dividend FROM `raw_data_yahoo`.`dividend` WHERE symbol=#{symbol} order by timestamp")
	public List<RawDataYahooDividend> getAll(String symbol);
}
