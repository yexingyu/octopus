/**
 *
 */
package org.nxstudio.octopus.mybatis.model;

import java.util.Date;

import com.google.common.base.MoreObjects;

/**
 * @author TimoYe
 */
public class RawDataYahooHistorical {
	private String	symbol;
	private Date	timestamp;
	private double	open;
	private double	high;
	private double	low;
	private double	close;
	private long	volume;
	private double	adjClose;
	private Date	modified;

	public RawDataYahooHistorical() {}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass()).add("symbol", symbol).add("timestamp", timestamp).add("open", open).add("high", high)
		                .add("low", low).add("close", close).add("volume", volume).add("adjClose", adjClose).add("modified", modified)
		                .toString();
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
	 * @return the open
	 */
	public double getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(double open) {
		this.open = open;
	}

	/**
	 * @return the high
	 */
	public double getHigh() {
		return high;
	}

	/**
	 * @param high the high to set
	 */
	public void setHigh(double high) {
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public double getLow() {
		return low;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(double low) {
		this.low = low;
	}

	/**
	 * @return the close
	 */
	public double getClose() {
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(double close) {
		this.close = close;
	}

	/**
	 * @return the volume
	 */
	public long getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(long volume) {
		this.volume = volume;
	}

	/**
	 * @return the adjClose
	 */
	public double getAdjClose() {
		return adjClose;
	}

	/**
	 * @param adjClose the adjClose to set
	 */
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}

}
