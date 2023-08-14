package com.uralkali.server.services;

import com.uralkali.server.utils.Json;
import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.system.Encryption;
import com.swlibs.common.types.DateTime;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.server.models.AppClient;
import com.uralkali.common.models.dto.AppVersion;
import com.uralkali.common.models.dto.ClientStatus;
import com.uralkali.common.models.dto.CreateSessionData;
import com.uralkali.common.models.dto.ServerStatus;
import com.uralkali.common.models.dto.SessionResult;
import com.uralkali.server.Application;
import com.uralkali.server.models.entities.ApplicationEntity;
import java.util.Random;
import org.hibernate.Session;

/**
 *
 * @author brzsmg
 */
public class SessionApi {
    
    public static long mAllowDiffTime = 60000;
    
    
        
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(2)){
            case "create" :
                result = createSession(webRequest, router);
                break;
            case "status" :
                result = getSessionStatus(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    private static Boolean updateApp(ApplicationEntity app, CreateSessionData cs) {
        Boolean result = false;
        if(app.getVersion() != cs.getVersion()) {
            app.setVersion(cs.getVersion());
            result = true;
        }
        if(app.isDevMode() != cs.isDevMode()) {
            app.setDevMode(cs.isDevMode());
            result = true;
        }
        return result;
    }
    
    /**
     * TODO: Нет синхронизации между потоками для mSessions
     * @param webRequest
     * @param router
     * @return HTTP код
     */
    public static int createSession(WebRequest webRequest, ClientRouter router) {
        CreateSessionData cs = null;
        try {
            cs = (CreateSessionData) Json.fromJson(webRequest.getPostAsText(), CreateSessionData.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, Http.getMessage(400));
        }
        
        Long time = (new DateTime()).getTime();
        Long rand = (new Random()).nextLong();
        String sid = Encryption.getMd5(time.toString() + rand.toString());
        
        if(router.getSessionAppId(sid) != null) {
            throw new ServerException(500, "SID collision occurred");
        }
        
        Session session = router.getSession();
        session.beginTransaction();
        try {
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
            result.setVersion(Application.getInstance().getServerVersion());
            result.setAllowDiffTime(mAllowDiffTime);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(Json.toJson(result));
            response.sendHeaders(200);
            
            session.getTransaction().commit();
            
            return 200;
        }catch(Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    public static int getSessionStatus(WebRequest webRequest, ClientRouter router) {
        ClientStatus cs = null;
        String sid = "";
        try{
            sid = webRequest.getParam("sid");
            cs = (ClientStatus) Json.fromJson(webRequest.getPostAsText(), ClientStatus.class);
        }catch(RuntimeException ex) {
            throw new ServerException(400, Http.getMessage(400));
        }
        Long appId = router.getSessionAppId(sid);
        if(appId == null) {
            throw new ServerException(202, "SID no found.");
        }
        AppClient appClient = router.getAppClient(appId);
        appClient.setStatus(cs);
        
        ServerStatus result = new ServerStatus();
        if(appClient.getApp().isDevMode()){
            result.setVersion(Application.mClientMinVersion); //TODO: need dev version
        } else {
            result.setVersion(Application.mClientMinVersion);
        }
        
        result.setDateTime(DateTime.asString(DateTime.now()));
        
        WebResponse response = new WebResponse(webRequest);
        response.setContentType("application/json");
        response.send(Json.toJson(result));
        response.sendHeaders(200);
        return 200;
    }
}
