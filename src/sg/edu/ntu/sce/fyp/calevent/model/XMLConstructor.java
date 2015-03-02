package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;

public class XMLConstructor {
	
	private DataManager dtMgr;

	public XMLConstructor (){
		dtMgr = DataManager.getInstance();
	}
	
	public String getToBeSharedEventsInXML(){
		String data = new String();
		data = "<data>"; 
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
		data += "</data>";
		return data;
	}
}
