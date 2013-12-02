package com.ese2013.mub.util;

import com.ese2013.mub.social.LoginService;
import com.parse.ParseException;

public class LoginTask extends AbstractAsyncTask<String, Void, Void> {

	private LoginTaskCallback callback;

	public LoginTask(LoginTaskCallback callback) {
		this.callback = callback;
	}

	@Override
	protected Void doInBackground(String... email) {
		try {
			LoginService.login(email[0]);
		} catch (ParseException e) {
			setException(e);
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void v) {
		super.onPostExecute(v);
		callback.onTaskFinished(this);
	}
}
