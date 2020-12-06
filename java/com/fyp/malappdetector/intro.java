package com.fyp.malappdetector;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class intro extends AppCompatActivity {

    private static final long SPLASH_TIME = 4000;

    private ProgressBar progressBar;
    private int progressStatus = 0;
    TextView totalApps,scannedApps;
    Button toMalcious;
    private TextView textView;
    LinearLayout linearLayout;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        totalApps=findViewById(R.id.countTotalApps);
        linearLayout=findViewById(R.id.outPutLayout);
        scannedApps=findViewById(R.id.countScannedApps);
        toMalcious=findViewById(R.id.toMalicious);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
//
//
//        TimerTask task=new TimerTask() {
//            @Override
//            public void run() {
//                Intent mainIntent=new Intent().setClass(intro.this,MainActivity.class);
//                startActivity(mainIntent);
//                finish();
//            }
//        };
//        Timer timer=new Timer();
//        timer.schedule(task,SPLASH_TIME);
        final int total=getInstalledApps();
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < total) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            //textView.setText(progressStatus+"/"+progressBar.getMax());
                            totalApps.setText(Integer.toString(total));
                            scannedApps.setText(Integer.toString(progressStatus));
                            //progressBar.invalidate();
                                    if(progressStatus==total-1){
                                progressBar.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.VISIBLE);

                            }
//                            Intent serviceIntent=new Intent();
//                            serviceIntent.setAction("com.example.progressbar.ScannerService");
//                            startService(new Intent(intro.this,ScannerService.class));
//                            Intent stopService=new Intent(intro.this,ScannerService.class);
//                            stopService(stopService);

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
        toMalcious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(intro.this,MaliciousApps.class);
                startActivity(intent);
            }
        });

                            Intent serviceIntent=new Intent();
                            serviceIntent.setAction("com.example.progressbar.ScannerService");
                            startService(new Intent(intro.this,ScannerService.class));
//                            Intent stopService=new Intent(intro.this,ScannerService.class);
//                            stopService(stopService);

    }
    int getInstalledApps(){
        List<Application> pkgs=new ArrayList<>();

        final PackageManager pm = getPackageManager();
        final List<ApplicationInfo> installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo app : installedApps) {
            Application InstalledApp=new Application();
            InstalledApp.setAppName(app.loadLabel(pm).toString());
            InstalledApp.setLogo(app.loadIcon(pm));
            pkgs.add(InstalledApp);

        }
        return pkgs.size();

    }


}
