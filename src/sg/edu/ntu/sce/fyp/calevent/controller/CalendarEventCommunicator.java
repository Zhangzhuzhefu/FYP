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
		reader = new CalendarEventReader(context);
		writer = new CalendarEventWriter();
		
		//functionality test 
		reader.getAllCalendars();
		reader.getSelectedCalendars(new String[] {"1","4","3"});
	}
	
	
}
