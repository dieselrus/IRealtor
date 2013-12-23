package ru.dieselru.irealtor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class QuickSearch extends Activity {

	final String LOG_TAG = "myLogs";
	
	private static String DB_PATH =  Environment.getDataDirectory().toString(); //"/data/data/YOUR_PACKAGE/databases/"
    private static String DB_NAME = "irealtor.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
   


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quicks_search);

		//Наш ключевой хелпер
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        //Все, база открыта!
        
        // адаптер
        ArrayList arrCity = new ArrayList<String>();
		//Cursor friendCursor = database.query(_table, new String[] {FRIEND_ID, FRIEND_NAME},
		//				     null, null,null,null, FRIEND_NAME);
		Cursor _cursorCity = database.query("City", new String[] {"_id", "name"}, null, null,null,null, "name");
		
		_cursorCity.moveToFirst();
		if(!_cursorCity.isAfterLast()) {
			do {
				String name = _cursorCity.getString(1);
				arrCity.add(name);
			} while (_cursorCity.moveToNext());
		}
		_cursorCity.close();
		
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrCity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        Spinner spinner = (Spinner) findViewById(R.id.editCity);
        spinner.setAdapter(adapter);
        
        ArrayList arrType = new ArrayList<String>();
		//Cursor friendCursor = database.query(_table, new String[] {FRIEND_ID, FRIEND_NAME},
		//				     null, null,null,null, FRIEND_NAME);
		Cursor _cursorType = database.query("Types", new String[] {"_id", "name"}, null, null,null,null, "name");
		
		_cursorType.moveToFirst();
		if(!_cursorType.isAfterLast()) {
			do {
				String name = _cursorType.getString(1);
				arrType.add(name);
			} while (_cursorType.moveToNext());
		}
		_cursorType.close();
		
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrType);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        Spinner spinnerType = (Spinner) findViewById(R.id.editType);
        spinnerType.setAdapter(adapterType);
        
        ArrayList arrRegion = new ArrayList<String>();
		//Cursor friendCursor = database.query(_table, new String[] {FRIEND_ID, FRIEND_NAME},
		//				     null, null,null,null, FRIEND_NAME);
		Cursor _cursorRegion = database.query("Regions", new String[] {"_id", "name"}, null, null,null,null, "name");
		
		_cursorRegion.moveToFirst();
		if(!_cursorRegion.isAfterLast()) {
			do {
				String name = _cursorRegion.getString(1);
				arrRegion.add(name);
			} while (_cursorRegion.moveToNext());
		}
		_cursorRegion.close();
		
        ArrayAdapter<String> adapterRegion = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrRegion);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        Spinner spinnerRegion = (Spinner) findViewById(R.id.editRegion);
        spinnerRegion.setAdapter(adapterRegion);
        //database.close();
	}

	@Override
	protected void onDestroy() {
		database.close();
		super.onDestroy();		
	}

	@Override
	protected void onPause() {
		database.close();
		super.onPause();
	}
	
	// ќбработка нажати€
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
    	
    	Intent intent = new Intent(QuickSearch.this, SearchResult.class);
        startActivity(intent);
    }
}
