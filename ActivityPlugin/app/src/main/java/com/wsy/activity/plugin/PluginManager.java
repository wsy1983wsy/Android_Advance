package com.wsy.activity.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private static final String TAG = PluginManager.class.getSimpleName();

    private static PluginManager pluginManager;

    private Context context;

    public static PluginManager getInstance(Context context) {
        if (pluginManager == null) {
            synchronized (PluginManager.class) {
                if (pluginManager == null) {
                    pluginManager = new PluginManager(context);
                }
            }
        }
        return pluginManager;
    }

    public PluginManager(Context context) {
        this.context = context;
    }

    private DexClassLoader dexClassLoader;
    private Resources resources;

    public void loadPlugin() {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "p.apk");
            if (!file.exists()) {
                Log.d(TAG, "插件包 不存在...");
                return;
            }
            String pluginFilePath = file.getAbsolutePath();
            /**
             *   下面是加载插件里面的 class
             */

            // dexClassLoader需要一个缓存目录   /data/data/当前应用的包名/pDir
            File fileDir = context.getDir("pDir", Context.MODE_PRIVATE);
            dexClassLoader = new DexClassLoader(pluginFilePath, fileDir.getAbsolutePath(), null, context.getClassLoader());

            AssetManager assetManager = AssetManager.class.newInstance();

            Method addAssetPathMethod = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, pluginFilePath);
            Resources r = context.getResources();
            resources = new Resources(assetManager, r.getDisplayMetrics(), r.getConfiguration());
        } catch (Exception e) {
        }
    }

    public ClassLoader getClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

}
