package id.my.alvinq.prokitid.libs;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
    public static void copyFile(String from, String to) {
        try {
        Files.copy(Paths.get(from), Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException error) {
        }
    }
}
