package sg.edu.ntu.sce.fyp.calevent;

import sg.edu.ntu.sce.fyp.calevent.fragment.HomeFragment;
import sg.edu.ntu.sce.fyp.calevent.fragment.NewEventFragment;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//set up navigation tabs
		ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.setDisplayShowTitleEnabled(false);

		Tab tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_home)
				.setTabListener(
						new TabListener<NewEventFragment>(this, "home",
								NewEventFragment.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_new)
				.setTabListener(
						new TabListener<HomeFragment>(this, "newevent",
								HomeFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_inbox)
				.setTabListener(
						new TabListener<HomeFragment>(this, "inbox",
								HomeFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_info)
				.setTabListener(
						new TabListener<HomeFragment>(this, "info",
								HomeFragment.class));
		actionBar.addTab(tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

}
