package ru.dieselru.irealtor;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;

public class SearchResult extends Activity {
	public static String strCity = "";
	public static String strRegion = "";
	public static String  strType = "";
	public static String strDeal = "";
	public static String strRoom = "";
	public static String strCost = "";

	private static String DB_PATH =  Environment.getDataDirectory().toString(); //"/data/data/YOUR_PACKAGE/databases/"
    private static String DB_NAME = "irealtor.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    
    ArrayList<RealtyObject> RealtyObjects = new ArrayList<RealtyObject>();
    BoxAdapter boxAdapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultlayout);
		
		//Наш ключевой хелпер
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        //Все, база открыта!
        
		//Cursor friendCursor = database.query(_table, new String[] {FRIEND_ID, FRIEND_NAME},
		//				     null, null,null,null, FRIEND_NAME);
		Cursor _cursorRealty = database.query("data", new String[] {"_id", "street", "room", "cost"}, null, null,null,null, "cost");
		
		_cursorRealty.moveToFirst();
		if(!_cursorRealty.isAfterLast()) {
			do {
				//String name = _cursorRealty.getString(1);
				RealtyObjects.add(new RealtyObject(_cursorRealty.getString(1), " омнат: " + _cursorRealty.getString(2), "÷ена: " + _cursorRealty.getString(3) + " руб."));
			} while (_cursorRealty.moveToNext());
		}
		_cursorRealty.close();
		
		// создаем адаптер
	    boxAdapter = new BoxAdapter(this, RealtyObjects);

	    // настраиваем список
	    ListView lvMain = (ListView) findViewById(R.id.lvMain);
	    lvMain.setAdapter(boxAdapter);
	    
		// Использование собственного шаблона
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.detalsviewrowlayout, R.id.label, values);
        //setListAdapter(adapter);
	    
	    database.close();
	}

}
