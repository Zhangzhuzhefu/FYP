package sg.edu.ntu.sce.fyp.calevent.activity;

import sg.edu.ntu.sce.fyp.calevent.controller.CalendarHelper;
import sg.edu.ntu.sce.fyp.calevent.controller.TransferHelper;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarViewManager;
import sg.edu.ntu.sce.fyp.calevent.view.HomeViewManager;
import android.app.Activity;
import android.os.Bundle;

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
		
	}
	
}
