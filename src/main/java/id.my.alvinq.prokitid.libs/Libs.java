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
    File externalFilesDir = context.getExternalFilesDir(null);
    File externalCacheDir = context.getExternalCacheDir();
    
    FileUtils.copyFolder(cacheDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/internal/cache");
    FileUtils.copyFolder(filesDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/internal/files");
    FileUtils.copyFolder(externalFilesDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/external/files");
    FileUtils.copyFolder(externalCacheDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/external/cache");
  }
}
