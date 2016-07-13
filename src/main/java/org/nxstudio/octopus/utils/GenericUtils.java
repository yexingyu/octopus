package org.nxstudio.octopus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericUtils {
	private final static Logger l = LoggerFactory.getLogger(GenericUtils.class);

	/**
	 * sleep
	 *
	 * @param millis
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			l.error("GenericUtils.sleep: InterruptedException", e);
		}
	}

	/**
	 * isSpecificHour
	 *
	 * @param specificHour
	 * @return
	 */
	//	public static boolean isSpecificHour(int specificHour) {
	//		Calendar c = Calendar.getInstance();
	//		final int hour = c.get(Calendar.HOUR_OF_DAY);
	//		if (specificHour == hour) {
	//			return true;
	//		} else {
	//			return false;
	//		}
	//	}

	/**
	 * isNotTradingTimeUS
	 *
	 * @return
	 */
	//	public static boolean isTradingTimeUS() {
	//		Calendar c = Calendar.getInstance();
	//		final int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
	//		final int hour = c.get(Calendar.HOUR_OF_DAY);
	//		final int minute = c.get(Calendar.MINUTE);
	//
	//		//weekend, return false
	//		if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) { return false; }
	//
	//		//<9:30 or >16:30, return false
	//		final int t = hour * 100 + minute;
	//		if (t < 930 || t > 1630) { return false; }
	//
	//		//otherwise, return true
	//		return true;
	//	}

	/**
	 * isNotTradingTimeUS
	 *
	 * @return
	 */
	//	public static boolean isNotTradingTimeUS() {
	//		return !isTradingTimeUS();
	//	}
	//
	//	public static void main(String[] args) {
	//		System.out.println(isTradingTimeUS());
	//		System.out.println(isNotTradingTimeUS());
	//	}
}
