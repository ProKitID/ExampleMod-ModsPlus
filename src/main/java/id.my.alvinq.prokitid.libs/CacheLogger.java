package id.my.alvinq.prokitid.libs;

import android.content.Context;
import org.levimc.launcher.util.Logger;

import java.io.File;

public class CacheLogger {

    //private static final String TAG = "CacheLogger";

    public static void logAllCacheFiles(Context context) {
        if (context == null) {
            Logger.get().info(TAG, "Context is null, cannot get cache directory!");
            return;
        }

        File cacheDir = context.getCacheDir();
        if (cacheDir != null && cacheDir.exists()) {
            Logger.get().info(TAG, "Cache directory: " + cacheDir.getAbsolutePath());

            File[] files = cacheDir.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        Logger.get().info(TAG, "DIR : " + file.getAbsolutePath());
                    } else {
                        Logger.get().info(TAG, "FILE: " + file.getAbsolutePath() + " (" + file.length() + " bytes)");
                    }
                }
            } else {
                Logger.get().info(TAG, "Cache directory is empty.");
            }
        } else {
            Logger.get().info(TAG, "Cache directory does not exist.");
        }
    }
}
