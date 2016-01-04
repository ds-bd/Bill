package com.mm.bill;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.text.TextUtils;

import java.util.UUID;

public class CommConst {

    public static final int APP_VERSION_CODE;

    public static final String APP_VERSION_NAME;

    public static final String UPDATE_FILE_NAME = "BDLockScreen.apk";

    public static final String CLEAR_DATA_FILE_NAME = "cleardata";

    public static final String SDCARD_DIR_PATH = Environment.getExternalStorageDirectory() + "/";
    public static final String COM_DIR_PATH = SDCARD_DIR_PATH + "MM/";
    public static final String APP_DIR_PATH = COM_DIR_PATH + getAppDirName() + "/";
    public static final String LOG_DIR_PATH = APP_DIR_PATH + "Log/";

    public static final int MAX_SUPPORT_SCAN_SIZE = 9999;

    public static final int DATABASE_VERSION = 1;

    static {
        BaseApplication app = BaseApplication.instance();
        {
            int versionCode = 1;
            String versionName = "1.0.0";

            try {
                Context ctx = app.getBaseContext();
                PackageInfo info = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
                versionCode = info.versionCode;
                versionName = info.versionName;
            } catch (Exception ex) {
            }
            APP_VERSION_CODE = versionCode;
            APP_VERSION_NAME = versionName;
        }
    }

    public static final String ALIAS_SHORTCUT_ACTIVITY = "com.baidu.lockscreen.test.AliasShortcutActivity";

    public static class Path {
        public static final String APP_DATA_SD_PATH = Environment.getExternalStorageDirectory() + "/baidu/"
                + BillApplication.instance().getAppDirName() + "/";
    }

    public class URL {
        public static final String FEEDBACK_URL = "http://app.video.baidu.com/third/2005/";
        public static final String CLEAR_DATA_CONFIG_URL = "http://app.video.baidu.com/third/2004/?";
    }

    public class ManageApps {
        public static final int DB_VERSION = 1;
    }

    private static String getAppDirName() {
        String appDirName = null;
        BaseApplication app = BaseApplication.instance();

        if (null != app) {
            Context ctx = app.getBaseContext();
            appDirName = app.getAppDirName();
            appDirName = (TextUtils.isEmpty(appDirName) ? ctx.getPackageName() : appDirName);
        } else {
            final int DEFAULT_NAME_SIZE = 8;
            appDirName = UUID.randomUUID().toString();
            if ((null != appDirName) && (appDirName.length() > DEFAULT_NAME_SIZE)) {
                appDirName = appDirName.substring(0, DEFAULT_NAME_SIZE);
            }
        }
        return appDirName;
    }
}
