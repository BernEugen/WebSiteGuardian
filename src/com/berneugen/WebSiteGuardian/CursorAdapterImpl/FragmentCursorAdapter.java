package com.berneugen.WebSiteGuardian.CursorAdapterImpl;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;
import com.berneugen.WebSiteGuardian.R;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 03.06.13
 * Time: 0:16
 */
public class FragmentCursorAdapter extends SimpleCursorAdapter {

    public FragmentCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView dateText = (TextView) view.findViewById(R.id.status_date);
        ImageView statusImage = (ImageView) view.findViewById(R.id.status_image);
        Cursor cursor = (Cursor) getItem(position);
//        String dateCursor = cursor.getString(cursor.getColumnIndex(WebSiteDB.DATE_COLUMN));
        String statusCodeCursor = cursor.getString(cursor.getColumnIndex(WebSiteDB.STATUS_COLUMN));
//
        if (statusCodeCursor.equals("200")) {
            statusImage.setImageResource(R.drawable.green_check);
        } else {
            statusImage.setImageResource(R.drawable.red_check);
        }

//        dateText.setText(new Date(dateCursor).toString());
        return view;
    }
}
