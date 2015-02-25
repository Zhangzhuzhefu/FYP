package sg.edu.ntu.sce.fyp.calevent.view;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.listener.CalendarOnClickListner;
import sg.edu.ntu.sce.fyp.calevent.model.Today;
import sg.edu.ntu.sce.fyp.calevent.util.DateHelper;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class CalendarWeekView {
	private Context context;
	private MainActivity activity;
	private View calendarViewWeek;

	public CalendarWeekView(Context ctx, Activity act){
		this.context = ctx;
		this.activity = (MainActivity) act;
	}
	
	public void setupCalendarViewWeek(){
		/**
		 * set up calendarViewWeek
		 */
		calendarViewWeek = this.activity.findViewById(R.id.calendar_layout_week);
		Today today = this.activity.modelManager.getToday();
		TextView currentMonthTextView = (TextView) this.activity.findViewById(R.id.currentMonthTextView);
		TextView currentYearTextView = (TextView) this.activity.findViewById(R.id.currentYearTextView);
		currentMonthTextView.setText(DateHelper.convertMonth(today.monthInt));
		currentYearTextView.setText(today.year);
		
		TextView dateTextView1 = (TextView) this.activity.findViewById(R.id.dateTextView1);
		TextView dateTextView2 = (TextView) this.activity.findViewById(R.id.dateTextView2);
		TextView dateTextView3 = (TextView) this.activity.findViewById(R.id.dateTextView3);
		TextView dateTextView4 = (TextView) this.activity.findViewById(R.id.dateTextView4);
		TextView dateTextView5 = (TextView) this.activity.findViewById(R.id.dateTextView5);
		TextView dateTextView6 = (TextView) this.activity.findViewById(R.id.dateTextView6);
		TextView dateTextView7 = (TextView) this.activity.findViewById(R.id.dateTextView7);
		dateTextView1.setText(today.day);
		dateTextView2.setText(DateHelper.dateIncr(today, 1));
		dateTextView3.setText(DateHelper.dateIncr(today, 2));
		dateTextView4.setText(DateHelper.dateIncr(today, 3));
		dateTextView5.setText(DateHelper.dateIncr(today, 4));
		dateTextView6.setText(DateHelper.dateIncr(today, 5));
		dateTextView7.setText(DateHelper.dateIncr(today, 6));
		
		TextView weekdayTextView1 = (TextView) this.activity.findViewById(R.id.weekdayTextView1);
		TextView weekdayTextView2 = (TextView) this.activity.findViewById(R.id.weekdayTextView2);
		TextView weekdayTextView3 = (TextView) this.activity.findViewById(R.id.weekdayTextView3);
		TextView weekdayTextView4 = (TextView) this.activity.findViewById(R.id.weekdayTextView4);
		TextView weekdayTextView5 = (TextView) this.activity.findViewById(R.id.weekdayTextView5);
		TextView weekdayTextView6 = (TextView) this.activity.findViewById(R.id.weekdayTextView6);
		TextView weekdayTextView7 = (TextView) this.activity.findViewById(R.id.weekdayTextView7);
		weekdayTextView1.setText(today.dayOfWeek);
		weekdayTextView2.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 1));
		weekdayTextView3.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 2));
		weekdayTextView4.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 3));
		weekdayTextView5.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 4));
		weekdayTextView6.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 5));
		weekdayTextView7.setText(DateHelper.convertDayOfWeek(today.dayOfWeekInt + 6));
		
		calendarViewWeek.setOnClickListener(new CalendarOnClickListner(this.context, this.activity));

		calendarViewWeek.setVisibility(View.INVISIBLE);
	}
}
