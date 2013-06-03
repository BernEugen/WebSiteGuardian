package com.berneugen.WebSiteGuardian.Service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.Toast;
import com.berneugen.WebSiteGuardian.ContentProvider.WebSiteContentProvider;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;
import com.berneugen.WebSiteGuardian.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 23.05.13
 * Time: 7:59
 */
public class WebSiteService extends Service {

    private Timer timer;
    private WebSiteDB webSiteDB;
    private String url;
    private Handler uiHandler;
    public static final int FAILED_STATUS = 500;

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        webSiteDB = new WebSiteDB(this);
        url = getUrlFromPreference();
        uiHandler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (isInternetConnected()) {
                    int statusConnection = checkSiteAvailability(url);
                    putDataToDB(statusConnection);
                } else {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
            }
        }, 0L, 10000L);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void putDataToDB(int statusConnection) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WebSiteDB.HOST_COLUMN, url);
        contentValues.put(WebSiteDB.STATUS_COLUMN, statusConnection);
        getContentResolver().insert(WebSiteContentProvider.CONTENT_URI, contentValues);
    }

    public String getUrlFromPreference() {
        String prefsKey = getApplicationContext().getString(R.string.pref_key);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return preferences.getString(prefsKey, "");
    }

    public boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }

    public int checkSiteAvailability(String url) {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        HttpResponse response;
        try {
            response = client.execute(request);
        } catch (ClientProtocolException ex) {
            return FAILED_STATUS;
        } catch (IOException ex) {
            return FAILED_STATUS;
        }

        int statusCode = response.getStatusLine().getStatusCode();
        return statusCode;
    }

}












































