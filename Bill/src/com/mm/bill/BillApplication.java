package com.mm.bill;

import com.facebook.drawee.backends.pipeline.Fresco;

public class BillApplication extends BaseApplication {

    private static BillApplication mInstance = null;

    public static boolean isEnablePreCacheImages = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
    }

    public static BillApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public String getAppDirName() {
        return "bill";
    }
}
