package com.berneugen.WebSiteGuardian.Service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDBHelper;
import com.berneugen.WebSiteGuardian.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 23.05.13
 * Time: 7:59
 */
public class WebSiteService extends Service {

    private String[] statusList = {"200 OK", "500 ERROR"};
    private Timer timer;
    private WebSiteDBHelper dbHelper;
    private ContentValues cv;
    private SQLiteDatabase db;
    private Random random = new Random();

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new WebSiteDBHelper(this);
        timer = new Timer();
        cv = new ContentValues();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                putData();
            }
        }, 0L, 1000L);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        dbHelper.close();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void putData() {

        int randNum = random.nextInt(2);

        db = dbHelper.getWritableDatabase();
        cv.put("status", statusList[randNum]);
        db.insert("dbTable", null, cv);
    }
}






































