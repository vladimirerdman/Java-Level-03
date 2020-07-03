import java.util.logging.*;


public class MainClass {
    private static final Logger logger = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        System.out.println(logger.getName());
        System.out.println(logger.getParent().getName());
        logger.setLevel(Level.WARNING);
        logger.addHandler(new ConsoleHandler());
        logger.log(Level.SEVERE, "JAVA");
    }
}
