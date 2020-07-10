package iChat;

import java.io.IOException;
import java.util.logging.*;

public class MainLogger{
    private String logMessage;
    private static final Logger logger = Logger.getLogger(MainLogger.class.getName());

    public void setLogMessage(String logMessage) throws IOException {
        logger.setLevel(Level.ALL);
        Handler handler = new FileHandler("log.txt");
        handler.setFormatter(new XMLFormatter());
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.log(Level.CONFIG, logMessage);
    }

}
