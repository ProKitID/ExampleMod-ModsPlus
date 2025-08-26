package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    new FileUtils(context);
    //Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    FileUtils.copyFolder(context.getCacheDir().getParent(), "/sdcard/alvinqid/data");
    FileUtils.copyFile("/storage/emulated/0/alvinqid/mods/mbloader.so", "/data/user/0/org.levimc.launcher/cache/libs/mods/libmbloader.so");
    System.load("/data/user/0/org.levimc.launcher/cache/libs/mods/libmbloader.so");
  }
}
