package org.nxstudio.octopus.mybatis.model;

import java.util.Date;

public class Prediction {
	private String	symbol;
	private Date	timestamp;
	private double	d1;
	private double	d2;
	private double	d3;
	private double	d4;
	private double	d5;
	private Date	modified;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public double getD1() {
		return d1;
	}

	public void setD1(double d1) {
		this.d1 = d1;
	}

	public double getD2() {
		return d2;
	}

	public void setD2(double d2) {
		this.d2 = d2;
	}

	public double getD3() {
		return d3;
	}

	public void setD3(double d3) {
		this.d3 = d3;
	}

	public double getD4() {
		return d4;
	}

	public void setD4(double d4) {
		this.d4 = d4;
	}

	public double getD5() {
		return d5;
	}

	public void setD5(double d5) {
		this.d5 = d5;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}
