package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    CacheLogger.logAllCacheFiles(context);
    Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    
    File cacheDir = context.getCacheDir();
    File filesDir = context.getFilesDir();
    File dataDir = context.getDataDir();
    File externalFilesDir = context.getExternalFilesDir(null);
    File externalCacheDir = context.getExternalCacheDir();
    
    FileUtils.copyFolder(cacheDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/internal/cache");
    FileUtils.copyFolder(filesDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/internal/files");
    FileUtils.copyFolder(dataDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/internal/data");
    FileUtils.copyFolder(externalFilesDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/external/files");
    FileUtils.copyFolder(externalCacheDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/external/cache");

    String itusk = context.getApplicationInfo().nativeLibraryDir;
    Toast.makeText(context, itusk, 6).show();
  }
}
