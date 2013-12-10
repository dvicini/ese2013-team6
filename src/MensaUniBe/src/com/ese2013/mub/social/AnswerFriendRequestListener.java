package com.ese2013.mub.social;

import android.view.View;
import android.view.View.OnClickListener;

import com.ese2013.mub.social.model.FriendRequest;
import com.ese2013.mub.social.model.SocialManager;
/**
 * 
 * Listener for accepting and declining friendReqests
 *
 */
public class AnswerFriendRequestListener implements OnClickListener {
	private FriendRequest friendRequest;
	private boolean accept;

	public AnswerFriendRequestListener(FriendRequest friendRequest, boolean accept) {
		this.friendRequest = friendRequest;
		this.accept = accept;
	}

	@Override
	public void onClick(View v) {
		SocialManager.getInstance().answerFriendRequest(friendRequest, accept);
	}
}

