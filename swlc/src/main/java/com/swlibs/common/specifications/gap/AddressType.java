package com.swlibs.common.specifications.gap;

/**
 * GAP_ADDR_TYPE_DEFINES
 *
 * Address Types
 */
public class AddressType {

    /**
     * Use the BD_ADDR
     */
    public static final int ADDRTYPE_PUBLIC               = 0x00;

    /**
     * Static address
     */
    public static final int ADDRTYPE_STATIC               = 0x01;

    /**
     * Generate Non-Resolvable Private Address
     */
    public static final int ADDRTYPE_PRIVATE_NONRESOLVE   = 0x02;

    /**
     * Generate Resolvable Private Address
     */
    public static final int ADDRTYPE_PRIVATE_RESOLVE      = 0x03;

}
