package org.nxstudio.octopus.mybatis.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;

public class RawDataNasdaqCompany {
	private String	symbol;
	private String	name;
	private double	lastSale;
	private double	marketCap;
	private int		ADR_TSO;
	private int		IPOYear;
	private String	sector;
	private String	industry;
	private String	summaryQuote;
	private Date	modified;

	public RawDataNasdaqCompany() {}

	public RawDataNasdaqCompany(String csv) {
		load(csv);
	}

	private void load(String csv) {
		if (Strings.isNullOrEmpty(csv)) { return; }
		String[] fs = StringUtils.splitByWholeSeparator(csv, "\",\"");
		fs[0] = StringUtils.removeStart(fs[0], "\"");
		fs[fs.length - 1] = StringUtils.removeEnd(fs[fs.length - 1], "\",");

		setSymbol(fs[0]);
		setName(fs[1]);
		setLastSale(fixDouble(Doubles.tryParse(fs[2])));
		setMarketCap(fixDouble(Doubles.tryParse(fs[3])));
		setADR_TSO(fixInt(Ints.tryParse(fs[4])));
		setIPOYear(fixInt(Ints.tryParse(fs[5])));
		setSector(fs[6]);
		setIndustry(fs[7]);
		setSummaryQuote(fs[8]);
	}

	private int fixInt(Integer intValue) {
		return intValue == null ? 0 : intValue;
	}

	private double fixDouble(Double doubleValue) {
		return doubleValue == null ? 0 : doubleValue;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(RawDataNasdaqCompany.class).add("symbol", symbol).add("name", name).add("lastSale", lastSale)
		                .add("marketCap", marketCap).add("ADR_TSO", ADR_TSO).add("IPOYear", IPOYear).add("sector", sector)
		                .add("industry", industry).add("summaryQuote", summaryQuote).toString();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLastSale() {
		return lastSale;
	}

	public void setLastSale(double lastSale) {
		this.lastSale = lastSale;
	}

	public double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(double marketCap) {
		this.marketCap = marketCap;
	}

	public int getADR_TSO() {
		return ADR_TSO;
	}

	public void setADR_TSO(int aDR_TSO) {
		ADR_TSO = aDR_TSO;
	}

	public int getIPOYear() {
		return IPOYear;
	}

	public void setIPOYear(int iPOYear) {
		IPOYear = iPOYear;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSummaryQuote() {
		return summaryQuote;
	}

	public void setSummaryQuote(String summaryQuote) {
		this.summaryQuote = summaryQuote;
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

}
