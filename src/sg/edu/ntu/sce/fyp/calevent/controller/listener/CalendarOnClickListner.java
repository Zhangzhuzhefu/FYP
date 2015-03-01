package sg.edu.ntu.sce.fyp.calevent.controller.listener;

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
	private View week_view;
	private View month_view;
	
	public CalendarOnClickListner (Context context, Activity activity){
		this.context = context;
		this.activity =activity;  
		week_view = this.activity.findViewById(R.id.calendar_layout_week);
		month_view = this.activity.findViewById(R.id.calendar_layout_month);
	}
	
	@Override
	public void onClick(View v) {
		int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Toast.makeText(context , dayOfMonth + "/" + month+1 + "/" + year, Toast.LENGTH_SHORT).show();
		
		if (month_view.getVisibility() == View.VISIBLE) {
			week_view.setVisibility(View.VISIBLE);
			month_view.setVisibility(View.INVISIBLE);
		} else {
			week_view.setVisibility(View.INVISIBLE);
			month_view.setVisibility(View.VISIBLE);
		}
		
	}
}
