package com.frame.projectframe.module.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.frame.projectframe.module.bean.DownInfo;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "WARE".
 */
public class DownInfoDao extends AbstractDao<DownInfo, Long> {

    public static final String TABLENAME = "downInfo";

    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "Id", true, "_id");
        public final static Property savePath = new Property(1, String.class, "savePath", false, "SAVE_PATH");
        public final static Property downUrl = new Property(2, String.class, "downUrl", false, "DOWN_URL");
        public final static Property baseUrl = new Property(3, String.class, "baseUrl", false, "BASE_URL");
        public final static Property contentLength = new Property(4, long.class, "contentLength", false, "CONTENT_LENGTH");
        public final static Property downedLength = new Property(5, long.class, "downedLength", false, "DOWNED_LENGTH");
    }


    public DownInfoDao(DaoConfig config) {
        super(config);
    }

    public DownInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"" + TABLENAME + "\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: Id
                "\"SAVE_PATH\" TEXT ," + // 1: savePath
                "\"DOWN_URL\" TEXT ," + // 2: downUrl
                "\"BASE_URL\" TEXT," + // 3: baseUrl
                "\"CONTENT_LENGTH\" INTEGER NOT NULL," + // 4: contentLength
                "\"DOWNED_LENGTH\" INTEGER NOT NULL );"); // 5: downedLength
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"" + TABLENAME + "\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DownInfo entity) {
        stmt.clearBindings();

        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }


        String savePath = entity.getSavePath();
        if (savePath != null) {
            stmt.bindString(2, savePath);
        }

        String downUrl = entity.getDownUrl();
        if (downUrl != null) {
            stmt.bindString(3, downUrl);
        }

        String baseUrl = entity.getBaseUrl();
        if (baseUrl != null) {
            stmt.bindString(4, baseUrl);
        }

        stmt.bindLong(5, entity.getContentLength());
        stmt.bindLong(6, entity.getDownedLength());

    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DownInfo entity) {
        stmt.clearBindings();

        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }


        String savePath = entity.getSavePath();
        if (savePath != null) {
            stmt.bindString(2, savePath);
        }

        String downUrl = entity.getDownUrl();
        if (downUrl != null) {
            stmt.bindString(3, downUrl);
        }

        String baseUrl = entity.getBaseUrl();
        if (baseUrl != null) {
            stmt.bindString(4, baseUrl);
        }

        stmt.bindLong(5, entity.getContentLength());
        stmt.bindLong(6, entity.getDownedLength());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset) ? null : cursor.getLong(offset);
    }

    @Override
    public DownInfo readEntity(Cursor cursor, int offset) {
        return new DownInfo( //
                cursor.isNull(offset) ? null : cursor.getLong(offset), // Id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // savePath
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // downUrl
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // baseUrl
                cursor.getLong(offset + 4), // contentLength
                cursor.getLong(offset + 5) // downedLength
        );
    }

    @Override
    public void readEntity(Cursor cursor, DownInfo entity, int offset) {
        entity.setId(cursor.isNull(offset) ? null : cursor.getLong(offset));
        entity.setSavePath(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDownUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBaseUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setContentLength(cursor.getLong(offset + 4));
        entity.setDownedLength(cursor.getLong(offset + 5));
    }

    @Override
    protected final Long updateKeyAfterInsert(DownInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(DownInfo entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DownInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

}
