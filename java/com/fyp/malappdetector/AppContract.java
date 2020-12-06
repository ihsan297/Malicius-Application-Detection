package com.fyp.malappdetector;

import android.provider.BaseColumns;

public class AppContract {
    private AppContract(){}

    public static final class AppTable implements BaseColumns {
        public static final String APP_TABLE_NAME="applications";
        public static final String NAME="name";

    }
}
