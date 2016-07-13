/**
 *
 */
package org.nxstudio.octopus.mybatis.model;

import java.util.Date;

import com.google.common.base.MoreObjects;

/**
 * @author TimoYe
 */
public class RawDataYahooQuote {
	private String	symbol;
	private Date	timestamp;
	private double	avgDailyVolume;
	private double	change;
	private double	days_low;
	private double	days_high;
	private double	year_low;
	private double	year_high;
	private String	marketCapitalization;
	private double	lastTradePriceOnly;
	private String	days_range;
	private String	name;
	private double	volume;
	private String	stockExchange;
	private Date	modified;

	public RawDataYahooQuote() {}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass()).add("symbol", symbol).add("timestamp", timestamp)
		                .add("avgDailyVolume", avgDailyVolume).add("change", change).add("days_low", days_low).add("days_high", days_high)
		                .add("year_low", year_low).add("year_high", year_high).add("marketCapitalization", marketCapitalization)
		                .add("lastTradePriceOnly", lastTradePriceOnly).add("days_range", days_range).add("name", name).add("volume", volume)
		                .add("stockExchange", stockExchange).add("modified", modified).toString();
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the avgDailyVolume
	 */
	public double getAvgDailyVolume() {
		return avgDailyVolume;
	}

	/**
	 * @param avgDailyVolume the avgDailyVolume to set
	 */
	public void setAvgDailyVolume(double avgDailyVolume) {
		this.avgDailyVolume = avgDailyVolume;
	}

	/**
	 * @return the change
	 */
	public double getChange() {
		return change;
	}

	/**
	 * @param change the change to set
	 */
	public void setChange(double change) {
		this.change = change;
	}

	/**
	 * @return the days_low
	 */
	public double getDays_low() {
		return days_low;
	}

	/**
	 * @param days_low the days_low to set
	 */
	public void setDays_low(double days_low) {
		this.days_low = days_low;
	}

	/**
	 * @return the days_high
	 */
	public double getDays_high() {
		return days_high;
	}

	/**
	 * @param days_high the days_high to set
	 */
	public void setDays_high(double days_high) {
		this.days_high = days_high;
	}

	/**
	 * @return the year_low
	 */
	public double getYear_low() {
		return year_low;
	}

	/**
	 * @param year_low the year_low to set
	 */
	public void setYear_low(double year_low) {
		this.year_low = year_low;
	}

	/**
	 * @return the year_high
	 */
	public double getYear_high() {
		return year_high;
	}

	/**
	 * @param year_high the year_high to set
	 */
	public void setYear_high(double year_high) {
		this.year_high = year_high;
	}

	/**
	 * @return the marketCapitalization
	 */
	public String getMarketCapitalization() {
		return marketCapitalization;
	}

	/**
	 * @param marketCapitalization the marketCapitalization to set
	 */
	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}

	/**
	 * @return the lastTradePriceOnly
	 */
	public double getLastTradePriceOnly() {
		return lastTradePriceOnly;
	}

	/**
	 * @param lastTradePriceOnly the lastTradePriceOnly to set
	 */
	public void setLastTradePriceOnly(double lastTradePriceOnly) {
		this.lastTradePriceOnly = lastTradePriceOnly;
	}

	/**
	 * @return the days_range
	 */
	public String getDays_range() {
		return days_range;
	}

	/**
	 * @param days_range the days_range to set
	 */
	public void setDays_range(String days_range) {
		this.days_range = days_range;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * @return the stockExchange
	 */
	public String getStockExchange() {
		return stockExchange;
	}

	/**
	 * @param stockExchange the stockExchange to set
	 */
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

}
