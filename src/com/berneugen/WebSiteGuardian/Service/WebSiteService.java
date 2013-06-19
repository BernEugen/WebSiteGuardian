package com.berneugen.WebSiteGuardian.Service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.preference.PreferenceManager;
import com.berneugen.WebSiteGuardian.ContentProvider.WebSiteContentProvider;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;
import com.berneugen.WebSiteGuardian.InternetClient.InternetClient;
import com.berneugen.WebSiteGuardian.Model.StatusesModel;
import com.berneugen.WebSiteGuardian.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.Date;

public class WebSiteService extends Service {

    private String url;
    private TaskHelper taskHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        url = getUrlFromPreference();
        taskHelper = new TaskHelper();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        taskHelper.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void putDataToDB(int statusConnection) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WebSiteDB.HOST_COLUMN, url);
        contentValues.put(WebSiteDB.STATUS_COLUMN, statusConnection);
        contentValues.put(WebSiteDB.DATE_COLUMN, new Date().getTime());
        getContentResolver().insert(WebSiteContentProvider.CONTENT_URI, contentValues);
    }

    public String getUrlFromPreference() {
        String prefsKey = getApplicationContext().getString(R.string.pref_key);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return preferences.getString(prefsKey, "");
    }

    private class TaskHelper extends AsyncTask<Void, Void , Void > {

        private int statusConnection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            InternetClient internetClient = new InternetClient(getApplicationContext());
            statusConnection = internetClient.checkSiteAvailability(url);
            putDataToDB(statusConnection);
            return null;
        }

        @Override
        protected void onPostExecute(Void params) {
            super.onPostExecute(params);
            stopSelf();
        }
    }
}



















