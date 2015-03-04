package sg.edu.ntu.sce.fyp.calevent.model;

import java.util.ArrayList;

import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyEvent;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyTimeSlot;
import android.util.Log;

public class TimeSlotCalculator {
	private static final String DEBUG_TAG = TimeSlotCalculator.class.getSimpleName();

	private static TimeSlotCalculator tsCalculator;
	
	public static TimeSlotCalculator getInstance(){
		if (tsCalculator == null) 
			tsCalculator = new TimeSlotCalculator();
		return tsCalculator;
	}
	
	public ArrayList<MyTimeSlot> calculateTimeSlot(ArrayList<MyTimeSlot> evList, ArrayList<MyTimeSlot> tsList){
		ArrayList<MyTimeSlot> resultTimeSlotList = new ArrayList<MyTimeSlot>();
		MyTimeSlot lastSlot = new MyTimeSlot(0, 0);
		if (evList != null && tsList != null) {
			int evListMax = evList.size() - 1;
			int tsListMax = tsList.size() - 1;
			int i = 0, j = 0;
			while (i <= evListMax || j <= tsListMax) {
				long evS, evE, tsS, tsE;
				if (i > evListMax && j <= tsListMax) {
					/**compare lastSlot and ts*/
					tsS = tsList.get(j).getStart();
					tsE = tsList.get(j).getEnd();
					long start = lastSlot.getStart();
					long end = lastSlot.getEnd();
					if (start <= tsS && end >= tsE) { //lastSlot bigger
						
					} else if (start >= tsS && end <= tsE) { // ts bigger
						lastSlot.setStart(tsS);
						lastSlot.setEnd(tsE);
					} else if (start > tsE) { // disjoint ts first
						Log.d("logic bug", "lastSlot appears after a timeslot: timeSlotList is not in order");
					} else if (tsS > end) { // disjoint lastSlot first
						if (start != 0 && end!= 0)
							resultTimeSlotList.add(lastSlot);
						lastSlot = new MyTimeSlot(tsS, tsE);
					} else if (start > tsS) { // joint ts first 
						lastSlot.setStart(tsS);
					} else { // joint lastSlot first
						lastSlot.setEnd(tsE);
					}
					j ++;
				} else if (i <= evListMax && j > tsListMax) {
					/**compare lastSlot and ev*/
					evS = evList.get(i).getStart();
					evE = evList.get(i).getEnd();
					long start = lastSlot.getStart();
					long end = lastSlot.getEnd();
					if (start <= evS && end >= evE) { //lastSlot bigger
						
					} else if (start >= evS && end <= evE) { // ts bigger
						lastSlot.setStart(evS);
						lastSlot.setEnd(evE);
					} else if (start > evE) { // disjoint ts first
						Log.d("logic bug", "lastSlot appears after a timeslot: timeSlotList is not in order");
					} else if (evS > end) { // disjoint lastSlot first
						if (start != 0 && end!= 0)
							resultTimeSlotList.add(lastSlot);
						lastSlot = new MyTimeSlot(evS, evE);
					} else if (start > evS) { // joint ts first 
						lastSlot.setStart(evS);
					} else { // joint lastSlot first
						lastSlot.setEnd(evE);
					}
					i ++;
				} else {
					evS = evList.get(i).getStart();
					evE = evList.get(i).getEnd();
					tsS = tsList.get(j).getStart();
					tsE = tsList.get(j).getEnd();
					
					if (evS <= tsS && evE >= tsE) { //ev bigger
						j ++;
						continue;
					} else if (evS >= tsS && evE <= tsE) { //ts bigger
						i ++;
						continue;
					} else if (evS > tsE) { // disjoint ts first
						/**compare lastSlot and ts*/
						long start = lastSlot.getStart();
						long end = lastSlot.getEnd();
						if (start <= tsS && end >= tsE) { //lastSlot bigger
							
						} else if (start >= tsS && end <= tsE) { // ts bigger
							lastSlot.setStart(tsS);
							lastSlot.setEnd(tsE);
						} else if (start > tsE) { // disjoint ts first
							Log.d("logic bug", "lastSlot appears after a timeslot: timeSlotList is not in order");
						} else if (tsS > end) { // disjoint lastSlot first
							if (start != 0 && end!= 0)
								resultTimeSlotList.add(lastSlot);
							lastSlot = new MyTimeSlot(tsS, tsE);
						} else if (start > tsS) { // joint ts first 
							lastSlot.setStart(tsS);
						} else { // joint lastSlot first
							lastSlot.setEnd(tsE);
						}
						j ++;
					} else if (tsS > evE) { // disjoint ev first
						/**compare lastSlot and ev*/
						long start = lastSlot.getStart();
						long end = lastSlot.getEnd();
						if (start <= evS && end >= evE) { //lastSlot bigger
							
						} else if (start >= evS && end <= evE) { // ts bigger
							lastSlot.setStart(evS);
							lastSlot.setEnd(evE);
						} else if (start > evE) { // disjoint ts first
							Log.d("logic bug", "lastSlot appears after a timeslot: timeSlotList is not in order");
						} else if (evS > end) { // disjoint lastSlot first
							if (start != 0 && end!= 0)
								resultTimeSlotList.add(lastSlot);
							lastSlot = new MyTimeSlot(evS, evE);
						} else if (start > evS) { // joint ts first 
							lastSlot.setStart(evS);
						} else { // joint lastSlot first
							lastSlot.setEnd(evE);
						}
						i ++;
					} else if (evS > tsS) { // joint ts first 
						/**compare lastSlot and tsS & evE*/
						long start = lastSlot.getStart();
						long end = lastSlot.getEnd();
						if (start <= tsS && end >= evE) { //lastSlot bigger
							
						} else if (start >= tsS && end <= evE) { // ts bigger
							lastSlot.setStart(tsS);
							lastSlot.setEnd(evE);
						} else if (start > evE) { // disjoint ts first
							Log.d("logic bug", "lastSlot appears after a timeslot: timeSlotList is not in order");
						} else if (tsS > end) { // disjoint lastSlot first
							if (start != 0 && end!= 0)
								resultTimeSlotList.add(lastSlot);
							lastSlot = new MyTimeSlot(tsS, evE);
						} else if (start > tsS) { // joint ts first 
							lastSlot.setStart(tsS);
						} else { // joint lastSlot first
							lastSlot.setEnd(evE);
						}
						i ++;
						j ++;
					} else { // joint ev first
						/**compare lastSlot and evS & tsE*/
						long start = lastSlot.getStart();
						long end = lastSlot.getEnd();
						if (start <= evS && end >= tsE) { //lastSlot bigger
							
						} else if (start >= evS && end <= tsE) { // ts bigger
							lastSlot.setStart(evS);
							lastSlot.setEnd(tsE);
						} else if (start > tsE) { // disjoint ts first
							Log.d("logic bug", "lastSlot appears after a timeslot: timeSlotList is not in order");
						} else if (evS > end) { // disjoint lastSlot first
							if (start != 0 && end!= 0)
								resultTimeSlotList.add(lastSlot);
							lastSlot = new MyTimeSlot(evS, tsE);
						} else if (start > evS) { // joint ts first 
							lastSlot.setStart(evS);
						} else { // joint lastSlot first
							lastSlot.setEnd(tsE);
						}
						i ++;
						j ++;
					}
				}
				if (i > evListMax && j > tsListMax) {
					break;
				}
			}
			resultTimeSlotList.add(lastSlot);
		} else if (evList == null) {
			Log.d(DEBUG_TAG, "myTimeSlotList is null");
		} else {
			Log.d(DEBUG_TAG, "receivedTimeSlotList is null");
		}
		
		return resultTimeSlotList;
	}
	
	public ArrayList<MyTimeSlot> calculateTimeSlot(ArrayList<MyEvent> evList){
		ArrayList<MyTimeSlot> resultTimeSlotList = new ArrayList<MyTimeSlot>();
		MyTimeSlot lastSlot = new MyTimeSlot(0, 0);
		for (MyEvent ev : evList) {
			long start = lastSlot.getStart();
			long end = lastSlot.getEnd();
			long evS = Long.valueOf(ev.getDtstart());
			long evE = Long.valueOf(ev.getDtend());
			if (start <= evS && end >= evE) { //lastSlot bigger
				
			} else if (start >= evS && end <= evE) { // ev bigger
				lastSlot.setStart(evS);
				lastSlot.setEnd(evE);
			} else if (start > evE) { // disjoint ev first
				Log.d("logic bug", "lastSlot appears after a timeslot: timeSlotList is not in order");
			} else if (evS > end) { // disjoint lastSlot first
				if (start != 0 && end!= 0)
					resultTimeSlotList.add(lastSlot);
				lastSlot = new MyTimeSlot(evS, evE);
			} else if (start > evS) { // joint ev first 
				lastSlot.setStart(evS);
			} else { // joint lastSlot first
				lastSlot.setEnd(evE);
			}
		}
		resultTimeSlotList.add(lastSlot);
		return resultTimeSlotList;
	}
	
}
