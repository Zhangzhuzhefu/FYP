package sg.edu.ntu.sce.fyp.calevent.view;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.Event;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import android.content.Context;

public class CalendarViewManager {

	private ModelManager modelMgr; 
	
	private CalendarWeekView weekView;
	private CalendarMonthView monthView;
	
	public CalendarViewManager(Context ctx, MainActivity act){
		weekView = new CalendarWeekView(ctx, act);
		monthView = new CalendarMonthView(ctx, act);
		modelMgr = ModelManager.getInstance();
	}
	
	public void setupCalendarView(){
		weekView.setupCalendarViewWeek();
		monthView.setupCalendarViewMonth();
	}
	
	public void updateWeekView(){
		if (modelMgr.getMyEventList() != null){
			updateMyEventsInWeekView();
		}
		if (modelMgr.getOtherEventList() != null){
			updateEventsOfOthersInWeekView();
		}
	}
	
	private void updateMyEventsInWeekView(){
		weekView.updateEvents(modelMgr.getMyEventList());

	}
	
	private void updateEventsOfOthersInWeekView(){
		weekView.updateEvents(modelMgr.getOtherEventList());
	}
	

}
