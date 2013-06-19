package com.berneugen.WebSiteGuardian.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import com.berneugen.WebSiteGuardian.FragmentCursorImplementation.FragmentCursorAdapter;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;
import com.berneugen.WebSiteGuardian.R;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 08.06.13
 * Time: 14:11
 */
public class BaseStatus extends ListFragment implements LoaderCallbacks<Cursor> {

    private static final String[] FROM = {WebSiteDB.STATUS_COLUMN, WebSiteDB.HOST_COLUMN, WebSiteDB.DATE_COLUMN};
    private static final int[] TO = {R.id.status_image, R.id.status_host, R.id.status_date};
    private FragmentCursorAdapter fragmentCursorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentCursorAdapter = new FragmentCursorAdapter(getActivity().getApplicationContext(),
                R.layout.status_list_item, null, FROM, TO, 0);
        setListAdapter(fragmentCursorAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        fragmentCursorAdapter.swapCursor(cursor);
        fragmentCursorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        fragmentCursorAdapter.swapCursor(null);
    }
}
