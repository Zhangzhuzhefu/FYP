package sg.edu.ntu.sce.fyp.calevent.model;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

public class BeamHelper {
	private MainActivity activity;
	private DataManager dataMgr;

	public BeamHelper(MainActivity act) {
		this.activity = act;
		dataMgr = act.dataManager;
		checkNFCAvalibility();
	}

	public void sendAndReceiveData() {

	}

	private void checkNFCAvalibility() {
		PackageManager pm = activity.getPackageManager();
		// Check whether NFC is available on device
		if (!pm.hasSystemFeature(PackageManager.FEATURE_NFC)) {
			new AlertDialog.Builder(activity)
					.setTitle(activity.getResources().getString(R.string.alert))
					.setMessage(
							activity.getResources().getString(R.string.no_nfc))
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// continue with quit
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();
		}
		// Check whether device is running Android 4.1 or higher
		else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			new AlertDialog.Builder(activity)
					.setTitle(activity.getResources().getString(R.string.alert))
					.setMessage(
							activity.getResources().getString(R.string.no_beam))
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// continue with quit
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();
		}
	}

}
