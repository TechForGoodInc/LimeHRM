package limehrm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class LoggerUtil {
    private static Logger logger;
    
    enum LogType {
        TRACE, DEBUG, INFO, WARN, ERROR
    }
    
    public LoggerUtil(String className) {
        logger = LoggerFactory.getLogger(className);
    }
    
    /**
     * Log Levels Explanation:
     * <p>
     * Trace - Only when I would be "tracing" the code and trying to find one part of a function specifically.
     * <p>
     * Debug - Information that is diagnostically helpful to people more than just developers (IT, sysadmins, etc.).
     * <p>
     * Info - Generally useful information to log (service start/stop, configuration assumptions, etc).
     * ------ Info I want to always have available but usually don't care about under normal circumstances.
     * ------ This is my out-of-the-box config level.
     * <p>
     * Warn - Anything that can potentially cause application oddities, but for which I am automatically recovering.
     * ------ (Such as switching from a primary to backup server, retrying an operation, missing secondary data, etc.)
     * <p>
     * Error - Any error which is fatal to the operation, but not the service or application (can't open a required file, missing data, etc.).
     * ------- These errors will force user (administrator, or direct user) intervention.
     * ------- These are usually reserved (in my apps) for incorrect connection strings, missing services, etc.
     * <p>
     * Derived from https://stackoverflow.com/a/2031209/9150279
     */
    
    private UUID log(LogType logType, String message, Object... args) {
        UUID uuid = UUID.randomUUID();
        String loggerMessage = String.format("[Correlation ID: %s] ", uuid);
        loggerMessage += message;
        
        switch (logType) {
            case TRACE -> logger.trace(message, args);
            case DEBUG -> logger.debug(message, args);
            case INFO -> logger.info(message, args);
            case WARN -> logger.warn(message, args);
            case ERROR -> logger.error(message, args);
        }
        
        return uuid;
    }
    
    public UUID logTrace(String message, Object... args) {
        return log(LogType.TRACE, message, args);
    }
    
    public UUID logDebug(String message, Object... args) {
        return log(LogType.DEBUG, message, args);
    }
    
    public UUID logInfo(String message, Object... args) {
        return log(LogType.INFO, message, args);
    }
    
    public UUID logWarn(String message, Object... args) {
        return log(LogType.WARN, message, args);
    }
    
    public UUID logError(String message, Object... args) {
        return log(LogType.ERROR, message, args);
    }
    
    public static LoggerUtil getLogger(String className) {
        return new LoggerUtil(className);
    }
}