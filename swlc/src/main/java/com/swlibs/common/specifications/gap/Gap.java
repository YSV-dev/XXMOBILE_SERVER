package com.swlibs.common.specifications.gap;

/**
 * Generic Access Profile
 *
 * Профиль общего доступа
 *
 * https://github.com/lee-wei/CC2540/blob/master/Components/ble/include/gap.h
 */
public class Gap {

    //GAP_PARAMETER_ID_DEFINES

    //GAP_DEVDISC_MODE_DEFINES

    //GAP_ADDR_TYPE_DEFINES

    //GAP_ADVERTISEMENT_TYPE_DEFINES

    //GAP_FILTER_POLICY_DEFINES

    /**
     * Advertiser Channel Map
     */
    public int ADV_CHANMAP_SIZE                      = 5;

    /**
     * Maximum Pairing Passcode/Passkey value.  Range of a passkey can be 0 - 999,999.
     */
    public int GAP_PASSCODE_MAX                      = 999999;

    /**
     * Sign Counter Initialized - Sign counter hasn't been used yet.  Used when setting up
     * a connection's signing information.
     */
    public int GAP_INIT_SIGN_COUNTER                 = 0xFFFFFFFF;

    //GAP_ADVCHAN_DEFINES

    //GAP_WHITELIST_DEFINES

    //GAP_APPEARANCE_VALUES

}
