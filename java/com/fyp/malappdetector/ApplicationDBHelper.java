package com.fyp.malappdetector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.fyp.malappdetector.AppContract.*;

public class ApplicationDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="appdb.db";
    public static final int DATABASE_VERSION=1;

    public static final String CREATE_APP_TABLE="CREATE TABLE "+
            AppTable.APP_TABLE_NAME+" ("+
        AppTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            AppTable.NAME+" TEXT);";


    public static final String DROP_TABLE=
            "DROP TABLE IF EXISTS "+ AppTable.APP_TABLE_NAME;

    public ApplicationDBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_APP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);

    }
}
