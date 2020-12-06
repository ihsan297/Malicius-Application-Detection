package com.fyp.malappdetector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationDetails extends AppCompatActivity {
    List<Privacy> ls;
    ImageView appIcon;
    TextView appLabel;
    PrivRVAdapter adapter;
    RecyclerView rv;
    String whiteListApp;
    Button addToWhitelist;
    PrivacyManager privacyManager;
    SQLiteDatabase readDB;
    SQLiteDatabase writDB;
    ApplicationDBHelper applicationDBHelper;

    Map<String, Drawable> applications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        applicationDBHelper=new ApplicationDBHelper(ApplicationDetails.this);




        setContentView(R.layout.activity_application_details);
        appIcon=findViewById(R.id.appLogo);
        appLabel=findViewById(R.id.AppLabel);
        addToWhitelist=findViewById(R.id.btn_witelist);
         String TAG = "MyActivity";
        ls=new ArrayList<Privacy>();
        privacyManager=new PrivacyManager();
        rv=findViewById(R.id.rvPermissions);

        Intent intent=getIntent();
        String intentLabel=intent.getStringExtra("appLabel");
        //Application displayApp=(Application) getIntent().getSerializableExtra("app");

        appLabel.setText(intentLabel);


        final PackageManager pm = getPackageManager();
        final List<ApplicationInfo> installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo app : installedApps) {
            //Details:
            Log.d(TAG, "Package: " + app.packageName);
            Log.d(TAG, "UID: " + app.uid);
            Log.d(TAG, "Directory: " + app.sourceDir);
            if(app.loadLabel(pm).toString().equals(intentLabel)) {
                appIcon.setImageDrawable(app.loadIcon(pm));
                whiteListApp=app.loadLabel(pm).toString();

                Application application=new Application();
                application.setLogo(app.loadIcon(pm));
                application.setAppName(app.loadLabel(pm).toString());
                appLabel.setText(application.getAppName());
                privacyManager.setApplication(application);

                //Permissions:
                StringBuffer permissions = new StringBuffer();

                try {
                    PackageInfo packageInfo = pm.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS);

                    String[] requestedPermissions = packageInfo.requestedPermissions;
                    if (requestedPermissions != null) {



                        for (int i = 0; i < requestedPermissions.length; i++) {
                            permissions.append(requestedPermissions[i] + "\n");
                            Privacy pv=new Privacy();
                            pv.setPrivacyType(stringTRIM(requestedPermissions[i]));

                            ls.add(pv);
                        }

                        Log.d(TAG, "Permissions: " + permissions);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                privacyManager.setPrivacies(ls);
            }
        }


        adapter=new PrivRVAdapter(privacyManager.privacies);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ApplicationDetails.this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        addToWhitelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> appList=new ArrayList<>();
             //   readDB=applicationDBHelper.getReadableDatabase();
              //  appList=getData();
                boolean flag=false;
                //for(int i=0;i<appList.size();++i){
                  //  if(appList.get(i).equals(whiteListApp)){
                  //      Toast.makeText(getApplicationContext(),"Already Whitelisted",Toast.LENGTH_LONG).show();
                    //    flag=true;
                   // }
                //}
                if(flag==false){
                    writDB=applicationDBHelper.getWritableDatabase();

                    ContentValues cv=new ContentValues();
                    cv.put(AppContract.AppTable.NAME,whiteListApp);

                    writDB.insert(AppContract.AppTable.APP_TABLE_NAME,null,cv);
                    Toast.makeText(getApplicationContext(),"Added to Whitelist",Toast.LENGTH_LONG).show();
                    finish();

                }



            }
        });


    }
    String stringTRIM(String str){
        String myStr=str;
        myStr=myStr.replace('_',' ');
        return myStr.substring(19);
    }

    public List<String> getData()
    {
        List<String> ls=new ArrayList<>();
        String[] projection={AppContract.AppTable._ID,
                AppContract.AppTable.NAME};


        readDB=applicationDBHelper.getReadableDatabase();
        String sort= AppContract.AppTable.APP_TABLE_NAME+" ASC";

        Cursor c=readDB.query(AppContract.AppTable.APP_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sort);
        while (c.moveToNext())
        {
            ls.add(c.getString(c.getColumnIndex(AppContract.AppTable.NAME)));

        }
        c.close();
        return ls;
    }


}

