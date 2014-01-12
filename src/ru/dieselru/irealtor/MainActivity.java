package ru.dieselru.irealtor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import ru.dieselru.irealtor.R;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		    	Toast.makeText(getApplicationContext(),"Update procedure.", Toast.LENGTH_SHORT).show();
		        
		    	break;
		    case R.id.buttonSettings:
		    	Intent intentPref = new Intent(MainActivity.this, PrefActivity.class);
	            startActivity(intentPref);
		        
	            break;
	        case R.id.buttonExit:
	          // кнопка Cancel
	        	System.exit(0);
	        	
	        	break;
        }
    }
    
}
