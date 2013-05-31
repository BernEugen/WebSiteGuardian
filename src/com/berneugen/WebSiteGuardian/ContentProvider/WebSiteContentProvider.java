package com.berneugen.WebSiteGuardian.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
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
    private static final UriMatcher urimatcher;
    private static final int STATUS_CODE = 1;
    private WebSiteDB webSiteDB;

    static {
        urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
        urimatcher.addURI(PROVIDER_NAME, "status", STATUS_CODE);
    }


    @Override
    public boolean onCreate() {
        webSiteDB = new WebSiteDB(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (urimatcher.match(uri) == STATUS_CODE) {
            return webSiteDB.getAllData();
        } else {
            return null;
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
