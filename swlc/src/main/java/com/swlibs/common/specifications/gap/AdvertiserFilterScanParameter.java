package com.swlibs.common.specifications.gap;

/**
 * GAP_FILTER_POLICY_DEFINES
 *
 * Advertiser Filter Scan Parameters
 */
public class AdvertiserFilterScanParameter {

    /**
     * Allow Scan Request from Any, Allow Connect Request from Any (default).
     */
    public static final int GAP_FILTER_POLICY_ALL         = 0x00;

    /**
     * Allow Scan Request from White List Only, Allow Connect from Any
     */
    public static final int GAP_FILTER_POLICY_WHITE_SCAN  = 0x01;

    /**
     * Allow Scan Request from Any, Connect from White List Only
     */
    public static final int GAP_FILTER_POLICY_WHITE_CON   = 0x02;

    /**
     * Allow Scan Request and Connect from White List Only
     */
    public static final int GAP_FILTER_POLICY_WHITE       = 0x03;

}
