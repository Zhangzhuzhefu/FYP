package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.ArrayList;
import java.util.Calendar;

import sg.edu.ntu.sce.fyp.calevent.model.Event;
import android.content.ContentResolver;
import android.content.Context;

public class EventWriter {

	private ContentResolver contentResolver;
	
	public EventWriter(Context context) {
		contentResolver = context.getContentResolver();
	}

	public void addEvents(ArrayList<Event> events, Calendar cal){
		for (Event ev : events) {
			if (ev != null) {
				addANewEvent(ev, cal);
			}
		}
	}

	public void addANewEvent(Event ev, Calendar cal){
		
	}

	

}
