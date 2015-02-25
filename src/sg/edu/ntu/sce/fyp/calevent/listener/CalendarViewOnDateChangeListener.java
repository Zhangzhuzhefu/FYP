package sg.edu.ntu.sce.fyp.calevent.listener;

import android.content.Context;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class CalendarViewOnDateChangeListener implements OnDateChangeListener {
	private Context con; 
	
	public CalendarViewOnDateChangeListener (Context context){
		con = context;
	}
	
	@Override
	public void onSelectedDayChange(CalendarView view, int year, int month,
			int dayOfMonth) {
		Toast.makeText(con , dayOfMonth + "/" + month+1 + "/" + year, Toast.LENGTH_LONG).show();
		
		
	}

}
