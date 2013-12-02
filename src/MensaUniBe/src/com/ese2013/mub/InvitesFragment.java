package com.ese2013.mub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ese2013.mub.model.Day;
import com.ese2013.mub.model.Mensa;
import com.ese2013.mub.model.Model;
import com.ese2013.mub.social.Invitation;
import com.ese2013.mub.social.LoginService;
import com.ese2013.mub.util.parseDatabase.OnlineDBHandler;
import com.parse.ParseException;

public class InvitesFragment extends Fragment implements IFragmentsInvitation {
	
	private ListView invitedList;
	private InvitesListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		adapter = new InvitesListAdapter();
		View view = inflater.inflate(R.layout.fragment_invites, null);
		invitedList = (ListView) view.findViewById(R.id.invites_list);
		View emptyView = inflater.inflate(R.layout.invititation_empty_view, null);
		TextView showMessage = (TextView)emptyView.findViewById(R.id.show_message);
		
		showMessage.setText((LoginService.isLoggedIn())? R.string.no_invites : R.string.not_loged_in);
		
		invitedList.setEmptyView(emptyView);
		invitedList.setAdapter(adapter);
		return view;
	}

	@Override
	public Fragment getInstance() {
		return this;
	}

	class InvitesListAdapter extends BaseAdapter {

		private LayoutInflater inflater;
		private List<Invitation> invitations = new ArrayList<Invitation>();

		public InvitesListAdapter() {
			try {
				if(LoginService.isLoggedIn())
				invitations = new OnlineDBHandler().getSentInvitations(LoginService.getLoggedInUser());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null)
				inflater = (LayoutInflater) getActivity().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.invites_entry_layout, null);
			Invitation invite = invitations.get(position);

			setUpFromTextView(view, invite);
			setUpWhereTextView(view, invite);
			setUpWhenTextView(view, invite);
			
			setUpShowInvitedButton(view);
			
			return view;
		}

		private void setUpShowInvitedButton(View view) {
			ImageButton showInvitedButton = (ImageButton) view.findViewById(R.id.show_invited);
			showInvitedButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
		}

		private void setUpWhenTextView(View view, Invitation invite) {
			TextView whenTextView = (TextView) view
					.findViewById(R.id.when_text_field);
			Day day = new Day(invite.getTime());
			SimpleDateFormat timeOfDay = new SimpleDateFormat("HH:mm",
					Locale.getDefault());
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM",
					Locale.getDefault());
			if (day.equals(Day.today()))
				whenTextView.setText(R.string.today
						+ timeOfDay.format(invite.getTime()));
			else
				whenTextView.setText(R.string.today
						+ sdf.format(invite.getTime()));
		}

		private void setUpFromTextView(View view, Invitation invite) {
			TextView fromWhomTextView = (TextView) view
					.findViewById(R.id.from_whom_text_field);
			fromWhomTextView.setText(invite.getFrom().getNick());
			
		}
		private void setUpWhereTextView(View view, Invitation invite) {
			TextView whereTextView = (TextView) view
					.findViewById(R.id.where_text_field);
			Mensa mensa = null;
			for (Mensa m : Model.getInstance().getMensas()) {
				if (m.getId() == invite.getMensa())
					mensa = m;
			}
			whereTextView.setText(mensa.getName());
		}

		@Override
		public int getCount() {
			return invitations.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	}
}
