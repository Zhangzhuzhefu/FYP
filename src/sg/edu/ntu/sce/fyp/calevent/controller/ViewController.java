package sg.edu.ntu.sce.fyp.calevent.controller;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarMonthView;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarWeekView;
import sg.edu.ntu.sce.fyp.calevent.view.HomeView;
import android.content.Context;

public class ViewController {

	private DataManager dataMgr; 
	
	private CalendarWeekView weekView;
	private CalendarMonthView monthView;
	private HomeView homeView;
	
	public ViewController(MainActivity act){
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
	
	public void updateTimeSlotsInWeekView(){
		weekView.updateTimeLine();
		if (dataMgr.getTimeSlotList() != null){
			weekView.updateTimeSlots(dataMgr.getTimeSlotList());
		}
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
			weekView.updateEvents(dataMgr.getReceivedEventList(), MyEvent.RECEIVEDEVENT);
		}
	}
	
	public void displayReceivedEventsOnly(){
		if (weekView != null){
			weekView.onlyUpdateEventsOf(MyEvent.RECEIVEDEVENT);
		}
	}
	
	public void displayMyEventsOnly(){
		if (weekView != null){
			weekView.onlyUpdateEventsOf(MyEvent.MYEVENT);
		}
	}
	
	public void displayTimeSlotsOnly(){
		if (weekView != null){
			weekView.onlyUpdateEventsOf(MyEvent.TIMESLOT);
		}
	}
	
	public void homeViewSelectInboxTab(){
		if (homeView != null) 
			homeView.tab_inbox.select();
	}
	
	public void homeViewSelectTimeSlotTab(){
		if (homeView != null) 
			homeView.tab_time_slot.select();
	}
	
	public void setToggleTo(boolean isEventShare){
		if (isEventShare) {
			homeView.toggle.setChecked(false);
			dataMgr.getSettings().setToggle(false);
		} else {
			homeView.toggle.setChecked(true);
			dataMgr.getSettings().setToggle(true);
		}
	}

}
