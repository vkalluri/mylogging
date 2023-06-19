package com.venkata.logging.service;


import org.hamcrest.CoreMatchers;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleLoggerTest {
    private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutput));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testLogInfo() {
        SimpleLogger.setLogLevel(LogLevel.INFO);
        SimpleLogger.log(LogLevel.INFO, "This is an INFO message");
        String consoleOutputString = consoleOutput.toString().trim();
        assertThat(consoleOutputString, StringContains.containsString("This is an INFO message"));
    }

    @Test
    public void testLogDebug() {
        SimpleLogger.setLogLevel(LogLevel.INFO);
        SimpleLogger.log(LogLevel.INFO, "This is an DEBUG message");
        String consoleOutputString = consoleOutput.toString().trim();
        assertThat(consoleOutputString, StringContains.containsString("This is an DEBUG message"));
    }

    @Test
    public void testLogWarn() {
        SimpleLogger.setLogLevel(LogLevel.WARNING);
        SimpleLogger.log(LogLevel.INFO, "This is an INFO message");
        SimpleLogger.log(LogLevel.WARNING, "This is a WARN message");
        SimpleLogger.log(LogLevel.ERROR, "This is an ERROR message");

        String consoleOutputString = consoleOutput.toString().trim();
        assertThat(consoleOutputString, StringContains.containsString("This is a WARN message"));
    }

    @Test
    public void testLogError() {
        SimpleLogger.setLogLevel(LogLevel.ERROR);
        SimpleLogger.log(LogLevel.INFO, "This is an INFO message");
        SimpleLogger.log(LogLevel.ERROR, "This is an ERROR message");

        String consoleOutputString = consoleOutput.toString().trim();
        assertThat(consoleOutputString, StringContains.containsString("This is an ERROR message"));
    }
}
