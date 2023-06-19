package com.venkata.logging.service;


import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class SimpleLogger {
    private static final String LOG_FILE = "application.log";
    private static final DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final LogLevel DEFAULT_LOG_LEVEL = LogLevel.INFO;

    private static LogLevel logLevel;

    static {
        initializeLogLevel();
    }

    /**
     * This method logs the message
     * @param level
     * @param message
     */
    public static void log(LogLevel level, String message) {

        if (level.ordinal() <= logLevel.ordinal()) {
            String formattedMessage = formatLogMessage(level, message);
            writeLogToFile(level, formattedMessage);
        }
    }

    /**
     * This method reads the application.properties file and loads the log level
     */
    private static void initializeLogLevel() {
        try (InputStream input = SimpleLogger.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String level = properties.getProperty("log.level");
            if (level != null) {
                logLevel = LogLevel.valueOf(level.toUpperCase());
            } else {
                logLevel = DEFAULT_LOG_LEVEL;
            }
        } catch (IOException | IllegalArgumentException e) {
            logLevel = DEFAULT_LOG_LEVEL;
        }
    }

    /**
     * This method initializes the log level with passed value
     * @param level
     */
    public static void initializeLogLevel(String level) {
            if (level != null) {
                logLevel = LogLevel.valueOf(level.toUpperCase());
            } else {
                logLevel = DEFAULT_LOG_LEVEL;
            }
    }

    /**
     * This method formats the message and adds timestamp to the log message
     * @param level
     * @param message
     * @return
     */
    private static String formatLogMessage(LogLevel level, String message) {
        String timestamp = TIMESTAMP_FORMAT.format(new Date());
        return String.format("%s [%s] - %s", timestamp, level.name(), message);
    }

    /**
     *
     * @param message
     */
    private static void writeLogToFile(String message) {

        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets the target based on the Log Level
     * @param level
     * @param message
     */
    private static void writeLogToFile(LogLevel level, String message) {

        switch (level){
            case DEBUG:
                System.out.println("Console " + message + System.lineSeparator() );
                break;
            case WARNING:
                System.out.println("File System " + message + System.lineSeparator() );
                break;
            case ERROR:
                System.out.println("Server API " + message + System.lineSeparator() );
                break;
            default:
                System.out.println("EMail " + message + System.lineSeparator() );
        }

    }

    /**
     * Sets log level
     * @param level
     */
    public static void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    /**
     * Gets the log level
     * @return
     */
    public static LogLevel getLogLevel() {
        return logLevel;
    }

    /**
     * This method is used for Info logging
     * @param message
     */
    public static void info(String message){
        if (LogLevel.INFO.ordinal() <= logLevel.ordinal()) {
            String formattedMessage = formatLogMessage(LogLevel.INFO, message);
            writeLogToFile(LogLevel.INFO, formattedMessage);
        }
    }

    /**
     * This method is used to log Debug messages
     * @param message
     */
    public static void debug(String message){
        if (LogLevel.DEBUG.ordinal() <= logLevel.ordinal()) {
            String formattedMessage = formatLogMessage(LogLevel.DEBUG, message);
            writeLogToFile(LogLevel.DEBUG, formattedMessage);
        }
    }

    /**
     * This method is used to log Warning messages
     * @param message
     */
    public static void warning(String message){
        if (LogLevel.WARNING.ordinal() <= logLevel.ordinal()) {
            String formattedMessage = formatLogMessage(LogLevel.WARNING, message);
            writeLogToFile(LogLevel.WARNING, formattedMessage);
        }
    }

    /**
     * This method is used to log Error messages
     * @param message
     */
    public static void error(String message){
        if (LogLevel.ERROR.ordinal() <= logLevel.ordinal()) {
            String formattedMessage = formatLogMessage(LogLevel.ERROR, message);
            writeLogToFile(LogLevel.ERROR, formattedMessage);
        }
    }
}
