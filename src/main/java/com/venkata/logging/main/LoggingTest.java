package com.venkata.logging.main;

import com.venkata.logging.service.LogLevel;
import com.venkata.logging.service.SimpleLogger;

public class LoggingTest {
    public static void main(String[] args) {
        SimpleLogger.log(LogLevel.DEBUG, "Application started");
        SimpleLogger.log(LogLevel.ERROR, "An error occurred");
        SimpleLogger.info("Info Message");
        SimpleLogger.debug("Debug Message");
        SimpleLogger.warning("Warning Message");
        SimpleLogger.error("Error Message");
    }
}
