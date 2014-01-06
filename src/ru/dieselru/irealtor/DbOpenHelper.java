package ru.dieselru.irealtor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//Класс-помошник отвечающий за создание/отктрытие
//базы и осуществляющий контроль ее версий
public class DbOpenHelper extends SQLiteOpenHelper {

	public DbOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	// Вызывается при создании базы на устройстве
	public void onCreate(SQLiteDatabase db) {
		// Посроим стандартный sql-запрос для создания таблицы
		//final String CREATE_DB = "CREATE TABLE " + TABLE_NAME + " ("
		//		+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		//		+ KEY_NAME + " TEXT NOT NULL);";
		//db.execSQL(CREATE_DB);
	}

	@Override
	// Метод будет вызван, если изменится версия базы
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Тут можно организовать миграцию данных из старой базы в новую
		// или просто "выбросить" таблицу и создать заново
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		//onCreate(db);
	}
}
