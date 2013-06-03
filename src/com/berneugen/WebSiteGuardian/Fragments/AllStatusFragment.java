package com.berneugen.WebSiteGuardian.Fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.berneugen.WebSiteGuardian.ContentProvider.WebSiteContentProvider;
import com.berneugen.WebSiteGuardian.CursorAdapterImpl.FragmentCursorAdapter;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;
import com.berneugen.WebSiteGuardian.R;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 29.05.13
 * Time: 22:58
 */
public class AllStatusFragment extends ListFragment implements LoaderCallbacks<Cursor> {

    private static final String[] FROM = {WebSiteDB.HOST_COLUMN, WebSiteDB.DATE_COLUMN};
    private static final int[] TO = {R.id.status_host, R.id.status_date};
    private FragmentCursorAdapter fragmentCursorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentCursorAdapter = new FragmentCursorAdapter(getActivity().getApplicationContext(), R.layout.status_list_item, null, FROM, TO, 0);
        setListAdapter(fragmentCursorAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity().getApplicationContext(), WebSiteContentProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        fragmentCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        fragmentCursorAdapter.swapCursor(null);
    }
}




















































