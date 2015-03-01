package sg.edu.ntu.sce.fyp.calevent.controller.listener;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.Settings;
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

	/* The following are each of the ActionBar.TabListener callbacks */

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mActivity.findViewById(R.id.activity_home_layout).setVisibility(
				View.VISIBLE);
		if (mActivity.calendarViewMgr != null)
			mActivity.calendarViewMgr.updateTimeline();
		
		Toast.makeText(ctx, ctx.getString(R.string.inbox_on_tab),
				Toast.LENGTH_SHORT).show();
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		mActivity.caleventHelper.addNewEventsToCalendar(
				mActivity.modelManager.getAcceptedEventList(), 
				Settings.getInstance().getWriteCalendarId());
		mActivity.modelManager.getAcceptedEventList().clear();
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
}
