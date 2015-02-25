package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.ArrayList;

public class ModelManager {

	private static ModelManager mg;
	private Today today; 
	private ArrayList<Event> myEventList;
	private ArrayList<Event> otherEventList;
	
	public static ModelManager getInstance(){
		if (mg == null) {
			mg = new ModelManager();
		}
		return mg;
	}

	public ModelManager(){
		
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
}
