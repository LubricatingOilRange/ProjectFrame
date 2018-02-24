package com.frame.projectframe.module.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.frame.projectframe.app.Constant;
import com.frame.projectframe.app.MyApplication;
import com.frame.projectframe.module.greendao.gen.DaoMaster;
import com.frame.projectframe.module.greendao.gen.DaoSession;
import com.frame.projectframe.util.FileUtil;

import java.io.File;

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }

    private GreenDaoManager() {
        String db = "frame.db";
        if (Constant.DEBUG) {
            //判断sd卡是否存在
            if (FileUtil.isMountedSDCard()) {
                db = Environment.getExternalStorageDirectory().getPath() + File.separator + "ProjectFrame" + File.separator + db;
            }
        }
        BZOpenHelper bzOpenHelper = new BZOpenHelper(MyApplication.getInstance(), db, null);
        SQLiteDatabase writableDatabase = bzOpenHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(writableDatabase);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
