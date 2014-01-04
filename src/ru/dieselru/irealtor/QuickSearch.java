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
import android.sax.TextElementListener;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class QuickSearch extends Activity {

	final String LOG_TAG = "myLogs";
	
	private static String DB_PATH =  Environment.getDataDirectory().toString(); //"/data/data/YOUR_PACKAGE/databases/"
    private static String DB_NAME = "irealtor.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
   
    private Spinner spinnerCity;
    private Spinner spinnerType;
    private Spinner spinnerRegion;
    private Spinner spinnerStatus;
    private EditText editRoom; 
    private EditText editCost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quicks_search);

		//Наш ключевой хелпер
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        //Все, база открыта!
        
        editRoom = (EditText) findViewById(R.id.editRoom);
        editCost = (EditText) findViewById(R.id.editCost);
        
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
        
        spinnerCity = (Spinner) findViewById(R.id.editCity);
        spinnerCity.setAdapter(adapter);
        
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
        
        spinnerType = (Spinner) findViewById(R.id.editType);
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
        adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinnerRegion = (Spinner) findViewById(R.id.editRegion);
        spinnerRegion.setAdapter(adapterRegion);
        
        ArrayList arrStatus = new ArrayList<String>();
		//Cursor friendCursor = database.query(_table, new String[] {FRIEND_ID, FRIEND_NAME},
		//				     null, null,null,null, FRIEND_NAME);
		Cursor _cursorStatus = database.query("Status", new String[] {"_id", "name"}, null, null,null,null, "name");
		
		_cursorStatus.moveToFirst();
		if(!_cursorStatus.isAfterLast()) {
			do {
				String name = _cursorStatus.getString(1);
				arrStatus.add(name);
			} while (_cursorStatus.moveToNext());
		}
		_cursorStatus.close();
		
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrStatus);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinnerStatus = (Spinner) findViewById(R.id.editstrStatus);
        spinnerStatus.setAdapter(adapterStatus);
        //database.close();
        
        arrCity.clear();
        arrRegion.clear();
        arrStatus.clear();
        arrType.clear();
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
    	SearchResult.strRoom = editRoom.getText().toString();
    	SearchResult.strCost = editCost.getText().toString();
    	SearchResult.strCity = spinnerCity.getSelectedItem().toString();
    	SearchResult.strStatus = spinnerStatus.getSelectedItem().toString();
    	SearchResult.strRegion = spinnerRegion.getSelectedItem().toString();
    	SearchResult.strType = spinnerType.getSelectedItem().toString();
    	
    	Intent intent = new Intent(QuickSearch.this, SearchResult.class);
        startActivity(intent);
    }
}
