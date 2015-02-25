package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.ArrayList;
import java.util.Calendar;

import sg.edu.ntu.sce.fyp.calevent.model.Event;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class CalendarController {

	private Context context;
	private CalendarReader calReader;
	private CalendarWriter calWriter;
	private EventReader eventReader;
	
	private String[] selectedCalendarIDs;
	
	public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");
	public static final Uri EVENT_URI = Uri.parse("content://com.android.calendar/events");
	
	public CalendarController (Context context){
		this.context = context;
		calReader = new CalendarReader(this.context);
		calWriter = new CalendarWriter(this.context);
		eventReader = new EventReader(this.context);
		selectedCalendarIDs = new String[] {"1","2","3","4"};
		
		//functionality test 
		calReader.getAllCalendars();
		calReader.getSelectedCalendars(selectedCalendarIDs);
		
		/*
		Calendar c_start= Calendar.getInstance();
		c_start.set(2015,0,25,0,0); //Note that months start from 0 (January)   
		Calendar c_end= Calendar.getInstance();
		c_end.set(2015,5,7,0,0); //Note that months start from 0 (January)
		eventReader.readEventsFromCalendar(selectedCalendarIDs, c_start.getTimeInMillis(), c_end.getTimeInMillis());*/
		ArrayList<Event> eventList = readEventsOneWeekFromToday(selectedCalendarIDs);
	}
	
	public ArrayList<Event> readEventsOneWeekFromToday(String[] calIDs){
		long weekInMilli = 1000*60*60*24*7;
		return readEventsFromToday(calIDs, weekInMilli);
	}
	
	public ArrayList<Event> readEventsFromToday(String[] calIDs, long durationInMilli){
		Calendar calendar_now= Calendar.getInstance();
		long nowInMilli = calendar_now.getTimeInMillis();
		long offset = calendar_now.get(Calendar.ZONE_OFFSET) + calendar_now.get(Calendar.DST_OFFSET);
		long sinceMidnight = (nowInMilli + offset) % (24 * 60 * 60 * 1000);
		nowInMilli -= sinceMidnight;
		return eventReader.readEventsFromCalendar(selectedCalendarIDs, nowInMilli, nowInMilli + durationInMilli);
	}
}
