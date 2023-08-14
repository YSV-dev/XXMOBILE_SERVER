package com.swlibs.common.specifications.gap.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.swlibs.common.specifications.gap.AdvertisementDataType;

public class GapRecord {

    private String mLocalName;
    private List<Advertisement> mAdvertisements = new ArrayList<>();

    public GapRecord(byte[] advertising) {

        ByteBuffer bb = ByteBuffer.wrap(advertising);
        bb.order(ByteOrder.LITTLE_ENDIAN);

        while(bb.hasRemaining()) {
            int size = bb.get();
            if(size == 0) {
                break;
            }
            byte dataType = bb.get();
            byte[] data = new byte[size - 1];
            for(int i = 0; i < (size - 1); i++) {
                data[i] = bb.get();
            }
            addData(dataType, data);
        }

    }

    public List<Advertisement> getData() {
        return mAdvertisements;
    }

    private void addData(byte dataType, byte[] rawData) {
        String dataTypeName;
        String dataTypeDesc;
        Integer size;
        Object advData = null;

        switch (dataType) {
            case AdvertisementDataType.GAP_ADTYPE_FLAGS: {
                dataTypeName = "GAP_ADTYPE_FLAGS";
                dataTypeDesc = "Flags";
                size = 1;
                advData = new Flags(rawData[0]);
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_16BIT_MORE: {
                dataTypeName = "GAP_ADTYPE_16BIT_MORE";
                dataTypeDesc = "UUID";
                size = 2;
                advData = UUID.nameUUIDFromBytes(rawData);
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_16BIT_COMPLETE: {
                dataTypeName = "GAP_ADTYPE_16BIT_COMPLETE";
                dataTypeDesc = "UUID";
                size = 2;
                ByteBuffer bb = ByteBuffer.wrap(rawData);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                List<Short> array = new ArrayList<>();
                while (bb.hasRemaining()) {
                    array.add(bb.getShort());
                }
                advData = array;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_32BIT_MORE: {
                dataTypeName = "GAP_ADTYPE_32BIT_MORE";
                dataTypeDesc = "UUID";
                size = 4;
                advData = UUID.nameUUIDFromBytes(rawData);
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_32BIT_COMPLETE: {
                dataTypeName = "GAP_ADTYPE_32BIT_COMPLETE";
                dataTypeDesc = "UUID";
                size = 4;
                ByteBuffer bb = ByteBuffer.wrap(rawData);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                List<Integer> array = new ArrayList<>();
                while (bb.hasRemaining()) {
                    array.add(bb.getInt());
                }
                advData = array;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_128BIT_MORE: {
                dataTypeName = "GAP_ADTYPE_128BIT_MORE";
                dataTypeDesc = "UUID";
                size = 16;
                advData = UUID.nameUUIDFromBytes(rawData);
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_128BIT_COMPLETE: {
                dataTypeName = "GAP_ADTYPE_128BIT_COMPLETE";
                dataTypeDesc = "UUID";
                size = 16;
                advData = UUID.nameUUIDFromBytes(rawData);
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_LOCAL_NAME_SHORT:
                dataTypeName = "GAP_ADTYPE_LOCAL_NAME_SHORT";
                dataTypeDesc = "Short name";
                size = 0;
                mLocalName = new String(rawData);
                advData = mLocalName;
                break;
            case AdvertisementDataType.GAP_ADTYPE_LOCAL_NAME_COMPLETE: {
                dataTypeName = "GAP_ADTYPE_LOCAL_NAME_COMPLETE";
                dataTypeDesc = "Name";
                size = 0;
                mLocalName = new String(rawData);
                advData = mLocalName;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_POWER_LEVEL: {
                dataTypeName = "GAP_ADTYPE_POWER_LEVEL";
                dataTypeDesc = "Power level";
                size = 1;
                advData = rawData[0];
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_OOB_CLASS_OF_DEVICE: {
                dataTypeName = "GAP_ADTYPE_OOB_CLASS_OF_DEVICE";
                dataTypeDesc = "Simple pairing OOB Tag (Class of device)";
                size = 3;
                advData = rawData;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_OOB_SIMPLE_PAIRING_HASHC:
                dataTypeName = "GAP_ADTYPE_OOB_SIMPLE_PAIRING_HASHC";
                dataTypeDesc = "Simple pairing OOB Tag (Hash C)";
                size = 16;
                advData = rawData;
                break;
            case AdvertisementDataType.GAP_ADTYPE_OOB_SIMPLE_PAIRING_RANDR: {
                dataTypeName = "GAP_ADTYPE_OOB_SIMPLE_PAIRING_RANDR";
                dataTypeDesc = "Simple Pairing OOB Tag (Randomizer R)";
                size = 16;
                advData = rawData;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_SM_TK: {
                dataTypeName = "GAP_ADTYPE_SM_TK";
                dataTypeDesc = "Security Manager TK Value";
                size = 0;
                advData = rawData;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_SM_OOB_FLAG: {
                dataTypeName = "GAP_ADTYPE_SM_OOB_FLAG";
                dataTypeDesc = "Secutiry Manager OOB Flags";
                size = 0;
                advData = rawData;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_SLAVE_CONN_INTERVAL_RANGE: {
                dataTypeName = "GAP_ADTYPE_SLAVE_CONN_INTERVAL_RANGE";
                dataTypeDesc = "Min and Max values of the connection interval";
                size = 4;
                ByteBuffer bb = ByteBuffer.wrap(rawData);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                short[] values = new short[2];
                values[0] = bb.getShort();
                values[1] = bb.getShort();
                advData = values;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_SIGNED_DATA: {
                dataTypeName = "GAP_ADTYPE_SIGNED_DATA";
                dataTypeDesc = "Signed Data field";
                size = 0;
                advData = rawData;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_SERVICES_LIST_16BIT: {
                dataTypeName = "GAP_ADTYPE_SERVICES_LIST_16BIT";
                dataTypeDesc = "Service Solicitation";
                size = 2;
                advData = rawData;
            }
            case AdvertisementDataType.GAP_ADTYPE_SERVICES_LIST_128BIT: {
                dataTypeName = "GAP_ADTYPE_SERVICES_LIST_128BIT";
                dataTypeDesc = "Service Solicitation";
                size = 16;
                advData = rawData;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_SERVICE_DATA: {
                dataTypeName = "GAP_ADTYPE_SERVICE_DATA";
                dataTypeDesc = "Service Data";
                size = 0;
                advData = rawData;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_APPEARANCE: {
                dataTypeName = "GAP_ADTYPE_APPEARANCE";
                dataTypeDesc = "Appearance";
                size = 0;
                advData = rawData;
                break;
            }
            case AdvertisementDataType.GAP_ADTYPE_MANUFACTURER_SPECIFIC: {
                dataTypeName = "GAP_ADTYPE_MANUFACTURER_SPECIFIC";
                dataTypeDesc = "Manufacturer Specific Data";
                size = 0;
                advData = new ManufacturerSpecificData(rawData);
                break;
            }
            default: {
                dataTypeName = "N/A";
                dataTypeDesc = "N/A";
                size = null;
            }
        }
        mAdvertisements.add(new Advertisement(dataType, dataTypeName, dataTypeDesc, rawData, advData));
    }

}
