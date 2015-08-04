package com.adamin90.adamlee.disklrucacheexample;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

/**
 * Created by Administrator on 2015/8/4.
 */
public class App extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
        SQLiteDatabase database=Connector.getDatabase();
    }
}
