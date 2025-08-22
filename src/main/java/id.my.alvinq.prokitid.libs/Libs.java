package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    CacheLogger.logAllCacheFiles(context);
    Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    //File lcon = new File(context.getCacheDir() + "/prokitid/native/libmod.so");
   // System.loadLibrary("mod");
    FileUtils.copyFile("/storage/emulated/0/source/from.txt", "/storage/emulated/0/dest/target.txt")
  }
}
