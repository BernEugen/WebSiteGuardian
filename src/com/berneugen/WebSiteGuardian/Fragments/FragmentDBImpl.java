package com.berneugen.WebSiteGuardian.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDBHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 30.05.13
 * Time: 7:40
 */
public class FragmentDBImpl extends Fragment {

    private SQLiteDatabase db;
    private WebSiteDBHelper dbHelper;
    private Context context;

    public FragmentDBImpl(Context context) {
        this.context = context;
    }

    public void open() {
//        db = new WebSiteDBHelper(this);
    }

    public Cursor getAllData() {
        return db.query("dbTable", null, null, null, null, null, null);
    }
}
