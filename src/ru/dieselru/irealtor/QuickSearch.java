package ru.dieselru.irealtor;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class QuickSearch extends Activity {

	final String LOG_TAG = "myLogs";
	
	private static String DB_PATH =  Environment.getDataDirectory().toString(); //"/data/data/YOUR_PACKAGE/databases/"
    private static String DB_NAME = "myDBName";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase myDataBase;
   


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quicks_earch);

		// Подключаемся к БД
		DBHelper dbh = new DBHelper(this);
		SQLiteDatabase db = dbh.getWritableDatabase();

		// Описание курсора
		Cursor c;

		String sqlQuery = "select * "
				+ "from data ";
				//+ "inner join position as PS "
				//+ "on PL.posid = PS.id " + "where salary > ?";
		c = db.rawQuery(sqlQuery, new String[] { "12000" });
		logCursor(c);
		c.close();
		    
		dbh.close();
	}

	// вывод в лог данных из курсора
	void logCursor(Cursor c) {
		if (c != null) {
			if (c.moveToFirst()) {
				String str;
				do {
					str = "";
					for (String cn : c.getColumnNames()) {
						str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
					}
					Log.d(LOG_TAG, str);
				} while (c.moveToNext());
			}
		} else
			Log.d(LOG_TAG, "Cursor is null");
	}

	// класс для работы с БД
	class DBHelper extends SQLiteOpenHelper {

		private final Context myContext;
		 
//		public DBHelper(Context context) {
//			super(context, "DB_NAME", null, DATABASE_VERSION);
//		}

		public DBHelper(Context context) {
	    	super(context, DB_NAME, null, DATABASE_VERSION);
	        this.myContext = context;
	    }


		public void onCreate(SQLiteDatabase db) {
			Log.d(LOG_TAG, "--- onCreate database ---");

//			ContentValues cv = new ContentValues();
//
//			// создаем таблицу должностей
//			db.execSQL("create table position (" + "id integer primary key,"
//					+ "name text," + "salary integer" + ");");
//
//			// заполняем ее
//			for (int i = 0; i < position_id.length; i++) {
//				cv.clear();
//				cv.put("id", position_id[i]);
//				cv.put("name", position_name[i]);
//				cv.put("salary", position_salary[i]);
//				db.insert("position", null, cv);
//			}
//
//			// создаем таблицу людей
//			db.execSQL("create table people ("
//					+ "id integer primary key autoincrement," + "name text,"
//					+ "posid integer" + ");");
//
//			// заполняем ее
//			for (int i = 0; i < people_name.length; i++) {
//				cv.clear();
//				cv.put("name", people_name[i]);
//				cv.put("posid", people_posid[i]);
//				db.insert("people", null, cv);
//			}
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}
}
