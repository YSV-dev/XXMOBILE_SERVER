package com.swlibs.common.specifications.gap;

/**
 * GAP_DEVDISC_MODE_DEFINES
 *
 * Device Discovery Modes
 */
public class DeviceDiscoveryMode {

    /**
     * No discoverable setting
     */
    public static final int DEVDISC_MODE_NONDISCOVERABLE  = 0x00;

    /**
     * General Discoverable devices
     */
    public static final int DEVDISC_MODE_GENERAL          = 0x01;

    /**
     * Limited Discoverable devices
     */
    public static final int DEVDISC_MODE_LIMITED          = 0x02;

    /**
     * Not filtered
     */
    public static final int DEVDISC_MODE_ALL              = 0x03;
}
