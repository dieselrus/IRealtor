package ru.dieselru.irealtor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//�����-�������� ���������� �� ��������/���������
//���� � �������������� �������� �� ������
public class DbOpenHelper extends SQLiteOpenHelper {

	public DbOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	// ���������� ��� �������� ���� �� ����������
	public void onCreate(SQLiteDatabase db) {
		// ������� ����������� sql-������ ��� �������� �������
		//final String CREATE_DB = "CREATE TABLE " + TABLE_NAME + " ("
		//		+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		//		+ KEY_NAME + " TEXT NOT NULL);";
		//db.execSQL(CREATE_DB);
	}

	@Override
	// ����� ����� ������, ���� ��������� ������ ����
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// ��� ����� ������������ �������� ������ �� ������ ���� � �����
		// ��� ������ "���������" ������� � ������� ������
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		//onCreate(db);
	}
}
