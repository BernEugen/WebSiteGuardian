package com.berneugen.WebSiteGuardian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.berneugen.WebSiteGuardian.Fragments.AllStatusFragment;
import com.berneugen.WebSiteGuardian.Fragments.FailuresFragment;
import com.berneugen.WebSiteGuardian.PieChart.PieChartActivity;
import com.berneugen.WebSiteGuardian.Service.WebSiteService;

public class MyActivity extends FragmentActivity implements View.OnClickListener {

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
                startService(new Intent(this, WebSiteService.class));
                break;

            case R.id.stop:
                stopService(new Intent(this, WebSiteService.class));
                break;

            case R.id.enterUrl:
                startActivity(new Intent(this, PreferencesActivity.class));
                break;
        }
    }
}








































