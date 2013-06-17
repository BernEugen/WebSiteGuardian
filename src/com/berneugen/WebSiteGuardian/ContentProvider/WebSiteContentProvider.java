package com.berneugen.WebSiteGuardian.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Parcel;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 31.05.13
 * Time: 21:11
 */
public class WebSiteContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.berneugen.websiteguardian.contentprovider.websitecontentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/status" );
    private static final UriMatcher URI_MATCHER;
    private static final int STATUS_CODE = 1;
    private WebSiteDB webSiteDB;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVIDER_NAME, "status", STATUS_CODE);
    }

    @Override
    public boolean onCreate() {
        webSiteDB = new WebSiteDB(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = webSiteDB.connectToDB();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(WebSiteDB.TABLE_NAME);
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri result = null;
        if (URI_MATCHER.match(uri) != STATUS_CODE) {
            throw new IllegalArgumentException("Wrong URI " + uri);
        }

        SQLiteDatabase db = webSiteDB.connectToDB();
        long rowID = db.insert(WebSiteDB.TABLE_NAME, null, values);
        if (rowID > 0) {
            result = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(result, null);
        }
        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
