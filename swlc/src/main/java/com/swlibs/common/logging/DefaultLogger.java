package com.swlibs.common.logging;

import com.swlibs.common.logging.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultLogger implements Logger {

    private String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @Override
    public int writeMessage(int priority, String tag, String message) {
        String p = "[SRV] [" + getDate() + "] ";
        switch (priority) {
            case 1:
                p += "[ERROR] ";
                break;
            case 2:
                p += "[WARNING] ";
                break;
            case 3:
                p += "[DEBUG] ";
                break;
            case 4:
                p += "[INFO] ";
                break;
            default:
                p = "";
                break;
        }
        System.out.println(p + message);
        return 0;
    }
}
