package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.ArrayList;
import java.util.Calendar;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.Event;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import sg.edu.ntu.sce.fyp.calevent.util.DateHelper;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarViewManager;
import android.content.Context;
import android.net.Uri;

public class CalendarController {
	private Context context;
	private ModelManager modelMgr;
	private CalendarViewManager viewlMgr;
	
	private CalendarReader calReader;
	private CalendarWriter calWriter;
	private EventReader eventReader;
	
	private String[] selectedCalendarIDs;
	
	public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");
	public static final Uri EVENT_URI = Uri.parse("content://com.android.calendar/events");
	
	public CalendarController (Context context, MainActivity act){
		this.context = context;
		calReader = new CalendarReader(this.context);
		calWriter = new CalendarWriter(this.context);
		eventReader = new EventReader(this.context);
		modelMgr = ModelManager.getInstance();
		viewlMgr = act.calendarViewMgr;
		//TODO
		selectedCalendarIDs = new String[] {"1","2","3","4"};
		
		//functionality test 
		calReader.getAllCalendars();
		calReader.getSelectedCalendars(selectedCalendarIDs);

		ArrayList<Event> eventList = readEventsOneWeekFromToday(selectedCalendarIDs);
		modelMgr.setMyEventList(eventList);
		viewlMgr.updateWeekView();
	}
	
	public ArrayList<Event> readEventsOneWeekFromToday(String[] calIDs){
		return readEventsFromToday(calIDs, DateHelper.WEEKINMILLI);
	}
	
	public ArrayList<Event> readEventsFromToday(String[] calIDs, long durationInMilli){
		//c_start.set(2015,0,25,0,0); //Note that months start from 0 (January)   
		long nowInMilli = DateHelper.getTodayMidnightInMilli();
		
		return eventReader.readEventsFromCalendar(selectedCalendarIDs, nowInMilli, nowInMilli + durationInMilli);
	}
}
