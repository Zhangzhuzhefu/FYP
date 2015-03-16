package sg.edu.ntu.sce.fyp.calevent.controller;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.global.Settings;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import sg.edu.ntu.sce.fyp.calevent.model.TimeSlotCalculator;
import sg.edu.ntu.sce.fyp.calevent.model.XMLConstructor;
import sg.edu.ntu.sce.fyp.calevent.model.XMLParser;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyTimeSlot;
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
    private NfcAdapter mNfcAdapter;
    private XMLParser xmlParser;
    private XMLConstructor xmlConstructor;

	public BeamHelper(MainActivity act) {
		this.activity = act;
		xmlParser = new XMLParser();
		xmlConstructor = new XMLConstructor();
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
		eventstext = xmlConstructor.getToBeSentContentsInXML();
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
    	//activity.setIntent(intent);
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
    	DataManager dataMgr = DataManager.getInstance();
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        String payload = new String(msg.getRecords()[0].getPayload());
        Log.d(DEBUG_TAG, payload);
        String mode = xmlParser.parseResult(payload);
        activity.calendarViewManager.setToggleTo(mode.equalsIgnoreCase(Settings.EventSharing));
        if (mode.equalsIgnoreCase(Settings.TimeSlotSharing)){
        	activity.calendarViewManager.homeViewSelectInboxTab();
        } else {
			if (dataMgr != null) {
				if (dataMgr.getTimeSlotList() == null) {
					dataMgr.setTimeSlotList(
							TimeSlotCalculator.getInstance().calculateTimeSlot(
									dataMgr.getMyEventList()));
				}
				for (MyTimeSlot ts:dataMgr.getReceivedtTimeSlotList()) {
					Log.d(DEBUG_TAG, ts.toString());
				}
				dataMgr.setTimeSlotList(
						TimeSlotCalculator.getInstance().calculateTimeSlot(
								dataMgr.getTimeSlotList(),
								dataMgr.getReceivedtTimeSlotList()));
	        	
	        	for (MyTimeSlot ts:dataMgr.getTimeSlotList()) {
					Log.d(DEBUG_TAG, ts.toString());
				}
	        	activity.calendarViewManager.homeViewSelectTimeSlotTab();
			}
        }
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
									activity.finish();
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
									activity.finish();
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();
		}
	}

}
