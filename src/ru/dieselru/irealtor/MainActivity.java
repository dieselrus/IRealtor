package ru.dieselru.irealtor;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import ru.dieselru.irealtor.R;

public class MainActivity extends Activity {
	
	SharedPreferences sp;
	
	public static ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progressDialog = new ProgressDialog(this);
		
		sp = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		//return true;
		
	    MenuItem mi = menu.add(0, 1, 0, "Настройки");
	    mi.setIntent(new Intent(this, PrefActivity.class));
	    mi = menu.add(0, 1, 0, "О программе");
	    //mi.setIntent(new Intent(this, Abaut.class));
	    return super.onCreateOptionsMenu(menu);
	}

	// Обработка нажатия
    public void onClick(View v){
//            Toast.makeText(getApplicationContext(), "Click view id " + v.getId() + " " + viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
//            //Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
//            roomNum = viewPager.getCurrentItem() + 1;
//            //tableNum = (int) v.getTag();
//            tableNum = Integer.valueOf((String) v.getTag());
//            
//            sendData("|ReadTableData|" + roomNum + "-"+ tableNum + "|\n");
            
//            Intent intent = new Intent(MainActivity.this, ViewStatus.class);
//        startActivity(intent);
    	
    	

        switch (v.getId()) {
	        case R.id.buttonQuickSearch:
	        	Intent intent = new Intent(MainActivity.this, QuickSearch.class);
	            startActivity(intent);
	            
	            break;
	        case R.id.buttonDeepSearch:
	        	Toast.makeText(getApplicationContext(),"Deep search procedure.", Toast.LENGTH_SHORT).show();
	            
	        	break;
		    case R.id.buttonUpdate:
		    	//Toast.makeText(getApplicationContext(),"Update procedure.", Toast.LENGTH_SHORT).show();
		    	TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
				String uid = mngr.getDeviceId();
		    	//new LoadDBATask().execute("http://realtor38.ru/connect.php/?", "auth_submit=&imei=" + uid + "&sn=" + android.os.Build.SERIAL + "&companiid=" + sp.getString("company_id", ""));
		    	new LoadDBATask().execute("http://realtor38.ru/connect.php?", "imei=352249052003131&sn=8768285&companiid=users");
		    	break;
//		    case R.id.buttonSettings:
//		    	Intent intentPref = new Intent(MainActivity.this, PrefActivity.class);
//	            startActivity(intentPref);
//		        
//	            break;
	        case R.id.buttonExit:
	          // кнопка Cancel
	        	System.exit(0);
	        	
	        	break;
        }
    }
    
    public static void setProgres(Integer ... values){
    	progressDialog.setProgress((int) ((values[0] / (float) values[1]) * 100));
    }
    
    public static void openProgres(){
    	progressDialog.setMessage("Downloading ...");
		progressDialog.setCancelable(false);
		progressDialog.setMax(100);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		progressDialog.show();
    }
    
    public static void closeProgres(){
    	progressDialog.hide();
    }
    
//    private static final int MSG_SHOW_TOAST = 1;
//
//    private static Handler messageHandler = new Handler() {
//           public void handleMessage(android.os.Message msg) {
//               if (msg.what == MSG_SHOW_TOAST) {
//                   String message = (String)msg.obj;
//                   Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
//               }
//           }
//    };
//    
//    public static void setToast(String _messange){
//    	//Toast.makeText(_context, _messange, Toast.LENGTH_SHORT).show();
//    	Message msg = new Message();
//    	msg.what = MSG_SHOW_TOAST;
//    	msg.obj = "Message to show";
//    	messageHandler.sendMessage(msg);
//    }

}
