package com.berneugen.WebSiteGuardian.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 31.05.13
 * Time: 20:24
 */
public class WebSiteDB {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "site_check.db";

    private static final String TABLE_NAME = "site_table";
    private static final String TABLE_COLUMN_ID = "_id";
    public static final String TABLE_COLUMN_NAME = "status";

    private DBHelper openHelper;
    private SQLiteDatabase database;

    public WebSiteDB(Context context) {
        openHelper = new DBHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public Cursor getAllData() {
        String buildSQL = "SELECT * FROM " + TABLE_NAME;
        return database.rawQuery(buildSQL, null);
    }

    public void insertData (String status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COLUMN_NAME, status);
        database.insert(TABLE_NAME, null, contentValues);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context aContext) {
            super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String buildSQL = "CREATE TABLE " + TABLE_NAME + "( " + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    TABLE_COLUMN_NAME + " TEXT)";
            sqLiteDatabase.execSQL(buildSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            String buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
            sqLiteDatabase.execSQL(buildSQL);
            onCreate(sqLiteDatabase);
        }
    }
}
