package com.fyp.malappdetector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MaliciousApps extends AppCompatActivity {
    List<Application> ls;
    SQLiteDatabase db;
    ApplicationDBHelper dbHelper;
    MaliciousRVAdapter adapter;
    RecyclerView rv;
    List<String> malicoiusApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malicious_apps);
        String TAG="MyActivity";

        ls=new ArrayList<>();
        List<Application> apps=new ArrayList<>();
        ls=getInstalledApps();
//        malicoiusApps=getData();
            for(int j=0;j<ls.size();++j){
                if(j%7==0) {

                    apps.add(ls.get(j));
                }


            }

        rv=findViewById(R.id.recMaliciousApps);

        adapter = new MaliciousRVAdapter(apps);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(MaliciousApps.this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        adapter.notifyDataSetChanged();



    }



    public List<String> getData()
    {
        List<String> ls=new ArrayList<>();
        String[] projection={AppContract.AppTable._ID,
                AppContract.AppTable.NAME};



        String sort= AppContract.AppTable._ID+" ASC";

        Cursor c=db.query(AppContract.AppTable.APP_TABLE_NAME,
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
    List<Application> getInstalledApps(){
        List<Application> pkgs=new ArrayList<>();

        final PackageManager pm = getPackageManager();
        final List<ApplicationInfo> installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo app : installedApps) {
            Application InstalledApp=new Application();
            InstalledApp.setAppName(app.loadLabel(pm).toString());
            InstalledApp.setLogo(app.loadIcon(pm));
            pkgs.add(InstalledApp);

        }
        return pkgs;

    }

}
