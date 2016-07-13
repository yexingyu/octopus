/**
 *
 */
package org.nxstudio.octopus.mybatis.model;

import java.util.Date;

import com.google.common.base.MoreObjects;

/**
 * @author TimoYe
 */
public class RawDataYahooDividend {
	private String	symbol;
	private Date	timestamp;
	private double	dividend;
	private Date	modified;

	public RawDataYahooDividend() {}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass()).add("symbol", symbol).add("timestamp", timestamp).add("dividend", dividend)
		                .add("modified", modified).toString();
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
	 * @return the dividend
	 */
	public double getDividend() {
		return dividend;
	}

	/**
	 * @param dividend the dividend to set
	 */
	public void setDividend(double dividend) {
		this.dividend = dividend;
	}

}
