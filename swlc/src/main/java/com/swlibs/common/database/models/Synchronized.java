package com.swlibs.common.database.models;

import java.util.Date;

public interface Synchronized {
    void setSyncId(Long syncId);
    void setSyncAt(Date date);
    Date getSyncAt();
}
