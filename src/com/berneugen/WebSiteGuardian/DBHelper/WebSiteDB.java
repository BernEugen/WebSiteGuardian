package com.berneugen.WebSiteGuardian.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 31.05.13
 * Time: 20:24
 */
public class WebSiteDB {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "site3.db";

    public static final String TABLE_NAME = "siteStatuses";
    public static final String ID_COLUMN = "_id";
    public static final String HOST_COLUMN = "host";
    public static final String STATUS_COLUMN = "status";
    public static final String DATE_COLUMN = "date";

    private DBHelper openHelper;
    private SQLiteDatabase database;

    public WebSiteDB(Context context) {
        openHelper = new DBHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public SQLiteDatabase connectToDB() {
        return openHelper.getWritableDatabase();
    }

    public Cursor getAllData() {
        String buildSQL = "SELECT * FROM " + TABLE_NAME;
        return database.rawQuery(buildSQL, null);
    }

    public Cursor getFailedData() {
        return null;
    }

    public void insertData (String host, int status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HOST_COLUMN, host);
        contentValues.put(STATUS_COLUMN, status);
        database.insert(TABLE_NAME, null, contentValues);
    }

        private class DBHelper extends SQLiteOpenHelper {

            public DBHelper(Context aContext) {
                super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                String buildSQL = "CREATE TABLE " + TABLE_NAME + "( " + ID_COLUMN + " INTEGER PRIMARY KEY, " +
                        HOST_COLUMN + " TEXT, " + STATUS_COLUMN + " INTEGER, " + DATE_COLUMN + " TEXT)";
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
