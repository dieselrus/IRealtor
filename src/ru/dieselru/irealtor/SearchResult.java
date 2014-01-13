package ru.dieselru.irealtor;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
//import android.os.Environment;
import android.widget.ListView;

public class SearchResult extends Activity {
	public static String strCity = null;
	public static String strRegion = null;
	public static String  strType = null;
	public static String strStatus = null;
	public static String strRoom = null;
	public static String strCost = null;

	//private static String DB_PATH =  Environment.getDataDirectory().toString(); //"/data/data/YOUR_PACKAGE/databases/"
    private static String DB_NAME = "irealtor.db";
    //private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    
    ArrayList<RealtyObject> RealtyObjects = new ArrayList<RealtyObject>();
    BoxAdapter boxAdapter;
    ListView lvMain;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultlayout);
		
		//Наш ключевой хелпер
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        //Все, база открыта!
        
		//Cursor friendCursor = database.query(_table, new String[] {FRIEND_ID, FRIEND_NAME},
		//				     null, null,null,null, FRIEND_NAME);
        //String[] _param = null;
        //String _query = null;
        
        ArrayList<String> _arrParam = new ArrayList<String>();
        //String[] _param = null;
        String _query = null;
        
        // City
        _arrParam.add(strCity);
        _query = "city = ?";
        
        // Region
        if(!"*".equals(strRegion)){
        	_arrParam.add(strRegion);
        	_query += " AND region = ?";
        }
        
        // Type
        _arrParam.add(strType);
        _query += " AND type = ?";
        
        // Status
        _arrParam.add(strStatus);
        _query += " AND Status = ?";
        
        // Room
        if(!"*".equals(strRoom)){
        	_arrParam.add(strRoom);
        	_query += " AND room = ?";
        }
        
        // Cost
        if(!"*".equals(strCost)){
        	_arrParam.add(strCost);
        	_query += " AND cost <= ?";
        }
        
        
//		if(!"*".equals(strRoom) && !"*".equals(strCost)){
//			//_param = new String [] {strCity, strRegion, strType, strStatus,strRoom, strCost};
//			_query = "city = ? AND region = ? AND type = ? AND Status = ? AND room = ? AND cost <= ?";
//		}else if ("*".equals(strRoom) && !"*".equals(strCost)) {
//			//_param = new String [] {strCity, strRegion, strType, strStatus, strCost};
//			_query = "city = ? AND region = ? AND type = ? AND Status = ? AND cost <= ?";
//		}else if (!"*".equals(strRoom) && "*".equals(strCost)) {
//			//_param = new String [] {strCity, strRegion, strType, strStatus, strRoom};
//			_query = "city = ? AND region = ? AND type = ? AND Status = ? AND room = ?";
//		}else if ("*".equals(strRoom) && "*".equals(strCost)) {
//			//_param = new String [] {strCity, strRegion, strType, strStatus};
//			_query = "city = ? AND region = ? AND type = ? AND Status = ?";
//		}
		

		
		String[] _param = new String[_arrParam.size()];
		_arrParam.toArray(_param);
		
		//Cursor _cursorRealty = database.query("data", new String[] {"_id", "street", "room", "cost"}, "city = ? AND region = ? AND type = ? AND Status = ? AND room = ? AND cost <= ?", new String[] {strCity, strRegion, strType, strRoom, strStatus, strCost},null,null, "cost");
		Cursor _cursorRealty = database.query("data", new String[] {"_id", "street", "room", "cost"}, _query, _param, null, null, "cost");
				
		_cursorRealty.moveToFirst();
		if(!_cursorRealty.isAfterLast()) {
			do {
				//String name = _cursorRealty.getString(1);
				RealtyObjects.add(new RealtyObject(_cursorRealty.getString(1), getString(R.string.Rooms) + _cursorRealty.getString(2), getString(R.string.Cost) + _cursorRealty.getString(3) + " руб.", _cursorRealty.getString(0)));
			} while (_cursorRealty.moveToNext());
		}
		_cursorRealty.close();
		
		// создаем адаптер
	    boxAdapter = new BoxAdapter(this, RealtyObjects);

	    // настраиваем список
	    lvMain = (ListView) findViewById(R.id.lvMain);
	    lvMain.setAdapter(boxAdapter);
	    
		// Использование собственного шаблона
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.detalsviewrowlayout, R.id.label, values);
        //setListAdapter(adapter);
	    
	    database.close();
	    
	    lvMain.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(getApplicationContext(),view.getTag() + ""+position, Toast.LENGTH_SHORT).show();
				//View _Child = ((ViewGroup)view).getChildAt(0);
				//Toast.makeText(getApplicationContext(), ((ViewGroup)view).getChildAt(0).getTag().toString(), Toast.LENGTH_SHORT).show();
				DetalsResult._id = ((ViewGroup)view).getChildAt(0).getTag().toString();
				Intent intent = new Intent(SearchResult.this, DetalsResult.class);
		        startActivity(intent);
			}
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                    long id) {
//                Intent intent = new Intent(MainActivity.this, SendMessage.class);
//                String message = "abc";
//                intent.putExtra(EXTRA_MESSAGE, message);
//                startActivity(intent);
//            }
        });
	}
}
