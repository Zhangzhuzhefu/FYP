package sg.edu.ntu.sce.fyp.calevent.controller.listener;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.view.HomeViewManager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class TabListener implements ActionBar.TabListener {
	private Fragment mFragment_info;
	private final MainActivity mActivity;
	private final Context ctx;
	private final String mTag;
	private final Class<?> mClass;

	/**
	 * Constructor used each time a new tab is created.
	 * 
	 * @param activity
	 *            The host Activity, used to instantiate the fragment
	 * @param tag
	 *            The identifier tag for the fragment
	 * @param clz
	 *            The fragment's Class, used to instantiate the fragment
	 */
	public TabListener(Activity activity, String tag, Class<?> clz) {
		mActivity = (MainActivity) activity;
		mTag = tag;
		mClass = clz;
		ctx = mActivity.getApplicationContext();
	}

	/* The following are each of the ActionBar.TabListener callbacks */

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		if (mTag.equalsIgnoreCase(HomeViewManager.HOME)) {
			mActivity.findViewById(R.id.activity_home_layout).setVisibility(
					View.VISIBLE);
			if (mActivity.calendarViewMgr != null)
				mActivity.calendarViewMgr.updateTimeline();
			Toast.makeText(ctx, ctx.getString(R.string.home_on_tab),
					Toast.LENGTH_SHORT).show();
			
		} else if (mTag.equalsIgnoreCase(HomeViewManager.NEWEVENT)) {
			mActivity.findViewById(R.id.activity_home_layout).setVisibility(
					View.VISIBLE);
			if (mActivity.calendarViewMgr != null)
				mActivity.calendarViewMgr.updateTimeline();
			Toast.makeText(ctx, ctx.getString(R.string.new_on_tab),
					Toast.LENGTH_SHORT).show();
			
		} else if (mTag.equalsIgnoreCase(HomeViewManager.INBOX)) {
			mActivity.findViewById(R.id.activity_home_layout).setVisibility(
					View.VISIBLE);
			if (mActivity.calendarViewMgr != null)
				mActivity.calendarViewMgr.updateTimeline();
			Toast.makeText(ctx, ctx.getString(R.string.inbox_on_tab),
					Toast.LENGTH_SHORT).show();
			
		} else if (mTag.equalsIgnoreCase(HomeViewManager.INFO)) {
			Toast.makeText(ctx, ctx.getString(R.string.info_on_tab),
					Toast.LENGTH_SHORT).show();
			mActivity.findViewById(R.id.activity_home_layout).setVisibility(
					View.INVISIBLE);
			if (mFragment_info == null) {
				mFragment_info = Fragment.instantiate(mActivity,
						mClass.getName());
				ft.add(android.R.id.content, mFragment_info, mTag);
			} else {
				ft.attach(mFragment_info);
			}
		} else {

		}

	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (mTag.equalsIgnoreCase(HomeViewManager.HOME)) {

		} else if (mTag.equalsIgnoreCase(HomeViewManager.NEWEVENT)) {

		} else if (mTag.equalsIgnoreCase(HomeViewManager.INBOX)) {

		} else if (mTag.equalsIgnoreCase(HomeViewManager.INFO)) {
			if (mFragment_info != null) {
				// Detach the fragment, because another one is being attached
				ft.detach(mFragment_info);
			}
		} else {

		}
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// User selected the already selected tab. Usually do nothing.
	}
}
