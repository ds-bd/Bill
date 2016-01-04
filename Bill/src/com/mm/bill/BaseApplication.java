package com.mm.bill;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;

public class BaseApplication extends Application {

    private static WeakReference<BaseApplication> s_instance = null;

    private Stack<Resources> mPluginResStack = new Stack<Resources>();

    private Stack<AssetManager> mPluginAssetsStack = new Stack<AssetManager>();

    private Stack<Resources.Theme> mPluginThemeStack = new Stack<Resources.Theme>();

    protected LauncherUncaughtExceptionHandler.SaverHandler mSaverHandler =
            new LauncherUncaughtExceptionHandler.SaverHandler() {

                @Override
                public boolean handleUnCaughtedException(Thread thread, Throwable ex) {
                    return localHandleUnCaughtedException(thread, ex);
                }
            };

    public static BaseApplication instance() {
        return ((null != s_instance) ? s_instance.get() : null);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public BaseApplication() {
        super();
        s_instance = new WeakReference<BaseApplication>(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LauncherUncaughtExceptionHandler launcherUncaughtExceptionHandler = new LauncherUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(launcherUncaughtExceptionHandler);
    }

    public String getAppDirName() {
        return "video";
    }

    protected boolean localHandleUnCaughtedException(Thread thread, Throwable ex) {
        return false;
    }

    private static class LauncherUncaughtExceptionHandler implements UncaughtExceptionHandler {
        private final UncaughtExceptionHandler _defaultHandler;

        private final Map<String, String> _infos = new LinkedHashMap<String, String>();
        private final DateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US);
        private SaverHandler mSaverHandler;

        public LauncherUncaughtExceptionHandler() {
            _defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        }

        public void setSaveHandler(SaverHandler saveHandler) {
            mSaverHandler = saveHandler;
        }

        public interface SaverHandler {
            public boolean handleUnCaughtedException(Thread thread, Throwable ex);
        }

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            boolean ignoreForFatalError = false;
            try {
                collectDeviceInfo(BaseApplication.instance());
                saveCrashInfo2File(ex);
                saveHeapDump2File(ex);

                if (mSaverHandler != null) {
                    ignoreForFatalError = mSaverHandler.handleUnCaughtedException(thread, ex);
                }
            } catch (Exception e) {
            } finally {
                boolean shouldRestart = false;
                BaseApplication app = BaseApplication.instance();

                if (ignoreForFatalError == true) {
                    // kill myself
                    System.exit(2);
                    return;
                }
            }
        }

        public void collectDeviceInfo(Context context) {
            _infos.put("PackageName", context.getPackageName());
            _infos.put("VersionName", CommConst.APP_VERSION_NAME);
            _infos.put("VersionCode", String.valueOf(CommConst.APP_VERSION_CODE));
            _infos.put("=", "==============================");

            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    _infos.put(field.getName(), field.get("").toString());
                } catch (Exception e) {
                }
            }
            _infos.put("==", "=============================");
        }

        private String saveCrashInfo2File(Throwable ex) {
            String fileName = null;

            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, String> entry : _infos.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key + "=" + value + "\n");
            }

            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.close();
            sb.append(writer.toString());

            try {
                fileName = String.format("crash-%s.txt", _formatter.format(new Date()));
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    final String path = CommConst.LOG_DIR_PATH;
                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(path + fileName);
                    fos.write(sb.toString().getBytes());
                    fos.close();
                }
            } catch (Exception e) {
            }

            return fileName;
        }

        private void saveHeapDump2File(Throwable throwable) {
            if (isOutOfMemoryError(throwable)) {
                try {
                    String fileName = String.format("crash-%s.hprof", _formatter.format(new Date()));
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        final String path = CommConst.LOG_DIR_PATH;
                        File dir = new File(path);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        Debug.dumpHprofData(path + fileName);
                    }
                } catch (Exception ex) {
                }
            }
        }
    }

    private static boolean isOutOfMemoryError(Throwable ex) {
        if (OutOfMemoryError.class.equals(ex.getClass())) {
            return true;
        } else {
            Throwable cause = ex.getCause();
            while (null != cause) {
                if (OutOfMemoryError.class.equals(cause.getClass())) {
                    return true;
                }
                cause = cause.getCause();
            }
        }

        return false;
    }

    @Override
    public AssetManager getAssets() {
        if (mPluginAssetsStack.isEmpty()) {
            return super.getAssets();
        }
        return mPluginAssetsStack.peek();
    }

    @Override
    public Resources getResources() {
        if (mPluginResStack.isEmpty()) {
            return super.getResources();
        }
        return mPluginResStack.peek();
    }

    @Override
    public Theme getTheme() {
        if (mPluginThemeStack.isEmpty()) {
            return super.getTheme();
        }
        return mPluginThemeStack.peek();
    }
}
