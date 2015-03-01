package sg.edu.ntu.sce.fyp.calevent.activity.fragment;

import sg.edu.ntu.sce.fyp.calevent.controller.adapter.ReadCaleandarListAdapter;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class InfoFragment extends ListFragment{

	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    ReadCaleandarListAdapter adapter = new ReadCaleandarListAdapter(getActivity(), DataManager.getInstance().getAllCalendars()) ;
	    setListAdapter(adapter);
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
	  }
}
