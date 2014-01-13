package ru.dieselru.irealtor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PrefActivity extends Activity {

	TextView tvImai;
	TextView tvSN;
	EditText editCompanyID;
	Button btnCancel;
	Button btnOK;
	
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pref_activity);
		
		tvImai = (TextView) findViewById(R.id.tvImai);
		tvSN = (TextView) findViewById(R.id.tvSN);
		editCompanyID = (EditText) findViewById(R.id.editCompanyID);
		
		btnCancel = (Button) findViewById(R.id.buttonCancel);
		btnOK = (Button) findViewById(R.id.buttonOK);
		
		sp = PreferenceManager.getDefaultSharedPreferences(this);

		editCompanyID.setText(sp.getString("company_id", ""));
		
		tvImai.setText("");
		tvSN.setText("");
		
		TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		String uid = mngr.getDeviceId();
		String PhoneModel = android.os.Build.SERIAL;
		//String android_id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID); 
		if(uid != null)
			tvImai.setText(uid);
		if(PhoneModel != null)
			tvSN.setText(PhoneModel);
		
	}
	
	@Override
	protected void onResume() {
	    //String strCompanyID = sp.getString("company_id", "");
	    editCompanyID.setText(sp.getString("company_id", ""));
	    super.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		Editor _editor = sp.edit();
		_editor.putString("company_id", editCompanyID.getText().toString());
		super.onDestroy();
	}

	@Override
	public void finish() {
		
		Editor _editor = sp.edit();
		_editor.putString("company_id", editCompanyID.getText().toString());
		_editor.apply();
		
		super.finish();
	}
	
	// Обработка нажатия
    public void onClick(View v){
        switch (v.getId()) {
	        case R.id.buttonOK:
	        	Editor _editor = sp.edit();
	    		_editor.putString("company_id", editCompanyID.getText().toString());
	    		_editor.apply();
	            
	            break;

	        case R.id.buttonCancel:
	          // кнопка Cancel
	        	//System.exit(0);
	        	finish();
	        	break;
        }
    }

}
