package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.global.Settings;
import sg.edu.ntu.sce.fyp.calevent.global.Today;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyCalendar;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyTimeSlot;

public class DataManager {

	private static DataManager dataMgr;
	private Today today; 
	private Settings settings;
	private ArrayList<MyCalendar> allCalendars;
	private ArrayList<MyCalendar> selectedCalendars;
	private String[] selectedCalendarIDs;
	
	private ArrayList<MyEvent> myEventList;
	private ArrayList<MyEvent> toBeSharedEventList;
	private ArrayList<MyEvent> receivedEventList;
	private ArrayList<MyEvent> acceptedEventList;
	
	private ArrayList<MyTimeSlot> timeSlotList;
	private ArrayList<MyTimeSlot> receivedtTimeSlotList;
	
	public static DataManager getInstance(){
		if (dataMgr == null) {
			dataMgr = new DataManager();
		}
		return dataMgr;
	}

	public DataManager(){
		settings = Settings.getInstance();
	}
	
	public Today getToday() {
		if (today == null) {
			today = new Today();
		}
		return today;
	}

	public ArrayList<MyEvent> getMyEventList() {
		return myEventList;
	}

	public void setMyEventList(ArrayList<MyEvent> myEventList) {
		this.myEventList = myEventList;
	}

	public ArrayList<MyEvent> getToBeSharedEventList() {
		if (toBeSharedEventList == null){
			toBeSharedEventList = new ArrayList<MyEvent>();
		}
		return toBeSharedEventList;
	}
	public void setToBeSharedEventList(ArrayList<MyEvent> toBeSharedEventList) {
		this.toBeSharedEventList = toBeSharedEventList;
	}

	public ArrayList<MyEvent> getReceivedEventList() {
		if (receivedEventList == null){
			receivedEventList = new ArrayList<MyEvent>();
		}
		return receivedEventList;
	}

	public void setReceivedEventList(ArrayList<MyEvent> receivedEventList) {
		this.receivedEventList = receivedEventList;
	}
	
	public ArrayList<MyEvent> getAcceptedEventList() {
		if (acceptedEventList == null){
			acceptedEventList = new ArrayList<MyEvent>();
		}
		return acceptedEventList;
	}
	
	public void setAcceptedEventList(ArrayList<MyEvent> receivedEventList) {
		this.acceptedEventList = receivedEventList;
	}

	public Settings getSettings() {
		return settings;
	}

	public ArrayList<MyCalendar> getAllCalendars() {
		if (allCalendars == null)
			allCalendars = new ArrayList<MyCalendar>();
		return allCalendars;
	}

	public void setAllCalendarsAndUpdate(ArrayList<MyCalendar> allCalendars) {
		this.allCalendars = allCalendars;
		firstTimeUpdateSelectedCalendarsAndIDs();
		settings.setWriteCalendarId(Long.valueOf(getSelectedCalendarIDs()[0]));
	}
	
	public void firstTimeUpdateSelectedCalendarsAndIDs(){
		selectedCalendars = new ArrayList<MyCalendar>();
		int numSelected = 0;
		for (MyCalendar cal : allCalendars) {
			if (cal.isVisible()){
				selectedCalendars.add(cal);
				numSelected ++;
				cal.setSelected(true);
			}
		}
		updateSelectedCalendarIDs(numSelected);
	}
	
	public void updateSelectedCalendarsAndIDs(){
		selectedCalendars = new ArrayList<MyCalendar>();
		int numSelected = 0;
		for (MyCalendar cal : allCalendars) {
			if (cal.isSelected()){
				selectedCalendars.add(cal);
				numSelected ++;
			}
		}
		updateSelectedCalendarIDs(numSelected);
	}
	
	private void updateSelectedCalendarIDs(int numSelected) {
		selectedCalendarIDs = new String [numSelected];
		int i = 0;
		for (MyCalendar cal : allCalendars) {
			if (cal.isSelected()){
				selectedCalendarIDs[i] = cal.get_id();
				i++;
			}
		}
	}

	public ArrayList<MyCalendar> getSelectedCalendars() {
		if (selectedCalendars == null)
			selectedCalendars = new ArrayList<MyCalendar>();
		return selectedCalendars;
	}

	public String[] getSelectedCalendarIDs() {
		return selectedCalendarIDs;
	}
	
	public MyEvent getEventByID(String event_id){
		for (MyEvent ev : myEventList) {
			if (ev.getEvent_id().equalsIgnoreCase(event_id)) {
				return ev;
			}
		}
		return null;
	}
	
	public void addNewEvent(MyEvent ev) {
		if (ev != null)
			myEventList.add(ev);
	}

	public void deleteEvent(MyEvent ev) {
		if (ev != null)
			myEventList.remove(ev);
	}

	public ArrayList<MyTimeSlot> getTimeSlotList() {
		return timeSlotList;
	}

	public void setTimeSlotList(ArrayList<MyTimeSlot> timeSlotList) {
		this.timeSlotList = timeSlotList;
	}

	public ArrayList<MyTimeSlot> getReceivedtTimeSlotList() {
		return receivedtTimeSlotList;
	}

	public void setReceivedtTimeSlotList(ArrayList<MyTimeSlot> receivedtTimeSlotList) {
		this.receivedtTimeSlotList = receivedtTimeSlotList;
	}

}
