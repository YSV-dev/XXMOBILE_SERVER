package com.swlibs.common.specifications.gap;

/**
 * GAP_ADVERTISEMENT_TYPE_DEFINES
 *
 * Advertiser Event Types
 */
public class AdvertiserEventType {

    /**
     * Connectable undirected advertisement
     */
    public static final int GAP_ADTYPE_ADV_IND                = 0x00;

    /**
     * Connectable directed advertisement
     */
    public static final int GAP_ADTYPE_ADV_DIRECT_IND         = 0x01;

    /**
     * Discoverable undirected advertisement
     */
    public static final int GAP_ADTYPE_ADV_DISCOVER_IND       = 0x02;

    /**
     * Non-Connectable undirected advertisement
     */
    public static final int GAP_ADTYPE_ADV_NONCONN_IND        = 0x03;

    /**
     * Only used in gapDeviceInfoEvent_t
     */
    public static final int GAP_ADTYPE_SCAN_RSP_IND           = 0x04;

}
