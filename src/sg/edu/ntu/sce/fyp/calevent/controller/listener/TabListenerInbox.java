package sg.edu.ntu.sce.fyp.calevent.controller.listener;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.global.Settings;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class TabListenerInbox implements ActionBar.TabListener {
	private final MainActivity mActivity;
	private final Context ctx;

	public TabListenerInbox(Activity activity) {
		mActivity = (MainActivity) activity;
		ctx = mActivity.getApplicationContext();
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mActivity.findViewById(R.id.activity_home_layout).setVisibility(
				View.VISIBLE);
		if (mActivity.calendarViewManager != null) {
			mActivity.calendarViewManager.updateWeekView();
		}
		
		Toast.makeText(ctx, ctx.getString(R.string.inbox_on_tab),
				Toast.LENGTH_SHORT).show();
		
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		for (MyEvent ev : mActivity.dataManager.getAcceptedEventList()){
			ev.setAccepted(false);
		}
		mActivity.caleventHelper.addNewEventsToCalendar(
				mActivity.dataManager.getAcceptedEventList(), 
				Settings.getInstance().getWriteCalendarId());
		mActivity.dataManager.getAcceptedEventList().clear();
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
}
