package com.fyp.malappdetector;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;

public class ScannerService extends Service {
    private final int UPDATE_INTERVAL = 60 * 1000;
    private Timer timer = new Timer();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(),"Service is started",Toast.LENGTH_SHORT).show();
//        Intent intent1=new Intent(getApplicationContext(),intro.class);
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent1);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }
}
