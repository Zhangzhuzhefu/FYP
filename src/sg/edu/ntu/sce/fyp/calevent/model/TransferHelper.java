package sg.edu.ntu.sce.fyp.calevent.model;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;

public class TransferHelper {
	private MainActivity activity;
	private DataManager dataMgr; 
	
	public TransferHelper(MainActivity act){
		this.activity = act;
		dataMgr = act.dataManager;
	}
	
	public void sendAndReceiveData(){
		
	}

}
