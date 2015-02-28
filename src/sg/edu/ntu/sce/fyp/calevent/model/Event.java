package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.Calendar;


public class Event {

	private String title;
	private String description;
	private String dtstart;
	private String dtend;
	private String event_location;
	private String calendar_id;
	private String event_id;
	private String duration;
	
	private String startTimeFromMidnight;
	private boolean toBeShared;
	private boolean accepted;
	
	public static final String MYEVENT = "myevent";
	public static final String RECEIVEDEVENT = "receivedevent";
	
	public Event(){
		toBeShared = false;
		accepted = false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDtstart() {
		return dtstart;
	}

	public void setDtstart(String dtstart) {
		this.dtstart = dtstart;
		
		Calendar calendar_now= Calendar.getInstance();
		calendar_now.setTimeInMillis(Long.valueOf(dtstart));
		long nowInMilli = calendar_now.getTimeInMillis();
		long offset = calendar_now.get(Calendar.ZONE_OFFSET) + calendar_now.get(Calendar.DST_OFFSET);
		long sinceMidnight = (nowInMilli + offset) % (24 * 60 * 60 * 1000);
		setStartTimeFromMidnight(String.valueOf(sinceMidnight));
	}

	public String getDtend() {
		return dtend;
	}

	public void setDtend(String dtend) {
		this.dtend = dtend;
	}

	public String getEvent_location() {
		return event_location;
	}

	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}

	public String getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar_id(String calendar_id) {
		this.calendar_id = calendar_id;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStartTimeFromMidnight() {
		return startTimeFromMidnight;
	}

	public void setStartTimeFromMidnight(String startTimeFromMidnight) {
		this.startTimeFromMidnight = startTimeFromMidnight;
	}

	public boolean isToBeShared() {
		return toBeShared;
	}

	public void setToBeShared(boolean toBeShared) {
		this.toBeShared = toBeShared;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
}
