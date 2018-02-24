package com.frame.projectframe.module.greendao.help;

import com.frame.projectframe.module.bean.DownInfo;
import com.frame.projectframe.module.greendao.GreenDaoManager;
import com.frame.projectframe.module.greendao.gen.DownInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class GreenDaoHelper {
    private static GreenDaoHelper helper;
    private DownInfoDao mDownInfoDao;

    private GreenDaoHelper() {
        mDownInfoDao = GreenDaoManager.getInstance().getSession().getDownInfoDao();
    }

    public static GreenDaoHelper getInstance() {
        if (helper == null) {
            helper = new GreenDaoHelper();
        }
        return helper;
    }

    //添加和更新数据
    public void insertDownInfo(DownInfo downInfo) {
        if (downInfo == null || mDownInfoDao == null) {
            return;
        }
        DownInfo queryDownInfo = queryDownInf(downInfo.getSavePath(), downInfo.getContentLength());
        if (queryDownInfo == null) {//没有查询到，直接添加数据
            mDownInfoDao.insert(downInfo);
        } else {//如果数据库存在改数据，进行更新
            if (downInfo.getContentLength() > queryDownInfo.getContentLength()) {
                queryDownInfo.setContentLength(downInfo.getContentLength());
                mDownInfoDao.update(queryDownInfo);
            }
        }
    }

    //查询数据
    private DownInfo queryDownInf(String savePath, long contentLength) {
        QueryBuilder<DownInfo> qb = mDownInfoDao.queryBuilder();
        return qb.where(qb.and(DownInfoDao.Properties.savePath.eq(savePath),
                DownInfoDao.Properties.contentLength.eq(contentLength))).build().unique();
    }

    //查询该表中的所有数据
    public List<DownInfo> queryAllDownInfo(Long userId) {
        return mDownInfoDao.queryBuilder()
                .build()
                .list();
    }

//    public List<DownInfo> queryAllWare(Long userId) {
//        QueryBuilder<DownInfo> qb = mDownInfoDao.queryBuilder();
//        qb.where(DownInfoDao.Properties.UserId.eq(userId));
//        qb.orderDesc(DownInfoDao.Properties.downedLength);
//        List<DownInfo> bookShelfList = qb.build().list();
//        return bookShelfList;
//    }
}
