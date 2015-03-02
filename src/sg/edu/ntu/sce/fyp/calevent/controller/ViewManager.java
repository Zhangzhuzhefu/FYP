package sg.edu.ntu.sce.fyp.calevent.controller;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarMonthView;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarWeekView;
import sg.edu.ntu.sce.fyp.calevent.view.HomeView;
import android.content.Context;

public class ViewManager {

	private DataManager dataMgr; 
	
	private CalendarWeekView weekView;
	private CalendarMonthView monthView;
	private HomeView homeView;
	
	public ViewManager(MainActivity act){
		Context ctx = act.getApplicationContext();
		weekView = new CalendarWeekView(ctx, act);
		monthView = new CalendarMonthView(ctx, act);
		homeView = new HomeView(act);
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
		updateMyEventsInWeekView();
		updateReceivedEventsInWeekView();
	}
	
	private void updateMyEventsInWeekView(){
		weekView.updateTimeLine();
		if (dataMgr.getMyEventList() != null){
			weekView.updateEvents(dataMgr.getMyEventList(), MyEvent.MYEVENT);
		}
	}
	
	private void updateReceivedEventsInWeekView(){
		weekView.updateTimeLine();
		if (dataMgr.getReceivedEventList() != null){
			weekView.updateEvents(dataMgr.getReceivedEventList(),MyEvent.RECEIVEDEVENT);
		}
	}
	
	public void displayReceivedEventsOnly(){
		if (weekView != null){
			weekView.updateReceivedEventsOnly(MyEvent.RECEIVEDEVENT);
		}
	}
	
	public void displayMyEventsOnly(){
		if (weekView != null){
			weekView.updateReceivedEventsOnly(MyEvent.MYEVENT);
		}
	}
	
	public void homeViewSelectInboxTab(){
		if (homeView != null) 
			homeView.tab_inbox.select();
	}

}
