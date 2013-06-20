package com.berneugen.WebSiteGuardian.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import com.berneugen.WebSiteGuardian.ContentProvider.WebSiteContentProvider;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;
import com.berneugen.WebSiteGuardian.Model.StatusesModel;
import com.berneugen.WebSiteGuardian.Service.WebSiteService;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 29.05.13
 * Time: 23:10
 */
public class FailuresFragment extends BaseStatus {

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] okStatus = new String[] {String .valueOf(StatusesModel.OK_STATUS)};
        return new CursorLoader(getActivity().getApplicationContext(), WebSiteContentProvider.CONTENT_URI, null,
                WebSiteDB.STATUS_COLUMN + " != ?", okStatus, null);
    }
}
