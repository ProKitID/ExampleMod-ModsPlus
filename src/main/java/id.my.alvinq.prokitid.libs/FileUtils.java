package id.my.alvinq.prokitid.libs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtils {
    public static void copyFile(String from, String to) throws IOException {
        Files.copy(
            Path.of(from), 
            Path.of(to), 
            StandardCopyOption.REPLACE_EXISTING // overwrite jika file sudah ada
        );
    }
}
