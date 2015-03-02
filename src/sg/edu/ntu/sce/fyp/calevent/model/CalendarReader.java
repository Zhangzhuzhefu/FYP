package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.controller.CalendarController;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyCalendar;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Calendars;
import android.util.Log;

public class CalendarReader {
	private static final String DEBUG_TAG = CalendarReader.class.getSimpleName();
	private static final String[] FIELDS = { 
		Calendars._ID,
		Calendars.NAME,
		Calendars.ACCOUNT_NAME,
		Calendars.ACCOUNT_TYPE,
		Calendars.OWNER_ACCOUNT,
		Calendars.CALENDAR_DISPLAY_NAME,
		Calendars.CALENDAR_COLOR,
		Calendars.VISIBLE 
	};
	private static final int ID_INDEX = 0;
	private static final int NAME_INDEX = 1;
	private static final int ACCOUNT_NAME_INDEX = 2;
	private static final int ACCOUNT_TYPE_INDEX = 3;
	private static final int OWNER_ACCOUNT_INDEX = 4;
	private static final int CALENDAR_DISPLAY_NAME_INDEX = 5;
	private static final int CALENDAR_COLOR_INDEX = 6;
	private static final int VISIBLE_INDEX = 7;

	private ContentResolver contentResolver;

	public CalendarReader(Context ctx) {
		contentResolver = ctx.getContentResolver();
	}

	public ArrayList<MyCalendar> getAllCalendars() {
		ArrayList<MyCalendar> allCalendars = new ArrayList<MyCalendar>();
		Cursor cursor = contentResolver.query(
				CalendarController.CALENDAR_URI, FIELDS, null, null, null);
		allCalendars = handleCursor_getCalendar(cursor);
		return allCalendars;
	}
	
	public ArrayList<MyCalendar> getSelectedCalendarsByQuery(String[] selectionIDs){
		Cursor cursor = null;  
		ArrayList<MyCalendar> selectedCalendars = new ArrayList<MyCalendar>();
		
		String selection = "(" + Calendars._ID + " = ?)";
		String[] selectionArgs = new String[1];
		
		for (String cal_id : selectionIDs) {
			selectionArgs[0] = cal_id;
			cursor = contentResolver.query(CalendarController.CALENDAR_URI, FIELDS, selection, selectionArgs, null);
			selectedCalendars.add(handleCursor_getCalendar(cursor).get(0));
		}
		
		for (MyCalendar cal: selectedCalendars) {
			Log.d(DEBUG_TAG,"selectedCalendars: "+cal.getCalendar_display_name());
		}
		
		return selectedCalendars;
	}
	
	
	
	private ArrayList<MyCalendar> handleCursor_getCalendar(Cursor cursor){
		ArrayList<MyCalendar> calendars = new ArrayList<MyCalendar>();
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
					Boolean visible = !cursor.getString(VISIBLE_INDEX).equals(
							"0");

					MyCalendar calendar = new MyCalendar();
					calendar.set_id(id);
					calendar.setName(name);
					calendar.setAccount_name(acc_name);
					calendar.setAccount_type(acc_type);
					calendar.setCalendar_display_name(cal_display_name);
					calendar.setOwner_account(owner_acc);
					calendar.setColor(cal_color);
					calendar.setVisible(visible);
					calendar.setSelected(false);
					
					calendars.add(calendar);
					Log.d(DEBUG_TAG, id + " " + name + "\n"
							+ acc_name + " " + acc_type + "\n"
							+ cal_display_name + "\n" + owner_acc + "\n"
							+ cal_color + " " + String.valueOf(visible));
				}
			}
		} catch (AssertionError ex) {
			Log.d(DEBUG_TAG,"catch with possible bug");
		}
		return calendars;
	}

	
}
