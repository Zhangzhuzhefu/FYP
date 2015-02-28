package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.ArrayList;

public class ModelManager {

	private static ModelManager mg;
	private Today today; 
	private Settings settings;
	private ArrayList<MyCalendar> allCalendars;
	private ArrayList<MyCalendar> selectedCalendars;
	private String[] selectedCalendarIDs;
	private ArrayList<Event> myEventList;
	private ArrayList<Event> toBeSharedEventList;
	private ArrayList<Event> receivedEventList;
	
	public static ModelManager getInstance(){
		if (mg == null) {
			mg = new ModelManager();
		}
		return mg;
	}

	public ModelManager(){
		settings = Settings.getInstance();
	}
	
	public Today getToday() {
		if (today == null) {
			today = new Today();
		}
		return today;
	}

	public ArrayList<Event> getMyEventList() {
		return myEventList;
	}

	public void setMyEventList(ArrayList<Event> myEventList) {
		this.myEventList = myEventList;
	}

	public ArrayList<Event> getToBeSharedEventList() {
		if (toBeSharedEventList == null){
			toBeSharedEventList = new ArrayList<Event>();
		}
		return toBeSharedEventList;
	}
	public void setToBeSharedEventList(ArrayList<Event> toBeSharedEventList) {
		this.toBeSharedEventList = toBeSharedEventList;
	}

	public ArrayList<Event> getReceivedEventList() {
		return receivedEventList;
	}

	public void setReceivedEventList(ArrayList<Event> receivedEventList) {
		this.receivedEventList = receivedEventList;
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
		updateSelectedCalendars();
	}
	
	public void updateSelectedCalendars(){
		selectedCalendars = new ArrayList<MyCalendar>();
		int numSelected = 0;
		for (MyCalendar cal : allCalendars) {
			if (cal.isSelected()){
				selectedCalendars.add(cal);
				numSelected ++;
			}
		}
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
	
	public Event getEventByID(String event_id){
		for (Event ev : myEventList) {
			if (ev.getEvent_id().equalsIgnoreCase(event_id)) {
				return ev;
			}
		}
		return null;
	}
	
	public void addNewEvent(Event ev) {
		if (ev != null)
			myEventList.add(ev);
	}

	public void deleteEvent(Event ev) {
		if (ev != null)
			myEventList.remove(ev);
	}

}
