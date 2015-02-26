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
	private ArrayList<Event> otherEventList;
	
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

	public ArrayList<Event> getOtherEventList() {
		return otherEventList;
	}

	public void setOtherEventList(ArrayList<Event> otherEventList) {
		this.otherEventList = otherEventList;
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

}
