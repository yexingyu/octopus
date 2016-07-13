/**
 *
 */
package org.nxstudio.octopus.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.nxstudio.octopus.mybatis.model.RawDataYahooDividend;

/**
 * @author TimoYe
 */
public interface RawDataYahooDividendMapper {

	@Insert("REPLACE INTO `raw_data_yahoo`.`dividend` (`symbol`,`timestamp`,`dividend`) VALUES (#{symbol},#{timestamp},#{dividend})")
	int replace(RawDataYahooDividend object);
}
