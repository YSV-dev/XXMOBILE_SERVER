package com.swlibs.common.database.models;

import java.util.Date;

/**
 *
 */
public interface WhoModel {
    //Создание
    Date getCreatedAt();
    Long getCreatedBy();
    //Обновление
    Date getUpdatedAt();
    Long getUpdatedBy();
    //Удаление
    Date getRemovedAt();
    Long getRemovedBy();
}
