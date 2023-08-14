package com.swlibs.common.specifications.gap;

/**
 * GAP_WHITELIST_DEFINES
 *
 * White List Options
 */
public class WhiteListOption {

    /**
     * White list not used but the advertiser's address in this command is used
     */
    public static final int WL_NOTUSED    = 0x00;

    /**
     * White list is used and the advertiser's address in this command is not used.
     */
    public static final int WL_USED       = 0x01;

}
