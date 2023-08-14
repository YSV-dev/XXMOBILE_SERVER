package com.swlibs.common.logging;

/**
 * Примитивный логгер.
 *
 * @author brzsmg
 */
public class Log {
    private static Logger sLogger = null;

    public static void setLogger(Logger logger) {
        sLogger = logger;
    }

    static int writeMessage(int priority, String tag, String message) {
        if(sLogger == null) {
            sLogger = new DefaultLogger();
        }
        return sLogger.writeMessage(priority, tag, message);
    }

    public static int l(String message) {
        return writeMessage(0, "APP", message);
    }

    public static int i(String message) {
        return writeMessage(4, "APP", message);
    }

    public static int d(String message) {
        return writeMessage(3, "APP", message);
    }
    
    public static int w(String message) {
        return writeMessage(2, "APP", message);
    }
    
    public static int e(String message) {
        return writeMessage(1, "APP", message);
    }

    /**
     * Подробная информация
     */
    public static int v(String tag, String message) {
        return writeMessage(5, tag, message);
    }

    /**
     * Важная информация
     */
    public static int i(String tag, String message) {
        return writeMessage(4, tag, message);
    }

    /**
     * информация при отладке
     */
    public static int d(String tag, String message) {
        return writeMessage(3, tag, message);
    }

    /**
     * Предупреждение
     */
    public static int w(String tag, String message) {
        return writeMessage(2, tag, message);
    }

    /**
     * Ошибка
     */
    public static int e(String tag, String message) {
        return writeMessage(1, tag, message);
    }

    /**
     * Ошибка (исключение)
     */
    public static int e(String tag, Exception e) {
        /*StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString();*/
        String msg = e.getMessage();
        msg = (msg == null) ? "" : (": " + msg);
        msg = e.getClass().getSimpleName() + msg;
        return writeMessage(1, tag, msg);
    }

}
