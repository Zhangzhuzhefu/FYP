package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;

public class BeamHelper {
	private static final String DEBUG_TAG = BeamHelper.class.getSimpleName();

	private MainActivity activity;
	private DataManager dataMgr;
    NfcAdapter mNfcAdapter;

	public BeamHelper(MainActivity act) {
		this.activity = act;
		dataMgr = act.dataManager;
        mNfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        checkNFCAvalibility();
        if (mNfcAdapter == null) {
            activity.finish();
            return;
        }
        mNfcAdapter.setNdefPushMessageCallback(activity, activity);
        
	}

	//called when a device is in range to beam data to
	public NdefMessage createNdefMessage(NfcEvent event) {
		String eventstext = new String();
		ArrayList<MyEvent> eventList = dataMgr.getToBeSharedEventList();
		for (MyEvent ev : eventList){
			eventstext += (ev.getTitle() + ev.getDtstart());
		}
		
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { NdefRecord.createMime(
                        "application/sg.edu.ntu.sce.fyp.calevent", 
                        eventstext.getBytes())
        });
        
        return msg;
	}
	
	public void onResume() {
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(activity.getIntent().getAction())) {
            processIntent(activity.getIntent());
        }
    }

    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
    	activity.setIntent(intent);
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        String payload = new String(msg.getRecords()[0].getPayload()); 
        Log.d(DEBUG_TAG, payload);
        // record 0 contains the MIME type, record 1 is the AAR, if present
        new AlertDialog.Builder(activity)
		.setTitle(activity.getResources().getString(R.string.alert))
		.setMessage(payload)
		.setPositiveButton(android.R.string.yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						// continue with quit
					}
				}).setIcon(android.R.drawable.btn_star)
		.show();
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
