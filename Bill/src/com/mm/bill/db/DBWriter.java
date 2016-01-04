package com.mm.bill.db;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import com.mm.bill.BillApplication;

public class DBWriter {
    private SQLiteDatabase db = null;

    private static DBWriter mWriter = null;

    public DBWriter() {
        DatabaseHelper helper = DatabaseHelper.create(BillApplication.getInstance());
        this.db = helper.getWritableDatabase();
    }

    public static DBWriter getInstance() {
        synchronized (DatabaseHelper.class) {
            if (mWriter == null) {
                mWriter = new DBWriter();
            }
            return mWriter;
        }
    }
}
