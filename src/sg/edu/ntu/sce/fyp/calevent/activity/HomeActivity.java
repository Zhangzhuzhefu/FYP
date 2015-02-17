package sg.edu.ntu.sce.fyp.calevent.activity;

import java.lang.reflect.Field;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.fragment.HomeFragment;
import sg.edu.ntu.sce.fyp.calevent.fragment.NewEventFragment;
import sg.edu.ntu.sce.fyp.calevent.listener.CalendarViewOnDateChangeListener;
import sg.edu.ntu.sce.fyp.calevent.listener.TabListener;
import sg.edu.ntu.sce.fyp.calevent.model.Today;
import sg.edu.ntu.sce.fyp.calevent.util.DateHelper;
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
	Today today;

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
		
		initializeAppModels();
		initializeCalendar();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void initializeAppModels(){
		//set up global date 
		today = new Today();
		
	}
	public void initializeCalendar() {
		setupCalendarViewMonth();
		setupCalendarViewWeek();
		
		calendarViewMonth.setVisibility(View.VISIBLE);
		calendarViewWeek.setVisibility(View.INVISIBLE);
	}
	
	private void setupCalendarViewMonth(){
		calendarViewMonth = (CalendarView) findViewById(R.id.calendar_view);
		
		/**
		 * set up calendarViewMonth model
		 */
		// sets whether to show the week number.
		calendarViewMonth.setShowWeekNumber(false);
		// sets the first day of week according to Calendar.
				// here we set Sunday as the first day of the Calendar
		calendarViewMonth.setFirstDayOfWeek(1);
		
		/**
		 * set up appearance
		 */
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
		/**
		 * set up calendarViewWeek controller
		 */
		// sets the listener to be notified upon selected date change.
		calendarViewMonth.setOnDateChangeListener(new CalendarViewOnDateChangeListener(getApplicationContext(),this));
		
	}
	
	private void setupCalendarViewWeek(){
		/**
		 * set up calendarViewWeek
		 */
		calendarViewWeek = this.findViewById(R.id.calendar_layout_week);
		
		TextView currentMonthTextView = (TextView) this.findViewById(R.id.currentMonthTextView);
		TextView currentYearTextView = (TextView) this.findViewById(R.id.currentYearTextView);
		currentMonthTextView.setText(DateHelper.convertMonth(today.monthInt));
		currentYearTextView.setText(today.year);
		
		TextView dateTextView1 = (TextView) this.findViewById(R.id.dateTextView1);
		TextView dateTextView2 = (TextView) this.findViewById(R.id.dateTextView2);
		TextView dateTextView3 = (TextView) this.findViewById(R.id.dateTextView3);
		TextView dateTextView4 = (TextView) this.findViewById(R.id.dateTextView4);
		TextView dateTextView5 = (TextView) this.findViewById(R.id.dateTextView5);
		TextView dateTextView6 = (TextView) this.findViewById(R.id.dateTextView6);
		TextView dateTextView7 = (TextView) this.findViewById(R.id.dateTextView7);
		dateTextView1.setText(today.day);
		dateTextView2.setText(DateHelper.dateIncr(today, 1));
		dateTextView3.setText(DateHelper.dateIncr(today, 2));
		dateTextView4.setText(DateHelper.dateIncr(today, 3));
		dateTextView5.setText(DateHelper.dateIncr(today, 4));
		dateTextView6.setText(DateHelper.dateIncr(today, 5));
		dateTextView7.setText(DateHelper.dateIncr(today, 6));
		
		TextView weekdayTextView1 = (TextView) this.findViewById(R.id.weekdayTextView1);
		TextView weekdayTextView2 = (TextView) this.findViewById(R.id.weekdayTextView2);
		TextView weekdayTextView3 = (TextView) this.findViewById(R.id.weekdayTextView3);
		TextView weekdayTextView4 = (TextView) this.findViewById(R.id.weekdayTextView4);
		TextView weekdayTextView5 = (TextView) this.findViewById(R.id.weekdayTextView5);
		TextView weekdayTextView6 = (TextView) this.findViewById(R.id.weekdayTextView6);
		TextView weekdayTextView7 = (TextView) this.findViewById(R.id.weekdayTextView7);
		weekdayTextView1.setText(today.dayOfWeek);
		weekdayTextView2.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 1));
		weekdayTextView3.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 2));
		weekdayTextView4.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 3));
		weekdayTextView5.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 4));
		weekdayTextView6.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 5));
		weekdayTextView7.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 6));
		
	}

}
