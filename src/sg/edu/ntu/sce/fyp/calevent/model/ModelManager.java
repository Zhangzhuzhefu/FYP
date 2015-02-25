package sg.edu.ntu.sce.fyp.calevent.model;

public class ModelManager {

	private Today today; 
	private static ModelManager mg;
	
	public ModelManager getInstance(){
		if (mg == null) {
			mg = new ModelManager();
		}
		return mg;
	}

	public Today getToday() {
		if (today == null) {
			today = new Today();
		}
		return today;
	}
}
