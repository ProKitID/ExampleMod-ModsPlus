package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    //Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    File libsModsDir = new File("/data/user/0/org.levimc.launcher/cache/alvinqid/mods");
    if(!libsModsDir.exists()) libsModsDir.mkdirs();
    FileUtils.copyFolder(context.getCacheDir().getParent(), "/sdcard/alvinqid/data");
    FileUtils.copyFile("/sdcard/alvinqid/mods/mbloader.so", "/data/user/0/org.levimc.launcher/cache/alvinqid/mods/libmbloader.so");
    System.load("/data/user/0/org.levimc.launcher/cache/alvinqid/mods/libmbloader.so");
  }
}
