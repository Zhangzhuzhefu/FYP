package sg.edu.ntu.sce.fyp.calevent.view;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.fragment.HomeFragment;
import sg.edu.ntu.sce.fyp.calevent.fragment.NewEventFragment;
import sg.edu.ntu.sce.fyp.calevent.listener.TabListener;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class HomeViewManager {
	private Context context;
	private Activity activity;
	public HomeViewManager(Context ctx, MainActivity act){
		this.context = ctx;
		this.activity = act;
	}
	
	public void setupHomeView(){
		activity.setContentView(R.layout.activity_home);
		
		/*set up navigation tabs*/
		ActionBar actionBar = activity.getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.setDisplayShowTitleEnabled(false);

		Tab tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_home)
				.setTabListener(
						new TabListener<NewEventFragment>(activity, "home",
								NewEventFragment.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_new)
				.setTabListener(
						new TabListener<HomeFragment>(activity, "newevent",
								HomeFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_inbox)
				.setTabListener(
						new TabListener<HomeFragment>(activity, "inbox",
								HomeFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_info)
				.setTabListener(
						new TabListener<HomeFragment>(activity, "info",
								HomeFragment.class));
		actionBar.addTab(tab);
		
		/*setup toggle*/
		Switch toggle = (Switch) this.activity.findViewById(R.id.switch_mode);
		toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				ModelManager.getInstance().getSettings().setToggle(isChecked);
				
				Toast.makeText(
						context,
						isChecked ? activity.getResources().getText(
								R.string.find_mode) : activity.getResources()
								.getText(R.string.share_mode),
						Toast.LENGTH_LONG).show();
				
			}
		});
		
		}	

}
