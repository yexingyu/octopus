/**
 *
 */
package org.nxstudio.octopus.mybatis.model;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

/**
 * @author TimoYe
 */
public class RawDataYahooMinutely {
	private String	symbol;
	private Date	timestamp;
	private double	close;
	private double	high;
	private double	low;
	private double	open;
	private int		volume;
	private Date	modified;

	public RawDataYahooMinutely() {}

	public RawDataYahooMinutely(String symbol, String csv) {
		setSymbol(symbol);
		load(csv);
	}

	private void load(String csv) {
		String[] fs = StringUtils.split(csv, ",");
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
		c.setTimeInMillis(Longs.tryParse(fs[0]) * 1000L);
		c.clear(Calendar.MILLISECOND);
		setTimestamp(c.getTime());
		setClose(Doubles.tryParse(fs[1]));
		setHigh(Doubles.tryParse(fs[2]));
		setLow(Doubles.tryParse(fs[3]));
		setOpen(Doubles.tryParse(fs[4]));
		setVolume(Ints.tryParse(fs[5]));
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass()).add("symbol", symbol).add("timestamp", timestamp).add("close", close)
		                .add("high", high).add("low", low).add("open", open).add("volume", volume).add("modified", modified).toString();
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
		return this.symbol;
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
		return this.timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the close
	 */
	public double getClose() {
		return this.close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(double close) {
		this.close = close;
	}

	/**
	 * @return the high
	 */
	public double getHigh() {
		return this.high;
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
		return this.low;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(double low) {
		this.low = low;
	}

	/**
	 * @return the open
	 */
	public double getOpen() {
		return this.open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(double open) {
		this.open = open;
	}

	/**
	 * @return the volume
	 */
	public int getVolume() {
		return this.volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

}
