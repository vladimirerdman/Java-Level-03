package lesson6.logging;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.*;

public class Example1Base {

    private static final Logger logger = Logger.getLogger(Example1Base.class.getName());

    public static void main(String[] args) {
        Example1Base ex1 = new Example1Base();
        ex1.tryToDivBy0();
    }

    public void tryToDivBy0() {
        try{
            int a = 5 / 0;
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, e.getMessage(), e.getMessage());
        }
    }
}
