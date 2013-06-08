package com.berneugen.WebSiteGuardian.CursorAdapterImpl;

import android.content.Context;
import android.database.Cursor;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;
import com.berneugen.WebSiteGuardian.R;
import com.berneugen.WebSiteGuardian.Service.WebSiteService;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 03.06.13
 * Time: 0:16
 */
public class FragmentCursorAdapter extends SimpleCursorAdapter {

    private PrettyTime time;

    public FragmentCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        time = new PrettyTime();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        Cursor cursor = (Cursor) getItem(position);

        ImageView statusImage = (ImageView) view.findViewById(R.id.status_image);
        int statusCodeCursor = cursor.getInt(cursor.getColumnIndex(WebSiteDB.STATUS_COLUMN));
        if (statusCodeCursor == WebSiteService.OK_STATUS) {
            statusImage.setImageResource(R.drawable.green_check);
        } else {
            statusImage.setImageResource(R.drawable.red_check);
        }

        TextView dateText = (TextView) view.findViewById(R.id.status_date);
        long  nowTime = cursor.getLong(cursor.getColumnIndex(WebSiteDB.DATE_COLUMN));
        dateText.setText(time.format(new Date(nowTime)));

        return view;
    }

}
