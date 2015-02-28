package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.Event;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import sg.edu.ntu.sce.fyp.calevent.util.DateHelper;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarViewManager;
import android.content.Context;
import android.net.Uri;

public class CalendarHelper {
	private MainActivity activity;
	private ModelManager modelMgr;
	private CalendarViewManager viewlMgr;
	
	private CalendarReader calReader;
	private CalendarWriter calWriter;
	private EventReader eventReader;
	private EventWriter eventWriter;
	
	public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");
	public static final Uri EVENT_URI = Uri.parse("content://com.android.calendar/events");
	
	public CalendarHelper (MainActivity act){
		this.activity = act;
		Context context = act.getApplicationContext();
		calReader = new CalendarReader(context);
		calWriter = new CalendarWriter(context);
		eventReader = new EventReader(context);
		eventWriter = new EventWriter(context);
		//TODO eventWriter
		modelMgr = ModelManager.getInstance();
		viewlMgr = this.activity.calendarViewMgr;
		
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

	public void addNewEventsToCalendar(ArrayList<Event> events, long cal_id){
		for (Event ev : events) {
			addNewEventToCalendar(ev, cal_id);
		}
	}

	public void addNewEventToCalendar(Event ev, long cal_id){
		if (ev != null) {
			eventWriter.addNewEvent(ev, cal_id);
			modelMgr.addNewEvent(ev);
			viewlMgr.updateWeekView();
		}
	}
	
	public void updateNewEvents(ArrayList<Event> events){
		for (Event ev : events) {
			updateNewEvent(ev);
		}
	}

	public void updateNewEvent(Event ev) {
		if (ev != null) {
			eventWriter.updateEvent(ev);
			viewlMgr.updateWeekView();
		}
	}
	public void deletEvents(ArrayList<Event> events){
		for (Event ev : events) {
			deletEvent(ev);
		}
	}
	public void deletEvent(Event ev) {
		if (ev != null) {
			eventWriter.deletEvent(ev);
			modelMgr.deleteEvent(ev);
			viewlMgr.updateWeekView();
		}
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
