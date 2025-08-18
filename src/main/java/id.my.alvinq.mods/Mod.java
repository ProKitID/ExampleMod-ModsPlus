package id.my.alvinq.mods;

import android.widget.Toast;
import android.content.Context;
import org.levimc.launcher.util.Logger;
import java.io.File;

public class Mod {
  public static void onLoad(Context context) {
    logFiles(context)
    Toast.makeText(context, "Pesan yang ingin ditampilkan", Toast.LENGTH_SHORT).show();
  }
  public static void logFiles(Context context) {
        File directory = context.getFilesDir();
        logFiles(directory, "");
  }

  private static void logFiles(File directory, String indent) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    Logger.get().info("FileLogger", indent + file.getName() + "/");
                    logFiles(file, indent + "  ");
                } else {
                    Logger.get().info("FileLogger", indent + file.getName());
                }
            }
        }
  }
}
