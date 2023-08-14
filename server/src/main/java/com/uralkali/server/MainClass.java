package com.uralkali.server;

/**
 *
 * @author brzsmg
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int code = Application.getInstance().execute();
        System.exit(code);
    }
    
}
