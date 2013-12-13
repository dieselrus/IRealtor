package ru.dieselru.irealtor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class Search extends Activity {
	
	private String arrayTypes[], arrayCitys[], arrayRegions[], arrayStreets[], arrayRooms[];

//	DbOpenHelper dbHelper;
	MyDatabase db;
	String LOG_TAG = "sqlite";
	private Cursor _cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
	
		
		// создаем объект для создания и управления версиями БД
		db = new MyDatabase(this);
		_cursor = db.getTypes();
		
		ArrayList strings = new ArrayList();
		
		for(_cursor.moveToFirst(); !_cursor.isAfterLast(); _cursor.moveToNext()) {
			   String mTitleRaw = _cursor.getString(_cursor.getColumnIndex("Name"));
			   strings.add(mTitleRaw);
			}
		String[] arrayTypes = (String[]) strings.toArray(new String[strings.size()]);
		
		strings.clear();
		//_cursor.close();
		
		Spinner spinnerType = (Spinner) findViewById(R.id.editType);
		ArrayAdapter adapterType = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayTypes);
		//ArrayAdapter adapterType = new ArrayAdapter(this, android.R.layout.simple_spinner_item, adapter);
		spinnerType.setAdapter(adapterType);
	
		
		_cursor = db.getCitys();
		
		for(_cursor.moveToFirst(); !_cursor.isAfterLast(); _cursor.moveToNext()) {
			   String mTitleRaw = _cursor.getString(_cursor.getColumnIndex("Name"));
			   strings.add(mTitleRaw);
			}
		String[] arrayCitys = (String[]) strings.toArray(new String[strings.size()]);
		
		Spinner spinnerCity = (Spinner) findViewById(R.id.editCity);
		ArrayAdapter adapterCity = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayCitys);
		spinnerCity.setAdapter(adapterCity);
		
		strings.clear();

		_cursor = db.getRegions();
		
		for(_cursor.moveToFirst(); !_cursor.isAfterLast(); _cursor.moveToNext()) {
			   String mTitleRaw = _cursor.getString(_cursor.getColumnIndex("Name"));
			   strings.add(mTitleRaw);
			}
		String[] arrayRegions = (String[]) strings.toArray(new String[strings.size()]);
		
		Spinner spinnerRegion = (Spinner) findViewById(R.id.editRegion);
		ArrayAdapter adapterRegion = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayRegions);
		spinnerRegion.setAdapter(adapterRegion);
		
		strings.clear();
/*		
		arrayStreets=new String[4];
		arrayStreets[0]="ул. Кожова";
		arrayStreets[1]="мрн. Университетский";
		arrayStreets[2]="пер. Пионерский";
		arrayStreets[3]="сад. Кооператор";
		
		Spinner spinnerStreets = (Spinner) findViewById(R.id.editStreet);
		ArrayAdapter adapterStreets = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayStreets);
		spinnerStreets.setAdapter(adapterStreets);
		
		arrayRooms=new String[5];
		arrayRooms[0]="1";
		arrayRooms[1]="2";
		arrayRooms[2]="3";
		arrayRooms[3]="4";
		arrayRooms[4]="5";
		
		Spinner spinnerRooms = (Spinner) findViewById(R.id.editRooms);
		ArrayAdapter adapterRooms = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayRooms);
		spinnerRooms.setAdapter(adapterRooms);
		
		sqlRead("");
*/		
//		dbHelper.close();
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
            
    	
//    	Intent intent = new Intent(MainActivity.this, EditStatus.class);
//        startActivity(intent);

    }
    
    String[] sqlRead(String _table){
    	String array[] = null;
    	
        DbOpenHelper dbOpenHelper = new DbOpenHelper(Search.this);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put(DbOpenHelper.LOGIN,loginEditText.getText().toString());
//        cv.put(DbOpenHelper.PASSW,passEditText.getText().toString());
        db.insert(DbOpenHelper.TABLE_NAME,null,cv);
        db.close();
    	
    	return array ;
    }

}
