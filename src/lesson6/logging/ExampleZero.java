package lesson6.logging;

import java.io.IOException;
import java.util.logging.*;

public class ExampleZero {
    private static final Logger logger = Logger.getLogger(ExampleZero.class.getName());

    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.ALL);

        Handler h = new FileHandler("1.txt");
        h.setFormatter(new XMLFormatter());
        h.setLevel(Level.ALL);
        h.setFilter(new Filter() {
            @Override
            public boolean isLoggable(LogRecord record) {
                if (record.getMessage().startsWith("Java"))
                    return true;
                return false;
            }
        });
        logger.addHandler(h);
        logger.log(Level.CONFIG, "Something wrong...");
        logger.log(Level.CONFIG, "Java 123");
        logger.log(Level.CONFIG, "java...");
        logger.log(Level.CONFIG, "Java 123");



    }

}
