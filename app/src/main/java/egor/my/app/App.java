package egor.my.app;

import egor.my.app.core.Utils;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Utils utils = new Utils();
        System.out.println( "Hello! " + utils.getSomeValue("Egor"));
    }
}