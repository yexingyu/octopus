/**
 *
 */
package org.nxstudio.octopus.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.nxstudio.octopus.mybatis.model.RawDataNasdaqCompany;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author TimoYe
 */
public interface RawDataNasdaqCompanyMapper {

	/**
	 * insert
	 *
	 * @param company
	 * @return
	 */
	@Insert("REPLACE INTO `raw_data_nasdaq`.`company` "
	                + "(`symbol`,`name`,`last_sale`,`market_cap`,`adr_tso`,`ipo_year`,`sector`,`industry`,`summary_quote`) "
	                + "VALUES(#{symbol},#{name},#{lastSale},#{marketCap},#{ADR_TSO},#{IPOYear},#{sector},#{industry},#{summaryQuote})")
	public int insert(RawDataNasdaqCompany company);

	/**
	 * getAllSymbols
	 *
	 * @return
	 */
	@Select("SELECT distinct symbol FROM `raw_data_nasdaq`.`company`")
	public List<String> getAllSymbols();

	/**
	 * getIpoYear
	 * 
	 * @param symbol
	 * @return
	 */
	@Select("SELECT ipo_year FROM `raw_data_nasdaq`.`company` WHERE symbol=#{symbol}")
	public int getIpoYear(@Value("symbol") String symbol);
}
