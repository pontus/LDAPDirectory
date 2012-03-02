package net.soua.ldapddirectory;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class LDAPSync extends Activity {

	class onSaveClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			onSave(v);
		}
	}

	class onConnectClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			onConnect(v);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		String a = i.getAction();
			
		Button b = (Button) findViewById(R.id.exitButton);
		
		if (a.equals(R.string.prefAction))
		{
			b.setText(R.string.saveButton);
			b.setOnClickListener(new onSaveClickListener());
						
		}
		else if(a.equals(R.string.createAction))
		{	
			b.setText(R.string.createAccount);
			b.setOnClickListener(new onConnectClickListener());
		}
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Log.e("LDAPSync", "onCreate");

	}
	
	
	public void onSave(View v)
	{
		
		
	}

	public void onConnect(View v) {

		String username = "pontus";
		String password = "hemligt";

		Account account = new Account(username,
				getString(R.string.ACCOUNT_TYPE));
		AccountManager am = AccountManager.get(this);
		boolean accountCreated = am.addAccountExplicitly(account, password,
				null);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (accountCreated) { // Pass the new account back to the account
									// manager
				AccountAuthenticatorResponse response = extras
						.getParcelable(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);
				Bundle result = new Bundle();
				result.putString(AccountManager.KEY_ACCOUNT_NAME, username);
				result.putString(AccountManager.KEY_ACCOUNT_TYPE,
						getString(R.string.ACCOUNT_TYPE));
				response.onResult(result);
			}
			finish();
		}
	}
}
