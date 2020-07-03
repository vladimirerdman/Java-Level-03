package lesson6.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Example3Levels {
    // OFF
    // SEVERE
    // WARNING
    // INFO
    // CONFIG
    // FINE
    // FINER
    // FINEST
    // ALL

    private static final Logger logger = Logger.getLogger(Example3Levels.class.getName());

    public static void main(String[] args) {
        Handler h = new ConsoleHandler();
        h.setLevel(Level.FINEST);
        logger.setLevel(Level.FINEST);
        logger.addHandler(h);
        logger.log(Level.SEVERE, "SEVERE");
        logger.log(Level.INFO, "INFO");
        logger.log(Level.CONFIG, "CONFIG");
        logger.info("");
    }
}
