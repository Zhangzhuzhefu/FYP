package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.HashMap;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Calendars;
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
		Events.EVENT_LOCATION
	};
	
	private static final int CALENDAR_ID_INDEX = 0;
	private static final int TITLE_INDEX = 1;
	private static final int DESCRIPTION_INDEX = 2;
	private static final int DTSTART_INDEX = 3;
	private static final int DTEND_INDEX = 4;
	private static final int EVENT_LOCATION_INDEX = 5;
	
	public EventReader(Context ctx) {
		contentResolver = ctx.getContentResolver();
	}
	
	public void readEventsFromCalendar(String[] calIDs){
		Cursor cursor = null;  
		
		for (String calId : calIDs) {
			cursor = contentResolver.query(CalendarController.EVENT_URI,
					FIELDS, "calendar_id=" + calId, null, null);
			Log.d(DEBUG_TAG, calId);
			try {
				if (cursor.getCount() > 0) {
					
					while (cursor.moveToNext()) {
						String calendar_id, title, description, dtstart, dtend, event_loc;
						calendar_id = cursor.getString(CALENDAR_ID_INDEX);
						title = cursor.getString(TITLE_INDEX);
						description = cursor.getString(DESCRIPTION_INDEX);
						dtstart = cursor.getString(DTSTART_INDEX);
						dtend = cursor.getString(DTEND_INDEX);
						event_loc = cursor.getString(EVENT_LOCATION_INDEX);
					
						Log.d(DEBUG_TAG, calendar_id + " " + title + "\n"
								+ description + "\n" + 
								dtstart + " " + dtend + "\n" 
								+ event_loc + "\n");
					}
				}
			} catch (AssertionError ex) {
				Log.d(DEBUG_TAG,"catch with possible bug");
			}
		}
	}

}
