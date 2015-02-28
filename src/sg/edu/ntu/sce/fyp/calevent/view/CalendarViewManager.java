package sg.edu.ntu.sce.fyp.calevent.view;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import android.content.Context;

public class CalendarViewManager {

	private ModelManager modelMgr; 
	
	private CalendarWeekView weekView;
	private CalendarMonthView monthView;
	
	public CalendarViewManager(MainActivity act){
		Context ctx = act.getApplicationContext();
		weekView = new CalendarWeekView(ctx, act);
		monthView = new CalendarMonthView(ctx, act);
		modelMgr = ModelManager.getInstance();
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
		if (modelMgr.getMyEventList() != null){
			updateMyEventsInWeekView();
		}
		if (modelMgr.getReceivedEventList() != null){
			updateReceivedEventsInWeekView();
		}
	}
	
	private void updateMyEventsInWeekView(){
		weekView.updateEvents(modelMgr.getMyEventList(),R.color.myEvent_blue);
	}
	
	private void updateReceivedEventsInWeekView(){
		weekView.updateEvents(modelMgr.getReceivedEventList(),R.color.otherEvent_green);
	}
	

}
