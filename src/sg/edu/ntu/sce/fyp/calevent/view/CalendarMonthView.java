package sg.edu.ntu.sce.fyp.calevent.view;

import java.lang.reflect.Field;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.listener.CalendarOnClickListner;
import sg.edu.ntu.sce.fyp.calevent.listener.CalendarViewOnDateChangeListener;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalendarMonthView {
	private Context context;
	private MainActivity activity;
	private CalendarView calendarViewMonth;

	public CalendarMonthView(Context ctx, Activity act){
		this.context = ctx;
		this.activity = (MainActivity) act;
	}
	
	public void setupCalendarViewMonth(){
		calendarViewMonth = (CalendarView) this.activity.findViewById(R.id.calendar_view);
		
		/**
		 * set up calendarViewMonth model
		 */
		calendarViewMonth.setShowWeekNumber(false);
		// here we set Sunday as the first day of the Calendar
		calendarViewMonth.setFirstDayOfWeek(1);
		
		/**
		 * set up appearance
		 */
		calendarViewMonth.setBackgroundColor(this.activity.getResources().getColor(R.color.white));
		calendarViewMonth.setSelectedWeekBackgroundColor(this.activity.getResources().getColor(
				R.color.bg_record_color));
		// sets the color for the vertical bar shown at the beginning and at the
				// end of the selected date.
		calendarViewMonth.setSelectedDateVerticalBar(R.color.greyCC);
		
		calendarViewMonth.setWeekDayTextAppearance(R.style.CalendarDayOfWeeks);
		calendarViewMonth.setFocusedMonthDateColor(this.activity.getResources().getColor(R.color.black));
		calendarViewMonth.setUnfocusedMonthDateColor(this.activity.getResources().getColor(R.color.greyCC));
		
		calendarViewMonth.setWeekSeparatorLineColor(this.activity.getResources().getColor(
				R.color.bg_record_color));

		//set color of month name
		try
	    {
	        CalendarView cv = (CalendarView) this.activity.findViewById(R.id.calendar_view);
	        Class<?> cvClass = cv.getClass();
	        Field field = cvClass.getDeclaredField("mMonthName");
	        field.setAccessible(true);

	        try
	        {
	            TextView tv = (TextView) field.get(cv);
	            tv.setTextColor(this.activity.getResources().getColor(R.color.orange));
	        } 
	        catch (IllegalArgumentException e)
	        {
	            e.printStackTrace();
	        }
	        catch (IllegalAccessException e)
	        {
	            e.printStackTrace();
	        }
	    }
	    catch (NoSuchFieldException e)
	    {
	        e.printStackTrace();
	    }
		/**
		 * set up calendarViewWeek controller
		 */
		// sets the listener to be notified upon selected date change.
		calendarViewMonth.setOnDateChangeListener(new CalendarViewOnDateChangeListener(this.context,this.activity));
		calendarViewMonth.setOnClickListener(new CalendarOnClickListner(this.context,this.activity));
		
		calendarViewMonth.setVisibility(View.VISIBLE);
	}

}
