package com.frame.projectframe.module.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.frame.projectframe.module.greendao.gen.DaoMaster;
import com.frame.projectframe.module.greendao.gen.DownInfoDao;
import com.frame.projectframe.module.greendao.help.MigrationHelper;

public class BZOpenHelper extends DaoMaster.OpenHelper {
    public BZOpenHelper(Context context, String name) {
        super(context, name);
    }

    public BZOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
//        dropAllTables(db, true);
//        onCreate(db);
        MigrationHelper.getInstance().migrate(db, DownInfoDao.class);
    }
}
