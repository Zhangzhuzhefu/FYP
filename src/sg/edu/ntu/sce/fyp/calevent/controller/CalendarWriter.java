package sg.edu.ntu.sce.fyp.calevent.controller;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class CalendarWriter {
	private static final String DEBUG_TAG = CalendarWriter.class.getSimpleName();
	private ContentResolver contentResolver;
	
	public CalendarWriter(Context ctx) {
		contentResolver = ctx.getContentResolver();
	}

	public void updateCalendarField(String field, String value, String calendarID){
		long calID = Long.valueOf(calendarID);
		ContentValues values = new ContentValues();
		// The new display name for the calendar
		values.put(field, value);
		Uri updateUri = ContentUris.withAppendedId(CalendarHelper.CALENDAR_URI, calID);
		int rows = contentResolver.update(updateUri, values, null, null);
		Log.i(DEBUG_TAG, "Rows updated: " + rows);
	}
}
