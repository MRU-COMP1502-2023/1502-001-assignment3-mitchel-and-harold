package application;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String LOG_FILE_NAME = "app.log";
    private static final String DEBUG_LEVEL = "DEBUG";
    private static final String INFO_LEVEL = "INFO";
    private static final String WARNING_LEVEL = "WARNING";
    private static final String ERROR_LEVEL = "ERROR";
    
    private String logFile;
    
    public Logger() {
        this.logFile = LOG_FILE_NAME;
    }
    
    public Logger(String logFile) {
        this.logFile = logFile;
    }
    
    private void log(String level, String message) {
        try {
            FileWriter writer = new FileWriter(logFile, true);
            writer.write(level + ": " + message + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
    
    public void debug(String message) {
        log(DEBUG_LEVEL, message);
    }
    
    public void info(String message) {
        log(INFO_LEVEL, message);
    }
    
    public void warning(String message) {
        log(WARNING_LEVEL, message);
    }
    
    public void error(String message) {
        log(ERROR_LEVEL, message);
    }
}
