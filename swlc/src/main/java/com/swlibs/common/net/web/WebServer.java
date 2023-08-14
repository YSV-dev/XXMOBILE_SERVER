package com.swlibs.common.net.web;


import com.swlibs.common.logging.Log;
import com.swlibs.common.utils.Properties;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brzsmg
 */
public class WebServer {
    
    private static int c_max_request_workers = Properties.getInt("max_request_workers");
    
    private static int mPoolCounter = 0;
    
    private ExecutorService mExecutors;
    private int mPort = 80;                  //прослушиваемый порт
    private InetAddress mBindAddress = null; //прослушиваемый интерфейс
    private int mBacklog = 50;               //максимальное количество подключений
    private IWebRouter mWebRouter;
    private ServerSocket mServerSocket = null;
    private int mThreadCounter = 0;
    private AtomicInteger mCountConnections = new AtomicInteger(0);
    private AtomicBoolean mWaiting = new AtomicBoolean(false);

    public WebServer() {
        mPoolCounter++;
        mExecutors = Executors.newFixedThreadPool(c_max_request_workers, (Runnable r) -> {
            mThreadCounter++;
            String name = "client_" + mPoolCounter + "_" + mThreadCounter;
            try {
                Thread result = new Thread(r, name);
                Log.i("Thread \"" + name + "\", created success, id \"" + result.getId() + "\".");
                return result;
            } catch(Throwable e) {
                Log.i("[INFO] Thread \"" + name + "\", created filed.");
                throw e;
            }
        });
        
    }

    public ExecutorService getExecutors() {
        return mExecutors;
    }
    
    public boolean isClosed() {
        return mServerSocket.isClosed();
    }
    
    public boolean isWaiting() {
        return mWaiting.get();
    }
    
    public int getCountConnections() {
        return mCountConnections.get();
    }
    
    
    
    //getExecuteCount
    
    public WebServer(int port){
        this();
        mPort = port;
        mWebRouter = new WebRouterDefault();
    }
    
    public WebServer(int port, IWebRouter webRouter){
        this();
        mPort = port;
        mWebRouter = webRouter;
        if(mWebRouter == null){
                mWebRouter = new WebRouterDefault();
        }
    }
    
    public void Start()
    {
        if(mServerSocket != null){
            return;
        }
        
        Thread thread = new Thread(()->{
            ServerThread();
        });
        thread.setName("web-server");
        thread.start();
    }
    
    public void Stop() {
        if(mServerSocket == null) {
            return;
        }
        try {
            Log.i("Stopping server ...");
            mServerSocket.close();
            mServerSocket = null;
        } catch (IOException ex) {
            Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ServerThread()
    {
        try {
            try {
                mServerSocket = new ServerSocket(mPort, mBacklog, mBindAddress);
                //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while (!mServerSocket.isClosed()) {
                    mWaiting.set(true);
                    Socket client = mServerSocket.accept();
                    mWaiting.set(false);
                    mExecutors.execute(() -> {
                        long startTime = new Date().getTime();
                        int requestId = mCountConnections.incrementAndGet();
                        try {
                            try {
                                WebRequest webRequest = new WebRequest(requestId, this, client, startTime);
                                webRequest.wait(mWebRouter);
                            } finally {
                                client.close();
                            }
                        } catch (Throwable e) {
                            Log.w("Uncaught exception in client thread.");
                            if("OutOfMemoryError".equals(e.getClass().getSimpleName())) {
                                //Application.getInstance().printMemory();//TODO
                            }
                            String msg = e.getMessage();
                            msg = msg == null ? "" : msg;
                            Log.e(e.getClass().getSimpleName() + ": " + msg);
                            e.printStackTrace();
                        }
                        mCountConnections.getAndDecrement();
                    });
                }
                Log.i("Server closed.");
            } finally {
                if(mServerSocket != null) {
                    mServerSocket.close();
                }
            }
        //} catch (IOException ex) {
        //    System.out.println("[ERROR] " + ex.getClass().getSimpleName() + ((ex.getMessage() != null) ? (": " + ex.getMessage()) : ""));
        //    ex.printStackTrace();
        } catch (Exception ex) {
            if("socket closed".equals(ex.getMessage())) {
                Log.w("Server socket closed.");
            } else {
                Log.e(ex.getClass().getSimpleName() + ((ex.getMessage() != null) ? (": " + ex.getMessage()) : ""));
                ex.printStackTrace();
            }
        }
        
    }
    
    public int getMaxWorkers() {
        return c_max_request_workers;
    }
    
    public int getBusyWorkers() {
        return mCountConnections.get();
    }
    
}
