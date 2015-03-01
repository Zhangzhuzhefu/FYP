package sg.edu.ntu.sce.fyp.calevent.model.myclass;

public class MyCalendar {
	
	private String _id;
	private String name;
	private String account_name;
	private String account_type;
	private String owner_account;
	private String calendar_display_name;
	private String color;
	private boolean visible;
	private boolean selected;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getOwner_account() {
		return owner_account;
	}
	public void setOwner_account(String owner_account) {
		this.owner_account = owner_account;
	}
	public String getCalendar_display_name() {
		return calendar_display_name;
	}
	public void setCalendar_display_name(String calendar_display_name) {
		this.calendar_display_name = calendar_display_name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

}
