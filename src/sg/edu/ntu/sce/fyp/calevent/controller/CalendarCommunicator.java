package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.Event;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import sg.edu.ntu.sce.fyp.calevent.util.DateHelper;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarViewManager;
import android.content.Context;
import android.net.Uri;

public class CalendarCommunicator {
	private Context context;
	private ModelManager modelMgr;
	private CalendarViewManager viewlMgr;
	
	private CalendarReader calReader;
	private CalendarWriter calWriter;
	private EventReader eventReader;
	private EventWriter eventWriter;
	
	public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");
	public static final Uri EVENT_URI = Uri.parse("content://com.android.calendar/events");
	
	public CalendarCommunicator (Context context, MainActivity act){
		this.context = context;
		calReader = new CalendarReader(this.context);
		calWriter = new CalendarWriter(this.context);
		eventReader = new EventReader(this.context);
		modelMgr = ModelManager.getInstance();
		viewlMgr = act.calendarViewMgr;
		
		/*set allCalendars, selecedCalendarsIDs and selectedCalendars*/
		modelMgr.setAllCalendarsAndUpdate(calReader.getAllCalendars());
		
		/*set myEvents and update view*/
		getMyEventsAndUpdateView();
	}
	
	public void getMyEventsAndUpdateView(){
		ArrayList<Event> eventList = readEventsOneWeekFromToday(modelMgr.getSelectedCalendarIDs());
		modelMgr.setMyEventList(eventList);
		viewlMgr.updateWeekView();
	}
	
	public ArrayList<Event> readEventsOneWeekFromToday(String[] calIDs){
		return readEventsFromToday(calIDs, DateHelper.WEEKINMILLI);
	}
	
	public ArrayList<Event> readEventsFromToday(String[] calIDs, long durationInMilli){
		//c_start.set(2015,0,25,0,0); //Note that months start from 0 (January)   
		long nowInMilli = DateHelper.getTodayMidnightInMilli();
		
		return eventReader.readEventsFromCalendar(modelMgr.getSelectedCalendarIDs(), nowInMilli, nowInMilli + durationInMilli);
	}

	public CalendarReader getCalReader() {
		return calReader;
	}

	public void setCalReader(CalendarReader calReader) {
		this.calReader = calReader;
	}

	public CalendarWriter getCalWriter() {
		return calWriter;
	}

	public void setCalWriter(CalendarWriter calWriter) {
		this.calWriter = calWriter;
	}

	public EventReader getEventReader() {
		return eventReader;
	}

	public void setEventReader(EventReader eventReader) {
		this.eventReader = eventReader;
	}

	public EventWriter getEventWriter() {
		return eventWriter;
	}

	public void setEventWriter(EventWriter eventWriter) {
		this.eventWriter = eventWriter;
	}
}
