package sg.edu.ntu.sce.fyp.calevent.controller.listener;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class EventOnClickListner implements OnClickListener{

	private Context context; 
	private Activity activity;
	
	public EventOnClickListner (Activity activity){
		this.activity = activity;
		this.context = this.activity.getApplicationContext();
	}
	
	@Override
	public void onClick(View v) {
		int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Toast.makeText(context , dayOfMonth + "/" + month+1 + "/" + year, Toast.LENGTH_LONG).show();
		
	}
}
