package com.uralkali.server;

import com.swlibs.common.net.web.WebServer;
import com.swlibs.common.types.DateTime;
import com.uralkali.common.models.dto.AppVersion;
import com.uralkali.server.utils.Properties;
import java.io.Console;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.Objects;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author brzsmg
 */
public class Application {
    
    private static final AppVersion mServerVersion = new AppVersion(2719, "2.7.19");
    public static final AppVersion mClientMinVersion = new AppVersion(23702, "2.3.7.02");
    
    public static final String sProdBaseName = "prod";
    public static final String sAdminUserName = "ADM_SPPORTAL";
    
    private static Application mInstance;
    
    private final String mSystemUserName;
    private final String mBaseName;
    private final String mBasePath;
    private final Date mStartDate;

    private WebServer mServer;
    
    public static String getBaseName(String v) {
        String l = v.toLowerCase();
        String c;
        if(l.length() > 3) {
            c = l.substring(l.length() - 3, l.length());
            if ("cdb".equals(c)) {
                return l.substring(0, l.length() - 3);
            } else {
                c = l.substring(0, 4);
                if ("ebs_".equals(c)) {
                    return l.substring(4, l.length());
                }
            }
        }
        return l;
    }
    
    private Application() {
        mSystemUserName = System.getProperty("user.name").toUpperCase();
        String instance = Properties.getProperty("db_instance");
        String sid = Properties.getProperty("db_sid");
        if(instance != null) {
            mBaseName = getBaseName(instance);
            mBasePath = "/" + instance;
        } else {
            mBaseName = sid.toLowerCase();
            mBasePath = ":" + sid;
        }
        mStartDate = DateTime.now();
        mServer = null;
    }
    
    public static Application getInstance() {
        if(mInstance == null) {
            mInstance = new Application();
            mInstance.onCreate();
        }
        return mInstance;
    }

    public AppVersion getServerVersion() {
        return mServerVersion;
    }
    
    public AppVersion getClientMinVersion() {
        return mClientMinVersion;
    }  
    
    public int execute() {
        Console console = System.console();
        if(console != null) {
            Log.i("Console found.");
        }
        //getMessage();
        String readCommand = "";
        String command = "";
        boolean run = true;
        while(run || mServer.getCountConnections() > 0) {
            try {
                if(console != null) {
                    command = console.readLine();
                } else {
                    command = "";
                    int a = System.in.available();
                    if(a > 0) {
                        byte[] b = new byte[a];
                        System.in.read(b, 0, a);
                        String s = new String(b);
                        readCommand += s;
                        String last = readCommand.substring(readCommand.length() - 1);
                        if(last.equals("\r") || last.equals("\n")) {
                            command = readCommand.substring(0, readCommand.length() - 1);
                            command = command.replace("\r","");
                            command = command.replace("\n","");
                            readCommand = "";
                        }
                    }
                    Thread.sleep(100);
                    if(command.equals("")) {
                        continue;
                    }
                }

                if(command.equals("exec")) {
                    Log.i("Statistics:");
                    Log.l("Shutdown: " + (mServer.getExecutors().isShutdown() ? "true" : "false") );
                    Log.l("Terminated: " + (mServer.getExecutors().isTerminated()? "true" : "false") );
                    Log.l("Closed: " + (mServer.isClosed()? "true" : "false") );
                    Log.l("Waiting: " + (mServer.isWaiting()? "true" : "false") );
                    Log.l("Count: " + mServer.getCountConnections());
                } else if(command.equals("memory")) {
                    printMemory();
                } else if(command.equals("close") || command.equals("exit")) {
                    mServer.Stop();
                    Log.i("Exit command received.");
                    run = false;
                } else {
                    Log.i("Unknown command: " + command);
                }
            } catch (InterruptedException e) {
                Log.e(e.getMessage());
                run = false;
            } catch (IOException e) {
                Log.e("IO. " + e.getMessage());
                run = false;
            }
        }
        mServer.Stop();
        Log.i("Application completed.");
        return 0;
    }
    
    public void onCreate() {
        printSystemInfo();
        Log.i("BaseName: " + mBaseName);
        Log.i("BasePath: " + mBasePath);
        if(!Objects.equals(mServerVersion.getName(), getClass().getPackage().getImplementationVersion())) {
            Log.w("Versions no equals: [" + mServerVersion.getName() + "] != [" + getClass().getPackage().getImplementationVersion() + "].");
        }
        
        printMemory();
        printLibs();
        
        ClientRouter router = new ClientRouter();

        /*/////
        try {
            Log.i("FIX started.");
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/fix.sql");
            CallableStatement st1 = connection.prepareCall(sql);
            st1.execute();
            st1.close();
            Log.i("FIX executed_2.");
            /////
        } catch (SQLException ex) {
            Log.e("FIX exception.");
        }*/
        
        mServer = new WebServer(Properties.getInt("server_port"), router);//81, 8082
        mServer.Start();
        Log.i("WebServer started...");
    }
    
    public void printSystemInfo() {
        String path = "";
        try {
            path = Application.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        }catch(Exception e) { }
        Log.i("Runtime Name: " + ManagementFactory.getRuntimeMXBean().getName());
        Log.i("Path: " + path);
        Log.i("Port: " + Properties.getInt("server_port"));
        Log.i("System: " + System.getProperty("os.name"));
        Log.i("Runtime: " + System.getProperty("java.runtime.name"));
        Log.i("Username: " + mSystemUserName);
        int ap = Runtime.getRuntime().availableProcessors();
        Log.i("Processors: " + ap);
    }
    
    public void printMemory() {
        Log.i("Max__ memory: " + FileUtils.byteCountToDisplaySize(Runtime.getRuntime().maxMemory()));
        Log.i("Total memory: " + FileUtils.byteCountToDisplaySize(Runtime.getRuntime().totalMemory()));
        Log.i("Free_ memory: " + FileUtils.byteCountToDisplaySize(Runtime.getRuntime().freeMemory()));
    }
    
    public void printLibs() {
        //java.security.AccessController.doPrivileged()
        //sun.misc.Unsafe.defineClass
        //org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Injector t;
        /*List<String> libs = Arrays.asList(
                "com.google.gson.Gson",
                //"com.squareup.moshi.Moshi",
                //"org.postgresql.Driver",
                "oracle.jdbc.driver.OracleDriver",
                //"com.j256.ormlite.dao.DaoManager",
                "com.swlibs.common.system.Log",
                "com.uralkali.common.models.dto.AppVersion",
                "com.uralkali.common.models.dto.Shift",
                "org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Injector"
                );
        for(String lib : libs) {
            try {
                Class.forName(lib);
                Log.i("\"" + lib + "\" exists.");
            } catch (ClassNotFoundException ex) {
                Log.e("\"" + lib + "\" not exists!");
            }
        }*/
    }
    
    public String getSystemUserName() {
        return mSystemUserName;
    }

    public String getBaseName() {
        return mBaseName;
    }

    public String getBasePath() {
        return mBasePath;
    }

    public WebServer getServer() {
        return mServer;
    }

    public Date getStartDate() {
        return mStartDate;
    }
    
}
