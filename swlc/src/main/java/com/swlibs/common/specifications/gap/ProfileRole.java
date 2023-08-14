package com.swlibs.common.specifications.gap;

/**
 * GAP_PROFILE_ROLE_DEFINES
 *
 * Profile Roles
 */
public class ProfileRole {

    /**
     * A device that sends advertising events only.
     */
    public static final int GAP_PROFILE_BROADCASTER               = 0x01;

    /**
     * A device that receives advertising events only.
     */
    public static final int GAP_PROFILE_OBSERVER                  = 0x02;

    /**
     * A device that accepts the establishment of an LE physical link using the connection establishment procedure
     */
    public static final int GAP_PROFILE_PERIPHERAL                = 0x04;

    /**
     * A device that supports the Central role initiates the establishment of a physical connection
     */
    public static final int GAP_PROFILE_CENTRAL                   = 0x08;

}
