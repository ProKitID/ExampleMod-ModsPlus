package id.my.alvinq.prokitid.app.libs;

import dalvik.system.DexClassLoader;
import android.content.Context;
import java.io.*;
import java.lang.reflect.Method;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import org.json.JSONObject;

public class LibsManager {
  private final Context context;
  private final File cDir;
  //private final DexClassLoader dcl;
  public LibsManager(Context ctx) {
    String dirPath = context.getDir("alvinqid", Context.MODE_PRIVATE).getAbsolutePath();
    File cDir = new File(dirPath, "cache/dexout");
    if(!cDir.exists()) cDir.mkdirs();
    this.cDir = cDir;
    this.context = context;
  }

  public static LibsManager get(Context ctx) {
    LibsManager lm = new LibsManager(ctx);
    return lm;
  }
  private static String getIsIis(File fl) {
    File file = new File(fl, "native");
    if(getNaviteLibsFromManifest(fl)) return file.getAbsolutePath();
    return null;
  }
  public void loadLib(File file) {
    
    DexClassLoader dcl = new DexClassLoader(file.getAbsolutePath(),this.cDir.getAbsolutePath(),getIsIis(file),this.context.getClassLoader());
    String className = getMainClassFromManifest(jarFile);
    if (className == null) {
        Logger.get().error("main class tidak ditemukan di manifest.json di jar: " + jarFile.getName());
        return;
    }
    Logger.get().info("Loaded Class -> " + className);
    try {
        Class<?> clazz = dcl.loadClass(className);
        Method method = clazz.getDeclaredMethod("onLoad", Context.class);
        method.invoke(null, this.context); // static method, no instance
        Logger.get().info("Loaded Class -> " + className + " Done!");
    } catch (Exception e) {
        Throwable real = e instanceof InvocationTargetException ? ((InvocationTargetException) e).getCause() : e;
        String errorLog = getStackTraceAsString(real);
        //System.err.println(errorLog); // atau kirim ke LeviLogger
        Logger.get().error("Error!: " + errorLog);
    }
  }
  /*
  public void unLoadLib(File file) {
    DexClassLoader dcl = new DexClassLoader(file.getAbsolutePath(),this.cDir.getAbsolutePath(),null,this.context.getClassLoader());
    String className = getMainClassFromManifest(jarFile);
    if (className == null) {
        Logger.get().error("main class tidak ditemukan di manifest.json di jar: " + jarFile.getName());
        return;
    }
    Logger.get().info("Unloaded Class -> " + className);
    try {
        Class<?> clazz = dcl.loadClass(className);
        Method method = clazz.getDeclaredMethod("onLoad", Context.class);
        method.invoke(null, this.context); // static method, no instance
        Logger.get().info("Unloaded Class -> " + className + " Done!");
    } catch (Exception e) {
        Throwable real = e instanceof InvocationTargetException ? ((InvocationTargetException) e).getCause() : e;
        String errorLog = getStackTraceAsString(real);
        //System.err.println(errorLog); // atau kirim ke LeviLogger
        Logger.get().error("Error!: " + errorLog);
    }
  }
  */
  private String getMainClassFromManifest(File jarFile) {
        try (JarFile jar = new JarFile(jarFile)) {
            JarEntry entry = jar.getJarEntry("manifest.json");
            if (entry == null) return null;

            InputStream input = jar.getInputStream(entry);
            StringBuilder jsonBuilder = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }
            }

            JSONObject json = new JSONObject(jsonBuilder.toString());
            if(!json.has("main") return null;
            return json.getString("main");

        } catch (Exception e) {
            Logger.get().error("Error!: " + e.toString());
        }
        return null;
  }
  private Boolean getNaviteLibsFromManifest(File jarFile) {
        try (JarFile jar = new JarFile(jarFile)) {
            JarEntry entry = jar.getJarEntry("manifest.json");
            if (entry == null) return null;

            InputStream input = jar.getInputStream(entry);
            StringBuilder jsonBuilder = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }
            }

            JSONObject json = new JSONObject(jsonBuilder.toString());
            if(!json.has("native") return false;
            return json.getBoolean("native");

        } catch (Exception e) {
            Logger.get().error("Error!: " + e.toString());
        }
        return null;
  }
  public static String getStackTraceAsString(Throwable th) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        th.printStackTrace(pw);
        return sw.toString();
  }
  
}
