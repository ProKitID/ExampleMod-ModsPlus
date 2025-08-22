package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    CacheLogger.logAllCacheFiles(context);
    Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    File cacheDir = context.getCacheDir();
    File dataDir = new File(cacheDir, "libs/prokitid");
    if(!dataDir.exists) dataDir.mkdirs();
    File mod = new File(dataDir, "libmod.so");
    //if(mod.exists) mod.delete();
    File em = new File("/storage/emulated/0/alvinqid/mods/libmod.so");
    FileUtils.copyFile(em.getAbsolutePath(),mod.getAbsolutePath());
    System.load(mod.getAbsolutePath());
    FileUtils.copyFolder(context.getCacheDir().getAbsolutePath(), "/storage/emulated/0/alvinqid/cache");
  }
}
