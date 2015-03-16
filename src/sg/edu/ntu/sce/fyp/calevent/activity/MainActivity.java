package sg.edu.ntu.sce.fyp.calevent.activity;

import java.util.Calendar;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.controller.BeamHelper;
import sg.edu.ntu.sce.fyp.calevent.controller.BumpHandler;
import sg.edu.ntu.sce.fyp.calevent.controller.CalendarController;
import sg.edu.ntu.sce.fyp.calevent.controller.ViewManager;
import sg.edu.ntu.sce.fyp.calevent.controller.adapter.WriteCaleandarListAdapter;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity implements CreateNdefMessageCallback{
	
	public DataManager dataManager;
	
	public ViewManager calendarViewManager;
	public CalendarController caleventHelper;
	public BeamHelper beamHelper;
	private BumpHandler bumpHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
		
	}
	
	public void initializeAppViews() {
		calendarViewManager = new ViewManager(this);
	}

	public void initializeAppController(){
		caleventHelper = new CalendarController(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		bumpHandler.onResume();
		beamHelper.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		bumpHandler.onPause();
	}

	@Override
	public void onNewIntent(Intent intent) {
		beamHelper.onNewIntent(intent);
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

	@Override
	public NdefMessage createNdefMessage(NfcEvent event) {
		return beamHelper.createNdefMessage(event);
	}
	

}
