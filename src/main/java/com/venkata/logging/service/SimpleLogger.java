package com.venkata.logging.service;


import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SimpleLogger {
    private static final String LOG_FILE = "application.log";
    private static final DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final LogLevel DEFAULT_LOG_LEVEL = LogLevel.INFO;

    private static LogLevel logLevel;

    static {
        initializeLogLevel();
    }

    public static void log(LogLevel level, String message) {

        if (level.ordinal() <= logLevel.ordinal()) {
            String formattedMessage = formatLogMessage(level, message);
            writeLogToFile(level, formattedMessage);
        }
    }


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

    private static void initializeLogLevel(String level) {
            if (level != null) {
                logLevel = LogLevel.valueOf(level.toUpperCase());
            } else {
                logLevel = DEFAULT_LOG_LEVEL;
            }
    }

    private static String formatLogMessage(LogLevel level, String message) {
        String timestamp = TIMESTAMP_FORMAT.format(new Date());
        return String.format("%s [%s] - %s", timestamp, level.name(), message);
    }

    private static void writeLogToFile(String message) {

        System.out.println(message + System.lineSeparator() );
    }

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

    public static void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    public static LogLevel getLogLevel() {
        return logLevel;
    }
}
