package sg.edu.ntu.sce.fyp.calevent.fragment;

import sg.edu.ntu.sce.fyp.calevent.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment  extends Fragment{
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/**
		 * Inflate the layout for this fragment
		 */
		return inflater.inflate(R.layout.activity_home, container, false);
		// return super.onCreateView(inflater, container, savedInstanceState);
	}

}
