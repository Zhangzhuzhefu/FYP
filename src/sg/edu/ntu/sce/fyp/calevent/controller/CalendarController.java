package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.global.DateHelper;
import sg.edu.ntu.sce.fyp.calevent.model.CalendarReader;
import sg.edu.ntu.sce.fyp.calevent.model.CalendarWriter;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import sg.edu.ntu.sce.fyp.calevent.model.EventReader;
import sg.edu.ntu.sce.fyp.calevent.model.EventWriter;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import android.net.Uri;

public class CalendarController {
	private MainActivity activity;
	
	private ViewManager viewHelper;
	
	private DataManager dataMgr;
	private CalendarReader calReader;
	private CalendarWriter calWriter;
	private EventReader eventReader;
	private EventWriter eventWriter;
	
	public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");
	public static final Uri EVENT_URI = Uri.parse("content://com.android.calendar/events");
	
	public CalendarController (MainActivity act){
		this.activity = act;
		calReader = new CalendarReader(activity.getApplicationContext());
		calWriter = new CalendarWriter(activity.getApplicationContext());
		eventReader = new EventReader(activity.getApplicationContext());
		eventWriter = new EventWriter(activity.getApplicationContext());
		dataMgr = DataManager.getInstance();
		viewHelper = this.activity.calendarViewManager;
		
		/*set allCalendars, selecedCalendarsIDs and selectedCalendars*/
		dataMgr.setAllCalendarsAndUpdate(calReader.getAllCalendars());
		
		/*set myEvents and update view*/
		getMyEventsAndUpdateView();
	}
	
	public void getMyEventsAndUpdateView(){
		
		ArrayList<MyEvent> eventList = readEventsOneWeekFromToday(dataMgr.getSelectedCalendarIDs());
		dataMgr.setMyEventList(eventList);
		viewHelper.updateWeekView();
	}
	
	public ArrayList<MyEvent> readEventsOneWeekFromToday(String[] calIDs){
		return readEventsFromToday(calIDs, DateHelper.WEEKINMILLI);
	}
	
	public ArrayList<MyEvent> readEventsFromToday(String[] calIDs, long durationInMilli){
		//c_start.set(2015,0,25,0,0); //Note that months start from 0 (January)   
		long nowInMilli = DateHelper.getTodayMidnightInMilli();
		
		return eventReader.readEventsFromCalendar(dataMgr.getSelectedCalendarIDs(), nowInMilli, nowInMilli + durationInMilli);
	}

	public void addNewEventsToCalendar(ArrayList<MyEvent> events, long cal_id){
		for (MyEvent ev : events) {
			addNewEventToCalendar(ev, cal_id);
		}
		viewHelper.updateWeekView();
	}
	public void addNewEventToCalendar(MyEvent ev, long cal_id){
		if (ev != null) {
			eventWriter.addNewEvent(ev, cal_id);
			dataMgr.addNewEvent(ev);
		}
	}
	public void addNewEventToCalendarAndUpdateView(MyEvent ev, long cal_id){
		if (ev != null) {
			eventWriter.addNewEvent(ev, cal_id);
			dataMgr.addNewEvent(ev);
			viewHelper.updateWeekView();
		}
	}
	
	public void updateNewEvents(ArrayList<MyEvent> events){
		for (MyEvent ev : events) {
			updateNewEvent(ev);
		}
		viewHelper.updateWeekView();
	}

	public void updateNewEvent(MyEvent ev) {
		if (ev != null) {
			eventWriter.updateEvent(ev);
		}
	}
	
	public void updateNewEventAndUpdateView(MyEvent ev) {
		if (ev != null) {
			eventWriter.updateEvent(ev);
			viewHelper.updateWeekView();
		}
	}
	public void deletEvents(ArrayList<MyEvent> events){
		for (MyEvent ev : events) {
			deletEvent(ev);
		}
		viewHelper.updateWeekView();
	}
	public void deletEvent(MyEvent ev) {
		if (ev != null) {
			eventWriter.deletEvent(ev);
			dataMgr.deleteEvent(ev);
		}
	}
	public void deletEventAndUpdateView(MyEvent ev) {
		if (ev != null) {
			eventWriter.deletEvent(ev);
			dataMgr.deleteEvent(ev);
			viewHelper.updateWeekView();
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
