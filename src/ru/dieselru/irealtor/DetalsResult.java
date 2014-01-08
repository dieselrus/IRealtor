package ru.dieselru.irealtor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
//import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DetalsResult extends Activity {
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
    public static String _id = "";
    
    ArrayList<DetalsResultObject> DetalsResultObject = new ArrayList<DetalsResultObject>();
    BoxAdapterDetalsResult boxAdapter;
    ListView lvMain;
    private String strPhone = "";
    
//    TextView tvStreet;
//    TextView tvRooms;
//    TextView tvFloor;
//    TextView tvStorrey;
//    TextView tvGrossArea;
//    TextView tvLivingSpace;
//    TextView tvKitchenArea;
//    TextView tvPhone;
//    TextView tvCost;
//    TextView tvMaterial;
//    TextView tvComment;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detals_result_layout);
		
//		tvStreet = (TextView) findViewById(R.id.editStreet);
//		tvRooms = (TextView) findViewById(R.id.editRooms);
//		tvFloor = (TextView) findViewById(R.id.editFloor);
//		tvStorrey = (TextView) findViewById(R.id.editStorrey);
//		tvGrossArea = (TextView) findViewById(R.id.editGrossArea);
//		tvLivingSpace = (TextView) findViewById(R.id.editLivingSpace);
//		tvKitchenArea = (TextView) findViewById(R.id.editKitchenArea);
//		tvPhone = (TextView) findViewById(R.id.editPhone);
//		tvCost = (TextView) findViewById(R.id.editCost);
//		tvMaterial = (TextView) findViewById(R.id.editMaterial);
//		tvComment = (TextView) findViewById(R.id.editComment);
		
		//Наш ключевой хелпер
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        //Все, база открыта!
        
        // адаптер
        ArrayList arrObject = new ArrayList<String>();
		//Cursor friendCursor = database.query(_table, new String[] {FRIEND_ID, FRIEND_NAME},
		//				     null, null,null,null, FRIEND_NAME);
		Cursor _cursorObject = database.query("data", new String[] {"street", "home", "room", "floor", "storey", "material", "gross_area", "living_space", "kitchen_area", "cost", "comment", "phone", "region"}, "_id = ?", new String[] {_id},null,null, "cost");
		
		_cursorObject.moveToFirst();
		if(!_cursorObject.isAfterLast()) {
			do {
				//String name = _cursorRealty.getString(1);
				//RealtyObjects.add(new RealtyObject(_cursorRealty.getString(1), getString(R.string.Rooms) + _cursorRealty.getString(2), getString(R.string.Cost) + _cursorRealty.getString(3) + " руб.", _cursorRealty.getString(0)));
//				tvStreet.setText(_cursorObject.getString(0));
//				tvRooms.setText(_cursorObject.getString(2));
//			    tvFloor.setText(_cursorObject.getString(3));
//			    tvStorrey.setText(_cursorObject.getString(4));
//			    tvGrossArea.setText(_cursorObject.getString(6));
//			    tvLivingSpace.setText(_cursorObject.getString(7));
//			    tvKitchenArea.setText(_cursorObject.getString(8));
//			    tvPhone.setText(_cursorObject.getString(11));
//			    tvCost.setText(_cursorObject.getString(9));
//			    tvMaterial.setText(_cursorObject.getString(5));
//			    tvComment.setText(_cursorObject.getString(10));
			    
				DetalsResultObject.add(new DetalsResultObject(getString(R.string.SearchRegion), _cursorObject.getString(12)));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drStreet), _cursorObject.getString(0)));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drRooms), _cursorObject.getString(2)));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drFloor), _cursorObject.getString(3)));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drStorrey), _cursorObject.getString(4)));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drGrossArea), _cursorObject.getString(6) + " m2"));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drLivingSpace), _cursorObject.getString(7) + " m2"));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drKitchenArea), _cursorObject.getString(8) + "m 2"));
			    strPhone = _cursorObject.getString(11);
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drPhone), _cursorObject.getString(11)));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.SearchCost), _cursorObject.getString(9) + " руб."));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drMaterial), _cursorObject.getString(5)));
			    DetalsResultObject.add(new DetalsResultObject(getString(R.string.drComment), _cursorObject.getString(10)));
				
			} while (_cursorObject.moveToNext());
		}
		_cursorObject.close();
		
		// создаем адаптер
	    boxAdapter = new BoxAdapterDetalsResult(this, DetalsResultObject);

	    // настраиваем список
	    lvMain = (ListView) findViewById(R.id.lvMain);
	    lvMain.setAdapter(boxAdapter);
	    
		// Использование собственного шаблона
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.detalsviewrowlayout, R.id.label, values);
        //setListAdapter(adapter);
	}

	//private void setListAdapter(ArrayAdapter<String> adapter) {
		
	//}

	public void onClick(View v){
		switch (v.getId()) {
	     case R.id.btnCall:
	    	 if(strPhone != "" && strPhone != null){
	 			try {
	 		        //startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + strPhone)));
	 		        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + strPhone)));
	 		      } catch (Exception e) {
	 		        e.printStackTrace();
	 		      }
	 		}else{
	 			
	 		}
	       break;
	     }
		
	}
}
