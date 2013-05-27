package com.berneugen.WebSiteGuardian;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDBHelper;
import com.berneugen.WebSiteGuardian.Service.WebSiteService;

public class MyActivity extends Activity implements View.OnClickListener {

    private TextView display;
    private WebSiteDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        display = (TextView) findViewById(R.id.display);
        dbHelper = new WebSiteDBHelper(this);
        showStatus();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.start:
                startService(new Intent(this, WebSiteService.class));
                break;

            case R.id.stop:
                stopService(new Intent(this, WebSiteService.class));
                break;

            case R.id.refresh:
                showStatus();
                break;
        }
    }

    public void showStatus() {
        db = dbHelper.getWritableDatabase();
        c = db.query("dbTable", null, null, null, null, null, null);
        if (c.moveToLast()) {
            display.setText("Status " + c.getString(c.getColumnIndex("status")));
        }
        dbHelper.close();
    }
}








































