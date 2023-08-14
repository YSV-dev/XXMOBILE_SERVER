package com.uralkali.common.models;

import java.util.Date;

public interface InspectionInterface {
    Long getInstanceId();
    Long getQuestId();
    String getQuestName();
    String getAssetNumber();
    String getPositionNumber();
    String getBranchName();
    Integer getAssetStatus();
    Date getInspectionDate();
    String getCreatedByFullName();
    Integer getFailedCount();
}
