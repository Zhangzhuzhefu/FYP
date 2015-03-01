package sg.edu.ntu.sce.fyp.calevent.global;

import java.util.Calendar;

import android.util.Log;

public class Today {

	private static final String DEBUG_TAG = Today.class.getSimpleName();
	
	public String year;
	public String month; 
	public String day;
	public String hour;
	public String minute;
	public String second;
	public String dayOfWeek;
	
	public int yearInt;
	public int monthInt; //starting from 0 to 11
	public int dayInt;
	public int hourInt;
	public int minuteInt;
	public int secondInt;
	public int dayOfWeekInt; //starting from 1(Sun) to 7(Sat)
	
	public Today(){
		updateInfo();
		
	}
	
	public void updateInfo(){
		
		Calendar c = Calendar.getInstance(); 
		
		yearInt = c.get(Calendar.YEAR);
		monthInt = c.get(Calendar.MONTH);
		dayInt = c.get(Calendar.DATE);
		dayOfWeekInt = c.get(Calendar.DAY_OF_WEEK);
		hourInt = c.get(Calendar.HOUR_OF_DAY);
		minuteInt = c.get(Calendar.MINUTE);
		secondInt = c.get(Calendar.SECOND);
		
		year = String.valueOf(yearInt);
		month = String.valueOf(monthInt+1);
		day = String.valueOf(dayInt);
		dayOfWeek = DateHelper.convertDayOfWeek(dayOfWeekInt);
		hour = String.valueOf(hourInt);
		minute = String.valueOf(minuteInt);
		second = String.valueOf(secondInt);
		
		Log.d(DEBUG_TAG,"year: "+String.valueOf(year));
		Log.d(DEBUG_TAG,"month: "+String.valueOf(month));
		Log.d(DEBUG_TAG,"date: "+String.valueOf(day));
		Log.d(DEBUG_TAG,"dayofweek: "+String.valueOf(dayOfWeek));
		Log.d(DEBUG_TAG, "hour: "+String.valueOf(hour));
		Log.d(DEBUG_TAG, "minute: "+String.valueOf(minute));
		Log.d(DEBUG_TAG, "second: "+String.valueOf(second));
	}
}
