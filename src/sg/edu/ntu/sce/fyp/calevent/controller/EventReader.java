package sg.edu.ntu.sce.fyp.calevent.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sg.edu.ntu.sce.fyp.calevent.model.Event;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Events;
import android.util.Log;

public class EventReader {
	private static final String DEBUG_TAG = EventReader.class.getSimpleName();
	private ContentResolver contentResolver;
	public static final String[] FIELDS = { 
		Events.CALENDAR_ID,
		Events.TITLE,
		Events.DESCRIPTION,
		Events.DTSTART,
		Events.DTEND,
		Events.EVENT_LOCATION,
		Events._ID,
		Events.DURATION
	};
	
	private static final int CALENDAR_ID_INDEX = 0;
	private static final int TITLE_INDEX = 1;
	private static final int DESCRIPTION_INDEX = 2;
	private static final int DTSTART_INDEX = 3;
	private static final int DTEND_INDEX = 4;
	private static final int EVENT_LOCATION_INDEX = 5;
	private static final int ID_INDEX = 6;
	private static final int DURATION_INDEX = 7;
	
	public EventReader(Context ctx) {
		contentResolver = ctx.getContentResolver();
	}
	
	public void readEventsFromCalendar(String[] calIDs){
		Cursor cursor = null;  
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c_start= Calendar.getInstance();
		c_start.set(2015,0,2,0,0); //Note that months start from 0 (January)   
		Calendar c_end= Calendar.getInstance();
		c_end.set(2015,5,7,0,0); //Note that months start from 0 (January)
		//String selection = "((dtstart >= " + c_start.getTimeInMillis() + ") AND (dtend <= " + c_end.getTimeInMillis() + "))";//") AND (calendar_id = ?))";
		//String selection = "((calendar_id = ?) AND (dtend <= " + c_end.getTimeInMillis() + ") AND (dtstart >= " + c_start.getTimeInMillis() + "))";
		//String selection = "((calendar_id = ?) AND (2>1) AND (1<2))";
		String selection = "((calendar_id = ?) AND (dtstart >= " + c_start.getTimeInMillis() + ") AND (dtend <= " + c_end.getTimeInMillis() + "))";
		String[] arg = new String[1];
		
		for (String calId : calIDs) {
			
			arg[0] = calId;
			cursor = contentResolver.query(CalendarController.EVENT_URI,
					FIELDS, selection, arg, null);
			Log.d(DEBUG_TAG, "calId: "+ calId 								
					+ "c_start: " + c_start.getTimeInMillis() + " " + df.format(c_start.getTime()) + "\n");
			Log.d("zzzf", selection);
			try {
				if (cursor.getCount() > 0) {
					while (cursor.moveToNext()) {
						String calendar_id, title, description, dtstart, dtend, event_loc,event_id,duration;
						calendar_id = cursor.getString(CALENDAR_ID_INDEX);
						title = cursor.getString(TITLE_INDEX);
						description = cursor.getString(DESCRIPTION_INDEX);
						dtstart = cursor.getString(DTSTART_INDEX);
						dtend = cursor.getString(DTEND_INDEX);
						event_loc = cursor.getString(EVENT_LOCATION_INDEX);
						event_id = cursor.getString(ID_INDEX);
						duration = cursor.getString(DURATION_INDEX);
					
						Event ev = new Event();
						ev.setCalendar_id(calendar_id);
						ev.setDescription(description);
						ev.setDtend(dtend);
						ev.setDtstart(dtstart);
						ev.setEvent_location(event_loc);
						ev.setTitle(title);
						ev.setEvent_id(event_id);
						ev.setDuration(duration);
						
						Calendar beginTime = Calendar.getInstance();
						beginTime.setTimeInMillis(Long.valueOf(dtstart));
						
						Calendar endTime = Calendar.getInstance();
						if (dtend != null) {
							endTime.setTimeInMillis(Long.valueOf(dtend));
						}
						
						Log.d(DEBUG_TAG, event_id + ": " + title +"\n"
								+ description + "\n" 
								+ "dtstart: " + dtstart + " " + df.format(beginTime.getTime()) + "\n" 
								+ "dtend:" + dtend +" "
								+ (dtend==null?"null":df.format(endTime.getTime())) + "\n" + duration +"\n" 
								+ event_loc + "\n");
					}
					
				}
			} catch (AssertionError ex) {
				Log.d(DEBUG_TAG,"catch with possible bug");
			}
		}
	}

}
