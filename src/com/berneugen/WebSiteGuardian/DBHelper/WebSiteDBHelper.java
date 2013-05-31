package com.berneugen.WebSiteGuardian.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 23.05.13
 * Time: 8:00
 */
public class WebSiteDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "WebSiteDB.db";
    public static final String TABLE_NAME = "webSiteTable";
    public static final String STATUS_COLUMN = "status";

    public WebSiteDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("
                + "id integer primary key autoincrement, "
                + STATUS_COLUMN + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
