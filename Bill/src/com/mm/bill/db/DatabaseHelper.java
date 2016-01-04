package com.mm.bill.db;

import com.mm.bill.BillApplication;
import com.mm.bill.CommConst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "bdplayer_database";

    private static DatabaseHelper mDbHelpser;

    private Context mContext;

    private DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    private DatabaseHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    private DatabaseHelper(Context context) {
        this(context, DATABASE_NAME, CommConst.DATABASE_VERSION);
    }

    public static DatabaseHelper create(Context context) {
        synchronized (DatabaseHelper.class) {
            if (mDbHelpser == null) {
                context = (null == context) ? BillApplication.getInstance() : context;
                mDbHelpser = new DatabaseHelper(context.getApplicationContext());
            }
            return mDbHelpser;
        }
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        synchronized (DatabaseHelper.class) {
            return super.getWritableDatabase();
        }
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        synchronized (DatabaseHelper.class) {
            return super.getReadableDatabase();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        rebuildDatabase(db, false);
    }

    private void rebuildDatabase(SQLiteDatabase db, boolean doCleanFirst) {
        synchronized (DatabaseHelper.class) {
            try {
                if (doCleanFirst) {
                    // db.execSQL(DBTaskCache.DELETE_TABLE_SQL);
                    // db.execSQL(DBManageApp.DELETE_TABLE_SQL);

                }

                // db.execSQL(DBTaskCache.CREATE_TABLE_SQL);
                // db.execSQL(DBManageApp.CREATE_TABLE_SQL);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        synchronized (DatabaseHelper.class) {
            // db.execSQL(DBManageApp.CREATE_TABLE_SQL);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        synchronized (DatabaseHelper.class) {
            if (oldVersion > newVersion) {
                rebuildDatabase(db, true);
            }
        }
    }

    /**
     * É¾³ýÊý¾Ý¿â
     * 
     * @param context
     * @return
     */
    public static boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }

}
