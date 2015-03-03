package sg.edu.ntu.sce.fyp.calevent.model.myclass;

public class MyTimeSlot {

	private long start;
	private long end;
	
	public MyTimeSlot(long s, long e){
		start = s;
		end = e;
	}

	public MyTimeSlot() {
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
	
	
}
