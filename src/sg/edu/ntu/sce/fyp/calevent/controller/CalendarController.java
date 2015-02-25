package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.Calendar;

import android.content.Context;
import android.net.Uri;

public class CalendarController {

	private Context context;
	private CalendarReader calReader;
	private CalendarWriter calWriter;
	private EventReader eventReader;
	
	public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");
	public static final Uri EVENT_URI = Uri.parse("content://com.android.calendar/events");
	
	public CalendarController (Context context){
		this.context = context;
		calReader = new CalendarReader(this.context);
		calWriter = new CalendarWriter(this.context);
		eventReader = new EventReader(this.context);
		
		//functionality test 
		calReader.getAllCalendars();
		calReader.getSelectedCalendars(new String[] {"1","3"});
		
		Calendar c_start= Calendar.getInstance();
		c_start.set(2015,0,25,0,0); //Note that months start from 0 (January)   
		Calendar c_end= Calendar.getInstance();
		c_end.set(2015,5,7,0,0); //Note that months start from 0 (January)
		eventReader.readEventsFromCalendar(new String[] {"1","2","3","4"},c_start.getTimeInMillis(),c_end.getTimeInMillis());
	}
	
	
}
