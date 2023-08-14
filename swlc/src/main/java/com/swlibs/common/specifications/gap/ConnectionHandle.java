package com.swlibs.common.specifications.gap;

/**
 * GAP_CONN_HANDLE_DEFINES
 *
 * Special Connection Handles
 */
public class ConnectionHandle {

    /**
     * terminates a link create
     */
    public static final int GAP_CONNHANDLE_INIT                   = 0xFFFE;

    /**
     * terminates all links for the matching task ID.
     */
    public static final int GAP_CONNHANDLE_ALL                    = 0xFFFF;

}
