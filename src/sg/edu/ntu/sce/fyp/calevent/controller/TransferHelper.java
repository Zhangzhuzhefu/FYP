package sg.edu.ntu.sce.fyp.calevent.controller;

import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.ModelManager;

public class TransferHelper {
	private MainActivity activity;
	private ModelManager modelMgr; 
	
	public TransferHelper(MainActivity act){
		this.activity = act;
		modelMgr = act.modelManager;
	}

}
