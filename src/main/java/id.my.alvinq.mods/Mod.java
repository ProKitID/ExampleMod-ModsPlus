package id.my.alvinq.mods;

import android.widget.Toast;
import android.content.Context;

public class Mod {
  public static void onLoad(Context context) {
    Toast.makeText(context, "Pesan yang ingin ditampilkan", Toast.LENGTH_SHORT).show();
  }
}
