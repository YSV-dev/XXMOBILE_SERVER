package com.uralkali.server.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

//@Entity
//@Table(name = "XXEAM.XXMOBILE_INSPECTION_LINES")
public class InspectionLineEntity implements Serializable {

    //@Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="G_IL_ID_S")
    //@SequenceGenerator(name="G_IL_ID_S", sequenceName="XXEAM.XXMOBILE_INSPECT_LINE_ID_SEQ", allocationSize=1)
    @Column(name="INSPECTION_LINE_ID", updatable = false, nullable = false)
    private Long inspection_line_id;

    @Column(name = "inspection_id")
    private Long inspection_id;

    @Column(name = "position")
    private Integer position;

    @Column(name = "asset_status")
    private Integer asset_status;

    //@Column(name = "measure_point") //TODO:NONE
    //private String measure_point;

    @Column(name = "node_seq") //TODO:tag_position
    private Integer tag_position;

    @Column(name = "node")
    private String node;

    @Column(name = "quest_type")
    private String quest_type;

    @Column(name = "service")
    private String service;

    /*@Column(name = "answer_type_id")
    private Integer answer_type_id;

    @Column(name = "measure_units_id")
    private Integer measure_units_id;*/

    @Column(name = "operation")
    private String operation;

    @Column(name = "measure_type")
    private String measure_type;

    @Column(name = "description")
    private String description;

    @Column(name = "default_result")
    private String default_result;

    @Column(name = "current_result")
    private String current_result;

    /*@Column(name = "min_value")
    private Float min_value;

    @Column(name = "max_value")
    private Float max_value;*/

    @Column(name = "current_value")
    private Float current_value;

    @Column(name = "completion_date")
    private Date completion_date;
    
    @Column(name = "tag_serial_number")
    private String tag_serial_number;
    
    @Column(name = "measure_device_name")
    private String measure_device_name;
    
    @Column(name = "measure_device_number")
    private String measure_device_number;
    
    @Column(name = "measure_device_address")
    private String measure_device_address;

    @Column(name = "created_by")
    private Long created_by;
    
    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "attachment_id")*/
    @Transient
    private List<AttachmentEntity> attachments;

    public List<AttachmentEntity> getAttachments() {
        return attachments;
    }

    public InspectionLineEntity() {

    }

    public Boolean isAssetStatus(Integer value){
        if(asset_status == null){
            return true;
        }else{
            if(asset_status.equals(value)){
                return true;
            }else{
                return false;
            }
        }
    }

    public Boolean isNormal(){
        if(this.getDefaultResult().equals(this.getCurrentResult())){
            return true;
        }else{
            return false;
        }
    }

    public Boolean isAnswered(){
        if(this.completion_date != null){
            return true;
        }else{
            return false;
        }
    }

    public Long getInspectionLineId() {
        return this.inspection_line_id;
    }

    //public Integer getQuestId() {
    //    return this.questId;
    //}

    public Integer getPosition() {
        return this.position;
    }

    //public Integer getOrganizationId() {
    //    return organizationId;
    //}


    public String getOperation() {
        return operation;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMeasureType() {
        return this.measure_type;
    }

    public String getDefaultResult() {
        return default_result;
    }

    public String getCurrentResult() {
        return current_result;
    }

    public Float getCurrentValue() {
        return current_value;
    }

    public String getQuestType() {
        return quest_type;
    }

    public String getService() {
        return service;
    }

    public void setQuestType(String questType) {
        this.quest_type = questType;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setMeasureType(String measureType) {
        this.measure_type = measureType;
    }

    public Long getInspectionId() {
        return inspection_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setDefaultResult(String defaultResult) {
        this.default_result = defaultResult;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspection_id = inspectionId;
    }

    public void setInspectionLineId(Long inspectionLineId) {
        this.inspection_line_id = inspectionLineId;
    }

    public void setCurrentResult(String currentResult) {
        this.current_result = currentResult;
    }

    public void setCurrentValue(Float currentValue) {
        this.current_value = currentValue;
    }

    public Integer getAssetStatus() {
        return asset_status;
    }

    public void setAssetStatus(Integer assetStatus) {
        this.asset_status = assetStatus;
    }

    /*public Integer getNodeSeq() {
        return nodeSeq;
    }*/

    /*public String getMeasurePoint() {
        return measure_point;
    }*/

    public Integer getTagPosition() {
        return tag_position;
    }

    public String getNode() {
        return node;
    }

    /*public void setNodeSeq(Integer nodeSeq) {
        this.nodeSeq = nodeSeq;
    }*/

    public void setNode(String node) {
        this.node = node;
    }

    /*public void setMeasurePoint(String measurePoint) {
        this.measure_point = measurePoint;
    }*/

    public void setTagPosition(Integer tagPosition) {
        this.tag_position = tagPosition;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setCompletionDate(Date completionDate) {
        this.completion_date = completionDate;
    }

    public Date getCompletionDate() {
        return completion_date;
    }

    public String getTagSerialNumber() {
        return tag_serial_number;
    }

    public void setTagSerialNumber(String tagSerialNumber) {
        this.tag_serial_number = tagSerialNumber;
    }
    
    public String getMeasureDeviceName() {
        return measure_device_name;
    }

    public void setMeasureDeviceName(String measureDeviceName) {
        this.measure_device_name = measureDeviceName;
    }

    public String getMeasureDeviceNumber() {
        return measure_device_number;
    }
    
    public void setMeasureDeviceNumber(String measureDeviceNumber) {
        this.measure_device_number = measureDeviceNumber;
    }

    public String getMeasureDeviceAddress() {
        return measure_device_address;
    }
    
    public void setMeasureDeviceAddress(String measureDeviceAddress) {
        this.measure_device_address = measureDeviceAddress;
    }

    public Long getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(Long createdBy) {
        this.created_by = createdBy;
    }

}