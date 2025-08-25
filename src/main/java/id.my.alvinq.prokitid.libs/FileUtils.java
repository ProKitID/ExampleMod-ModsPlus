package id.my.alvinq.prokitid.libs;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {

    public static void copyFile(String from, String to) {
        try {
        Files.copy(Paths.get(from), Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception error) {
            Throwable real = (e instanceof InvocationTargetException)
                    ? ((InvocationTargetException) e).getCause()
                    : e;
            String errorLog = getStackTraceAsString(real);
            Toast.makeText(context, errorLog, Toast.LENGTH_LONG).show();
            Logger.get().error("Error!: " + errorLog);
        }
    }

    public static void copyFolder(String source, String target) {
        try {
        final Path from = Paths.get(source);
        final Path to = Paths.get(target);

        Files.walkFileTree(from, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetDir = to.resolve(from.relativize(dir));
                if (!Files.exists(targetDir)) {
                    Files.createDirectories(targetDir);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, to.resolve(from.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    } catch (IOException error) {
            Throwable real = (e instanceof InvocationTargetException)
                    ? ((InvocationTargetException) e).getCause()
                    : e;
            String errorLog = getStackTraceAsString(real);
            Toast.makeText(context, errorLog, Toast.LENGTH_LONG).show();
            Logger.get().error("Error!: " + errorLog);
    }
    }
}
