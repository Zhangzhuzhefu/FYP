package sg.edu.ntu.sce.fyp.calevent.activity;

import sg.edu.ntu.sce.fyp.calevent.communicator.CalendarCommunicator;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarViewManager;
import sg.edu.ntu.sce.fyp.calevent.view.HomeViewManager;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	public CalendarViewManager calendarViewMgr;
	public HomeViewManager homeViewMgr;
	
	public ModelManager modelManager;
	
	public CalendarCommunicator caleventCommunicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initializeAppModels();
		initializeAppViews();
		initializeAppCommunicator();
		
		//operationFlow();
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
		homeViewMgr = new HomeViewManager(getApplicationContext(),this);
		calendarViewMgr = new CalendarViewManager(getApplicationContext(),this);
	}

	public void initializeAppCommunicator(){
		caleventCommunicator = new CalendarCommunicator(getApplicationContext(), this);
	}
	
	public void operationFlow(){
		
	}
	
}
