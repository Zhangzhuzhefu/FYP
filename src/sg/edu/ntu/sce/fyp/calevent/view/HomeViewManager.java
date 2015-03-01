package sg.edu.ntu.sce.fyp.calevent.view;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.activity.fragment.InfoFragment;
import sg.edu.ntu.sce.fyp.calevent.controller.listener.TabListenerHome;
import sg.edu.ntu.sce.fyp.calevent.controller.listener.TabListenerInbox;
import sg.edu.ntu.sce.fyp.calevent.controller.listener.TabListenerInfo;
import sg.edu.ntu.sce.fyp.calevent.controller.listener.TabListenerNewevent;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class HomeViewManager {
	private Context context;
	private Activity activity;
	
	public static final String HOME = "home";
	public static final String NEWEVENT = "newevent";
	public static final String INBOX = "inbox";
	public static final String INFO = "info";
	
	public HomeViewManager(MainActivity act){
		this.context = act.getApplicationContext(); 
		this.activity = act;
		setupHomeView();
	}
	
	private void setupHomeView(){
		activity.setContentView(R.layout.activity_home);
		
		/*set up navigation tabs*/
		ActionBar actionBar = activity.getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.setDisplayShowTitleEnabled(false);

		Tab tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_home)
				.setTabListener(
						new TabListenerHome(activity));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_new)
				.setTabListener(
						new TabListenerNewevent(activity));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_inbox)
				.setTabListener(
						new TabListenerInbox(activity));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_info)
				.setTabListener(
						new TabListenerInfo(activity, INFO,
								InfoFragment.class));
		actionBar.addTab(tab);
		
		setupToggle();
		
		}	
	
	private void setupToggle(){
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
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
