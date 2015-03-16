package sg.edu.ntu.sce.fyp.calevent.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import sg.edu.ntu.sce.fyp.calevent.global.Settings;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyTimeSlot;
import android.util.Log;

public class XMLParser {
	private static final String DEBUG_TAG = XMLParser.class.getSimpleName();
	
	public XMLParser() {
	}
	
	public String parseResult(String xmlRecords){
		DataManager dtMgr = DataManager.getInstance();
	    String mode = new String();
	    String result = new String();

		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		    InputSource is = new InputSource();
		    is.setCharacterStream(new StringReader(xmlRecords));
		    Document doc = db.parse(is);
		    
		    NodeList nodes = doc.getElementsByTagName("mode");
		    Element element = (Element) nodes.item(0);
		    mode = getCharacterDataFromElement(element);
		    
		    if (mode.equalsIgnoreCase(Settings.TimeSlotSharing)){ 
		    	/*Share mode*/
			    nodes = doc.getElementsByTagName("event");
				for (int i = 0; i < nodes.getLength(); i++) {
					MyEvent nEvent = new MyEvent();
					element = (Element) nodes.item(i);
	
					NodeList title = element.getElementsByTagName("title");
					Element line = (Element) title.item(0);
					String content = getCharacterDataFromElement(line);
					nEvent.setTitle(content);
					result += (content + "\n");
	
					NodeList atstart = element.getElementsByTagName("dtstart");
					line = (Element) atstart.item(0);
					content = getCharacterDataFromElement(line);
					nEvent.setDtstart(content);
					result += (content + "\n");
	
					NodeList dtend = element.getElementsByTagName("dtend");
					line = (Element) dtend.item(0);
					content = getCharacterDataFromElement(line);
					nEvent.setDtend(content);
					result += (content + "\n");
	
					NodeList duration = element.getElementsByTagName("duration");
					line = (Element) duration.item(0);
					content = getCharacterDataFromElement(line);
					nEvent.setDuration(content);
					result += (content + "\n");
	
					NodeList description = element
							.getElementsByTagName("description");
					line = (Element) description.item(0);
					content = getCharacterDataFromElement(line);
					nEvent.setDescription(content);
					result += (content + "\n");
	
					NodeList event_location = element
							.getElementsByTagName("event_location");
					line = (Element) event_location.item(0);
					content = getCharacterDataFromElement(line);
					nEvent.setEvent_location(content);
					result += (content + "\n");
	
					dtMgr.getReceivedEventList().add(nEvent);
				}
		    } else { /*Find mode*/
		    	 nodes = doc.getElementsByTagName("timeslot");
		    	 ArrayList<MyTimeSlot> tsList = new ArrayList<MyTimeSlot>();
					for (int i = 0; i < nodes.getLength(); i++) {
						MyTimeSlot nTs = new MyTimeSlot();
						element = (Element) nodes.item(i);
		
						NodeList title = element.getElementsByTagName("dtstart");
						Element line = (Element) title.item(0);
						String content = getCharacterDataFromElement(line);
						nTs.setStart(Long.valueOf(content));
						result += (content + "\n");
		
						NodeList atstart = element.getElementsByTagName("dtend");
						line = (Element) atstart.item(0);
						content = getCharacterDataFromElement(line);
						nTs.setEnd(Long.valueOf(content));
						result += (content + "\n");
		
						tsList.add(nTs);
					}
					dtMgr.setReceivedtTimeSlotList(tsList);
		    }
		    
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Log.d(DEBUG_TAG, result);
		
		return mode;
	}
	
	public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	  }
}
