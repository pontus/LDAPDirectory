package net.soua.ldapddirectory;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;

import android.os.Bundle;

import android.app.Activity;

public class LDAPSync extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		String username = "pontus";
		String password = "hemligt";

	     super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        
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
