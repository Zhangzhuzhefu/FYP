package sg.edu.ntu.sce.fyp.calevent.view;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.controller.listener.CalendarOnClickListner;
import sg.edu.ntu.sce.fyp.calevent.controller.listener.MyEventOnClickListner;
import sg.edu.ntu.sce.fyp.calevent.global.DateHelper;
import sg.edu.ntu.sce.fyp.calevent.global.Today;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CalendarWeekView {
	private Context context;
	private MainActivity activity;
	private View calendarViewWeek;

	public CalendarWeekView(Context ctx, MainActivity act){
		this.context = ctx;
		this.activity = act;
	}
	
	public void setupCalendarViewWeek(){
		/**
		 * set up calendarViewWeek
		 */
		calendarViewWeek = this.activity.findViewById(R.id.calendar_layout_week);
		Today today = this.activity.dataManager.getToday();
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
		
		updateTimeLine();

		calendarViewWeek.setOnClickListener(new CalendarOnClickListner(this.context, this.activity));

		calendarViewWeek.setVisibility(View.VISIBLE);
	}
	
	public void updateTimeLine(){
		int marginTop;
		marginTop = (int) ((float) DateHelper
				.getCurrentTimeFromMidnightInMilli()
				/ (float) DateHelper.HOURINMILLI * 60);
		
		LinearLayout curTimeLine = (LinearLayout) this.activity.findViewById(R.id.currentTimeMarkerLinearLayout);
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) curTimeLine.getLayoutParams();
		
		layoutParams.setMargins(0, marginTop, 0, 0);
		curTimeLine.setLayoutParams(layoutParams);
	}
	
	public void updateEvents(ArrayList<MyEvent> eventList, String tag){
		/*remove all TextViews*/
		RelativeLayout[] colLayouts = new RelativeLayout[7];
		colLayouts[0] = (RelativeLayout) this.activity.findViewById(R.id.sundayRelativeLayout);
		colLayouts[1] = (RelativeLayout) this.activity.findViewById(R.id.mondayRelativeLayout);
		colLayouts[2] = (RelativeLayout) this.activity.findViewById(R.id.tuesdayRelativeLayout);
		colLayouts[3] = (RelativeLayout) this.activity.findViewById(R.id.wednesdayRelativeLayout);
		colLayouts[4] = (RelativeLayout) this.activity.findViewById(R.id.thursdayRelativeLayout);
		colLayouts[5] = (RelativeLayout) this.activity.findViewById(R.id.fridayRelativeLayout);
		colLayouts[6] = (RelativeLayout) this.activity.findViewById(R.id.saturdayRelativeLayout);
		for (RelativeLayout layout : colLayouts) {
			for (int i = 0; i < layout.getChildCount(); i++) {
				View v = layout.getChildAt(i);
				if (v.getTag() != null)
					if (v instanceof TextView) {
						if (v.getTag() != null) {
							if (v.getTag() instanceof String) {
								if (((String) v.getTag()).equalsIgnoreCase(tag)) {
									layout.removeView(v);
									i--;
								}
							}
						}
					}
			}
		}
		for (MyEvent ev : eventList) {
			TextView tv;
			String eventTitle;
			int colIndex, tvHeight, tvMargtinTop;
			
			tv = new TextView(activity);
			eventTitle = ev.getTitle();
			tvHeight =(int) (Float.valueOf(ev.getDuration()) / Float.valueOf(DateHelper.HOURINMILLI) * 60);
			tvMargtinTop = (int) (Float.valueOf(ev.getStartTimeFromMidnight()) / Float.valueOf(DateHelper.HOURINMILLI) * 60);
			colIndex = (int) ((Float.valueOf(ev.getDtstart()) - Float.valueOf(DateHelper.getTodayMidnightInMilli()))/DateHelper.DAYINMILLI);
			
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT, tvHeight);
			layoutParams.setMargins(0, tvMargtinTop, 0, 0);
			tv.setTag(tag);
			tv.setLayoutParams(layoutParams);
			tv.setText(eventTitle);
			tv.setTextSize(10);
			tv.setTextColor(this.activity.getResources().getColor(R.color.white));
			int color; 
			if (tag == MyEvent.MYEVENT) 
				if (ev.isToBeShared())
					color = R.color.myEvent_selected_blue;
				else 
					color = R.color.myEvent_blue;
			else 
				if (ev.isAccepted())
					color = R.color.otherEvent_selected_green;
				else 
					color = R.color.otherEvent_green;
			tv.setBackgroundColor(this.activity.getResources().getColor(color));
			tv.setOnClickListener(new MyEventOnClickListner(this.activity, ev));
			colLayouts[colIndex].addView(tv);
		}
	}
	
	public void updateReceivedEventsOnly(String tag){
		/*remove all TextViews that is not MyEvent.RECEIVEDEVENT*/
		RelativeLayout[] colLayouts = new RelativeLayout[7];
		colLayouts[0] = (RelativeLayout) this.activity.findViewById(R.id.sundayRelativeLayout);
		colLayouts[1] = (RelativeLayout) this.activity.findViewById(R.id.mondayRelativeLayout);
		colLayouts[2] = (RelativeLayout) this.activity.findViewById(R.id.tuesdayRelativeLayout);
		colLayouts[3] = (RelativeLayout) this.activity.findViewById(R.id.wednesdayRelativeLayout);
		colLayouts[4] = (RelativeLayout) this.activity.findViewById(R.id.thursdayRelativeLayout);
		colLayouts[5] = (RelativeLayout) this.activity.findViewById(R.id.fridayRelativeLayout);
		colLayouts[6] = (RelativeLayout) this.activity.findViewById(R.id.saturdayRelativeLayout);
		for (RelativeLayout layout : colLayouts) {
			for (int i = 0; i < layout.getChildCount(); i++) {
				View v = layout.getChildAt(i);
				if (v.getTag() != null)
					if (v instanceof TextView) {
						if (v.getTag() != null) {
							if (v.getTag() instanceof String) {
								if (!((String) v.getTag())
										.equalsIgnoreCase(tag)) {
									layout.removeView(v);
									i--;
								}
							}
						}
					}
			}
		}
	}
}
