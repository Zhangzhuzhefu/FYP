package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelManager {

	private static ModelManager mg;
	private Today today; 
	private Settings settings;
	private ArrayList<HashMap<String,String>> allCalendars;
	private ArrayList<HashMap<String,String>> selectedCalendars;
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

	public ArrayList<HashMap<String, String>> getAllCalendars() {
		if (allCalendars == null)
			allCalendars = new ArrayList<HashMap<String, String>>();
		return allCalendars;
	}

	public void setAllCalendars(ArrayList<HashMap<String, String>> allCalendars) {
		this.allCalendars = allCalendars;
	}

	public ArrayList<HashMap<String, String>> getSelectedCalendars() {
		if (selectedCalendars == null)
			selectedCalendars = new ArrayList<HashMap<String, String>>();
		return selectedCalendars;
	}

	public void setSelectedCalendars(
			ArrayList<HashMap<String, String>> selectedCalendars) {
		this.selectedCalendars = selectedCalendars;
	}

	public String[] getSelectedCalendarIDs() {
		return selectedCalendarIDs;
	}

	public void setSelectedCalendarIDs(String[] selectedCalendarIDs) {
		this.selectedCalendarIDs = selectedCalendarIDs;
	}

}
