package sg.edu.ntu.sce.fyp.calevent.controller;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

public class CalendarEventReader {

	public static final String[] FIELDS = { 
		CalendarContract.Calendars._ID,
		CalendarContract.Calendars.NAME,
		CalendarContract.Calendars.ACCOUNT_NAME,
		CalendarContract.Calendars.ACCOUNT_TYPE,
		CalendarContract.Calendars.OWNER_ACCOUNT,
		CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
		CalendarContract.Calendars.CALENDAR_COLOR,
		CalendarContract.Calendars.VISIBLE 
	};
	
	private static final int ID_INDEX = 0;
	private static final int NAME_INDEX = 1;
	private static final int ACCOUNT_NAME_INDEX = 2;
	private static final int ACCOUNT_TYPE_INDEX = 3;
	private static final int OWNER_ACCOUNT_INDEX = 4;
	private static final int CALENDAR_DISPLAY_NAME_INDEX = 5;
	private static final int CALENDAR_COLOR_INDEX = 6;
	private static final int VISIBLE_INDEX = 7;
	
	public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");

	private ContentResolver contentResolver;
	private ArrayList<HashMap<String,String>> allCalendars = new ArrayList<HashMap<String,String>>();
	private ArrayList<HashMap<String,String>> selectedCalendars = new ArrayList<HashMap<String,String>>();

	public CalendarEventReader(Context ctx) {
		contentResolver = ctx.getContentResolver();
	}

	public ArrayList<HashMap<String,String>> getAllCalendars() {
		// Fetch a list of all calendars sync'd with the device and their
		// display names
		allCalendars.clear();
		Cursor cursor = contentResolver.query(CALENDAR_URI, FIELDS, null, null, null);
		allCalendars = handleCursor(cursor);
		return allCalendars;
	}
	
	public ArrayList<HashMap<String,String>> getSelectedCalendars(String[] selectionIDs){
		Cursor cursor = null;  
		selectedCalendars.clear();
		/*String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND (" 
		                        + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
		                        + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";*/
//		selectionIDs = new String[] {"1","0","3"}; 
		
		String selection = "(" + CalendarContract.Calendars._ID + " = ?)";
		
		String[] selectionArgs = new String[1];
		
		for (String cal_id : selectionIDs) {
			selectionArgs[0] = cal_id;
			cursor = contentResolver.query(CALENDAR_URI, FIELDS, selection, selectionArgs, null);
			selectedCalendars.add(handleCursor(cursor).get(0));
		}
		
		for (HashMap<String,String> cal: selectedCalendars) {
			Log.d("CalendarEventReader",cal.get("selectedCalendars: "+"cal_display_name"));
		}
		
		return selectedCalendars;
	}
	
	
	private ArrayList<HashMap<String,String>> handleCursor(Cursor cursor){
		ArrayList<HashMap<String,String>> calendars = new ArrayList<HashMap<String,String>>();
		try {
			if (cursor.getCount() > 0) {
				while (cursor.moveToNext()) {
					String id, name, acc_name, acc_type, owner_acc, cal_display_name, cal_color;
					id = cursor.getString(ID_INDEX);
					name = cursor.getString(NAME_INDEX);
					acc_name = cursor.getString(ACCOUNT_NAME_INDEX);
					acc_type = cursor.getString(ACCOUNT_TYPE_INDEX);
					cal_display_name = cursor
							.getString(CALENDAR_DISPLAY_NAME_INDEX);
					owner_acc = cursor.getString(OWNER_ACCOUNT_INDEX);
					cal_color = cursor.getString(CALENDAR_COLOR_INDEX);
					Boolean selected = !cursor.getString(VISIBLE_INDEX).equals(
							"0");

					HashMap<String, String> calendar = new HashMap<String, String>();
					calendar.put("id", id);
					calendar.put("name", name);
					calendar.put("acc_name", acc_name);
					calendar.put("acc_type", acc_type);
					calendar.put("cal_display_name", cal_display_name);
					calendar.put("owner_acc", owner_acc);
					calendar.put("cal_color", cal_color);
					calendar.put("selected", String.valueOf(selected));

					calendars.add(calendar);
					Log.d("CalendarEventReader", id + " " + name + "\n"
							+ acc_name + " " + acc_type + "\n"
							+ cal_display_name + "\n" + owner_acc + "\n"
							+ cal_color + " " + String.valueOf(selected));
				}
			}
		} catch (AssertionError ex) {
			Log.d("CalendarEventReader","catch with possible bug");
		}
		return calendars;
	}

}
