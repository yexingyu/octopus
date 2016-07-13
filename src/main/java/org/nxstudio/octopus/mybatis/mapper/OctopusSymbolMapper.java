/**
 *
 */
package org.nxstudio.octopus.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

/**
 * @author TimoYe
 */
public interface OctopusSymbolMapper {

	@Select("SELECT symbol FROM `octopus`.`symbol`")
	public List<String> getAllSymbols();
}
