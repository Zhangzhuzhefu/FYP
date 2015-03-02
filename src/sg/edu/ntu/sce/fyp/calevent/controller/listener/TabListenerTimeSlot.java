package sg.edu.ntu.sce.fyp.calevent.controller.listener;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class TabListenerTimeSlot implements ActionBar.TabListener {
	private final MainActivity mActivity;
	private final Context ctx;

	public TabListenerTimeSlot(Activity activity) {
		mActivity = (MainActivity) activity;
		ctx = mActivity.getApplicationContext();
	}

	/* The following are each of the ActionBar.TabListener callbacks */

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mActivity.findViewById(R.id.activity_home_layout).setVisibility(
				View.VISIBLE);
		if (mActivity.calendarViewManager != null)
			mActivity.calendarViewManager.updateTimeline();

		Toast.makeText(ctx, ctx.getString(R.string.new_on_tab),
				Toast.LENGTH_SHORT).show();
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
}
