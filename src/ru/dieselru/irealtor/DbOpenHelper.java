package ru.dieselru.irealtor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper{

	   private static final int DB_VERSION = 1;
	   private static final String DB_NAME = "irealtor.sqlite3";

	   public static final String TABLE_NAME = "data";
	   public static final String LOGIN = "login";
	   public static final String PASSW = "passw";
	   private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( _id integer primary key autoincrement, "
	       + LOGIN + " TEXT, " + PASSW + " TEXT)";

	   public DbOpenHelper(Context context) {
	     super(context, DB_NAME, null,DB_VERSION);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase sqLiteDatabase) {
	     sqLiteDatabase.execSQL(CREATE_TABLE);
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
	   }
	 }

