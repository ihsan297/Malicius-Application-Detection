package com.fyp.malappdetector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentUris;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Audit extends AppCompatActivity {
    public RecyclerView rv;
    public List<Application> ls;
    public AppRVAdapter adapter;
    public TextView tv;
    TextView totalApps;
    public ImageView im1,im2;
    Uri ur1;
    Bitmap b;
    private ResourceBundle context;
    PackageManager mPm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit);
        ls=new ArrayList<Application>();
        totalApps=findViewById(R.id.totalApps);

        final PackageManager pm = getPackageManager();
        final List<ApplicationInfo> installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo app : installedApps) {
            Application InstalledApp=new Application();
            InstalledApp.setAppName(app.loadLabel(pm).toString());
            InstalledApp.setLogo(app.loadIcon(pm));
            ls.add(InstalledApp);

            //Details:
//            Log.d(TAG, "Package: " + app.packageName);
//            Log.d(TAG, "UID: " + app.uid);
//            Log.d(TAG, "Directory: " + app.sourceDir);


            //Permissions:
            StringBuffer permissions = new StringBuffer();

            try {
                PackageInfo packageInfo = pm.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS);

                String[] requestedPermissions = packageInfo.requestedPermissions;
                if (requestedPermissions != null) {



                    for (int i = 0; i < requestedPermissions.length; i++) {
                        permissions.append(requestedPermissions[i] + "\n");
                        Privacy pv=new Privacy();
                        pv.setPrivacyType(requestedPermissions[i]);
                      //  ls.add(pv);
                    }

//                    Log.d(TAG, "Permissions: " + permissions);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }


/*
        final PackageManager pm = getPackageManager();
//get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            //Log.d(TAG, "Installed package :" + packageInfo.name);
            Application app=new Application();
            app.setAppName(packageInfo.loadLabel(pm).toString());
            app.setLogo(packageInfo.loadIcon(pm));

            ls.add(app);

         //   Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }
        */


       // totalApps.setText(ls.size());

        rv=findViewById(R.id.recVw);



        adapter=new AppRVAdapter(ls);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Audit.this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        totalApps.setText(Integer.toString(ls.size()));

    }
}
