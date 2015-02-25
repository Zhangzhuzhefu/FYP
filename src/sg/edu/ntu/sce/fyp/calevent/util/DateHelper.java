package sg.edu.ntu.sce.fyp.calevent.util;

import java.util.Calendar;
import java.util.Date;

import sg.edu.ntu.sce.fyp.calevent.model.Today;

public class DateHelper {
	public static final long WEEKINMILLI = 1000*60*60*24*7;
	public static final long DAYINMILLI = 1000*60*60*24;
	public static final long HOURINMILLI = 1000*60*60;
	public static String convertDayOfWeek(int d) {
		String s;
		switch (d%7){
		case Calendar.SUNDAY:
			s = "Sun";
			break;
		case Calendar.MONDAY:
			s = "Mon";
			break;
		case Calendar.TUESDAY:
			s = "Tue";
			break;
		case Calendar.WEDNESDAY:
			s = "Wed";
			break;
		case Calendar.THURSDAY:
			s = "Thu";
			break;
		case Calendar.FRIDAY:
			s = "Fri";
			break;
		default:
			s = "Sat";
		}
			
		return s;
	}
	
	public static String convertMonth(int m) {
		String s;
		switch (m%12){
		case Calendar.JANUARY:
			s = "Jan";
			break;
		case Calendar.FEBRUARY:
			s = "Feb";
			break;
		case Calendar.MARCH:
			s = "Mar";
			break;
		case Calendar.APRIL:
			s = "Apr";
			break;
		case Calendar.MAY:
			s = "May";
			break;
		case Calendar.JUNE:
			s = "Jun";
			break;
		case Calendar.JULY:
			s = "Jul";
			break;
		case Calendar.AUGUST:
			s = "Aug";
			break;
		case Calendar.SEPTEMBER:
			s = "Sep";
			break;
		case Calendar.OCTOBER:
			s = "Oct";
			break;
		case Calendar.NOVEMBER:
			s = "Nov";
			break;
		case Calendar.DECEMBER:
			s = "Dec";
			break;
		default:
			s = "";
		}
		
		return s;
	}
	
	@SuppressWarnings("deprecation")
	public static String dateIncr(Today today, int i){
		Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DAY_OF_YEAR, i);
	    Date newDate = calendar.getTime();
		return String.valueOf(newDate.getDate());
		
	}
	
	public static long getCurrentTimeFromMidnightInMilli(){
		long nowInMilli = Calendar.getInstance().getTimeInMillis();
		return getTimeFromMidnightInMilli(nowInMilli);
	}
	
	public static long getTimeFromMidnightInMilli(long nowInMilli){
		Calendar calendar_now= Calendar.getInstance();
		long offset = calendar_now.get(Calendar.ZONE_OFFSET) + calendar_now.get(Calendar.DST_OFFSET);
		long sinceMidnight = (nowInMilli + offset) % (24 * 60 * 60 * 1000);
		return sinceMidnight;
	}
	
	

}
