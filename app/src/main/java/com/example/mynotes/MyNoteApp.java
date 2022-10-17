package com.example.mynotes;

import android.app.Application;

import com.example.mynotes.Models.DaoMaster;
import com.example.mynotes.Models.DaoSession;

import org.greenrobot.greendao.database.Database;

public class MyNoteApp extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
