package com.swlibs.common.specifications.gap.data;

public class Advertisement {
    private byte mDataType;
    private String mDataTypeCode;
    private String mDataTypeName;
    private byte[] mRawData;
    private Object mData;

    public Advertisement(byte dataType, String dataTypeCode, String dataTypeName, byte[] rawData, Object data) {
        this.mDataType = dataType;
        this.mDataTypeCode = dataTypeCode;
        this.mDataTypeName = dataTypeName;
        this.mRawData = rawData;
        this.mData = data;
    }

    public byte getDataType() {
        return mDataType;
    }

    public String getDataTypeCode() {
        return mDataTypeCode;
    }

    public String getDataTypeName() {
        return mDataTypeName;
    }

    public byte[] getRawData() {
        return mRawData;
    }

    public Object getData() {
        return mData;
    }
}
