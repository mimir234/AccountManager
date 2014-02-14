package org.jco.accountManager.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public class DateTools {
	
	private static Random random = new Random(999);

	/**
	 * Gets the month number between two dates
	 * @param start
	 * @param end
	 * @return -1 if start > end
	 */
	public static int getMonthNumberBetween(final Date start, final Date end) {
		if( start.after(end) ){
			return -1;
		}
		int monthNumber = 0;
		final GregorianCalendar initialDate = new GregorianCalendar();
		final GregorianCalendar endDate = new GregorianCalendar();
		initialDate.setTime(start);
		endDate.setTime(end);
		final int initialMonth = initialDate.get(Calendar.MONTH)+1;
		final int initialYear = initialDate.get(Calendar.YEAR);
		
		final int nowMonth = endDate.get(Calendar.MONTH)+1;
		final int nowYear = endDate.get(Calendar.YEAR);
		
		if( nowYear-initialYear > 0 ){
			monthNumber+=12-initialMonth;
			monthNumber+=nowMonth;
		} else {
			monthNumber+=nowMonth-initialMonth;
		}
		
		if( nowYear-initialYear > 1 ){
			monthNumber+=(nowYear-initialYear-1)*12;
		}
		return monthNumber;
	}
	
	public static long generateEventUID(final Date date) {
		long netxLong = random.nextLong();
		long uid = date.getTime()*(netxLong);
		return uid;
	}

}
