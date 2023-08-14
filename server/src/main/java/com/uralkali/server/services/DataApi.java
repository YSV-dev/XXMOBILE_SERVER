package com.uralkali.server.services;

import com.uralkali.server.services.data.*;
import com.uralkali.server.utils.Json;
import com.uralkali.server.utils.Resources;
import com.swlibs.common.net.Http;
import com.swlibs.common.net.web.WebRequest;
import com.swlibs.common.net.web.WebResponse;
import com.swlibs.common.system.Numbers;
import com.swlibs.common.system.Strings;
import com.swlibs.common.types.DateTime;
import com.swlibs.common.utils.MoreObjects;
import com.uralkali.common.models.dto.Asset;
import com.uralkali.common.models.dto.Attachment;
import com.uralkali.common.models.dto.Document;
import com.uralkali.common.models.dto.Inspection;
import com.uralkali.server.ClientRouter;
import com.uralkali.server.exceptions.ServerException;
import com.uralkali.common.models.dto.Shift;
import com.uralkali.common.models.dto.UploadResult;
import com.uralkali.common.models.dto.UserData;
import com.uralkali.common.models.dto.filters.UsersFilter;
import com.uralkali.server.Application;
import com.uralkali.server.Log;
import com.uralkali.server.data.NamedParameterStatement;
import com.uralkali.server.models.entities.AttachmentEntity;
import com.uralkali.server.models.entities.UAttachmentEntity;
import com.uralkali.server.models.entities.InspectionEntity;
import com.uralkali.server.models.entities.InspectionLineEntity;
import com.uralkali.server.models.entities.TagEntity;
import com.uralkali.server.models.entities.TagObjectEntity;
import com.uralkali.common.models.dto.OebsUser;
import com.uralkali.common.models.dto.Specification;
import com.uralkali.common.models.dto.TechnicalCard;
import com.uralkali.common.models.dto.User;
import com.uralkali.server.utils.Pojo;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;

/**
 *
 * @author brzsmg
 */
public class DataApi {
    
    public static int route(WebRequest webRequest, ClientRouter router) {
        int result;
        switch (webRequest.getPath(2)) {
            case "inspection" :
                result = addInspection(webRequest, router);
                break;
            case "tag" :
                result = addTag(webRequest, router);
                break;
            case "oebs_users" :
                result = getOebsUsers(webRequest, router);
                break;
            case "reset_person_pin" :
                result = resetPersonPin(webRequest, router);
                break;
            case "users" :
                result = getUsers(webRequest, router);
                break;
            case "user" :
                result = getUser(webRequest, router);
                break;
            case "shift" :
                result = getShift(webRequest, router);
                break;
            case "asset" :
                result = getAsset(webRequest, router);
                break;
            case "attachments" :
                result = getAttachments(webRequest, router);
                break;
            case "upload_u_attachments":
                result = addUAttachment(webRequest, router);
                break;
            case "get_u_attachments":
                result = getUAttachments(webRequest, router);
                break;
            case "specifications" :
                result = getSpecifications(webRequest, router);
                break;
            case "document" :
                result = getDocument(webRequest, router);
                break;
            case "inspections" :
                result = getInspections(webRequest, router);
                break;
            case "work_request" :
                result = WorkRequestApi.route(webRequest, router);
                break;
            case "work_order" :
                result = WorkOrderApi.route(webRequest, router);
                break;
            case "work_tech_card" :
                result = getTechnicalCards(webRequest, router);
                break;
            case "failures" :
                result = FailureApi.getFailures(webRequest, router);
                break;
            case "plans" :
                result = PlanApi.route(webRequest, router);
                break;
            case "problem" :
                result = ProblemApi.route(webRequest, router);
                break;
            case "user_data" :
                switch (webRequest.getPath(3)) {
                    case "upload" :
                        result = uploadUserData(webRequest, router);
                        break;
                    default :
                        result = getUserData(webRequest, router);
                }
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
    
    public static int getTechnicalCards(WebRequest webRequest, ClientRouter router){
        try {
            Integer instanceId = Numbers.parseInt(webRequest.getParam("instance_id"));

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/tech_cards.sql");
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {
            
                statement.setObject("p_instance_id", instanceId);

                try(ResultSet rs = statement.executeQuery()) {
                    String json;
                    
                    List<TechnicalCard> data = Pojo.cursorToInstances(rs, TechnicalCard.class);
                    json = Json.toJson(data);
                    
                    rs.close();

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int getAsset(WebRequest webRequest, ClientRouter router) {
        try {
            Integer instanceId = Numbers.parseInt(webRequest.getParam("instance_id"));

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/asset.sql");
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {
            
                statement.setObject("p_instance_id", instanceId);

                try(ResultSet rs = statement.executeQuery()) {
                    String json;
                    if(rs.next()) {
                        Asset asset = Pojo.cursorToInstance(rs, Asset.class);
                        rs.close();
                        json = Json.toJson(asset);
                        
                    } else {
                        json = Json.toJson(null);
                    }

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int getAttachments(WebRequest webRequest, ClientRouter router) {
        try {
            String entityName;
            String entityClass = webRequest.getParam("entity_class");
            Integer pk1 = Numbers.parseInt(webRequest.getParam("pk1"));
            Integer pk2 = Numbers.parseInt(webRequest.getParam("pk2"));
            Integer pk3 = Numbers.parseInt(webRequest.getParam("pk3"));
            
            if(Objects.equals(entityClass, "Asset")) {
                entityName = "CSI_ITEM_INSTANCES";
                //PK1=InstanceId
            } else if(Objects.equals(entityClass, "WorkOrder")) {
                entityName = "WIP_DISCRETE_JOBS";
                //PK1=WIP_ENTITY_ID
                //PK2=ORGANIZATION_ID
            } else if(Objects.equals(entityClass, "WorkRequest")) {
                entityName = "EAM_WORK_REQUESTS";
                //PK1=WORK_REQUEST_ID
            } else if(Objects.equals(entityClass, "Failure")) {
                entityName = "EAM_WORK_REQUESTS";
                //PK1=WORK_REQUEST_ID
            } else {
                throw new ServerException(400, new RuntimeException("Unknown entity class"));
            }
        
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/attachments.sql");
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {

                statement.setObject("p_entity_name", entityName);
                statement.setObject("p_pk1", pk1);
                statement.setObject("p_pk2", pk2);
                statement.setObject("p_pk3", pk3);

                try(ResultSet rs = statement.executeQuery()) {
                    List<Attachment> data = Pojo.cursorToInstances(rs, Attachment.class);
                    rs.close();

                    String json = Json.toJson(data);

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public static int addUAttachment(WebRequest webRequest, ClientRouter router) {
        ApiDebugTools.printRequestParams(webRequest);

        UAttachmentEntity entity = null;
        String text = webRequest.getPostAsText();

        try {
            entity = (UAttachmentEntity) Json.fromJson(text, UAttachmentEntity.class);

        } catch (RuntimeException ex) {
            System.out.println(ex.getClass().getSimpleName());
            System.out.println(ex.getMessage());
            if (ex.getCause() != null) {
                System.out.println(ex.getCause().getClass().getSimpleName());
                System.out.println(ex.getCause().getMessage());
            }
            ex.printStackTrace();
            throw new ServerException(400, ex.getClass().getSimpleName());
        }

        Date date = new DateTime().toDate();
        Long userId = entity.getUpdatedBy();

        try {
            String attachment_type = entity.getAttachmentType();
            String entity_name = entity.getEntityName();
            Date creation_date = toSqlDate(entity.getCreationDate());
            Long app_id        = entity.getAppId();
            Long created_by    = entity.getCreatedBy();
            String attribute_0 = entity.getAttribute_0();
            String attribute_1 = entity.getAttribute_1();
            String attribute_2 = entity.getAttribute_2();
            String attribute_3 = entity.getAttribute_3();
            String attribute_4 = entity.getAttribute_4();
            String attribute_5 = entity.getAttribute_5();
            String attribute_6 = entity.getAttribute_6();
            String attribute_7 = entity.getAttribute_7();
            String attribute_8 = entity.getAttribute_8();
            String attribute_9 = entity.getAttribute_9();
            String description = entity.getDescription();
            byte[] data        = entity.getData();
            byte[] thumbnail   = entity.getThumbnail();
            String result = null;

            String url = "";
            String file_path = "";

            ////////////////////////////
            if (attachment_type.equals("IMAGE")){
                String ext = FilenameUtils.getExtension(entity.getFilePath()).toLowerCase();

                String base_name = Application.getInstance().getBaseName();
                String path_app = "\\\\local.uralkali.com\\app\\Portal_Mobile";
                String path_url = "http://ber-spportal01.local.uralkali.com:8080/u_attachments/";

                RandomString gen = new RandomString(8, new SecureRandom());

                String name = attribute_0 + "_" + gen.nextString() + "_"  + new Date().getTime(); //att.getPosition();

                file_path = "uploads" + File.separator;

                path_app = path_app + File.separator + base_name;

                if (Application.sAdminUserName.equals(Application.getInstance().getSystemUserName())) {
                    if (ext.equals("jpg")) {
                        file_path = path_app + File.separator + "Photos" + File.separator;
                    } else if (ext.equals("wav")) {
                        file_path = path_app + File.separator + "Waves" + File.separator;
                    } else {
                        file_path = path_app + File.separator + "Others" + File.separator;
                    }
                }
                file_path = file_path + name + "." + ext;
                url = path_url + base_name + "/" + name + "." + ext;
                //TODO UAT path

                //"_"+ att.getFileName() +
                File file = new File(file_path);
                Log.i("Write to file \"" + file.getAbsolutePath() + "\".");
                FileUtils.writeByteArrayToFile(file, data);
            }
            ////////////////////////////

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });

            String sql = Resources.getAsString("/sql/u_attachment_insert.sql");


            try ( CallableStatement st = connection.prepareCall(sql)) {
                st.setString("p_attachment_type", attachment_type);
                st.setString("p_entity_name", entity_name);
                st.setDate("p_creation_date", (java.sql.Date) creation_date);
                st.setLong  ("p_app_id", app_id);
                st.setString("p_url", url);
                st.setString("p_file_path", file_path);
                st.setString("p_thumbnail_path", url);
                st.setObject("p_thumbnail", thumbnail);
                st.setString("p_description", description);
                st.setLong  ("p_created_by", created_by);
                st.setString("p_attribute_0", attribute_0);
                st.setString("p_attribute_1", attribute_1);
                st.setString("p_attribute_2", attribute_2);
                st.setString("p_attribute_3", attribute_3);
                st.setString("p_attribute_4", attribute_4);
                st.setString("p_attribute_5", attribute_5);
                st.setString("p_attribute_6", attribute_6);
                st.setString("p_attribute_7", attribute_7);
                st.setString("p_attribute_8", attribute_8);
                st.setString("p_attribute_9", attribute_9);


                st.registerOutParameter("p_result", Types.INTEGER);

                st.execute();

                result = st.getString("p_result");

                st.close();
            }

        } catch (RuntimeException | SQLException ex) {
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            Logger.getLogger(DataApi.class.getName()).log(Level.SEVERE, null, ex);
        }

        UploadResult response_result = new UploadResult(
                0L, //Строковый ключ можно не передавать
                date,//Created
                userId,
                date,//Updated
                userId,
                date,//Removed
                userId
        );

        String json = Json.toJson(response_result);

        WebResponse response = new WebResponse(webRequest);
        response.setContentType("application/json");
        response.send(json);
        response.sendHeaders(200);
        return 200;
    }

    public static int getUAttachments(WebRequest webRequest, ClientRouter router){
        ApiDebugTools.printRequestParams(webRequest);

        try {
            String entity_type = webRequest.getParam("entity_type");
            String attachment_type = webRequest.getParam("attachment_type");
            String attribute_0 = webRequest.getParam("pk0");
            String attribute_1 = webRequest.getParam("pk1");
            String attribute_2 = webRequest.getParam("pk2");
            String attribute_3 = webRequest.getParam("pk3");
            String attribute_4 = webRequest.getParam("pk4");
            String attribute_5 = webRequest.getParam("pk5");
            String attribute_6 = webRequest.getParam("pk6");
            String attribute_7 = webRequest.getParam("pk7");
            String attribute_8 = webRequest.getParam("pk8");
            String attribute_9 = webRequest.getParam("pk9");

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/u_attachment.sql");

            try ( NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {

                statement.setObject("p_entity_name", entity_type);
                //statement.setObject("p_attachment_type", attachment_type);
                statement.setObject("p_attribute_0", attribute_0);
                statement.setObject("p_attribute_1", attribute_1);
                statement.setObject("p_attribute_2", attribute_2);
                statement.setObject("p_attribute_3", attribute_3);
                statement.setObject("p_attribute_4", attribute_4);
                statement.setObject("p_attribute_5", attribute_5);
                statement.setObject("p_attribute_6", attribute_6);
                statement.setObject("p_attribute_7", attribute_7);
                statement.setObject("p_attribute_8", attribute_8);
                statement.setObject("p_attribute_9", attribute_9);


                try ( ResultSet rs = statement.executeQuery()) {
                    String json;
                    if (rs != null) {
                        List<UAttachmentEntity> data = Pojo.cursorToInstances(rs, UAttachmentEntity.class);
                        rs.next();
                        json = Json.toJson(data);
                    } else {
                        json = Json.toJson(null);
                    }
                    rs.close();
                    statement.close();

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch (RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int getSpecifications(WebRequest webRequest, ClientRouter router){
        try {
            Integer organizationId = Numbers.parseInt(webRequest.getParam("organization_id"));
            Integer inventoryItemId = Numbers.parseInt(webRequest.getParam("inventory_item_id"));
            //String segment1 = webRequest.getParam("segment1");
            
            
            //ApiDebugTools.printRequestParams(webRequest);

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/specifications.sql");
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {
            
                statement.setObject("p_organization_id", organizationId);
                statement.setObject("p_inventory_item_id", inventoryItemId);
                //statement.setObject("p_segment1", segment1);
                
                try(ResultSet rs = statement.executeQuery()) {
                    List<Specification> data = Pojo.cursorToInstances(rs, Specification.class);
                    rs.close();

                    String json = Json.toJson(data);

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int getDocument(WebRequest webRequest, ClientRouter router) {
        try {
            Integer documentId = Numbers.parseInt(webRequest.getParam("document_id"));

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/document.sql");
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {

                statement.setObject("p_document_id", documentId);

                try(ResultSet rs = statement.executeQuery()) {
                    rs.next();
                    Document document = Pojo.cursorToInstance(rs, Document.class);
                    rs.close();

                    if(document.getData() == null && document.getUrl() != null) {
                        throw new RuntimeException("Необходима загрузка документа");
                    }

                    String json = Json.toJson(document);

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int getInspections(WebRequest webRequest, ClientRouter router) {
        try {
            Long instanceId = Numbers.parseLong(webRequest.getParam("instance_id"));
        
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/inspections.sql");
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {

                statement.setLong("p_instance_id", instanceId);

                try(ResultSet rs = statement.executeQuery()) {
                    List<Inspection> data = Pojo.cursorToInstances(rs, Inspection.class);
                    rs.close();

                    String json = Json.toJson(data);

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int addInspection(WebRequest webRequest, ClientRouter router) {
        InspectionEntity entity = null;
        String text = webRequest.getPostAsText();
        try {
            entity = (InspectionEntity) Json.fromJson(webRequest.getPostAsText(), InspectionEntity.class);
        } catch(RuntimeException ex) {
            Log.l(ex.getClass().getSimpleName());
            Log.i(ex.getMessage());
            if(ex.getCause() != null) {
                Log.l(ex.getCause().getClass().getSimpleName());
                Log.l(ex.getCause().getMessage());
            }
            ex.printStackTrace();
            throw new ServerException(400, ex.getClass().getSimpleName());
            //throw new ServerException(400, Http.getMessage(400));
        }
        try {
            Long inspection_id = 0L;

            //Date date = new DateTime().asDate();

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            Boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            try {
                String sql = Resources.getAsString("/sql/inspection_insert.sql");
                //NamedParameterStatement st1 = new NamedParameterStatement(connection, sql);
                   
                Long result = 0L;
                Long userId = 0L;
                Date date = null;
                
                //Integer appId = -1; //TODO: Невозможно определить app_id без полноценных сессий
                String source = "SMARTPHONE";
                
                try(CallableStatement st = connection.prepareCall(sql)) {
                    
                    st.setObject("p_created_by", entity.getCreatedBy());
                    st.setObject("p_organization_id", entity.getOrganizationId());
                    st.setObject("p_instance_id", entity.getInstanceId());
                    st.setObject("p_instance_number", entity.getAssetNumber());
                    st.setObject("p_position_number", entity.getPositionNumber());
                    st.setObject("p_branch_name", entity.getBranchName());
                    st.setObject("p_department_name", entity.getDepartmentName());
                    st.setObject("p_service", entity.getService());
                    st.setDate("p_inspection_date", toSqlDate(entity.getInspectionDate()));
                    st.setDate("p_completion_date", toSqlDate(entity.getCompletionDate()));
                    st.setObject("p_asset_status", entity.getAssetStatus());
                    st.setObject("p_quest_id", entity.getQuestId());
                    st.setObject("p_quest_name", entity.getQuestName());
                    st.setObject("p_brigade", entity.getBrigade());
                    st.setObject("p_shift", entity.getShift());
                    
                    st.setObject("p_source", source);
                    st.setObject("p_app_id", entity.getAppId());
                    /*st1.setObject("p_collection_id", entity.getCollectionId());
                    st1.setObject("p_expired_date", entity.getExpiredDate());
                    st1.setObject("p_created_at", entity.getCreatedAt());
                    st1.setObject("p_created_by", entity.getCreatedBy());
                    st1.setObject("p_updated_at", entity.getUpdatedAt());
                    st1.setObject("p_updated_by", entity.getUpdatedBy());
                    st1.setObject("p_removed_at", entity.getRemovedAt());
                    st1.setObject("p_removed_by", entity.getRemovedBy());*/

                    st.registerOutParameter("p_result", Types.NUMERIC);
                    st.registerOutParameter("p_inspection_id", Types.NUMERIC);
                    st.registerOutParameter("p_created_date", Types.DATE);
                    
                    st.execute();

                    userId = entity.getCreatedBy();
                    
                    result = st.getLong("p_result");
                    inspection_id = st.getLong("p_inspection_id");
                    date = st.getDate("p_created_date");
                    
                    st.close();
                }
                
                if(result > 0L) {

                    String sql2 = Resources.getAsString("/sql/inspection_line_insert.sql");
                    for(InspectionLineEntity line: entity.getLines()) {

                        Long inspection_line_id = null;

                        try(CallableStatement st = connection.prepareCall(sql2)) {
                            //CallableStatement st2 = connection.prepareCall(sql2);

                            st.setObject("p_created_by", entity.getCreatedBy());

                            st.setObject("p_inspection_id", inspection_id /*line.getInspectionId()*/);
                            st.setObject("p_position", line.getPosition());
                            st.setObject("p_asset_status", line.getAssetStatus());

                            st.setObject("p_node_seq", line.getTagPosition());
                            st.setObject("p_node", line.getNode());
                            st.setObject("p_quest_type", line.getQuestType());
                            st.setObject("p_service", line.getService());

                            st.setObject("p_operation", line.getOperation());
                            st.setObject("p_measure_type", line.getMeasureType());
                            st.setObject("p_description", line.getDescription());
                            st.setObject("p_default_result", line.getDefaultResult());
                            st.setObject("p_current_result", line.getCurrentResult());
                            st.setObject("p_current_value", line.getCurrentValue());
                            st.setDate("p_completion_date", toSqlDate(line.getCompletionDate()));

                            st.setObject("p_tag_serial_number", line.getTagSerialNumber());
                            
                            st.setObject("p_measure_device_name", line.getMeasureDeviceName());
                            st.setObject("p_measure_device_number", line.getMeasureDeviceNumber());
                            st.setObject("p_measure_device_address", line.getMeasureDeviceAddress());
                            
                            st.registerOutParameter("p_result", Types.NUMERIC);
                            st.registerOutParameter("p_inspection_line_id", Types.NUMERIC);

                            st.execute();

                            result = st.getLong("p_result");
                            inspection_line_id = st.getLong("p_inspection_line_id");
                        }

                        if(result < 0L) {
                            throw new RuntimeException("Line id = " + Strings.fromValue(inspection_line_id));
                        }

                        String sql3 = Resources.getAsString("/sql/attachment_insert.sql");
                        int attachePosition = 0;
                        for(AttachmentEntity att : line.getAttachments()) {
                            attachePosition++;
                            byte[] data = att.getImage();

                            String ext = FilenameUtils.getExtension(att.getFileName()).toLowerCase();

                            if(data == null) {
                                break;
                            }

                            String base_name = Application.getInstance().getBaseName();
                            String path_app = "\\\\local.uralkali.com\\app\\Portal_Mobile";
                            String path_url = "http://ber-spportal01.local.uralkali.com:8080/attachments/";

                            String name = inspection_id + "_" + inspection_line_id + "_" + attachePosition; //att.getPosition();

                            String file_path = "uploads" + File.separator;

                            path_app = path_app + File.separator + base_name;

                            if(Application.sAdminUserName.equals(Application.getInstance().getSystemUserName())) {
                                if(ext.equals("jpg")) {
                                    file_path = path_app + File.separator + "Photos" + File.separator;
                                } else if(ext.equals("wav")) {
                                    file_path = path_app + File.separator + "Waves" + File.separator;
                                } else {
                                    file_path = path_app + File.separator + "Others" + File.separator;
                                }
                            }
                            file_path = file_path + name + "." + ext;
                            //TODO UAT path

                            //"_"+ att.getFileName() +
                            File file = new File(file_path);
                            Log.i("Write to file \"" + file.getAbsolutePath()+ "\".");
                            FileUtils.writeByteArrayToFile(file, data);

                            String url = path_url + base_name + "/";
                            url = url + name + "." + ext;

                            try(CallableStatement st3 = connection.prepareCall(sql3)) {
                                st3.setObject("p_inspection_id", inspection_id);
                                st3.setObject("p_inspection_line_id", inspection_line_id);
                                st3.setObject("p_position", att.getPosition());
                                st3.setObject("p_file_name", att.getFileName());
                                st3.setObject("p_file_path", file_path);
                                st3.setObject("p_thumbnail_path", null);
                                st3.setObject("p_url", url);
                                st3.setObject("p_created_by", entity.getCreatedBy());

                                st3.registerOutParameter("p_result", Types.NUMERIC);

                                st3.execute();

                                Long attachment_id = st3.getLong("p_result");
                                Log.i("AttachmentId: \"" + attachment_id + "\"");
                            }
                        }

                    }
                    connection.commit(); //TODO
                }
                connection.setAutoCommit(autoCommit);
                //connection.setAutoCommit(false);
                //connection.createStatement().execute("commit");

                UploadResult resultData = new UploadResult(
                        inspection_id,
                        date,//Created
                        userId,
                        date,//Updated
                        userId,
                        date,//Removed
                        userId
                );

                String json = Json.toJson(resultData);

                WebResponse response = new WebResponse(webRequest);
                response.setContentType("application/json");
                response.send(json);
                response.sendHeaders(200);
                return 200;
            } catch (Throwable ex) {
                connection.rollback();
                connection.setAutoCommit(autoCommit);
                //connection.createStatement().execute("rollback");
                throw new RuntimeException(ex);
            }
            
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int addTag(WebRequest webRequest, ClientRouter router) {
        TagEntity entity = null;
        String text = webRequest.getPostAsText();
        try {
            entity = (TagEntity) Json.fromJson(webRequest.getPostAsText(), TagEntity.class);
        } catch(RuntimeException ex) {
            System.out.println(ex.getClass().getSimpleName());
            System.out.println(ex.getMessage());
            if(ex.getCause() != null) {
                System.out.println(ex.getCause().getClass().getSimpleName());
                System.out.println(ex.getCause().getMessage());
            }
            ex.printStackTrace();
            throw new ServerException(400, ex.getClass().getSimpleName());
        }

        Date date = new DateTime().toDate();
        
        try {
            Long userId = entity.getUpdatedBy();
            
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            Boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            try {
                String sql = Resources.getAsString("/sql/update_tag.sql");
                //NamedParameterStatement st1 = new NamedParameterStatement(connection, sql);
                CallableStatement st1 = connection.prepareCall(sql);
                st1.setObject("p_serial_number", entity.getSerialNumber());
                st1.setObject("p_tag_type_id", entity.getTagTypeId());
                st1.setObject("p_organization_id", entity.getOrganizationId());
                st1.setObject("p_category", entity.getCategory());
                st1.setObject("p_app_id", entity.getOrganizationId());
                st1.setObject("p_user_id", entity.getUpdatedBy());
                st1.execute();
                st1.close();
                
                if(entity.getObjects() != null) {
                    for(TagObjectEntity tagObject : entity.getObjects()) {
                        String sql2 = Resources.getAsString("/sql/update_tag_object.sql");
                        CallableStatement st2 = connection.prepareCall(sql2);

                        st2.setObject("p_serial_number", tagObject.getSerialNumber());
                        st2.setObject("p_organization_id", tagObject.getOrganizationId());
                        st2.setObject("p_object_type", tagObject.getObjectType());
                        st2.setObject("p_object_id", tagObject.getObjectId());
                        st2.setObject("p_tag_position", tagObject.getTagPosition());
                        st2.setObject("p_user_id", tagObject.getUpdatedBy());
                        st2.execute();
                        st2.close();
                    }
                }
                
            } catch(Exception ex) {
                connection.rollback();
                connection.setAutoCommit(autoCommit);
                throw new RuntimeException(ex);
            }
            
            connection.commit();
            connection.setAutoCommit(autoCommit);

            UploadResult result = new UploadResult(
                    0L, //Строковый ключ можно не передавать
                    date,//Created
                    userId,
                    date,//Updated
                    userId,
                    date,//Removed
                    userId
            );

            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    private static int getOebsUsers(WebRequest webRequest, ClientRouter router) {
        UsersFilter filter = null;
        try {
            filter = (UsersFilter) Json.fromJson(webRequest.getPostAsText(), UsersFilter.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, ex);
        }
        
        try {
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/oebs_users.sql");
        
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            if(filter.getUserId()!= null) {
                params.put("p_user_id", filter.getUserId());
                conditions += "and USER_ID = :p_user_id ";
            } else {
                if(filter.getUserName()!= null) {
                    params.put("p_user_name", filter.getUserName());
                    conditions += "and upper(USER_NAME) like upper('%'||:p_user_name||'%') ";
                }
                if(filter.getFullName()!= null) {
                    params.put("p_full_name", filter.getFullName());
                    conditions += "and upper(FULL_NAME) like upper('%'||:p_full_name||'%') ";
                }
            }
            
            sql = sql.replace("/*%CONDITIONS%*/", conditions);
            
            String sortOrder = " ";
            if("asc".equals(filter.getSortingOrder())) {
                sortOrder = " asc ";
            } else if("desc".equals(filter.getSortingOrder())) {
                sortOrder = " desc ";
            }
            
            if("USER_NAME".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by USER_NAME" + sortOrder);
            } else if("FULL_NAME".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by FULL_NAME" + sortOrder);
            } else {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by USER_ID" + sortOrder);
            }
            
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {
            
                for(String key : params.keySet()) {
                    statement.setObject(key, params.get(key));
                }
                statement.setObject("p_limit", limit);

                try(ResultSet rs = statement.executeQuery()) {
                    List<OebsUser> data = Pojo.cursorToInstances(rs, OebsUser.class);
                    rs.close();

                    String json = Json.toJson(data);

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int resetPersonPin(WebRequest webRequest, ClientRouter router) {
        try {
            Integer personId = Numbers.parseInt(webRequest.getParam("person_id"));
            String result = null;
            
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            
            String sql = Resources.getAsString("/sql/users/reset_person_pin.sql");
            
            try(CallableStatement st = connection.prepareCall(sql)) {
                    st.setInt("p_person_id", personId);

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
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private static int getUsers(WebRequest webRequest, ClientRouter router) {
        //List<AppClient> c = router.getAppClients();
        //Нет связи AppClients и Users
        //Надо при входе пользователя сообщать user_id
        UsersFilter filter = null;
        try {
            filter = (UsersFilter) Json.fromJson(webRequest.getPostAsText(), UsersFilter.class);
        } catch(RuntimeException ex) {
            throw new ServerException(400, ex);
        }
        
        try {
            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/users.sql");
        
            String conditions = " ";
            HashMap<String, Object> params = new HashMap<>();
            
            if(filter.getUserId()!= null) {
                params.put("p_user_id", filter.getUserId());
                conditions += "and USER_ID = :p_user_id ";
            } else {
                if(filter.getUserName()!= null) {
                    params.put("p_user_name", filter.getUserName());
                    conditions += "and upper(USER_NAME) like upper('%'||:p_user_name||'%') ";
                }
                if(filter.getFullName()!= null) {
                    params.put("p_full_name", filter.getFullName());
                    conditions += "and upper(FULL_NAME) like upper('%'||:p_full_name||'%') ";
                }
                if(filter.getCompanyExists()!= null) {
                    if(filter.getCompanyExists() == true) {
                       conditions += "and COMPANY is not NULL ";
                    }
                }
            }
            
            sql = sql.replace("/*%CONDITIONS%*/", conditions);
            
            String sortOrder = " ";
            if("asc".equals(filter.getSortingOrder())) {
                sortOrder = " asc ";
            } else if("desc".equals(filter.getSortingOrder())) {
                sortOrder = " desc ";
            }
            
            if("USER_NAME".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by USER_NAME" + sortOrder);
            } else if("FULL_NAME".equals(filter.getSorting())) {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by FULL_NAME" + sortOrder);
            } else {
                sql = sql.replace("/"+"*%SORTING%*"+"/", "order by USER_ID" + sortOrder);
            }
            
            Integer limit = MoreObjects.firstNonNull(filter.getLimit(), 20);
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {

                for(String key : params.keySet()) {
                    statement.setObject(key, params.get(key));
                }

                statement.setObject("p_limit", limit);

                try(ResultSet rs = statement.executeQuery()) {
                    List<User> data = Pojo.cursorToInstances(rs, User.class);
                    rs.close();

                    String json = Json.toJson(data);

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    statement.close();
                    return 200;
                }
            }
            
        
            /*String sql = Resources.getAsString("/sql/users.sql");
            String version = webRequest.getParam("version");
            String fullNamePattern = webRequest.getParam("full_name_pattern");
            if(fullNamePattern == null) {
                throw new ServerException(400, Http.getMessage(400));
            }
            
            Session session = router.getSession();
            Query<UserEntity> q = session.createQuery("select at from UserEntity at where rownum <= 10 and upper(full_name) like upper(:p_pattern)", UserEntity.class);
            q.setParameter("p_pattern", fullNamePattern + "%");
            List<UserEntity> result = q.getResultList();*/

            /*List<User> result = session.doReturningWork((Connection c) -> {
                NamedParameterStatement statement = new NamedParameterStatement(c, sql);
                statement.setString("p_full_name_pattern", fullNamePattern);
                ResultSet rs = statement.executeQuery();
                List<User> r = Pojo.cursorToInstances(rs, User.class);
                rs.close();
                statement.close();
                return r;
            });  */
            
            /*Connection connection = router.getMainBase().getConnection();
            
            NamedParameterStatement statement = new NamedParameterStatement(connection, sql);
            statement.setString("p_full_name_pattern", fullNamePattern);
            ResultSet rs = statement.executeQuery();
            List result = Pojo.cursorToInstances(rs, User.class);*/
            
            //DateTime dt = new DateTime(result.get(0).getCreationDate().getTime());
            //String g = dt.toString();
            /*String json = Json.toJson(result);
            
            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;*/
        /*} catch (Exception ex) {
            return router.sendError(webRequest, 500, ex);
        }*/
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int getUser(WebRequest webRequest, ClientRouter router) {
        try {
            Integer userId = Numbers.parseInt(webRequest.getParam("user_id"));

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/user.sql");
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {

                statement.setObject("p_user_id", userId);

                try(ResultSet rs = statement.executeQuery()) {
                    String json;
                    if(rs.next()) {
                        User user = Pojo.cursorToInstance(rs, User.class);
                        rs.close();

                        json = Json.toJson(user);
                    } else {
                        json = Json.toJson(null);
                    }

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch(RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static int getShift(WebRequest webRequest, ClientRouter router) { //userId, DateTime dateTime
        try{
            Long userId;
            Long orgId;
            DateTime date;
            try{
                userId = Numbers.parseLong(webRequest.getParam("user_id"));
                orgId = Numbers.parseLong(webRequest.getParam("org_id"));
                date = new DateTime(webRequest.getParam("date"));
            }catch(RuntimeException ex) {
                return router.sendError(webRequest, 400, new Exception(Http.getMessage(400)) );
            }

            Session session = router.getSession();
            Integer brigade = 0;
            Integer shift = 0;
            if(userId == 0) {
                final Long p_org_id = orgId;
                final java.sql.Date p_date = new java.sql.Date(date.getTime());
                brigade = session.doReturningWork((Connection c) -> {
                    try(CallableStatement statement = c.prepareCall("begin :p_result := xxeam.xxmobile_api_pkg.get_brigade(:p_date, :p_org_id); end;")) {
                        statement.registerOutParameter("p_result", Types.INTEGER);
                        statement.setDate("p_date", p_date);
                        statement.setLong("p_org_id", p_org_id);
                        statement.execute();
                        Integer result = statement.getInt("p_result");
                        return result;
                    }
                });
                if(brigade == null) {
                    brigade = 0;
                }
            }

            if(userId != 0) {
                final Long p_user_id = userId;
                final java.sql.Date p_date = new java.sql.Date(date.getTime());
                brigade = session.doReturningWork((Connection c) -> {
                    try(CallableStatement statement = c.prepareCall("begin :p_result := xxeam.xxmobile_api_pkg.get_brigade(:p_user_id, :p_date); end;")) {
                        statement.registerOutParameter("p_result", Types.INTEGER);
                        statement.setLong("p_user_id", p_user_id);
                        statement.setDate("p_date", p_date);
                        statement.execute();
                        Integer result = statement.getInt("p_result");
                        return result;
                    }
                });
                if(brigade == null) {
                    brigade = 0;
                }
            }

            if(userId != 0) {
                final Long p_user_id = userId;
                final Long p_org_id = orgId;
                final Integer p_brigade = brigade;
                final java.sql.Date p_date = new java.sql.Date(date.getTime());
                shift = session.doReturningWork((Connection c) -> {
                    try(CallableStatement statement = c.prepareCall("begin :p_result := nvl(xxeam.xxmobile_api_pkg.get_shift(:p_user_id, :p_date), xxeam.xxmobile_api_pkg.get_shift_pl(:p_date, :p_brigade, :p_org_id)); end;")) {
                        statement.registerOutParameter("p_result", Types.INTEGER);// 
                        statement.setLong("p_user_id", p_user_id);
                        statement.setDate("p_date", p_date);
                        statement.setDate("p_date2", p_date);
                        statement.setInt("p_brigade", p_brigade);
                        statement.setLong("p_org_id", p_org_id);
                        statement.execute();
                        Integer result = statement.getInt("p_result");
                        return result;
                    }
                });
                if(shift == null) {
                    shift = 0;
                }
            }

            if(userId == 0) {
                final Long p_user_id = userId;
                final Long p_org_id = orgId;
                final Integer p_brigade = brigade;
                final java.sql.Date p_date = new java.sql.Date(date.getTime());
                shift = session.doReturningWork((Connection c) -> {
                    try(CallableStatement statement = c.prepareCall("begin :p_result := xxeam.xxmobile_api_pkg.get_shift_pl(:p_date, :p_brigade, :p_org_id); end;")) {
                        statement.registerOutParameter("p_result", Types.INTEGER);
                        statement.setDate("p_date", p_date);
                        statement.setInt("p_brigade", p_brigade);
                        statement.setLong("p_org_id", p_org_id);
                        statement.execute();
                        Integer result = statement.getInt("p_result");
                        return result;
                    }
                });
                if(shift == null) {
                    shift = 0;
                }
            }

            Shift result = new Shift();
            result.setBrigade(brigade);
            result.setShift(shift);

            String json = Json.toJson(result);

            WebResponse response = new WebResponse(webRequest);
            response.setContentType("application/json");
            response.send(json);
            response.sendHeaders(200);
            return 200;
        } catch(RuntimeException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
        
    public static int getUserData(WebRequest webRequest, ClientRouter router) {
        try {
            Long userId = null;
            String dataName = null;
            UserData userData = null;
            try {
                userId = Numbers.parseLong(webRequest.getParam("user_id"));
                dataName = webRequest.getParam("data_name");
            } catch(RuntimeException ex) {
                ex.printStackTrace();
                throw new ServerException(400, ex.getClass().getSimpleName());
            }

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
            String sql = Resources.getAsString("/sql/user_data.sql");
            
            try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {

                statement.setObject("p_user_id", userId);
                statement.setObject("p_data_name", dataName);

                try(ResultSet rs = statement.executeQuery()) {

                    Map<String,String> data = new HashMap<>();
                    while(rs.next()) {
                        String key = rs.getString("KEY_NAME");
                        String value = rs.getString("VALUE");
                        data.put(key, value);
                        //rs.getString("USER_ID")
                        //rs.getString("DATA_NAME")
                    }
                    rs.close();
                    userData = new UserData(data);

                    String json = Json.toJson(userData);

                    WebResponse response = new WebResponse(webRequest);
                    response.setContentType("application/json");
                    response.send(json);
                    response.sendHeaders(200);
                    return 200;
                }
            }
        } catch (RuntimeException | SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);//SQLRecoverableException
        }
    }
    
    public static int uploadUserData(WebRequest webRequest, ClientRouter router) {
        try {
            Long userId = null;
            String dataName = null;
            Long updatedBy = null;
            UserData userData = null;
            try {
                userId = Numbers.parseLong(webRequest.getParam("user_id"));
                dataName = webRequest.getParam("data_name");
                updatedBy = Numbers.parseLong(webRequest.getParam("updated_by"));
                String postData = webRequest.getPostAsText();
                userData = (UserData) Json.fromJson(postData, UserData.class);
            } catch(RuntimeException ex) {
                ex.printStackTrace();
                throw new ServerException(400, ex.getClass().getSimpleName());
            }

            Date date = new DateTime().toDate();
            final java.sql.Date p_date = new java.sql.Date(date.getTime());

            Session session = router.getSession();
            Connection connection = session.doReturningWork((Connection c) -> {
                return c;
            });
        
            String sql = Resources.getAsString("/sql/user_data_delete.sql");
            try(NamedParameterStatement statement1 = new NamedParameterStatement(connection, sql)) {
                statement1.setObject("p_user_id", userId);
                statement1.setObject("p_data_name", dataName);
                statement1.execute();

                sql = Resources.getAsString("/sql/user_data_insert.sql");
                for(Map.Entry<String,String> entry: userData.getUserData().entrySet()) {

                    try(NamedParameterStatement statement = new NamedParameterStatement(connection, sql)) {

                        statement.setObject("p_user_id", userId);
                        statement.setObject("p_data_name", dataName);
                        statement.setObject("p_key_name", entry.getKey());
                        statement.setObject("p_value", entry.getValue());
                        statement.setObject("p_last_update_date", p_date);
                        statement.setObject("p_last_updated_by", updatedBy);

                        //NTLMAuthentication
                        //NTSystem nt = new NTSystem();
                        //SpnegoHttpURLConnection
                        statement.execute();
                        //connection.prepareStatement("rollback").execute();
                    }
                }
                PreparedStatement commit = connection.prepareStatement("commit");
                commit.execute();

                UploadResult result = new UploadResult(
                        0L,
                        date,//Created
                        userId,
                        date,//Updated
                        userId,
                        date,//Removed
                        userId
                );

                String json = Json.toJson(result);

                WebResponse response = new WebResponse(webRequest);
                response.setContentType("application/json");
                response.send(json);
                response.sendHeaders(200);
                return 200;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
}
