package id.my.alvinq.prokitid.app.libs;

import dalvik.system.DexClassLoader;
import android.content.Context;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import org.json.JSONObject;
import org.levimc.launcher.util.Logger;

public class LibsManager {
  private final Context context;
  private final File cDir;
  //private final DexClassLoader dcl;
  public LibsManager(Context ctx) {
    String dirPath = ctx.getDir("alvinqid", Context.MODE_PRIVATE).getAbsolutePath();
    File cDir = new File(dirPath, "cache/dexout");
    if(!cDir.exists()) cDir.mkdirs();
    this.cDir = cDir;
    this.context = ctx;
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
    
    DexClassLoader dcl;
    if(getNaviteLibsFromManifest(file)) {
      //File nativePath = new File(file, "native
      File libPath = new File(file.getName().substring(0, file.getName().lastIndexOf(".")) + "/native");
      if(!libPath.exists()) {
        libPath.mkdirs();
      } else {
        if(!libPath.isDirectory()) {
          libPath.delete();
          libPath.mkdirs();
        }
      }
      try {
      copyFolderFromJar(file.getAbsolutePath(), "native", libPath);
      dcl = new DexClassLoader(file.getAbsolutePath(),this.cDir.getAbsolutePath(),libPath.getAbsolutePath(),this.context.getClassLoader());
      } catch (IOException e) {}
    } else {
      dcl = new DexClassLoader(file.getAbsolutePath(),this.cDir.getAbsolutePath(),null,this.context.getClassLoader());
    }
    
    String className = getMainClassFromManifest(file);
    if (className == null) {
        Logger.get().error("main class tidak ditemukan di manifest.json di jar: " + file.getName());
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
  private static String getMainClassFromManifest(File jarFile) {
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
            if(!json.has("main")) return null;
            return json.getString("main");

        } catch (Exception e) {
            Logger.get().error("Error!: " + e.toString());
        }
        return null;
  }

public void copyFileFromJar(String jarFilePath, String sourceFileName, File destFile) throws IOException {
    try (JarFile jarFile = new JarFile(jarFilePath)) {
        JarEntry jarEntry = jarFile.getJarEntry(sourceFileName);
        try (InputStream in = jarFile.getInputStream(jarEntry);
             FileOutputStream out = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}

public void copyFolderFromJar(String jarFilePath, String sourceFolderName, File destFolder) throws IOException {
    try (JarFile jarFile = new JarFile(jarFilePath)) {
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.getName().startsWith(sourceFolderName + "/")) {
                String fileName = entry.getName().substring(sourceFolderName.length() + 1);
                File destFile = new File(destFolder, fileName);
                destFile.getParentFile().mkdirs();
                try (InputStream in = jarFile.getInputStream(entry);
                     FileOutputStream out = new FileOutputStream(destFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }
        }
    }
}
  private static Boolean getNaviteLibsFromManifest(File jarFile) {
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
            if(!json.has("native")) return false;
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
