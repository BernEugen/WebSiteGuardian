package com.berneugen.WebSiteGuardian;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.berneugen.WebSiteGuardian.Fragments.AllStatusFragment;
import com.berneugen.WebSiteGuardian.Fragments.FailuresFragment;
import com.berneugen.WebSiteGuardian.Model.StatusesModel;
import com.berneugen.WebSiteGuardian.PieChart.PieChartActivity;
import com.berneugen.WebSiteGuardian.Service.WebSiteService;

import java.util.Calendar;

public class MyActivity extends FragmentActivity implements View.OnClickListener {

    private FragmentTabHost tabHost;
    private Intent intent;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    String test;

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


        intent = new Intent(this, WebSiteService.class);
        pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_history:
                break;

            case R.id.menu_availability:
                Intent intent = new Intent(this, PieChartActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.start:
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                        StatusesModel.TIMEOUT_HTTP_CLIENT, pendingIntent);
                break;

            case R.id.stop:
                alarmManager.cancel(pendingIntent);
                break;

            case R.id.enterUrl:
                startActivity(new Intent(this, PreferencesActivity.class));
                break;
        }
    }
}








































