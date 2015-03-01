package sg.edu.ntu.sce.fyp.calevent.activity;

import java.util.Calendar;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.controller.CalendarHelper;
import sg.edu.ntu.sce.fyp.calevent.controller.adapter.WriteCaleandarListAdapter;
import sg.edu.ntu.sce.fyp.calevent.model.BumpHandler;
import sg.edu.ntu.sce.fyp.calevent.model.CalendarReader;
import sg.edu.ntu.sce.fyp.calevent.model.CalendarWriter;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import sg.edu.ntu.sce.fyp.calevent.model.EventReader;
import sg.edu.ntu.sce.fyp.calevent.model.EventWriter;
import sg.edu.ntu.sce.fyp.calevent.model.BeamHelper;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import sg.edu.ntu.sce.fyp.calevent.view.CalendarViewManager;
import sg.edu.ntu.sce.fyp.calevent.view.HomeViewManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	private Context context;
	public CalendarViewManager calendarViewMgr;
	public HomeViewManager homeViewMgr;
	
	public DataManager dataManager;
	public CalendarReader calReader;
	public CalendarWriter calWriter;
	public EventReader eventReader;
	public EventWriter eventWriter;
	
	public CalendarHelper caleventHelper;
	public BeamHelper beamHelper;
	private BumpHandler bumpHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();

		initializeAppModels();
		initializeAppViews();
		initializeAppController();
		
		
//		operationFlowForUnitTesting();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override  
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			LayoutInflater inflater = getLayoutInflater();
			View convertView = (View) inflater.inflate(
					R.layout.calendar_write_list, null);
			alertDialog.setView(convertView);
			alertDialog.setTitle(getResources().getText(
					R.string.select_to_be_written_calendar));
			alertDialog.setCanceledOnTouchOutside(true);
			ListView listview = (ListView) convertView
					.findViewById(R.id.calendar_write_list);
			WriteCaleandarListAdapter adapter = new WriteCaleandarListAdapter(
					this, DataManager.getInstance().getAllCalendars());
			listview.setAdapter(adapter);
			alertDialog.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void initializeAppModels(){
		dataManager = DataManager.getInstance();
		bumpHandler = new BumpHandler(this);
		beamHelper = new BeamHelper(this);
		calReader = new CalendarReader(context);
		calWriter = new CalendarWriter(context);
		eventReader = new EventReader(context);
		eventWriter = new EventWriter(context);
	}
	
	public void initializeAppViews() {
		homeViewMgr = new HomeViewManager(this);
		calendarViewMgr = new CalendarViewManager(this);
	}

	public void initializeAppController(){
		caleventHelper = new CalendarHelper(this);
	}
	
	@Override
	public void onResume() {
		bumpHandler.resume();
		super.onResume();
	}

	@Override
	public void onPause() {

		bumpHandler.pause();
		super.onPause();
	}
	
	public void operationFlowForUnitTesting(){

		///create a new event
		MyEvent newEvent = new MyEvent();
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
	}
	

}
