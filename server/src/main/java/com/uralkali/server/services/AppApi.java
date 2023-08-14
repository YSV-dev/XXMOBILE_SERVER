/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.services;

import com.uralkali.server.utils.Json;
import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.system.Encryption;
import com.swlibs.common.types.DateTime;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.common.models.dto.RegistrationData;
import com.uralkali.common.models.dto.RegistrationResult;
import com.uralkali.server.Application;
import com.uralkali.server.models.entities.ApplicationEntity;
import com.uralkali.server.utils.Properties;
import org.hibernate.Session;

/**
 *
 * @author brzsmg
 */
public class AppApi {
        
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(2)){
            case "registration" :
                result = appRegistration(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    public static int appRegistration(WebRequest webRequest, ClientRouter router) {

        RegistrationData rd;
        try{
            rd = (RegistrationData) Json.fromJson(webRequest.getPostAsText(), RegistrationData.class);
        }catch(RuntimeException ex) {
            throw new ServerException(400, Http.getMessage(400));
        }
        if(rd.getAppName() == null){
            throw new ServerException(400, Http.getMessage(400));
        }
        if(!rd.getAppName().equals("com.uralkali.client") && !rd.getAppName().equals("com.postman.client")) {
            throw new ServerException(415, "Unsupported application");
        }

        DateTime date = new DateTime();
        ApplicationEntity app = new ApplicationEntity();
        app.setAppName(rd.getAppName());
        app.setVersion(rd.getAppVersion());
        app.setDevMode(false);
        app.setToken("");
        app.setSoftwareId(rd.getSoftwareId());
        app.setImei(rd.getImei());
        app.setDeviceName(rd.getDeviceName());
        app.setSystemName(rd.getSystemName());
        app.setSystemVersion(rd.getSystemVersion());
        app.setCreatedAt(date.toDate());
        app.setCreatedBy(1);
        app.setUpdatedAt(date.toDate());
        app.setUpdatedBy(1);
        
        Session session = router.getSession();
        session.beginTransaction();
        try {
            session.save(app);

            Long appId = app.getAppId();
            String token = Encryption.getHmac(Properties.getProperty("token_key"), appId.toString());
            app.setToken(token);

            session.update(app);

            RegistrationResult result = new RegistrationResult();
            result.setAppId(appId);
            result.setToken(token);
            result.setServerName(Application.getInstance().getBaseName());
            result.setMessage("Приложение зарегистрировано.");
            
            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json;charset=UTF-8");
            response.send(Json.toJson(result));
            response.sendHeaders(200);
            
            session.getTransaction().commit();
            
            return 200;
        }catch(Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

}
