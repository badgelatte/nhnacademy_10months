import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {
    private final static Logger logger = Logger.getLogger("myLogger");

    public static Logger getLogger() {
        logger.setLevel(Level.INFO);    // 여기에 INFO 대신 SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
        return logger;
    }
}