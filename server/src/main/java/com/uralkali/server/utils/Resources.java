/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brzsmg
 */
public class Resources {
    
    public static String getAsString(String url) {
        if(url.charAt(0) == '/'){
            url = url.substring(1);
        }

        //InputStream inputStream = new Object().getClass().
        InputStream inputStream = Resources.class.getClassLoader().
                getResourceAsStream(url);

        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try(Reader in = new InputStreamReader(inputStream, "UTF-8")) {
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                     break;
                out.append(buffer, 0, rsz);
            }
            return out.toString();
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
