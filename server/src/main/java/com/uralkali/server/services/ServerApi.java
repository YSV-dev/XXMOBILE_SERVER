package com.uralkali.server.services;

import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.types.DateTime;
import com.uralkali.common.models.dto.AppVersion;
import com.uralkali.common.models.dto.ServerInfo;
import com.uralkali.server.Application;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.server.utils.Json;
import java.util.Date;

/**
 *
 * @author brzsmg
 */
public class ServerApi {
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(2)) {
            case "info" :
                result = info(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    private  static int info(WebRequest webRequest, ClientRouter router) {
        Date startDate = new Date(webRequest.getStartTime());
        Date requestTime = DateTime.now();
        
        Application app = Application.getInstance();
        
        AppVersion sv = app.getServerVersion();
        AppVersion cv = app.getClientMinVersion();

        long fm = Runtime.getRuntime().freeMemory();
        long tm = Runtime.getRuntime().totalMemory();
        long um = tm - fm;
        long mm = Runtime.getRuntime().maxMemory();
        
        ServerInfo info = new ServerInfo();
        info.setVersion(sv);
        info.setStartTime(startDate);
        info.setRequestTime(requestTime);
        info.setAddress(webRequest.getServerAddress());
        info.setUser(app.getSystemUserName());
        info.setSid(app.getBaseName().toUpperCase());
        info.setFreeMemory(fm);
        info.setFreeMemory(tm);
        info.setUsedMemory(um);
        info.setMaxMemory(mm);
        info.setBusyWorkers(app.getServer().getBusyWorkers());
        info.setMaxWorkers(app.getServer().getMaxWorkers());
        info.setSessionCount(router.getCountSessions());
        
        String json = Json.toJson(info);
        
        WebResponse response = new WebResponse(webRequest);
        response.setContentType("application/json");
        response.send(json);
        response.sendHeaders(200);
        return 200;
    }
    
}
