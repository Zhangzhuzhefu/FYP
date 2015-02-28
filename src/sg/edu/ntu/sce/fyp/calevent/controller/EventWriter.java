package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.TimeZone;

import sg.edu.ntu.sce.fyp.calevent.model.Event;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.util.Log;

public class EventWriter {
	private static final String DEBUG_TAG = EventWriter.class.getSimpleName();
	private ContentResolver contentResolver;
	public EventWriter(Context context) {
		contentResolver = context.getContentResolver();
	}


	public long addNewEvent(Event ev, long cal_id){
		
		ContentValues values = new ContentValues();
		TimeZone tz = TimeZone.getDefault();
		
		values.put(Events.DTSTART, Long.valueOf(ev.getDtstart()));
		values.put(Events.DTEND, Long.valueOf(ev.getDtend()));
		values.put(Events.TITLE, ev.getTitle());
		values.put(Events.DESCRIPTION, ev.getDescription());
		values.put(Events.CALENDAR_ID, cal_id);
		values.put(Events.EVENT_TIMEZONE, tz.getID());
		Uri uri = contentResolver.insert(Events.CONTENT_URI, values);

		return Long.parseLong(uri.getLastPathSegment());
	}
	
	public void updateEvent(Event ev) {
		long eventID = Long.valueOf(ev.getEvent_id());
		
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, Long.valueOf(ev.getDtstart()));
		values.put(Events.DTEND, Long.valueOf(ev.getDtend()));
		values.put(Events.TITLE, ev.getTitle());
		values.put(Events.DESCRIPTION, ev.getDescription());
		values.put(Events.CALENDAR_ID, Long.valueOf(ev.getCalendar_id()));
		Uri updateUri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
		int rows = contentResolver.update(updateUri, values, null, null);
		Log.d(DEBUG_TAG, "Rows updated: " + rows);  

	}
	
	public void deletEvent (Event ev) {
		Uri deleteUri = ContentUris.withAppendedId(Events.CONTENT_URI, Long.valueOf(ev.getEvent_id()));
		int rows = contentResolver.delete(deleteUri, null, null);
		Log.d(DEBUG_TAG, "Rows deleted: " + rows);
	}

	

}
