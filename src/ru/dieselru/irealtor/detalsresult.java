package ru.dieselru.irealtor;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.os.Environment;
import android.widget.ArrayAdapter;

public class detalsresult extends Activity {
	public static String strCity = "";
	public static String strRegion = "";
	public static String  strType = "";
	public static String strDeal = "";
	public static String strRoom = "";
	public static String strCost = "";

	//private static String DB_PATH =  Environment.getDataDirectory().toString(); //"/data/data/YOUR_PACKAGE/databases/"
    private static String DB_NAME = "irealtor.db";
    //private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalsviewrowlayout);
		
		//Наш ключевой хелпер
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        //Все, база открыта!
        
        // адаптер
        ArrayList arrObject = new ArrayList<String>();
		//Cursor friendCursor = database.query(_table, new String[] {FRIEND_ID, FRIEND_NAME},
		//				     null, null,null,null, FRIEND_NAME);
		Cursor _cursorObject = database.query("data", new String[] {"_id", "type", "city", "region", "street", "home", "room", "floor", "storey", "material", "gross_area", "living_space", "kitchen_area", "cost", "comment"}, null, null,null,null, "cost");
		
		// Использование собственного шаблона
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.detalsviewrowlayout, R.id.label, values);
        //setListAdapter(adapter);
	}

	//private void setListAdapter(ArrayAdapter<String> adapter) {
		
	//}

}
