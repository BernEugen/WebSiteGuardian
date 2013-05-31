package com.berneugen.WebSiteGuardian;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.Toast;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDBHelper;
import com.berneugen.WebSiteGuardian.Fragments.AllStatusFragment;
import com.berneugen.WebSiteGuardian.Fragments.FailuresFragment;
import com.berneugen.WebSiteGuardian.Service.WebSiteService;

public class MyActivity extends FragmentActivity implements View.OnClickListener {

    private WebSiteDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor c;
    private FragmentTabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        tabHost.addTab(tabHost.newTabSpec("allStatus").setIndicator("All Status"),
                AllStatusFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("failures").setIndicator("Failures"),
                FailuresFragment.class, null);

        dbHelper = new WebSiteDBHelper(this);
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

            case R.id.enterUrl:
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void showStatus() {
        db = dbHelper.getWritableDatabase();
        c = db.query(WebSiteDBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (c.moveToLast()) {
            Toast.makeText(this, "Status " + c.getString(c.getColumnIndex(WebSiteDBHelper.STATUS_COLUMN)), Toast.LENGTH_SHORT).show();
        }
        dbHelper.close();
    }
}








































