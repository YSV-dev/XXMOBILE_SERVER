package com.uralkali.server.services.data;

import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.utils.MoreObjects;
import com.uralkali.common.models.dto.Failure;
import com.uralkali.common.models.dto.filters.FailuresFilter;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.data.NamedParameterStatement;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.server.utils.Json;
import com.uralkali.server.utils.Pojo;
import com.uralkali.server.utils.Resources;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author brzsmg
 */
public class FailureApi {
    
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(3)) {
            case "" :
                result = getFailures(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    public static int getFailures(WebRequest webRequest, ClientRouter router) {
        FailuresFilter filter = null;
        try {
            filter = (FailuresFilter) Json.fromJson(webRequest.getPostAsText(), FailuresFilter.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, ex);
        }
        
        try {
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/failures.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            if(filter.getFailureId() != null) {
                params.put("p_failure_id", filter.getFailureId());
                conditions += "and FAILURE_ID = :p_failure_id ";
            } else {
                if(filter.getFailureNumber()!= null) {
                    params.put("p_failure_number", filter.getFailureNumber());
                    conditions += "and FAILURE_NUMBER = :p_failure_number ";
                }
                if(filter.getOrganizationId()!= null) {
                    params.put("p_organization_id", filter.getOrganizationId());
                    conditions += "and ORGANIZATION_ID = :p_organization_id ";
                }
                if(filter.getInstanceId() != null) {
                    params.put("p_instance_id", filter.getInstanceId());
                    conditions += "and INSTANCE_ID = :p_instance_id ";
                }
                if(filter.getPositionNumber()!= null) {
                    params.put("p_position_number", filter.getPositionNumber());
                    conditions += "and POSITION_NUMBER = :p_position_number ";
                }
                if(filter.getInstanceNumber() != null) {
                    params.put("p_instance_number", filter.getInstanceNumber());
                    conditions += "and INSTANCE_NUMBER = :p_instance_number ";
                }
                if(filter.getStatusId() != null) {
                    params.put("p_status_id", filter.getStatusId());
                    conditions += "and STATUS_ID = :p_status_id ";
                }
                if(filter.getStatusName() != null) {
                    params.put("p_status_name", filter.getStatusName());
                    conditions += "and STATUS_NAME = :p_status_name ";
                }
            }
            
            sql = sql.replace("/*%CONDITIONS%*/", conditions);
            
            String sortOrder = " ";
            if("asc".equals(filter.getSortingOrder())) {
                sortOrder = " asc ";
            } else if("desc".equals(filter.getSortingOrder())) {
                sortOrder = " desc ";
            }
            
            if("FAILURE_NUMBER".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by FAILURE_NUMBER" + sortOrder);
            /*} else if("SCHEDULED_START_DATE".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by SCHEDULED_START_DATE" + sortOrder);*/
            } else {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by FAILURE_ID" + sortOrder);
            }
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            
            for(String key : params.keySet()) {
                statement.setObject(key, params.get(key));
            }
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            statement.setObject("p_limit", limit);

            try(ResultSet rs = statement.executeQuery()) {
                List<Failure> data = Pojo.cursorToInstances(rs, Failure.class);
                rs.close();

                String json = Json.toJson(data);

                WebResponse response = new WebResponse(webRequest);
                response.setContentType("application/json");
                response.send(json);
                response.sendHeaders(200);
                return 200;
            } catch(RuntimeException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
