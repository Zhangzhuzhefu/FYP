package sg.edu.ntu.sce.fyp.calevent.model;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
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
import android.widget.Toast;

public class BeamHelper {
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

	public void sendAndReceiveData() {
		//TODO 
	}
	
	public NdefMessage createNdefMessage(NfcEvent event) {
		String text = ("Beam me up, Android!\n\n" +
                "Beam Time: " + System.currentTimeMillis());
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { NdefRecord.createMime(
                        "application/vnd.com.example.android.beam", text.getBytes())
         /**
          * The Android Application Record (AAR) is commented out. When a device
          * receives a push with an AAR in it, the application specified in the AAR
          * is guaranteed to run. The AAR overrides the tag dispatch system.
          * You can add it back in to guarantee that this
          * activity starts when receiving a beamed message. For now, this code
          * uses the tag dispatch system.
          */
          //,NdefRecord.createApplicationRecord("com.example.android.beam")
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
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        Toast.makeText(
				activity.getApplicationContext(),
				new String(msg.getRecords()[0].getPayload()),
				Toast.LENGTH_SHORT).show();
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
