package com.example.user.joe120204.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2016/12/8.
 */
//app第一次執行時,建立sqlite檔案,增加table-phone

public class MyDBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "student.sqlite";
    final static int version = 1;
    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"phone\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"SName\" VARCHAR, \"Tel\" VARCHAR, \"Addr\" VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
