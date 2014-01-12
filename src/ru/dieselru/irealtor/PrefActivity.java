package ru.dieselru.irealtor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.widget.EditText;
import android.widget.TextView;

public class PrefActivity extends Activity {

	TextView tvImai;
	TextView tvSN;
	EditText editCompanyID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pref_activity);
		
		tvImai = (TextView) findViewById(R.id.tvImai);
		tvSN = (TextView) findViewById(R.id.tvSN);
		editCompanyID = (EditText) findViewById(R.id.editCompanyID);
		
		TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		String uid = mngr.getDeviceId();
		String PhoneModel = android.os.Build.MODEL;
		String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID); 
		if(uid != null)
			tvImai.setText(uid);
		
		tvSN.setText(PhoneModel);
		
	}
}
