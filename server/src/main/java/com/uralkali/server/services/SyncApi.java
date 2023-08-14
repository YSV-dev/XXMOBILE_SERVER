package com.uralkali.server.services;

import com.uralkali.server.utils.Json;
import com.uralkali.server.utils.Resources;
import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.system.Strings;
import com.swlibs.common.types.DateTime;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.data.NamedParameterStatement;
import com.uralkali.common.models.dto.CountResult;
import com.uralkali.server.Application;
import com.uralkali.server.Log;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.common.models.dto.SyncRequest;
import static com.uralkali.server.services.DataApi.toSqlDate;
import com.uralkali.server.utils.Pojo;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;
import org.hibernate.Session;

/**
 *
 * @author brzsmg
 */
public class SyncApi {
    
    public static int route(WebRequest webRequest, ClientRouter router) {
        String isCount = webRequest.getPath(2);
        SyncRequest syncRequest = null;
        try {
            syncRequest = (SyncRequest) Json.fromJson(webRequest.getPostAsText(), SyncRequest.class);
        } catch(RuntimeException ex) {
            Log.e(ex.getClass().getSimpleName());
            Log.e(ex.getMessage());
            if(ex.getCause() != null) {
                Log.e(ex.getCause().getClass().getSimpleName());
                Log.e(ex.getCause().getMessage());
            }
            ex.printStackTrace();
            throw new ServerException(400, ex.getClass().getSimpleName());
        }
        
        if(syncRequest.getSyncDate() == null) {
            syncRequest.setSyncDate(DateTime.fromLong(0));
        }
        
        if(syncRequest.getClassName()== null) {
            syncRequest.setSyncDate(DateTime.fromLong(0));
        }

        Session session = router.getSession();
        Connection connection = session.doReturningWork((Connection c) -> {
            return c;
        });
        
        try {
            //Connection connection = router.getMainBase().getConnection();
            String alias = getClassAlias(syncRequest.getClassName());
            if(alias == null) {
                return router.sendError(webRequest, 404, new Exception(Http.getMessage(404)));
            }
            if(syncRequest.isCount()) {
                CountResult result = new CountResult();
                String sql = Resources.getAsString("/sql/entities/" + alias + "_count.sql");
                //NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
                try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {
                    if(!"organizations".equals(alias)) {
                        statement.setObject("p_organization_id", syncRequest.getOrganizationId());
                    }
                    statement.setObject("p_last_id", syncRequest.getLastId());
                    statement.setDate("p_sync_date", toSqlDate(syncRequest.getSyncDate()));
                    
                    Log.i("[" + alias + "] Query count; thread = " + Thread.currentThread().getId() + "; p_sync_date = " + DateTime.asString(syncRequest.getSyncDate()) + "; p_last_id = " + syncRequest.getLastId() + ";");
                    ResultSet rs = statement.executeQuery();
                    
                    while(rs.next()) {
                        String typeCount = rs.getString(1);
                        if("ALL_COUNT".equals(typeCount)) {
                            result.setAllCount(rs.getInt(2));
                        } else if("QUERY_COUNT".equals(typeCount)) {
                            result.setQueryCount(rs.getInt(2));
                        } else {
                            return router.sendError(webRequest, 500, new Exception(Http.getMessage(500)));
                        }
                    }
                    rs.close();
                    statement.close();
                }
                
                if(result.getAllCount() == null) {
                    return router.sendError(webRequest, 500, new Exception(Http.getMessage(500)));
                }
                
                if(result.getQueryCount() == null) {
                    return router.sendError(webRequest, 500, new Exception(Http.getMessage(500)));
                }
                
                result.setClassName(syncRequest.getClassName());
                result.setLimit(syncRequest.getLimit());
                result.setSyncDate(syncRequest.getSyncDate());
                result.setOrganizationId(syncRequest.getOrganizationId());
                result.setLastId(syncRequest.getLastId());
                
                
                Log.e("[" + alias + "] Count result; thread = " + Thread.currentThread().getId() + "; allCount = " + result.getAllCount() + "; queryCount = " + result.getQueryCount() + ";");

                WebResponse response = new WebResponse(webRequest);
                response.setContentType("application/json");
                response.addHeader("Server-Version", Strings.fromValue(Application.getInstance().getServerVersion().getNumber()));
                response.send(Json.toJson(result));
                response.sendHeaders(200);
                return 200;
            } else {
                String json;
                String sql;
                if("Inspection".equals(syncRequest.getClassName()) || "Tag".equals(syncRequest.getClassName())) {
                    sql = Resources.getAsString("/sql/entities/" + alias + "_json.sql");
                } else {
                    sql = Resources.getAsString("/sql/entities/" + alias + ".sql");
                }
                //NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
                try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {
                    if(!"organizations".equals(alias)) {
                        statement.setObject("p_organization_id", syncRequest.getOrganizationId());
                    }
                    statement.setObject("p_last_id", syncRequest.getLastId());
                    statement.setDate("p_sync_date", toSqlDate(syncRequest.getSyncDate()));
                    statement.setObject("p_limit", syncRequest.getLimit());
                
                    ResultSet rs = statement.executeQuery();
                    if("Inspection".equals(syncRequest.getClassName()) || "Tag".equals(syncRequest.getClassName())) {
                        if(rs.next()) {
                            json = rs.getString(1);
                        } else {
                           json = Json.toJson(null);
                        }

                    } else {
                        Class c = Class.forName("com.uralkali.server.models.entities." + syncRequest.getClassName() + "Entity");
                        List result = Pojo.cursorToInstances(rs, c);
                        json = Json.toJson(result);
                    }

                    rs.close();
                    statement.close();
                }
                
                WebResponse response = new WebResponse(webRequest);
                response.setContentType("application/json");
                response.addHeader("Server-Version", Strings.fromValue(Application.getInstance().getServerVersion().getNumber()));
                response.send(json);
                response.sendHeaders(200);
                return 200;
            }

            /*} else {
                return router.sendError(webRequest, 404, new Exception(Http.getMessage(404)));
            }*/

        /*} catch (SQLException ex) {
            throw new RuntimeException(ex);*/
        //} catch (SQLRecoverableException ex){
            //return router.sendError(webRequest, 503, new Exception(Http.getMessage(503)));
        } catch (/*UnsupportedEncodingException |*/ ClassNotFoundException | SQLException ex) {
            //return router.sendError(webRequest, 500, new Exception(Http.getMessage(500)));
            throw new RuntimeException(ex);//SQLRecoverableException
        }
    }
    
    private static String getClassAlias(String className) {
        switch (className){
            case "Organization" : return "organizations";
            case "Department" : return "departments";
            case "Quest" : return "quests";
            case "QuestOperation" : return "quest_operations";
            case "Asset" : return "assets";
            case "AssetQuest" : return "asset_quests";
            case "Tag" : return "tags";
            case "Inspection" : return "inspections";
            default : return null;
        }
    }
    
}
