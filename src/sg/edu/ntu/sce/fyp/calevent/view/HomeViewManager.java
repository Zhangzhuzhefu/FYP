package sg.edu.ntu.sce.fyp.calevent.view;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.fragment.HomeFragment;
import sg.edu.ntu.sce.fyp.calevent.fragment.InboxFragment;
import sg.edu.ntu.sce.fyp.calevent.fragment.InfoFragment;
import sg.edu.ntu.sce.fyp.calevent.fragment.NewEventFragment;
import sg.edu.ntu.sce.fyp.calevent.listener.TabListener;
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
	
	public HomeViewManager(Context ctx, MainActivity act){
		this.context = ctx;
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
						new TabListener<HomeFragment>(activity, HOME,
								HomeFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_new)
				.setTabListener(
						new TabListener<NewEventFragment>(activity, NEWEVENT,
								NewEventFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_inbox)
				.setTabListener(
						new TabListener<InboxFragment>(activity, INBOX,
								InboxFragment.class));
		actionBar.addTab(tab);
		tab = actionBar
				.newTab()
				.setIcon(R.drawable.tab_info)
				.setTabListener(
						new TabListener<InfoFragment>(activity, INFO,
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
						Toast.LENGTH_LONG).show();
			}
		});
	}

}
