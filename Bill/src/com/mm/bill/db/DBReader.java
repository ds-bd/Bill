package com.mm.bill.db;

import java.util.List;

import com.mm.bill.BillApplication;

import android.database.sqlite.SQLiteDatabase;

public class DBReader {

    private SQLiteDatabase db = null;
    private static DBReader mReader = null;

    private DBReader() {
        DatabaseHelper helper = DatabaseHelper.create(BillApplication.getInstance());
        this.db = helper.getReadableDatabase();
    }

    public static DBReader getInstance() {
        synchronized (DatabaseHelper.class) {
            if (mReader == null) {
                mReader = new DBReader();
            }
            return mReader;
        }
    }

}
