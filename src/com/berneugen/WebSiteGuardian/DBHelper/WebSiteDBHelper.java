package com.berneugen.WebSiteGuardian.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 23.05.13
 * Time: 8:00
 */
public class WebSiteDBHelper extends SQLiteOpenHelper {

    public WebSiteDBHelper(Context context) {
        super(context, "WebSiteDB", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table dbTable ("
                + "id integer primary key autoincrement,"
                + "status text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
