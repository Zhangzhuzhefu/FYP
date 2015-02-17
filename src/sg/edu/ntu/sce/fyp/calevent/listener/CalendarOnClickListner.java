package sg.edu.ntu.sce.fyp.calevent.listener;

import java.util.Calendar;

import sg.edu.ntu.sce.fyp.calevent.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class CalendarOnClickListner implements OnClickListener{

	private Context context; 
	private Activity activity;
	
	public CalendarOnClickListner (Context context, Activity activity){
		this.context = context;
		this.activity =activity;  
	}
	
	@Override
	public void onClick(View v) {
		int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Toast.makeText(context , dayOfMonth + "/" + month+1 + "/" + year, Toast.LENGTH_LONG).show();
		
		if (this.activity.findViewById(R.id.calendar_layout_month).getVisibility() == View.VISIBLE) {
			this.activity.findViewById(R.id.calendar_layout_month).setVisibility(View.INVISIBLE);
			this.activity.findViewById(R.id.calendar_layout_week).setVisibility(View.VISIBLE);
		} else {
			this.activity.findViewById(R.id.calendar_layout_month).setVisibility(View.VISIBLE);
			this.activity.findViewById(R.id.calendar_layout_week).setVisibility(View.INVISIBLE);
		}
		
	}
}
