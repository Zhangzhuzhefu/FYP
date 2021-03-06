package sg.edu.ntu.sce.fyp.calevent.controller;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.controller.listener.BumpListener;
import android.content.Context;
import android.os.Vibrator;
import android.widget.Toast;

public class BumpControler {
	private MainActivity activity;
	private BumpListener bumper;

	public BumpControler(MainActivity mainActivity) {
		activity = mainActivity;
		final Vibrator vibe = (Vibrator)activity.getSystemService(Context.VIBRATOR_SERVICE);

	    bumper = new BumpListener(activity);
	    bumper.setOnShakeListener(new BumpListener.OnShakeListener () {
	      public void onShake()
	      {
	        vibe.vibrate(100);
			Toast.makeText(activity.getApplicationContext(), "shaken", Toast.LENGTH_SHORT).show();
	      }
	    });
	}

	public void onResume() {
		bumper.resume();
	}

	public void onPause() {
		bumper.pause();
	}

}
