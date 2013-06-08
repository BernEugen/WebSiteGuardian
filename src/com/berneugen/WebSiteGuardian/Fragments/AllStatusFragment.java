package com.berneugen.WebSiteGuardian.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import com.berneugen.WebSiteGuardian.ContentProvider.WebSiteContentProvider;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 29.05.13
 * Time: 22:58
 */
public class AllStatusFragment extends AbstractStatus {

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity().getApplicationContext(), WebSiteContentProvider.CONTENT_URI, null, null, null, null);
    }
}




















































