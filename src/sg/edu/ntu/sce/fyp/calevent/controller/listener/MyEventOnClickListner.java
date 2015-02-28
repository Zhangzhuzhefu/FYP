package sg.edu.ntu.sce.fyp.calevent.controller.listener;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.model.Event;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MyEventOnClickListner implements OnClickListener{

	private Context context; 
	private Activity activity;
	private Event event;
	private ModelManager modelMgr;
	
	public MyEventOnClickListner (Activity activity, Event ev){
		this.activity = activity;
		this.context = this.activity.getApplicationContext();
		this.event = ev;
		this.modelMgr = ModelManager.getInstance();
	}
	
	@Override
	public void onClick(View v) {
		if (event.isToBeShared()) {
			event.setToBeShared(false);
			v.setBackgroundColor(this.activity.getResources().getColor(R.color.myEvent_blue));
			modelMgr.getToBeSharedEventList().remove(event);
		} else {
			event.setToBeShared(true);
			v.setBackgroundColor(this.activity.getResources().getColor(R.color.myEvent_selected_blue));
			modelMgr.getToBeSharedEventList().add(event);
		}
		
		Toast.makeText(context , event.getTitle(), Toast.LENGTH_LONG).show();
		
	}
}
