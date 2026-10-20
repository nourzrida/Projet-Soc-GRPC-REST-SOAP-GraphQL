package util;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameLogger {
    private static final Path LOG_FILE = Paths.get("game.log");
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static GameLogger instance;

    private GameLogger() {
        try {
            if (!Files.exists(LOG_FILE)) Files.createFile(LOG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized GameLogger getInstance() {
        if (instance == null) instance = new GameLogger();
        return instance;
    }

    public synchronized void log(String type, String message) {
        String line = String.format("[%s] [%s] %s%n", LocalDateTime.now().format(FMT), type, message);
        try {
            Files.writeString(LOG_FILE, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

