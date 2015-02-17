package sg.edu.ntu.sce.fyp.calevent.listener;

import android.app.Activity;
import android.content.Context;
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
		
		
	}

}
