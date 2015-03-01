package sg.edu.ntu.sce.fyp.calevent.view;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import android.content.Context;

public class CalendarViewManager {

	private DataManager dataMgr; 
	
	private CalendarWeekView weekView;
	private CalendarMonthView monthView;
	
	public CalendarViewManager(MainActivity act){
		Context ctx = act.getApplicationContext();
		weekView = new CalendarWeekView(ctx, act);
		monthView = new CalendarMonthView(ctx, act);
		dataMgr = DataManager.getInstance();
		setupCalendarView();
	}
	
	private void setupCalendarView(){
		weekView.setupCalendarViewWeek();
		monthView.setupCalendarViewMonth();
	}
	
	public void updateTimeline(){
		weekView.updateTimeLine();
	}
	
	public void updateWeekView(){
		weekView.updateTimeLine();
		if (dataMgr.getMyEventList() != null){
			updateMyEventsInWeekView();
		}
		if (dataMgr.getReceivedEventList() != null){
			updateReceivedEventsInWeekView();
		}
	}
	
	private void updateMyEventsInWeekView(){
		weekView.updateEvents(dataMgr.getMyEventList(),R.color.myEvent_blue, MyEvent.MYEVENT);
	}
	
	private void updateReceivedEventsInWeekView(){
		weekView.updateEvents(dataMgr.getReceivedEventList(),R.color.otherEvent_green,MyEvent.RECEIVEDEVENT);
	}
	

}
