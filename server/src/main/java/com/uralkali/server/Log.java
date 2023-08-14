/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author brzsmg
 */
public class Log {
    
    static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    static void writeMessage(int priority, String message) {
        String p = "[SRV] [" + getDate() + "] ";
        switch (priority) {
            case 1:
                p += "[ERROR] ";
                break;
            case 2:
                p += "[WARNING] ";
                break;
            case 4:
                p += "[INFO] ";
                break;
            default:
                p = "";
                break;
        }
        System.out.println(p + message);
    }
    
    public static void l(String message) {
        writeMessage(0, message);
    }
    
    public static void i(String message) {
        writeMessage(4, message);
    }
    
    public static void w(String message) {
        writeMessage(2, message);
    }
    
    public static void e(String message) {
        writeMessage(1, message);
    }
}
