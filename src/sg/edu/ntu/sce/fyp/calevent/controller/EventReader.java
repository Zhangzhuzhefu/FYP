package sg.edu.ntu.sce.fyp.calevent.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sg.edu.ntu.sce.fyp.calevent.model.Event;
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
	
	public void readEventsFromCalendar(String[] calIDs, long startMilli, long endMilli){
		Cursor cursor = null;  
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		String selection = "((calendar_id = ?) AND (dtstart >= ?) AND (dtstart <= ?))";
//		String selection = "((calendar_id = ?))";
		String[] arg = new String[3];
		arg[1] = Long.toString(startMilli);
		arg[2] = Long.toString(endMilli);
		String sort = Events.DTSTART + "";
		
		for (String calId : calIDs) {
			
			arg[0] = calId;
//			arg[0] = "zhangzhuzhefu@gmail.com";
			cursor = contentResolver.query(CalendarController.EVENT_URI, FIELDS, selection, arg, sort);
			Log.d(DEBUG_TAG, "calId: "+ calId + "\n"
					+ "startMilli: " + startMilli + " " + df.format(new Date(startMilli)) + "\n"
					+ "endMilli: " + endMilli + " " + df.format(new Date(endMilli)));
			Log.d("zzz", selection);
			
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
