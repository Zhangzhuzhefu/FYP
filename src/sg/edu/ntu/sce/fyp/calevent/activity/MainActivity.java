package sg.edu.ntu.sce.fyp.calevent.activity;

import java.util.Calendar;

import sg.edu.ntu.sce.fyp.calevent.controller.CalendarHelper;
import sg.edu.ntu.sce.fyp.calevent.controller.TransferHelper;
import sg.edu.ntu.sce.fyp.calevent.model.Event;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarViewManager;
import sg.edu.ntu.sce.fyp.calevent.view.HomeViewManager;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public CalendarViewManager calendarViewMgr;
	public HomeViewManager homeViewMgr;
	
	public ModelManager modelManager;
	
	public CalendarHelper caleventHelper;
	public TransferHelper transferHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initializeAppModels();
		initializeAppViews();
		initializeAppCommunicator();
		
		operationFlowForUnitTesting();
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}*/
	
	public void initializeAppModels(){
		modelManager = ModelManager.getInstance();
	}
	
	public void initializeAppViews() {
		homeViewMgr = new HomeViewManager(this);
		calendarViewMgr = new CalendarViewManager(this);
	}

	public void initializeAppCommunicator(){
		caleventHelper = new CalendarHelper(this);
		transferHelper = new TransferHelper(this);
	}
	
	public void operationFlowForUnitTesting(){

		///create a new event
		Event newEvent = new Event();
		long startMillis = 0; 
		long endMillis = 0;   
		Calendar beginTime = Calendar.getInstance();
		beginTime.set(2015, 2, 2, 4, 10);
		startMillis = beginTime.getTimeInMillis();
		Calendar endTime = Calendar.getInstance();
		endTime.set(2015, 2, 2, 4, 45);
		endMillis = endTime.getTimeInMillis();
		
		newEvent.setDtstart(String.valueOf(startMillis));
		newEvent.setDtend(String.valueOf(endMillis));
		newEvent.setDuration(String.valueOf(endMillis-startMillis));
		newEvent.setTitle("New");
		newEvent.setDescription("Group workout");

		
		//caleventHelper.addNewEventToCalendar(newEvent, 3);
		//newEvent = modelManager.getEventByID("42");
		//caleventHelper.updateNewEvent(newEvent);
		//caleventHelper.deletEvent(newEvent);
		Toast.makeText(getApplicationContext(), String.valueOf(3), Toast.LENGTH_LONG).show();
	}
	
}
