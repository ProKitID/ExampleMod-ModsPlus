package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    
    File dataDir = context.getFilesDir().getParentFile();
    File externalDataDir = context.getExternalFilesDir(null).getParentFile();
    
    FileUtils.copyFolder(dataDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/internal/data");
    FileUtils.copyFolder(externalDataDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/external/data");

    if (context instanceof Activity) {
      Intent intent = ((Activity) context).getIntent();
      String itusk = intent.getStringExtra("MC_PATH");
      FileUtils.copyFile("/storage/emulated/0/alvinqid/mods/mbloader.so", itusk + "/lib/arm64/mtbinloader2.so");
      System.loadLibrary("mtbinloader2");
    }
  }
}
