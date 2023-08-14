package com.uralkali.server;

import com.uralkali.server.exceptions.ServerIsNotAvailableException;
import com.swlibs.common.types.DateTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MainBase {
    
    private final Long mWaitTime = 30000L;
    
    //String url = String.format("----------------", "dprod");oa3db.uralkali.com(10.0.12.21:1563)
    private String url = "----------------";
    private String user = "apps";
    private String password = "apps";
    
    protected Connection mConnection;
    
    private DateTime mStartWaitTime;
    
    public MainBase(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        mStartWaitTime = null;
        synchronized(mWaitTime) {
            connect();
        }
    }
    
    protected void connect() {
        try {            
            if(mStartWaitTime != null){
                Long difference = mStartWaitTime.getDifference(new DateTime());
                long time = mWaitTime - difference;
                if(time > 0) {
                    Log.i("Повторная попытка, через " + (time / 1000) + " сек.");
                    return;
                }
            }
            mConnection = DriverManager.getConnection(url, user, password);
            if (mConnection == null) {
                Log.w("Не удалось подключиться к БД.");
                mStartWaitTime = new DateTime();
                return;
            }
            mStartWaitTime = null;
            Log.i("Успешное подключение к БД.");
            /*Statement st = mConnection.createStatement();
            {
                ResultSet rs = st.executeQuery("select * from product_component_version");
                while(rs.next()) {
                //if (rs.next()) {
                    Log.i("R: " + rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));//PRODUCT
                }
            }*/
        } catch (SQLException ex) {
            mConnection = null;
            mStartWaitTime = new DateTime();
            Log.e("Не удалось подключиться к БД Oracle.");
            //throw new RuntimeException(ex);
        }
    }
    
    public boolean isValid() {
        synchronized(mWaitTime) {
            if(mConnection != null) {
                try {
                    if(mConnection.isClosed()){
                        Log.i("Подключение к БД разорвано.");
                        mConnection = null;
                    } else {
                        if(!mConnection.isValid(5)){
                            mConnection = null;
                        }
                    }
                } catch (SQLException ex) {
                    mConnection = null;
                }
            }
            if(mConnection == null) {
                connect();
            }
            return mConnection != null;
        }
    }

    public Connection getConnection() {
        if(isValid()) {
            return mConnection;
        } else {
            throw new ServerIsNotAvailableException("Подключение к БД не установлено.");
        }
    }
    
}
