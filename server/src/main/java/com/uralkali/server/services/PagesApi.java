/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.services;

import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.net.web.WebServer;
import com.swlibs.common.types.DateTime;
import com.uralkali.common.models.dto.AppVersion;
import com.uralkali.server.Application;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.exceptions.ServerException;
import java.util.Date;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author brzsmg
 */
public class PagesApi {
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(0)) {
            case "" :
                result = index(webRequest, router);
                break;
            case "status" :
                result = status(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    private  static int index(WebRequest webRequest, ClientRouter router) {
        WebResponse response = new WebResponse(webRequest);
        response.setContentType("text/html");
        response.send("<html><head>");
        response.send("<meta http-equiv=\"refresh\" content=\"0; URL=/status/\">");
        response.send("</head></html>");
        response.sendHeaders(200);
        return 200;
    }
    
    private  static int status(WebRequest webRequest, ClientRouter router) {
        Date startDate = new Date(webRequest.getStartTime());
        
        Application app = Application.getInstance();
        
        WebResponse response = new WebResponse(webRequest);
        response.setContentType("text/html");
        
        AppVersion sv = app.getServerVersion();
        AppVersion cv = app.getClientMinVersion();
        
        response.send("<html><head>");
        if (webRequest.getPath(1).equals("auto")) {
            response.send("<meta http-equiv=\"refresh\" content=\"5\"><body>");
        }
        response.send("</head><body>");

        response.send("<h2>Uralkali Mobile Server</h2>");
        
        response.send("<h3>Client Application</h3>");
        response.send("<div><span>Min version: </span><b>" + cv.getName() + " ("+ cv.getNumber() + ")</b></div>");
        
        response.send("<h3>Server</h3>");
        response.send("<div><span>Server address: </span><b>" + webRequest.getServerAddress()+ "</b></div>");
        response.send("<div><span>Version: </span><b>" + sv.getName() + " ("+ sv.getNumber() + ")</b></div>");
        response.send("<div><span>User: </span><b>" + app.getSystemUserName() + "</b></div>");
        response.send("<div><span>Base name: </span><b>" + app.getBaseName().toUpperCase() + "</b></div>");
        response.send("<div><span>Start time (UTC): </span><b>" + DateTime.toFormatUTC(app.getStartDate(), "yyyy-MM-dd HH:mm:ss") + "</b></div>");
        response.send("<div><span>Now time (UTC): </span><b>" + DateTime.toFormatUTC(DateTime.now(), "yyyy-MM-dd HH:mm:ss") + "</b></div>");
        
        response.send("<h3>Statistic</h3>");
        response.send("<div><span>Sessions: </span><b>" + router.getCountSessions() + "</b></div>");
        
        WebServer server = app.getServer();
        
        int busy = server.getBusyWorkers();
        int max = server.getMaxWorkers();
        if(busy >= max) {
            response.send("<div><span>Workers: </span><b style=\"color:red;\">" + server.getBusyWorkers()+ " / "+ server.getMaxWorkers() + "</b></div>");
        } else if(busy >= (max / 4)) {
            response.send("<div><span>Workers: </span><b style=\"color:orange;\">" + server.getBusyWorkers()+ " / "+ server.getMaxWorkers() + "</b></div>");
        } else {
            response.send("<div><span>Workers: </span><b style=\"color:green;\">" + server.getBusyWorkers()+ " / "+ server.getMaxWorkers() + "</b></div>");
        }
        
        long fm = Runtime.getRuntime().freeMemory();
        long tm = Runtime.getRuntime().totalMemory();
        long um = tm - fm;
        long mm = Runtime.getRuntime().maxMemory();
        
        if(um >= (mm - (mm / 4))) {
            response.send("<div><span>Memory: </span><b style=\"color:red;\">" + FileUtils.byteCountToDisplaySize(um)+ " / "+ FileUtils.byteCountToDisplaySize(tm) + " (Max " + FileUtils.byteCountToDisplaySize(mm) + ")</b></div>");
        } else if(um >= (mm / 2)) {
            response.send("<div><span>Memory: </span><b style=\"color:orange;\">" + FileUtils.byteCountToDisplaySize(um)+ " / "+ FileUtils.byteCountToDisplaySize(tm) + " (Max " + FileUtils.byteCountToDisplaySize(mm) + ")</b></div>");
        } else {
            response.send("<div><span>Memory: </span><b style=\"color:green;\">" + FileUtils.byteCountToDisplaySize(um)+ " / "+ FileUtils.byteCountToDisplaySize(tm) + " (Max " + FileUtils.byteCountToDisplaySize(mm) + ")</b></div>");
        }
        response.send("<br/>");
        
        response.send("<h3>Modules</h3>");
        response.send("<ul>");
        response.send("<li>APP - Main module.</li>");
        response.send("<li>SWJC - Java common.</li>");
        response.send("<li>UKAPI - Uralkali API.</li>");
        response.send("</ul><br/>");

        response.send("<br/>");
        response.send("<div><span>IP: </span><b>" + webRequest.getAddress() + "</b> | RT: " + DateTime.getDifference(DateTime.now(), startDate) + " ms | ");
        
        if (webRequest.getPath(1).equals("auto")) {
            response.send("Auto: <a href=\"/status/\">OFF</a></div>");
        } else {
            response.send("Auto: <a href=\"/status/auto/\">ON</a></div>");
        }
        
        response.send("</body></html>");
        response.sendHeaders(200);
        return 200;
    }
    
}
