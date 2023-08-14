package com.swlibs.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author brzsmg
 */
public class Properties {
    private static java.util.Properties cProperties = null;
    
    private static void init() {
        if(cProperties == null) {
            try {
                InputStream inputStream;
                File file = new File("server.cfg");
                if(file.exists()) {
                    inputStream = new FileInputStream(file);
                } else {
                    inputStream = Properties.class.getResourceAsStream("/server.properties");
                }
                cProperties = new java.util.Properties();
                cProperties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Read properties failed.");
            }
        }
    }
    
    public static String getProperty(String property) {
        init();
        return cProperties.getProperty(property);
    }
    
    public static int getInt(String property) {
        init();
        return Integer.parseInt(cProperties.getProperty(property));
    }
    
}
