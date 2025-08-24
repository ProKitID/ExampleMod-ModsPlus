package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    CacheLogger.logAllCacheFiles(context);
    Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    
    File dataDir = context.getFilesDir().getParentFile();
    File externalDataDir = context.getExternalFilesDir(null).getParentFile();
    
    FileUtils.copyFolder(dataDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/internal/data");
    FileUtils.copyFolder(externalDataDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/external/data");
    
    String itusk = context.mcPath;
    Toast.makeText(context, itusk, 6).show();
  }
}
