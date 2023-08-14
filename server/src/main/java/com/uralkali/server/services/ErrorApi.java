package com.uralkali.server.services;

import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.types.DateTime;
import com.swlibs.common.utils.MoreObjects;
import com.uralkali.common.models.dto.ClientError;
import com.uralkali.common.models.dto.ErrorReport;
import com.uralkali.common.models.dto.ResultData;
import com.uralkali.common.models.dto.filters.ErrorReportsFilter;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.Log;
import com.uralkali.server.data.NamedParameterStatement;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.server.utils.Json;
import com.uralkali.server.utils.Pojo;
import com.uralkali.server.utils.Resources;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author brzsmg
 */
public class ErrorApi {
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(2)) {
            case "reports" :
                result = getReports(webRequest, router);
                break;
            case "report" :
                result = report(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    public static int report(WebRequest webRequest, ClientRouter router) {
        try {
            ClientError entity = null;
            String text = webRequest.getPostAsText();
            try {
                entity = (ClientError) Json.fromJson(webRequest.getPostAsText(), ClientError.class);
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
        
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            Boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            try {
                Date occurrenceDate = entity.getOccurrenceDate();
                //Date occurrenceDate = new DateTime(entity.getOccurrenceDate()).getSystemType(); //TODO: ???
                String sql = Resources.getAsString("/sql/client_error_report.sql");
                try(CallableStatement statement = connection.prepareCall(sql)) {
                    statement.setDate("p_occurrence_date", toSqlDate(occurrenceDate));
                    statement.setObject("p_error_type", entity.getErrorType());
                    statement.setObject("p_description", entity.getDescription());
                    statement.setObject("p_app_id", entity.getAppId());
                    statement.setObject("p_app_version", entity.getAppVersion());
                    statement.setObject("p_organization_id", entity.getOrganizationId());
                    statement.setObject("p_user_id", entity.getUserId());
                    statement.setObject("p_language", entity.getLanguage());
                    statement.setObject("p_exception_class", entity.getExceptionClass());
                    statement.setObject("p_exception_message", entity.getExceptionMessage());
                    statement.setObject("p_stack_trace", entity.getStackTrace());
                    statement.registerOutParameter("p_result", Types.VARCHAR);
                    statement.execute();

                    String result = statement.getString("p_result");

                    ResultData resultData = new ResultData(0, result);

                    String json = Json.toJson(resultData);

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            } catch (Throwable ex) {
                connection.rollback();
                connection.setAutoCommit(autoCommit);
                //connection.createStatement().execute("rollback");
                throw ex;
            }
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int getReports(WebRequest webRequest, ClientRouter router) {
        ErrorReportsFilter filter = null;
        try {
            filter = (ErrorReportsFilter) Json.fromJson(webRequest.getPostAsText(), ErrorReportsFilter.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, ex);
        }
        
        try {
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/error_reports.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            if(filter.getErrorReportId() != null) {
                    params.put("p_error_report_id", filter.getErrorReportId());
                    conditions += "and ERROR_REPORT_ID = :p_error_report_id ";
            } else {
                if(filter.getOrganizationId()!= null) {
                    params.put("p_organization_id", filter.getOrganizationId());
                    conditions += "and ORGANIZATION_ID = :p_organization_id ";
                }
                
                if(filter.getDateFrom() != null) {
                    params.put("p_date_from", new Timestamp(filter.getDateFrom()) );
                    conditions += "and OCCURRENCE_DATE >= :p_date_from ";
                }
                if(filter.getDateTo() != null) {
                    params.put("p_date_to", new Timestamp(filter.getDateTo()) );
                    conditions += "and OCCURRENCE_DATE <= :p_date_to ";
                }
            }
            
            sql = sql.replace("/*%CONDITIONS%*/", conditions);
            
            String sortOrder = " ";
            if("asc".equals(filter.getSortingOrder())) {
                sortOrder = " asc ";
            } else if("desc".equals(filter.getSortingOrder())) {
                sortOrder = " desc ";
            }
            
            if("OCCURRENCE_DATE".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by OCCURRENCE_DATE" + sortOrder);
            } else {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by ERROR_REPORT_ID" + sortOrder);
            }
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            
            for(String key : params.keySet()) {
                statement.setObject(key, params.get(key));
            }
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            statement.setObject("p_limit", limit);

            try(ResultSet rs = statement.executeQuery()) {
                List<ErrorReport> data = Pojo.cursorToInstances(rs, ErrorReport.class);
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
    
    public static java.sql.Date toSqlDate(Date date) {
        if(date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
    
}
