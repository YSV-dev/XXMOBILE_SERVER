package com.uralkali.server.services;

import com.swlibs.common.net.web.WebRequest;
import java.util.Map;

/**
 * Класс используемый только для дебага
 * @author Yanishen_SV
 */
public class ApiDebugTools {
    public static void printRequestParams(WebRequest webRequest){
        System.out.println("==========================");
            System.out.println("POST:");
            for(Map.Entry<String, String> entry : webRequest.getPostParams().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                System.out.println(key + " = " + value);
            }
            System.out.println("GET:");
            for(Map.Entry<String, String> entry : webRequest.getGetParams().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                System.out.println(key + " = " + value);
            }
            System.out.println("==========================");
    }
}
