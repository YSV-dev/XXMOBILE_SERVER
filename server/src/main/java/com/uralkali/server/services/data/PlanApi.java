package com.uralkali.server.services.data;

import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.utils.MoreObjects;
import com.uralkali.common.models.dto.Plan;
import com.uralkali.common.models.dto.PlanLine;
import com.uralkali.common.models.dto.filters.PlanLinesFilter;
import com.uralkali.common.models.dto.filters.PlansFilter;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.data.NamedParameterStatement;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.server.utils.Json;
import com.uralkali.server.utils.Pojo;
import com.uralkali.server.utils.Resources;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.hibernate.Session;

/**
 *
 * @author brzsmg
 */
public class PlanApi {
    
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(3)) {
            case "" :
                result = getPlans(webRequest, router);
                break;
            case "lines" :
                result = getPlanLines(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    public static int getPlans(WebRequest webRequest, ClientRouter router) {
        PlansFilter filter = null;
        try {
            filter = (PlansFilter) Json.fromJson(webRequest.getPostAsText(), PlansFilter.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, ex);
        }
        
        try {
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/plans.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            if(filter.getPlanId() != null) {
                params.put("p_plan_id", filter.getPlanId());
                conditions += "and PLAN_ID = :p_plan_id ";
            } else {
                //Timestamp приходит в UTC.
                //При передаче в Oracle будет приведен в строку с учетом текущего часового пояса.
                //Поэтому смещаем метку времени перед передачей в Oracle.
                
                Long timeFrom = filter.getDateFrom();
                if(timeFrom != null) {
                    timeFrom = timeFrom - TimeZone.getDefault().getRawOffset();
                }
                
                Long timeTo = filter.getDateTo();
                if(timeTo != null) {
                    timeTo = timeTo - TimeZone.getDefault().getRawOffset();
                }
                
                if(filter.getOrganizationId()!= null) {
                    params.put("p_organization_id", filter.getOrganizationId());
                    conditions += "and ORGANIZATION_ID = :p_organization_id ";
                }
                if(filter.getService()!= null) {
                    params.put("p_service", filter.getService());
                    conditions += "and SERVICE = :p_service ";
                }
                
                if(filter.getDateFrom() != null) {
                    params.put("p_date_from", new Timestamp(timeFrom) ); 
                    conditions += "and PLAN_DATE >= :p_date_from ";
                }
                if(filter.getDateTo() != null) {
                    params.put("p_date_to", new Timestamp(timeTo) );
                    conditions += "and PLAN_DATE <= :p_date_to ";
                }
                if(filter.getShiftId() != null) {
                    params.put("p_shift", filter.getShiftId() );
                    conditions += "and SHIFT = :p_shift ";
                }
                if(filter.getService()!= null) {
                    params.put("p_service", filter.getService() );
                    conditions += "and SERVICE <= :p_service ";
                }
            }
            
            sql = sql.replace("/*%CONDITIONS%*/", conditions);
            
            String sortOrder = " ";
            if("asc".equals(filter.getSortingOrder())) {
                sortOrder = " asc ";
            } else if("desc".equals(filter.getSortingOrder())) {
                sortOrder = " desc ";
            }
            
            if("PLAN_DATE".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by PLAN_DATE" + sortOrder);
            /*} else if("SCHEDULED_START_DATE".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by SCHEDULED_START_DATE" + sortOrder);*/
            } else {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by PLAN_ID" + sortOrder);
            }
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            
            for(String key : params.keySet()) {
                statement.setObject(key, params.get(key));
            }
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            statement.setObject("p_limit", limit);

            try(ResultSet rs = statement.executeQuery()) {
                List<Plan> data = Pojo.cursorToInstances(rs, Plan.class);
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
    
    public static int getPlanLines(WebRequest webRequest, ClientRouter router) {
        PlanLinesFilter filter = null;
        try {
            filter = (PlanLinesFilter) Json.fromJson(webRequest.getPostAsText(), PlanLinesFilter.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, ex);
        }
        
        try {
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/plan_lines.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            if(filter.getPlanId() != null) {
                params.put("p_plan_id", filter.getPlanId());
                conditions += "and PLAN_ID = :p_plan_id ";
                
                if(!filter.getInspected()) {
                    conditions += "and INSPECTION_ID is null ";
                }
                
                if(!filter.getNoInspected()) {
                    conditions += "and INSPECTION_ID is not null ";
                }
                
                if(filter.getOrganizationId()!= null) {
                    params.put("p_organization_id", filter.getOrganizationId());
                    conditions += "and ORGANIZATION_ID = :p_organization_id ";
                }
                
                if(filter.getDepartmentName()!= null) {
                    params.put("p_department_name", filter.getDepartmentName());
                    conditions += "and DEPARTMENT_NAME like '%' || :p_department_name || '%' ";
                }
                
                if(filter.getBranchName()!= null) {
                    params.put("p_loaction_code", filter.getBranchName());
                    conditions += "and LOCATION_CODE like '%' || :p_loaction_code || '%' ";
                }
                
                if(filter.getCategory()!= null) {
                    params.put("p_category", filter.getCategory());
                    conditions += "and CATEGORY like '%' || :p_category || '%' ";
                }
                
                if(filter.getPositionNumber()!= null) {
                    params.put("p_position_number", filter.getPositionNumber());
                    conditions += "and POSITION_NUMBER like '%' || :p_position_number || '%' ";
                }
                
                if(filter.getInstanceNumber()!= null) {
                    params.put("p_instance_number", filter.getInstanceNumber());
                    conditions += "and INSTANCE_NUMBER like '%' || :p_instance_number || '%' ";
                }
                

                
            } else {
                throw new ServerException(400, "Field PLAN_ID is empty.");
            }
            
            sql = sql.replace("/*%CONDITIONS%*/", conditions);
            
            String sortOrder = " ";
            if("asc".equals(filter.getSortingOrder())) {
                sortOrder = " asc ";
            } else if("desc".equals(filter.getSortingOrder())) {
                sortOrder = " desc ";
            }
            
            if("INSTANCE_ID".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by INSTANCE_ID" + sortOrder);
            } else if("INSTANCE_NUMBER".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by INSTANCE_NUMBER" + sortOrder);
            } else if("POSITION_NUMBER".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by POSITION_NUMBER" + sortOrder);
            } else {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by PLAN_LINE_ID" + sortOrder);
            }
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            
            for(String key : params.keySet()) {
                statement.setObject(key, params.get(key));
            }
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 2000);
            statement.setObject("p_limit", limit);

            try(ResultSet rs = statement.executeQuery()) {
                List<PlanLine> data = Pojo.cursorToInstances(rs, PlanLine.class);
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
