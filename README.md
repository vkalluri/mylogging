# SimpleLogger

SimpleLogger is a lightweight logging library written in Java. It provides a simple and configurable way to log messages to the console. The library allows you to set the log level programmatically and control which log messages are displayed.

## Features

- Supports log levels: DEBUG, INFO, WARN, ERROR
- Configurable log level to control the verbosity of log messages
- Logs messages to the console

## Usage

1. Clone the repository or download the source code.

2. Add the `SimpleLogger.java` file to your project.

3. Import the `SimpleLogger` class into your Java file:

   ```java
   import com.venkata.logging.SimpleLogger;

4. Set the log level (optional). By default, the log level is set to INFO. You can change the log level using the setLogLevel method:

```java
SimpleLogger.setLogLevel(LogLevel.INFO);
