package ru.dieselru.irealtor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "irealtor";
	private static final int DATABASE_VERSION = 1;
	
	public MyDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
		
		// you can use an alternate constructor to specify a database location
		// (such as a folder on the sd card)
		// you must ensure that this folder is available and you have permission
		// to write to it
		//super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
		
		// call this method to force a database overwrite if the version number 
		// is below a certain threshold
		//setForcedUpgradeVersion(2);
	}

//	public Cursor getEmployees() {
//
//		SQLiteDatabase db = getReadableDatabase();
//		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//
//		String [] sqlSelect = {"0 _id", "FullName"}; 
//		String sqlTables = "Employees";
//
//		qb.setTables(sqlTables);
//		Cursor c = qb.query(db, sqlSelect, null, null,
//				null, null, null);
//
//		c.moveToFirst();
//		return c;
//
//	}

	public Cursor getTypes() {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "Name"}; 
		//String [] sqlSelect = {"Name"}; 
		
		String sqlTables = "Types";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, null, null,
				null, null, null);

		c.moveToFirst();
		return c;
	}
	
	public Cursor getCitys() {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "Name"}; 
		//String [] sqlSelect = {"Name"}; 
		
		String sqlTables = "City";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, null, null,
				null, null, null);

		c.moveToFirst();
		return c;
	}
	
	public Cursor getRegions() {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "Name"}; 
		//String [] sqlSelect = {"Name"}; 
		
		String sqlTables = "Regions";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, null, null,
				null, null, null);

		c.moveToFirst();
		return c;
	}
	
}
