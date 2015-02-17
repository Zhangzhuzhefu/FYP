package sg.edu.ntu.sce.fyp.calevent.controller;

import android.app.Activity;
import android.content.Context;

public class CalendarEventCommunicator {

	private Context context;
	private Activity activity;
	private CalendarEventReader reader;
	private CalendarEventWriter writer;
	
	public CalendarEventCommunicator (Context context, Activity activity){
		this.context = context;
		this.activity =activity;
		reader = new CalendarEventReader();
		writer = new CalendarEventWriter();
	}
	
	
}
