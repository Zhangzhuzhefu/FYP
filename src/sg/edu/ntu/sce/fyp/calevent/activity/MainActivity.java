package sg.edu.ntu.sce.fyp.calevent.activity;

import java.util.Calendar;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.controller.CalendarHelper;
import sg.edu.ntu.sce.fyp.calevent.controller.TransferHelper;
import sg.edu.ntu.sce.fyp.calevent.controller.adapter.WriteCaleandarListAdapter;
import sg.edu.ntu.sce.fyp.calevent.model.Event;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarViewManager;
import sg.edu.ntu.sce.fyp.calevent.view.HomeViewManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
            case R.id.action_settings:  
              AlertDialog alertDialog = new AlertDialog.Builder(this).create();
              //AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
              LayoutInflater inflater = getLayoutInflater();
              View convertView = (View) inflater.inflate(R.layout.calendar_write_list, null);
              alertDialog.setView(convertView);
              alertDialog.setTitle(getResources().getText(R.string.select_to_be_written_calendar));
              alertDialog.setCanceledOnTouchOutside(true);
              ListView listview = (ListView) convertView.findViewById(R.id.calendar_write_list);
              WriteCaleandarListAdapter adapter = new WriteCaleandarListAdapter(this, ModelManager.getInstance().getAllCalendars()) ;
              listview.setAdapter(adapter);
              alertDialog.show();
            return true;      
              default:  
                return super.onOptionsItemSelected(item);  
        }  
    }  
	
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
