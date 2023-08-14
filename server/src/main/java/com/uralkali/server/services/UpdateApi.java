package com.uralkali.server.services;

import com.sun.security.auth.module.NTSystem;
import com.uralkali.server.utils.Json;
import com.uralkali.server.utils.Resources;
import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.types.DateTime;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.common.models.dto.Shift;
import com.uralkali.common.models.dto.UploadResult;
import com.uralkali.common.models.dto.UserData;
import com.uralkali.server.data.NamedParameterStatement;
import com.uralkali.server.models.entities.InspectionEntity;
import com.uralkali.server.models.entities.InspectionLineEntity;
import com.uralkali.server.models.entities.TagEntity;
import com.uralkali.server.models.entities.TagObjectEntity;
import com.uralkali.common.models.dto.User;
import com.uralkali.server.Application;
import com.uralkali.server.utils.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author brzsmg
 */
public class UpdateApi {
    
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(2)) {
            case "clients" :
                result = loadFile(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    public static java.sql.Date toSqlDate(Date date) {
        if(date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
    
    public static int loadFile(WebRequest webRequest, ClientRouter router) {
        String fileName = webRequest.getPath(3);
        //Небольшая защита, от смены каталога
        fileName = fileName
                .replace(" ", "")
                .replace("..", "")
                .replace("/", "")
                .replace("\\", "");
        
        /*String fileName = "com.uralkali.client." + SessionApi.mMinVersion.getName() + ".app";
        if(!fileName.equals(webRequest.getPath(3))){
            throw new ServerException(403, Http.getMessage(403));
        }*/
        try {
            String ext = Application.getInstance().getBaseName();
            ext = ext.toLowerCase().equals(Application.sProdBaseName) ? "" : "_" + ext.toUpperCase();
            File file = new File("C:\\Program Files\\Uralkali\\MobileServer" + ext + "\\clients\\" + fileName); //TODO: Hardcode
            if(!file.exists()) {
                throw new ServerException(404, Http.getMessage(404));
            }
            Long length = file.length();

            FileInputStream stream = new FileInputStream(file);
            int bufferSize = 16384;
            byte[] buffer = new byte[bufferSize];
            int readSize;

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/vnd.android.package-archive");
            response.setContentLength(length);
            response.sendHeaders(200);
            
            while(true) {
                readSize = stream.read(buffer);
                if(readSize >= 0) {
                    response.send(buffer, readSize);
                } else {
                    break;
                }
            }
            stream.close();
            
            return 200;
        } catch (IOException e) {
            throw new ServerException(500, e);
        }
    }
    
}
