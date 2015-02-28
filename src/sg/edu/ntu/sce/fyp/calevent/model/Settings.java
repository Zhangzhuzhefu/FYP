package sg.edu.ntu.sce.fyp.calevent.model;

public class Settings {
	private static Settings settings;
	
	private boolean toggle; //ture: find; flase: share
	private long writeCalendarId;
	
	private Settings (){
		toggle = false;
	}
	
	public static Settings getInstance(){
		if (settings == null)
			settings = new Settings();
		return settings;
	}

	public boolean isToggle() {
		return toggle;
	}

	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}

	public long getWriteCalendarId() {
		return writeCalendarId;
	}

	public void setWriteCalendarId(long writeCalendarId) {
		this.writeCalendarId = writeCalendarId;
	}

}
