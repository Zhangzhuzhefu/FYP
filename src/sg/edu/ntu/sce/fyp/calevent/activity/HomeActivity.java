package sg.edu.ntu.sce.fyp.calevent.activity;

import java.lang.reflect.Field;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.fragment.HomeFragment;
import sg.edu.ntu.sce.fyp.calevent.fragment.NewEventFragment;
import sg.edu.ntu.sce.fyp.calevent.listener.CalendarViewOnDateChangeListener;
import sg.edu.ntu.sce.fyp.calevent.listener.TabListener;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

public class HomeActivity extends Activity {
	CalendarView calendarViewMonth;
	View calendarViewWeek;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//set up navigation tabs
		ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.setDisplayShowTitleEnabled(false);

		Tab tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_home)
				.setTabListener(
						new TabListener<NewEventFragment>(this, "home",
								NewEventFragment.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_new)
				.setTabListener(
						new TabListener<HomeFragment>(this, "newevent",
								HomeFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_inbox)
				.setTabListener(
						new TabListener<HomeFragment>(this, "inbox",
								HomeFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_info)
				.setTabListener(
						new TabListener<HomeFragment>(this, "info",
								HomeFragment.class));
		actionBar.addTab(tab);
		
		initializeCalendar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void initializeCalendar() {
		calendarViewMonth = (CalendarView) findViewById(R.id.calendar_view);
		// sets whether to show the week number.
		calendarViewMonth.setShowWeekNumber(false);

		// sets the first day of week according to Calendar.
		// here we set Monday as the first day of the Calendar
		calendarViewMonth.setFirstDayOfWeek(1);

		calendarViewMonth.setBackgroundColor(getResources().getColor(R.color.white));
		// The background color for the selected week.
		calendarViewMonth.setSelectedWeekBackgroundColor(getResources().getColor(
				R.color.bg_record_color));
		// sets the color for the vertical bar shown at the beginning and at the
				// end of the selected date.
		calendarViewMonth.setSelectedDateVerticalBar(R.color.greyCC);
		
		// sets the color for the dates
		calendarViewMonth.setWeekDayTextAppearance(R.style.CalendarDayOfWeeks);

		calendarViewMonth.setFocusedMonthDateColor(getResources().getColor(R.color.black));
		calendarViewMonth.setUnfocusedMonthDateColor(getResources().getColor(R.color.greyCC));
		
		// sets the color for the separator line between weeks.
		calendarViewMonth.setWeekSeparatorLineColor(getResources().getColor(
				R.color.bg_record_color));

		// sets the listener to be notified upon selected date change.
		calendarViewMonth.setOnDateChangeListener(new CalendarViewOnDateChangeListener(getApplicationContext(),this));
		
		//set color of month name
		try
	    {
	        CalendarView cv = (CalendarView) this.findViewById(R.id.calendar_view);
	        Class<?> cvClass = cv.getClass();
	        Field field = cvClass.getDeclaredField("mMonthName");
	        field.setAccessible(true);

	        try
	        {
	            TextView tv = (TextView) field.get(cv);
	            tv.setTextColor(getResources().getColor(R.color.orange));
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
		
		//set up calendarViewWeek
		calendarViewWeek = this.findViewById(R.id.calendar_layout_week);
		
		
		calendarViewMonth.setVisibility(View.VISIBLE);
		calendarViewWeek.setVisibility(View.INVISIBLE);
	}

}