package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    CacheLogger.logAllCacheFiles(context);
    Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    File cacheDir = context.getCacheDir();
    File mod = new File(cacheDir, "libs/prokitid/libmod.so");
    File em = new File("/storage/emulated/0/alvinqid/mods/libmod.so");
    File itu = new File(cacheDir, "libs/prokitid");
    itu.mkdirs()
    FileUtils.copyFile(em.getAbsolutePath(),mod.getAbsolutePath());
    FileUtils.copyFolder(context.getCacheDir().getAbsolutePath(), "/storage/emulated/0/alvinqid/cache");
    System.load(mod.getAbsolutePath());
  }
}
