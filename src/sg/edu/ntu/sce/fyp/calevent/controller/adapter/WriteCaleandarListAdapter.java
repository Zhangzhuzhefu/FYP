package sg.edu.ntu.sce.fyp.calevent.controller.adapter;

import java.util.List;

import sg.edu.ntu.sce.fyp.calevent.R;
import sg.edu.ntu.sce.fyp.calevent.activity.MainActivity;
import sg.edu.ntu.sce.fyp.calevent.global.Settings;
import sg.edu.ntu.sce.fyp.calevent.model.myclass.MyCalendar;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class WriteCaleandarListAdapter extends ArrayAdapter<MyCalendar> {

	private final List<MyCalendar> calendarList;
	private final MainActivity activity;

	public WriteCaleandarListAdapter(Activity act, List<MyCalendar> list) {
		super(act, R.layout.calendar_rowlayout_radio, list);
		this.activity = (MainActivity) act;
		this.calendarList = list;
	}

	static class ViewHolder {
		protected TextView text_dsply;
		protected TextView text_owner;
		protected RadioButton radio;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = activity.getLayoutInflater();
			view = inflator.inflate(R.layout.calendar_rowlayout_radio, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text_dsply = (TextView) view.findViewById(R.id.label_display_name);
			viewHolder.text_owner = (TextView) view.findViewById(R.id.label_owner_account);
			viewHolder.radio = (RadioButton) view.findViewById(R.id.radioButton);
			viewHolder.radio.setOnCheckedChangeListener(
					new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
							MyCalendar element = (MyCalendar) viewHolder.radio.getTag();
							if (isChecked) {
								Settings.getInstance().setWriteCalendarId(Long.valueOf(element.get_id()));
							}
							
							for (int i = 0; i < parent.getChildCount(); i++) {
								ViewHolder vh = (ViewHolder) parent.getChildAt(i).getTag();
								MyCalendar myCal = (MyCalendar) vh.radio.getTag();
								vh.radio.setChecked(
										(Long.valueOf(myCal.get_id())
										- Settings.getInstance().getWriteCalendarId() == 0));
							}
						}
					});
			view.setTag(viewHolder);
			viewHolder.radio.setTag(calendarList.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).radio.setTag(calendarList
					.get(position));
		}

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text_dsply.setText(calendarList.get(position)
				.getCalendar_display_name());
		holder.text_owner.setText(calendarList.get(position).getAccount_name());
		holder.radio.setChecked((Long.valueOf(((MyCalendar) holder.radio
				.getTag()).get_id())
				- Settings.getInstance().getWriteCalendarId() == 0));
		return view;
	}
}