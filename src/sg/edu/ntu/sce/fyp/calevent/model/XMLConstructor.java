package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.global.Settings;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyTimeSlot;

public class XMLConstructor {
	
	private DataManager dtMgr;

	public XMLConstructor (){
		dtMgr = DataManager.getInstance();
	}
	
	public String getToBeSentContentsInXML(){
		String data = new String();
		data = "<data><mode>";
		data += ((Settings.getInstance().isFindMode() 
				? Settings.EventSharing : Settings.TimeSlotSharing)
				+ "</mode>");
		if (Settings.getInstance().isFindMode()) {
			if (dtMgr != null) {
				if (dtMgr.getTimeSlotList() == null) {
					dtMgr.setTimeSlotList(
							TimeSlotCalculator.getInstance().calculateTimeSlot(
									dtMgr.getMyEventList()));
				}
				for (MyTimeSlot ts : dtMgr.getTimeSlotList()) {
					data = data + 
							"<timeslot>" + 
								"<dtstart>" + ts.getStart() + "</dtstart>" + 
								"<dtend>" + ts.getEnd() + "</dtend>" + 
							"</timeslot>";
				}
			}
		} else {
			ArrayList<MyEvent> eventList = dtMgr.getToBeSharedEventList();
			for (MyEvent ev : eventList) {
				data = data + 
						"<event>" + 
							"<title>" + ev.getTitle() + "</title>" + 
							"<dtstart>" + ev.getDtstart() + "</dtstart>" + 
							"<dtend>" + ev.getDtend() + "</dtend>" + 
							"<duration>" + ev.getDuration() + "</duration>" + 
							"<description>" + ev.getDescription() + "</description>"+ 
							"<event_location>" + ev.getEvent_location() + "</event_location>" + 
						"</event>";
			}
		}
		data += "</data>";
		return data;
	}

}
