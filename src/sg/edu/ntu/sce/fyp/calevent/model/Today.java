package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.Calendar;

import sg.edu.ntu.sce.fyp.calevent.util.DateHelper;
import android.util.Log;

public class Today {

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
		
		Log.d("Today","year: "+String.valueOf(year));
		Log.d("Today","month: "+String.valueOf(month));
		Log.d("Today","date: "+String.valueOf(day));
		Log.d("Today","dayofweek: "+String.valueOf(dayOfWeek));
		Log.d("Today", "hour: "+String.valueOf(hour));
		Log.d("Today", "minute: "+String.valueOf(minute));
		Log.d("Today", "second: "+String.valueOf(second));
	}
}
