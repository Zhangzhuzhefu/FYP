package sg.edu.ntu.sce.fyp.calevent.view;

import android.app.Activity;
import android.content.Context;

public class CalendarViewManager {

	private CalendarWeekView weekView;
	private CalendarMonthView monthView;
	
	public CalendarViewManager(Context ctx, Activity act){
		weekView = new CalendarWeekView(ctx, act);
		monthView = new CalendarMonthView(ctx, act);
	}
	
	public void setupCalendarView(){
		weekView.setupCalendarViewWeek();
		monthView.setupCalendarViewMonth();
	}
	

}
