package sg.edu.ntu.sce.fyp.calevent.controller.adapter;

import java.util.List;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.model.DataManager;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyCalendar;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ReadCaleandarListAdapter extends ArrayAdapter<MyCalendar> {

	private final List<MyCalendar> calendarList;
	private final MainActivity activity;

	public ReadCaleandarListAdapter(Activity act, List<MyCalendar> list) {
		super(act, R.layout.calendar_rowlayout, list);
		this.activity = (MainActivity) act;
		this.calendarList = list;
	}

	static class ViewHolder {
		protected TextView text_dsply;
		protected TextView text_owner;
		protected CheckBox checkbox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = activity.getLayoutInflater();
			view = inflator.inflate(R.layout.calendar_rowlayout, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text_dsply = (TextView) view.findViewById(R.id.label_display_name);
			viewHolder.text_owner = (TextView) view.findViewById(R.id.label_owner_account);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							MyCalendar element = (MyCalendar) viewHolder.checkbox.getTag();
							element.setSelected(buttonView.isChecked());
							DataManager.getInstance().updateSelectedCalendarsAndIDs();
							activity.caleventHelper.getMyEventsAndUpdateView();
						}
					});
			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(calendarList.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(calendarList.get(position));
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text_dsply.setText(calendarList.get(position).getCalendar_display_name());
		holder.text_owner.setText(calendarList.get(position).getAccount_name());
		holder.checkbox.setChecked(calendarList.get(position).isSelected());
		return view;
	}
}