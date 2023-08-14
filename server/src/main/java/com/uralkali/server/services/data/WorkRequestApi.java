package com.uralkali.server.services.data;

import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.utils.MoreObjects;
import com.uralkali.common.models.dto.Defect;
import com.uralkali.common.models.dto.WorkRequest;
import com.uralkali.common.models.dto.filters.DefectsFilter;
import com.uralkali.common.models.dto.filters.WorkRequestsFilter;
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
public class WorkRequestApi {
    
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(3)) {
            case "" :
                result = getWorkRequests(webRequest, router);
                break;
            case "defects" :
                result = getDefects(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    public static int getWorkRequests(WebRequest webRequest, ClientRouter router) {
        WorkRequestsFilter filter = null;
        try {
            filter = (WorkRequestsFilter) Json.fromJson(webRequest.getPostAsText(), WorkRequestsFilter.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, ex);
        }
        
        try {
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/work_requests.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            if(filter.getWorkRequestId() != null) {
                params.put("p_work_request_id", filter.getWorkRequestId());
                conditions += "and WORK_REQUEST_ID = :p_work_request_id ";
            } else {
                if(filter.getWorkRequestNumber()!= null) {
                    params.put("p_work_request_number", filter.getWorkRequestNumber());
                    conditions += "and WORK_REQUEST_NUMBER = :p_work_request_number ";
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
            
            if("WORK_REQUEST_NUMBER".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by WORK_REQUEST_NUMBER" + sortOrder);
            /*} else if("SCHEDULED_START_DATE".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by SCHEDULED_START_DATE" + sortOrder);*/
            } else {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by WORK_ORDER_ID" + sortOrder);
            }
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            
            for(String key : params.keySet()) {
                statement.setObject(key, params.get(key));
            }
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            statement.setObject("p_limit", limit);

            try(ResultSet rs = statement.executeQuery()) {
                List<WorkRequest> data = Pojo.cursorToInstances(rs, WorkRequest.class);
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
    
public static int getDefects(WebRequest webRequest, ClientRouter router) {
        DefectsFilter filter = null;
        String text = webRequest.getPostAsText();
        try {
            filter = (DefectsFilter) Json.fromJson(webRequest.getPostAsText(), DefectsFilter.class);
        } catch(RuntimeException ex) {
            System.out.println(ex.getClass().getSimpleName());
            System.out.println(ex.getMessage());
            if(ex.getCause() != null) {
                System.out.println(ex.getCause().getClass().getSimpleName());
                System.out.println(ex.getCause().getMessage());
            }
            ex.printStackTrace();
            throw new ServerException(400, ex.getClass().getSimpleName());
            //throw new ServerException(400, Http.getMessage(400));
        }
        
        try {
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/defects.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            
            if(filter.getDefectId() != null) {
                params.put("p_defect_id", filter.getDefectId());
                conditions += "and DEFECT_ID = :p_defect_id ";
            }
                
            if(filter.getWorkRequestId() != null) {
                params.put("p_work_request_id", filter.getWorkRequestId());
                conditions += "and WORK_REQUEST_ID = :p_work_request_id ";
            }

            if(filter.getOrganizationId()!= null) {
                params.put("p_organization_id", filter.getOrganizationId());
                conditions += "and ORGANIZATION_ID = :p_organization_id ";
            }
            
            sql = sql.replace("/*%CONDITIONS%*/", conditions);
            
            String sortOrder = " ";
            if("asc".equals(filter.getSortingOrder())) {
                sortOrder = " asc ";
            } else if("desc".equals(filter.getSortingOrder())) {
                sortOrder = " desc ";
            }
            
            if("DEFECT_TYPE".equals(filter.getSorting())) {
                sql = sql.replace("/*%SORTING%*/", "order by DEFECT_TYPE" + sortOrder);
            } else {
                sql = sql.replace("/*%SORTING%*/", "order by DEFECT_ID" + sortOrder);
            }
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            
            for(String key : params.keySet()) {
                statement.setObject(key, params.get(key));
            }
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            statement.setObject("p_limit", limit);

            try(ResultSet rs = statement.executeQuery()) {
                List<Defect> data = Pojo.cursorToInstances(rs, Defect.class);
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
