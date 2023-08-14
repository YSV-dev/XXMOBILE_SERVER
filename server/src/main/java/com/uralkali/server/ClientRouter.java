package com.uralkali.server;

import com.uralkali.api.model.ExceptionData;
import com.uralkali.server.exceptions.ServerIsNotAvailableException;
import com.uralkali.server.services.*;
import com.uralkali.server.utils.Json;
import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.IWebRouter;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.server.models.AppClient;
import com.uralkali.server.exceptions.ClientCanceledConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 * TODO: Добавить запись в консоль с датой
 * @author brzsmg
 */
public class ClientRouter implements IWebRouter {
    
    private final Map<Long, AppClient> mAppClients;
    private final Map<String, Long> mSessions;
    
    //private final MobileBase mMobileBase;
    private final MainBase mMainBase = null;
    private final JpaBase mJpaBase;
    
    public ClientRouter() {
        mAppClients = new HashMap<>();
        mSessions = new HashMap<>();
        
        //mMobileBase = new MobileBase();
        //mMainBase = new MainBase("","","");
        mJpaBase = new JpaBase();
        Log.i("ClientRouter is ready.");
    }
    
    public Session getSession() {
        return mJpaBase.getSession();
    }
    
    public long getCountSessions() {
        synchronized(mSessions) {
            return mSessions.size();
        }
    }
    
    public Long getSessionAppId(String sid) {
        synchronized(mSessions) {
            if(!mSessions.containsKey(sid)) {
                return null;
            }
            return mSessions.get(sid);
        }
    }
    
    public void createSession(String sid, Long appId) {
        synchronized(mSessions) {
            mSessions.put(sid, appId);
        }
    }
    
    public void removeSession(String sid) {
        synchronized(mSessions) {
            mSessions.remove(sid);
        }
    }
    
    public List<AppClient> getAppClients() {
        ArrayList<AppClient> clients = new ArrayList();
        synchronized(mAppClients) {
            for(AppClient client : mAppClients.values()) {
                clients.add(client);
            }
        }
        return clients;
    }
    
    public AppClient getAppClient(Long appId) {
        synchronized(mAppClients) {
            if(!mAppClients.containsKey(appId)){
                return null;
            }
            return mAppClients.get(appId);
        }
    }
    
    public void setAppClient(Long appId, AppClient client) {
        synchronized(mAppClients) {
            /*if(!mClients.containsKey(id)){
                throw new RuntimeException("Method setClient failure.");
            }*/
            mAppClients.put(appId, client);
        }
    }

    @Override
    public void route(WebRequest webRequest, int httpStatus) {
        int result;
        String clientVersion = "0";
        /*Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        sessionFactory.openStatelessSession(cnctn)
        //sessionFactory.isClosed()
        Session session = sessionFactory.openSession();

        String name = session.doReturningWork((Connection c) -> {
                //String sql = Resources.getAsString("/sql/app_update.sql");
                PreparedStatement s = c.prepareStatement("");
                s.
                String sql = "declare v_str VARCHAR2(100); begin select user_name into :p_name from xxeam.xxmobile_users where user_id = :p_id; end;";
                CallableStatement cs = c.prepareCall(sql);
                cs.registerOutParameter("p_name", Types.VARCHAR);
                cs.setInt("p_id", 1563);
                cs.execute();
                return cs.getString("p_name");
        });*/
        
        try {
            if(httpStatus != 200) {
                result = sendError(webRequest, httpStatus, new Exception(Http.getMessage(httpStatus)));
            } else if(webRequest.getPath(0).equals("v1")){
                clientVersion = webRequest.getParam("version");
                clientVersion = clientVersion != null ? clientVersion : "000";
                switch (webRequest.getPath(1)) {
                    case "app" :
                        result = AppApi.route(webRequest, this);
                        break;
                    case "session" :
                        result = SessionApi.route(webRequest, this);
                        break;
                    case "data" :
                        result = DataApi.route(webRequest, this);
                        break;
                    case "sync" :
                        result = SyncApi.route(webRequest, this);
                        break;
                    case "update" :
                        result = UpdateApi.route(webRequest, this);
                        break;
                    case "server" :
                        result = ServerApi.route(webRequest, this);
                        break;
                    case "error" :
                        result = ErrorApi.route(webRequest, this);
                        break;
                    default :
                        result = sendError(webRequest, 404, new Exception(Http.getMessage(404)));
                }
            } else {
                //result = sendError(webRequest, 404, new Exception(Http.getMessage(404)));
                result = PagesApi.route(webRequest, this);
            }
        } catch(ServerException ex) {
            result = sendError(webRequest, ex.getCode(), ex);
        } catch(ServerIsNotAvailableException ex) {
            //SQLRecoverableException
            result = sendError(webRequest, 503, ex);
        } catch(ClientCanceledConnection ex) {
            //SQLRecoverableException
            result = 499;
        } catch(Exception ex) {
            Log.e(ex.getClass().getSimpleName() + ((ex.getMessage() != null) ? (": " + ex.getMessage()) : ""));
            if(ex.getCause() != null) {
                Log.e(ex.getCause().getClass().getSimpleName() + ((ex.getCause().getMessage() != null) ? (": " + ex.getCause().getMessage()) : ""));
            }
            ex.printStackTrace();
            result = sendError(webRequest, 520, ex);
        }
        Log.i("Request_" + webRequest.getRequestId() + " {" + clientVersion + "}: (" + result + ") " + webRequest.getQuery());
    }
    
    public int sendError(WebRequest webRequest, int httpStatus, Exception e) {
        try {
            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(Json.toJson(new ExceptionData(e)));
            response.sendHeaders(httpStatus);
            return httpStatus;
        } catch(Exception ex) {
            return httpStatus;
        }
    }
    
    public MainBase getMainBase() {
        return mMainBase;
    }

    /*public MobileBase getMobileBase() {
        return mMobileBase;
    }*/

}
