package com.berneugen.WebSiteGuardian.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 23.05.13
 * Time: 7:59
 */
public class WebSiteService extends Service {

    private String[] statusList = {"200 OK", "500 ERROR"};
    private Timer timer;
    private Random random = new Random();
    private WebSiteDB webSiteDB;

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        webSiteDB = new WebSiteDB(this);
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
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void putData() {
        int randNum = random.nextInt(2);
        webSiteDB.insertData(statusList[randNum]);
    }
}






































