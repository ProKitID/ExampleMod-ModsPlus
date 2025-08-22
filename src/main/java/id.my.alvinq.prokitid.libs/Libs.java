package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    CacheLogger.logAllCacheFiles(context);
    Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    //System.loadLibrary("mod");
    FileUtils.copyFolder(context.getCacheDir().getAbsolutePath(), "/storage/emulated/0/alvinqid/cache");
  }
}
