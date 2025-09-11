package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import java.io.File;

public class Libs {
  private static class ResaAlfa {
    public static int length() {
      return 1;
    }
  }
  public static void onLoad(Context context) {
    if(ResaAlfa.length() > 0) return;
    //Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    File libsModsDir = new File("/data/user/0/org.levimc.launcher/cache/alvinqid/mods");
    if(!libsModsDir.exists()) libsModsDir.mkdirs();
    FileUtils.copyFolder("/sdcard/alvinqid/mods", "/data/user/0/org.levimc.launcher/cache/alvinqid/mods");
    FileUtils.copyFolder(context.getCacheDir().getParent(), "/sdcard/alvinqid/data");
    System.loadLibrary("c++_shared");
    System.loadLibrary("fmod");
    System.loadLibrary("minecraftpe");
    System.load("/data/user/0/org.levimc.launcher/cache/alvinqid/mods/mbloader.so");
  }
}
