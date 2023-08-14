package com.uralkali.server;

import com.uralkali.server.exceptions.ServerIsNotAvailableException;
import com.swlibs.common.types.DateTime;
import com.uralkali.server.models.entities.*;
import com.uralkali.common.models.dto.User;
import com.uralkali.server.utils.Properties;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author brzsmg
 */
public class JpaBase {
    
    private final Long mWaitTime = 30000L;
    
    //private Session mSession;
    private final SessionFactory mSessionFactory;
    
    /**
     * Сессии для потоков
     */
    private final Map<Long,Session> mMap = new HashMap<>();
    
    private DateTime mStartWaitTime;
    
    public JpaBase() {
        //mSession = null;
        String basePath = Application.getInstance().getBasePath();
        String baseServer = Properties.getProperty("db_server");
        String basePort = Properties.getProperty("db_port");
        String username = Properties.getProperty("db_username");
        String password = Properties.getProperty("db_password");
        
        String url = "jdbc:oracle:thin:@" + baseServer + ":" + basePort + basePath;
        
        Configuration configuration = new Configuration();
        //configuration.configure("hibernate.cfg.xml");//TODO: org.glassfish.jaxb no work, wtf!
        configuration.getProperties().setProperty("hibernate.jdbc.time_zone", "Asia/Yekaterinburg");
        configuration.getProperties().setProperty("hibernate.show_sql", "true");
        configuration.getProperties().setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        //configuration.getProperties().setProperty("hibernate.dialect", "com.uralkali.server.data.CustomOracle10gDialect");
        //configuration.getProperties().setProperty("spring.jpa.properties.hibernate.dialect", "com.uralkali.server.data.CustomOracle10gDialect");

        configuration.getProperties().setProperty("hibernate.connection.url", url);
        configuration.getProperties().setProperty("hibernate.connection.username", username);
        configuration.getProperties().setProperty("hibernate.connection.password", password);
        
        configuration.addAnnotatedClass(ApplicationEntity.class);
        configuration.addAnnotatedClass(User.class);
        //configuration.addAnnotatedClass(InspectionEntity.class);
        //configuration.addAnnotatedClass(InspectionLineEntity.class);
        configuration.addAnnotatedClass(TagEntity.class);
        configuration.addAnnotatedClass(TagObjectEntity.class);
        configuration.addAnnotatedClass(ProblemEntity.class);
        
        mSessionFactory = configuration.buildSessionFactory();
        /*synchronized(mWaitTime) {
            connect();
        }*/
    }
    
    protected Session connect() {
        Session session;
        try {
            if(mStartWaitTime != null) {
                Long difference = mStartWaitTime.getDifference(new DateTime());
                long time = mWaitTime - difference;
                if(time > 0) {
                    Log.i("Retry, after " + (time / 1000) + " seconds.");
                    return null;
                }
            }
            session = mSessionFactory.openSession();
            if(session == null) {
                Log.e("DB connect failed.");
                mStartWaitTime = new DateTime();
                return null;
            }
            session.doWork((c) -> {
                c.createStatement().execute("ALTER SESSION SET NLS_SORT='BINARY'");
            });
            mStartWaitTime = null;
            Log.i("DB connect success (" + Thread.currentThread().getId() + ").");
        } catch (Exception ex) {
            session = null;
            mStartWaitTime = new DateTime();
            Log.e("DB connect exception.");
            //throw new RuntimeException(ex);
        }
        return session;
    }
    
    private Boolean isValid(Session session) {
        synchronized(mWaitTime) {
            if(session != null) {
                Boolean valid;
                try {
                    valid = session.doReturningWork((Connection c) -> {
                        try {
                            if(c.isClosed()){
                                Log.i("DB disconnected.");
                                return false;
                            } else {
                                if(!c.isValid(5)) {
                                    return false;
                                }
                            }
                            return true;
                        } catch (SQLException ex) {
                            return false;
                        }
                    });
                } catch(Exception e) {
                    valid = false;
                }
                if(!valid) {
                    session.close();
                    session = null;
                    mStartWaitTime = new DateTime();
                }
            }
            return session != null;
        }
    }

    public Session getSession() {
        Long threadId = Thread.currentThread().getId();
        Session session = null;
        if(mMap.containsKey(threadId)) {
            session = mMap.get(threadId);
            if(!isValid(session)) {
                session = connect();
            }
        } else {
            session = connect();
        }
        mMap.put(threadId, session);
        if(session != null) {
            return session;
        } else {
            throw new ServerIsNotAvailableException("DB connect failed.");
        }
    }
    
}
