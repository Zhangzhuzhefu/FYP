package sg.edu.ntu.sce.fyp.calevent.listener;

import sg.edu.ntu.sce.fyp.calevent.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class CalendarViewOnDateChangeListener implements OnDateChangeListener {
	private Context con; 
	private Activity activity;
	
	public CalendarViewOnDateChangeListener (Context context, Activity activity){
		con = context;
		this.activity =activity;  
	}
	
	@Override
	public void onSelectedDayChange(CalendarView view, int year, int month,
			int dayOfMonth) {
		Toast.makeText(con , dayOfMonth + "/" + month+1 + "/" + year, Toast.LENGTH_LONG).show();
		
		this.activity.findViewById(R.id.calendar_layout_month).setVisibility(View.INVISIBLE);
		this.activity.findViewById(R.id.calendar_layout_week).setVisibility(View.VISIBLE);
		
	}

}
