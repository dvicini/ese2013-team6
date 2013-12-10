package com.ese2013.mub.social;

import android.view.View;
import android.view.View.OnClickListener;

import com.ese2013.mub.social.model.Invitation;
import com.ese2013.mub.social.model.SocialManager;

/**
 * Listener for the Invite answerButtons, works for accept and decline
 * InvitationRequests
 * 
 */
public class AnswerInviteListener implements OnClickListener {
	private Invitation invite;
	private boolean isAccepted;

	public AnswerInviteListener(Invitation invite, boolean isAccepted) {
		this.invite = invite;
		this.isAccepted = isAccepted;
	}

	@Override
	public void onClick(View v) {
		if (isAccepted)
			SocialManager.getInstance().answerInvitation(invite, Invitation.Response.ACCEPTED);
		else
			SocialManager.getInstance().answerInvitation(invite, Invitation.Response.DECLINED);
	}
}
