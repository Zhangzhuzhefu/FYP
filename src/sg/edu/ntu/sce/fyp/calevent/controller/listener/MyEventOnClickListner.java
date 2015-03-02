package sg.edu.ntu.sce.fyp.calevent.controller.listener;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MyEventOnClickListner implements OnClickListener{

	private Context context; 
	private Activity activity;
	private MyEvent event;
	private DataManager dataMgr;
	
	public MyEventOnClickListner (Activity activity, MyEvent ev){
		this.activity = activity;
		this.context = this.activity.getApplicationContext();
		this.event = ev;
		this.dataMgr = DataManager.getInstance();
	}
	
	@Override
	public void onClick(View v) {
		if (v.getTag() instanceof String) {
			if (((String) v.getTag()).equalsIgnoreCase(MyEvent.MYEVENT)) {
				if (event.isToBeShared()) {
					event.setToBeShared(false);
					v.setBackgroundColor(this.activity.getResources().getColor(R.color.myEvent_blue));
					dataMgr.getToBeSharedEventList().remove(event);
				} else {
					event.setToBeShared(true);
					v.setBackgroundColor(this.activity.getResources().getColor(R.color.myEvent_selected_blue));
					dataMgr.getToBeSharedEventList().add(event);
				}
			} else if (((String) v.getTag()).equalsIgnoreCase(MyEvent.RECEIVEDEVENT)) {
				if (event.isAccepted()) {
					event.setAccepted(false);
					v.setBackgroundColor(this.activity.getResources().getColor(R.color.otherEvent_green));
					dataMgr.getReceivedEventList().add(event);
					dataMgr.getAcceptedEventList().remove(event);
					
				} else {
					event.setAccepted(true);
					v.setBackgroundColor(this.activity.getResources().getColor(R.color.otherEvent_selected_green));
					dataMgr.getAcceptedEventList().add(event);
					dataMgr.getReceivedEventList().remove(event);
				}
			}
		Toast.makeText(context , event.getTitle(), Toast.LENGTH_SHORT).show();
		}	
	}
}
