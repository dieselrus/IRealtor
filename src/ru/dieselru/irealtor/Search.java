package ru.dieselru.irealtor;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Search extends Activity {
	
	private String arrayTypes[], arrayCitys[], arrayRegions[], arrayStreets[], arrayRooms[];


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
	
		arrayTypes=new String[4];
		arrayTypes[0]="Квартира";
		arrayTypes[1]="Катедж";
		arrayTypes[2]="Дом";
		arrayTypes[3]="Участок";
		
		Spinner spinnerType = (Spinner) findViewById(R.id.editType);
		ArrayAdapter adapterType = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayTypes);
		spinnerType.setAdapter(adapterType);
		
		arrayCitys=new String[3];
		arrayCitys[0]="Иркутск";
		arrayCitys[1]="Ангарск";
		arrayCitys[2]="Шелехов";
		
		Spinner spinnerCity = (Spinner) findViewById(R.id.editCity);
		ArrayAdapter adapterCity = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayCitys);
		spinnerCity.setAdapter(adapterCity);
		
		arrayRegions=new String[4];
		arrayRegions[0]="Правобережный";
		arrayRegions[1]="Ленинский";
		arrayRegions[2]="Свердловский";
		arrayRegions[3]="Октябрьский";
		
		Spinner spinnerRegion = (Spinner) findViewById(R.id.editRegion);
		ArrayAdapter adapterRegion = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayRegions);
		spinnerRegion.setAdapter(adapterRegion);
		
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
