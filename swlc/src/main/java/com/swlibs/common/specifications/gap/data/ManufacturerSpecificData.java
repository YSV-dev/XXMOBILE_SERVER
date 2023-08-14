package com.swlibs.common.specifications.gap.data;

import com.swlibs.common.specifications.gap.Manufacturer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ManufacturerSpecificData {

    private String mManufacturerName;
    private short mManufacturerId;
    private byte[] mData;

    public ManufacturerSpecificData(byte[] data) {

        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        this.mData = new byte[data.length - 2];
        this.mManufacturerId = bb.getShort();
        int i = 0;
        while(bb.hasRemaining()) {
            this.mData[i] = bb.get();
            i++;
        }
        this.mManufacturerName = Manufacturer.getName(this.mManufacturerId);
    }

    public short getManufacturerId() {
        return mManufacturerId;
    }

    public String getManufacturerName() {
        return mManufacturerName;
    }

    public byte[] getData() {
        return mData;
    }

}
