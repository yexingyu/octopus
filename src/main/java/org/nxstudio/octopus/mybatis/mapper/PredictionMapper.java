/**
 *
 */
package org.nxstudio.octopus.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.nxstudio.octopus.mybatis.model.Prediction;

/**
 * @author TimoYe
 */
public interface PredictionMapper {

	/**
	 * insertOpen
	 *
	 * @param open
	 * @return
	 */
	@Insert("REPLACE INTO `prediction`.`open` " + "(`symbol`,`timestamp`,`d1`,`d2`,`d3`,`d4`,`d5`) "
	                + "VALUES(#{symbol},#{timestamp},#{d1},#{d2},#{d3},#{d4},#{d5})")
	public int insertOpen(Prediction open);

	/**
	 * getAllOpen
	 *
	 * @param symbol
	 * @return
	 */
	@Select("SELECT symbol, timestamp, d1, d2, d3, d4, d5, modified FROM `prediction`.`open` WHERE symbol=#{symbol} order by timestamp")
	public List<Prediction> getAllOpen(String symbol);

	/**
	 * getAllClose
	 * 
	 * @param symbol
	 * @return
	 */
	@Select("SELECT symbol, timestamp, d1, d2, d3, d4, d5, modified FROM `prediction`.`close` WHERE symbol=#{symbol} order by timestamp")
	public List<Prediction> getAllClose(String symbol);

	/**
	 * getAllHigh
	 * 
	 * @param symbol
	 * @return
	 */
	@Select("SELECT symbol, timestamp, d1, d2, d3, d4, d5, modified FROM `prediction`.`high` WHERE symbol=#{symbol} order by timestamp")
	public List<Prediction> getAllHigh(String symbol);

	/**
	 * getAllLow
	 * 
	 * @param symbol
	 * @return
	 */
	@Select("SELECT symbol, timestamp, d1, d2, d3, d4, d5, modified FROM `prediction`.`low` WHERE symbol=#{symbol} order by timestamp")
	public List<Prediction> getAllLow(String symbol);
}
