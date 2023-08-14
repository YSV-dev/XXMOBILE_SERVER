/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.services;

import com.uralkali.server.utils.Json;
import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.server.models.entities.InspectionEntity;

/**
 *
 * @author brzsmg
 */
public class InspectionRouter {
        
    public static int route(WebRequest webRequest, ClientRouter router) {
        return addInspection(webRequest, router);
    }
    
    public static int addInspection(WebRequest webRequest, ClientRouter router) {
        InspectionEntity cs = null;
        try {
            cs = (InspectionEntity) Json.fromJson(webRequest.getPostAsText(), InspectionEntity.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, Http.getMessage(400));
        }
        int y = 0;
        return 0;
        /*Long time = (new DateTime()).getTime();
        Long rand = (new Random()).nextLong();
        String sid = Encryption.getMd5(time.toString() + rand.toString());
        
        if(router.getSessionAppId(sid) != null) {
            throw new ServerException(500, "SID collision occurred");
        }
        
        Session session = router.getSession();
        session.beginTransaction();
        try{
            AppClient appClient = router.getAppClient(cs.getAppId());
            if(appClient == null) {
                ApplicationEntity app = null;
                app = session.find(ApplicationEntity.class, cs.getAppId());
                if(app == null) {
                    throw new ServerException(202, "AppId no found.");
                }
                appClient = new AppClient();
                appClient.setApp(app);
                appClient.setSid("");
            }
            if(updateApp(appClient.getApp(), cs)){
                session.update(appClient.getApp());
            }

            if(!appClient.getSid().equals("")) {
                router.removeSession(appClient.getSid());
            }

            appClient.setSid(sid);
            appClient.setStatus(cs.getStatus());

            Long appId = appClient.getApp().getAppId();

            router.setAppClient(appId, appClient);
            router.createSession(sid, appId);

            SessionResult result = new SessionResult();
            result.setSid(sid);
            result.setVersion(new AppVersion(260, "26.0"));
            result.setAllowDiffTime(60000);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(Json.toJson(result));
            response.sendHeaders(200);
            
            session.getTransaction().commit();
            
            return 200;
        }catch(Exception e) {
            session.getTransaction().rollback();
            throw e;
        }*/
    }
 
}
