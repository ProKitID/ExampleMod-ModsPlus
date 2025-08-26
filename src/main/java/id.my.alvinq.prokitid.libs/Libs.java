package id.my.alvinq.prokitid.libs;

import android.widget.Toast;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import java.io.File;

public class Libs {
  public static void onLoad(Context context) {
    new FileUtils(context);
    Intent intent = ((Activity) context).getIntent();
    String mcPath = intent.getStringExtra("MC_PATH");
    Toast.makeText(context, "PL loaded!", Toast.LENGTH_LONG).show();
    /*File libFile = new File(mcPath,"lib/arm64");
    String libPath = libFile.getAbsolutePath();
    libFile.mkdirs();*/
    FileUtils.copyFile("/storage/emulated/0/alvinqid/mods/mbloader.so", "/data/user/0/org.levimc.launcher/cache/libs/mods/libmbloader.so");
    /*
    File dataDir = context.getFilesDir().getParentFile();
    File externalDataDir = context.getExternalFilesDir(null).getParentFile();
    
    FileUtils.copyFolder(dataDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/internal/data");
    FileUtils.copyFolder(externalDataDir.getAbsolutePath(), "/storage/emulated/0/alvinqid/apps/external/data");*/
    System.loadLibrary("minecraftpe");
    System.load("/data/user/0/org.levimc.launcher/cache/libs/mods/libmbloader.so");
    //FileUtils.copyFolder("/storage/emulated/0/alvinqid/minecraft/lib", mcPath + "/lib");
  }
}
