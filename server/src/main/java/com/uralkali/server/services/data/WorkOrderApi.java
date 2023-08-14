package com.uralkali.server.services.data;

import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.system.Numbers;
import com.swlibs.common.utils.MoreObjects;
import com.uralkali.common.models.dto.WorkOrder;
import com.uralkali.common.models.dto.WorkOrderMaterial;
import com.uralkali.common.models.dto.WorkOrderOperation;
import com.uralkali.common.models.dto.WorkOrderResource;
import com.uralkali.common.models.dto.filters.WorkOrderMaterialsFilter;
import com.uralkali.common.models.dto.filters.WorkOrderOperationsFilter;
import com.uralkali.common.models.dto.filters.WorkOrderResourcesFilter;
import com.uralkali.common.models.dto.filters.WorkOrdersFilter;
import com.uralkali.server.ClientRouter;
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
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author brzsmg
 */
public class WorkOrderApi {
    
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(3)) {
            case "" :
                result = getWorkOrders(webRequest, router);
                break;
            case "operations" :
                result = getWorkOrderOperations(webRequest, router);
                break;
            case "materials" :
                result = getWorkOrderMaterials(webRequest, router);
                break;
            case "resources" :
                result = getWorkOrderResources(webRequest, router);
                break;
            case "execution_control" :
                result = executionControl(webRequest, router);
                break;
            case "start" :
                result = workOrderStart(webRequest, router);
                break;
            case "pause" :
                result = workOrderPause(webRequest, router);
                break;
            case "continue" :
                result = workOrderContinue(webRequest, router);
                break;
            case "complete" :
                result = workOrderComplete(webRequest, router);
                break;
            case "operation_start" :
                result = operationStart(webRequest, router);
                break;
            case "operation_pause" :
                result = operationPause(webRequest, router);
                break;
            case "operation_continue" :
                result = operationContinue(webRequest, router);
                break;
            case "operation_complete" :
                result = operationComplete(webRequest, router);
                break;
            default :
                throw new ServerException(404, Http.getMessage(404));
        }
        return result;
    }
    
    public static int getWorkOrders(WebRequest webRequest, ClientRouter router) {
        WorkOrdersFilter filter = null;
        try {
            filter = (WorkOrdersFilter) Json.fromJson(webRequest.getPostAsText(), WorkOrdersFilter.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, ex.getClass().getSimpleName());
        }

        try {
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/work_orders.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            if(filter.getWorkOrderId() != null) {
                params.put("p_work_order_id", filter.getWorkOrderId());
                conditions += "and WORK_ORDER_ID = :p_work_order_id ";
            } else {
                
                if(filter.getWorkOrderNumber()!= null) {
                    params.put("p_work_order_number", filter.getWorkOrderNumber());
                    conditions += "and WORK_ORDER_NUMBER = :p_work_order_number ";
                }
                if(filter.getPerformer()!= null) {
                    params.put("p_performer", filter.getPerformer());
                    conditions += "and upper(PERFORMER) like '%' || upper(:p_performer) || '%' ";
                }
                if(filter.getContractor()!= null) {
                    params.put("p_contractor", filter.getContractor());
                    conditions += "and upper(CONTRACTORS) like '%' || upper(:p_contractor) || '%' ";
                }
                if(filter.getOrganizationId()!= null) {
                    params.put("p_organization_id", filter.getOrganizationId());
                    conditions += "and ORGANIZATION_ID = :p_organization_id ";
                }
                if(filter.getInstanceId() != null) {
                    params.put("p_instance_id", filter.getInstanceId());
                    conditions += "and INSTANCE_ID = :p_instance_id ";
                }
                if(filter.getCategory() != null) {
                    params.put("p_category", filter.getCategory());
                    conditions += "and upper(CATEGORY) like '%' || upper(:p_category) || '%' ";
                }
                if(filter.getPositionNumber()!= null) {
                    params.put("p_position_number", filter.getPositionNumber());
                    conditions += "and upper(POSITION_NUMBER) like '%' || upper(:p_position_number) || '%'";
                }
                if(filter.getInstanceNumber() != null) {
                    params.put("p_instance_number", filter.getInstanceNumber());
                    conditions += "and upper(INSTANCE_NUMBER) like '%' || upper(:p_instance_number) || '%' ";
                }
                if(filter.getStatusId() != null) {
                    params.put("p_status_id", filter.getStatusId());
                    conditions += "and STATUS_ID = :p_status_id ";
                }
                if(filter.getStatusName() != null) {
                    params.put("p_status_name", filter.getStatusName());
                    conditions += "and STATUS_NAME = :p_status_name ";
                }
                if(filter.getActivityType()!= null) {
                    params.put("p_activity_type", filter.getActivityType());
                    conditions += "and ACTIVITY_TYPE = :p_activity_type ";
                }
                if(filter.getParentWorkOrderNumber()!= null) {
                    params.put("p_parent_work_order_number", filter.getParentWorkOrderNumber());
                    conditions += "and PARENT_WORK_ORDER_NUMBER like :p_parent_work_order_number ";
                }
                
                if(filter.getDateFrom() != null) {
                    params.put("p_date_from", new Timestamp(filter.getDateFrom()) );
                    conditions += "and SCHEDULED_START_DATE >= :p_date_from ";
                }
                if(filter.getDateTo() != null) {
                    params.put("p_date_to", new Timestamp(filter.getDateTo()) );
                    conditions += "and SCHEDULED_START_DATE <= :p_date_to ";
                }
            }
            
            sql = sql.replace("/*%CONDITIONS%*/", conditions);
            
            String sortOrder = " ";
            if("asc".equals(filter.getSortingOrder())) {
                sortOrder = " asc ";
            } else if("desc".equals(filter.getSortingOrder())) {
                sortOrder = " desc ";
            }
            
            if("WORK_ORDER_NUMBER".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by WORK_ORDER_NUMBER" + sortOrder);
            } else if("SCHEDULED_START_DATE".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by SCHEDULED_START_DATE" + sortOrder);
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
                List<WorkOrder> data = Pojo.cursorToInstances(rs, WorkOrder.class);
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
    
    public static int getWorkOrderOperations(WebRequest webRequest, ClientRouter router) {
        WorkOrderOperationsFilter filter = null;
        String text = webRequest.getPostAsText();
        try {
            filter = (WorkOrderOperationsFilter) Json.fromJson(webRequest.getPostAsText(), WorkOrderOperationsFilter.class);
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
            String sql = Resources.getAsString("/sql/wo_operations.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            
            if(filter.getWorkOrderId() != null) {
                params.put("p_work_order_id", filter.getWorkOrderId());
                conditions += "and WORK_ORDER_ID = :p_work_order_id ";
            }
                
            if(filter.getOperationNumber()!= null) {
                params.put("p_operation_number", filter.getOperationNumber());
                conditions += "and OPERATION_NUMBER = :p_operation_number ";
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
            
            if("OPERATION_NUMBER".equals(filter.getSorting())) {
                sql = sql.replace("/*%SORTING%*/", "order by OPERATION_NUMBER" + sortOrder);
            } else if("SCHEDULED_START_DATE".equals(filter.getSorting())) {
                sql = sql.replace("/*%SORTING%*/", "order by SCHEDULED_START_DATE" + sortOrder + ", OPERATION_NUMBER" + sortOrder);
            } else {
                //default sorting
                sql = sql.replace("/*%SORTING%*/", "order by OPERATION_NUMBER ");
            }
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            
            for(String key : params.keySet()) {
                statement.setObject(key, params.get(key));
            }
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            statement.setObject("p_limit", limit);

            try(ResultSet rs = statement.executeQuery()) {
                List<WorkOrderOperation> data = Pojo.cursorToInstances(rs, WorkOrderOperation.class);
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
    
    public static int getWorkOrderMaterials(WebRequest webRequest, ClientRouter router) {
        WorkOrderMaterialsFilter filter = null;
        String text = webRequest.getPostAsText();
        try {
            filter = (WorkOrderMaterialsFilter) Json.fromJson(webRequest.getPostAsText(), WorkOrderMaterialsFilter.class);
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
            String sql = Resources.getAsString("/sql/wo_materials.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            
            if(filter.getWorkOrderId() != null) {
                params.put("p_work_order_id", filter.getWorkOrderId());
                conditions += "and WORK_ORDER_ID = :p_work_order_id ";
            }
                
            if(filter.getOperationNumber() != null) {
                params.put("p_operation_number", filter.getOperationNumber());
                conditions += "and OPERATION_NUMBER = :p_operation_number ";
            }
            
            if(filter.getInventoryItemId() != null) {
                params.put("p_inventory_item_id", filter.getInventoryItemId());
                conditions += "and INVENTORY_ITEM_ID = :p_inventory_item_id ";
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
            
            if("OPERATION_NUMBER".equals(filter.getSorting())) {
                sql = sql.replace("/*%SORTING%*/", "order by OPERATION_NUMBER" + sortOrder);
            } else {
                sql = sql.replace("/*%SORTING%*/", "order by OPERATION_NUMBER, POSITION_CODE ");
            }
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            
            for(String key : params.keySet()) {
                statement.setObject(key, params.get(key));
            }
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            statement.setObject("p_limit", limit);

            try(ResultSet rs = statement.executeQuery()) {
                List<WorkOrderMaterial> data = Pojo.cursorToInstances(rs, WorkOrderMaterial.class);
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
    
    public static int getWorkOrderResources(WebRequest webRequest, ClientRouter router) {
        WorkOrderResourcesFilter filter = null;
        String text = webRequest.getPostAsText();
        try {
            filter = (WorkOrderResourcesFilter) Json.fromJson(webRequest.getPostAsText(), WorkOrderResourcesFilter.class);
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
            String sql = Resources.getAsString("/sql/wo_resources.sql");
            
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            
            if(filter.getWorkOrderId() != null) {
                params.put("p_work_order_id", filter.getWorkOrderId());
                conditions += "and WORK_ORDER_ID = :p_work_order_id ";
            }
                
            if(filter.getOperationNumber()!= null) {
                params.put("p_operation_number", filter.getOperationNumber());
                conditions += "and OPERATION_NUMBER = :p_operation_number ";
            }
            
            if(filter.getResourceNumber()!= null) {
                params.put("p_resource_number", filter.getResourceNumber());
                conditions += "and RESOURCE_NUMBER = :p_resource_number ";
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
            
            if("OPERATION_NUMBER".equals(filter.getSorting())) {
                sql = sql.replace("/*%SORTING%*/", "order by OPERATION_NUMBER" + sortOrder);
            } else {
                sql = sql.replace("/*%SORTING%*/", "order by OPERATION_NUMBER, RESOURCE_NUMBER ");
            }
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            
            for(String key : params.keySet()) {
                statement.setObject(key, params.get(key));
            }
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            statement.setObject("p_limit", limit);

            try(ResultSet rs = statement.executeQuery()) {
                List<WorkOrderResource> data = Pojo.cursorToInstances(rs, WorkOrderResource.class);
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
    
    public static int executionControl(WebRequest webRequest, ClientRouter router) {
        try {
            Long workOrderId = Numbers.parseLong(webRequest.getParam("work_order_id"));
            Long userId = Numbers.parseLong(webRequest.getParam("user_id"));
            Integer executionControl = Numbers.parseInt(webRequest.getParam("execution_control"));
            
            String result = null;
            
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/workorder/execution_control.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setObject("p_work_order_id", workOrderId);
                    st.setObject("p_user_id", userId);
                    st.setObject("p_execution_control", executionControl);

                    st.registerOutParameter("p_result", Types.VARCHAR);
                    st.execute();

                    result = st.getString("p_result");
                    st.close();
            }
            
            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static int workOrderStart(WebRequest webRequest, ClientRouter router) {
        try {
            Long workOrderId = Numbers.parseLong(webRequest.getParam("work_order_id"));
            Long userId = Numbers.parseLong(webRequest.getParam("user_id"));
            String reason = webRequest.getParam("reason");
            String description = webRequest.getParam("description");
            String result = null;

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/workorder/start.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setObject("p_work_order_id", workOrderId);
                    st.setObject("p_user_id", userId);
                    st.setObject("p_reason", reason);
                    st.setObject("p_description", description);
                    
                    st.registerOutParameter("p_result", Types.VARCHAR);

                    st.execute();

                    result = st.getString("p_result");

                    st.close();
            }
            
            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static int workOrderPause(WebRequest webRequest, ClientRouter router) {
        try {
            Long workOrderId = Numbers.parseLong(webRequest.getParam("work_order_id"));
            Long userId = Numbers.parseLong(webRequest.getParam("user_id"));
            Long operationNumber = Numbers.parseLong(webRequest.getParam("operation_number"));
            String reason = webRequest.getParam("reason");
            String description = webRequest.getParam("description");
                    
            String result = null;
            
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/workorder/pause.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setObject("p_work_order_id", workOrderId);
                    st.setObject("p_user_id", userId);
                    st.setObject("p_operation_number", operationNumber);
                    st.setObject("p_reason", reason);
                    st.setObject("p_description", description);
                    
                    st.registerOutParameter("p_result", Types.VARCHAR);

                    st.execute();

                    result = st.getString("p_result");

                    st.close();
            }
            
            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static int workOrderContinue(WebRequest webRequest, ClientRouter router) {
        try {
            Long workOrderId = Numbers.parseLong(webRequest.getParam("work_order_id"));
            Long userId = Numbers.parseLong(webRequest.getParam("user_id"));
            String result = null;
            
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/workorder/continue.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setObject("p_work_order_id", workOrderId);
                    st.setObject("p_user_id", userId);
                    
                    st.registerOutParameter("p_result", Types.VARCHAR);

                    st.execute();

                    result = st.getString("p_result");

                    st.close();
            }
            
            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static int workOrderComplete(WebRequest webRequest, ClientRouter router) {
        try {
            Long workOrderId = Numbers.parseLong(webRequest.getParam("work_order_id"));
            Long userId = Numbers.parseLong(webRequest.getParam("user_id"));
            String reason = webRequest.getParam("reason");
            String description = webRequest.getParam("description");
            String result = null;
            
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/workorder/complete.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setObject("p_work_order_id", workOrderId);
                    st.setObject("p_user_id", userId);
                    st.setObject("p_reason", reason);
                    st.setObject("p_description", description);
                    
                    st.registerOutParameter("p_result", Types.VARCHAR);

                    st.execute();

                    result = st.getString("p_result");

                    st.close();
            }
            
            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static int operationStart(WebRequest webRequest, ClientRouter router) {
        try {
            Long workOrderId = Numbers.parseLong(webRequest.getParam("work_order_id"));
            Long operationNumber = Numbers.parseLong(webRequest.getParam("operation_number"));
            Long userId = Numbers.parseLong(webRequest.getParam("user_id"));
            
            String result = null;

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/workorder/operation_start.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setObject("p_work_order_id", workOrderId);
                    st.setObject("p_operation_number", operationNumber);
                    st.setObject("p_user_id", userId);

                    st.registerOutParameter("p_result", Types.VARCHAR);

                    st.execute();

                    result = st.getString("p_result");

                    st.close();
            }
            
            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static int operationPause(WebRequest webRequest, ClientRouter router) {
        try {
            Long workOrderId = Numbers.parseLong(webRequest.getParam("work_order_id"));
            Long operationNumber = Numbers.parseLong(webRequest.getParam("operation_number"));
            Long userId = Numbers.parseLong(webRequest.getParam("user_id"));
            
            String reason = webRequest.getParam("reason");
            String description = webRequest.getParam("description");
                    
            String result = null;
            
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/workorder/operation_pause.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setObject("p_work_order_id", workOrderId);
                    st.setObject("p_operation_number", operationNumber);
                    st.setObject("p_user_id", userId);
                    st.setObject("p_reason", reason);
                    st.setObject("p_description", description);
                    
                    st.registerOutParameter("p_result", Types.VARCHAR);

                    st.execute();

                    result = st.getString("p_result");

                    st.close();
            }
            
            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static int operationContinue(WebRequest webRequest, ClientRouter router) {
        try {
            Long workOrderId = Numbers.parseLong(webRequest.getParam("work_order_id"));
            Long operationNumber = Numbers.parseLong(webRequest.getParam("operation_number"));
            Long userId = Numbers.parseLong(webRequest.getParam("user_id"));
            String result = null;
            
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/workorder/operation_continue.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setObject("p_work_order_id", workOrderId);
                    st.setObject("p_operation_number", operationNumber);
                    st.setObject("p_user_id", userId);
                    
                    st.registerOutParameter("p_result", Types.VARCHAR);

                    st.execute();

                    result = st.getString("p_result");

                    st.close();
            }
            
            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static int operationComplete(WebRequest webRequest, ClientRouter router) {
        try {
            Long workOrderId = Numbers.parseLong(webRequest.getParam("work_order_id"));
            Long operationNumber = Numbers.parseLong(webRequest.getParam("operation_number"));
            Long userId = Numbers.parseLong(webRequest.getParam("user_id"));
            String reason = webRequest.getParam("reason");
            String description = webRequest.getParam("description");
            String result = null;
            
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/workorder/operation_complete.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setObject("p_work_order_id", workOrderId);
                    st.setObject("p_operation_number", operationNumber);
                    st.setObject("p_user_id", userId);
                    st.setObject("p_reason", reason);
                    st.setObject("p_description", description);
                    
                    st.registerOutParameter("p_result", Types.VARCHAR);

                    st.execute();

                    result = st.getString("p_result");

                    st.close();
            }
            
            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
