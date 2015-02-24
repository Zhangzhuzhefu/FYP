package sg.edu.ntu.sce.fyp.calevent.controller;

import android.app.Activity;
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
		
		eventReader.readEventsFromCalendar(new String[] {"1","3"});
	}
	
	
}
