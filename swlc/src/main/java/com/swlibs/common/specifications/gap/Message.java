package com.swlibs.common.specifications.gap;

/**
 * GAP_MSG_EVENT_DEFINES
 *
 * Message IDs
 *
 * УН сообщений
 */
public class Message {

    /**
     * Sent when the Device Initialization is complete.  This event is sent as an OSAL message defined as gapDeviceInitDoneEvent_t.
     */
    public static final int GAP_DEVICE_INIT_DONE_EVENT            = 0x00;

    /**
     * Sent when the Device Discovery Process is complete. This event is sent as an OSAL message defined as gapDevDiscEvent_t.
     */
    public static final int GAP_DEVICE_DISCOVERY_EVENT            = 0x01;

    /**
     * Sent when the Advertising Data or SCAN_RSP Data has been updated. This event is sent as an OSAL message defined as gapAdvDataUpdateEvent_t.
     */
    public static final int GAP_ADV_DATA_UPDATE_DONE_EVENT        = 0x02;

    /**
     * Sent when the Make Discoverable Request is complete. This event is sent as an OSAL message defined as gapMakeDiscoverableRspEvent_t.
     */
    public static final int GAP_MAKE_DISCOVERABLE_DONE_EVENT      = 0x03;

    /**
     * Sent when the Advertising has ended. This event is sent as an OSAL message defined as gapEndDiscoverableRspEvent_t.
     */
    public static final int GAP_END_DISCOVERABLE_DONE_EVENT       = 0x04;

    /**
     * Sent when the Establish Link Request is complete. This event is sent as an OSAL message defined as gapEstLinkReqEvent_t.
     */
    public static final int GAP_LINK_ESTABLISHED_EVENT            = 0x05;

    /**
     * Sent when a connection was terminated. This event is sent as an OSAL message defined as gapTerminateLinkEvent_t.
     */
    public static final int GAP_LINK_TERMINATED_EVENT             = 0x06;

    /**
     * Sent when an Update Parameters Event is received. This event is sent as an OSAL message defined as gapLinkUpdateEvent_t.
     */
    public static final int GAP_LINK_PARAM_UPDATE_EVENT           = 0x07;

    /**
     * Sent when a random address was changed. This event is sent as an OSAL message defined as gapRandomAddrEvent_t.
     */
    public static final int GAP_RANDOM_ADDR_CHANGED_EVENT         = 0x08;

    /**
     * Sent when the device's signature counter is updated. This event is sent as an OSAL message defined as gapSignUpdateEvent_t.
     */
    public static final int GAP_SIGNATURE_UPDATED_EVENT           = 0x09;

    /**
     * Sent when the Authentication (pairing) process is complete. This event is sent as an OSAL message defined as gapAuthCompleteEvent_t.
     */
    public static final int GAP_AUTHENTICATION_COMPLETE_EVENT     = 0x0A;

    /**
     * Sent when a Passkey is needed.  This is part of the pairing process. This event is sent as an OSAL message defined as gapPasskeyNeededEvent_t.
     */
    public static final int GAP_PASSKEY_NEEDED_EVENT              = 0x0B;

    /**
     * Sent when a Slave Security Request is received. This event is sent as an OSAL message defined as gapSlaveSecurityReqEvent_t.
     */
    public static final int GAP_SLAVE_REQUESTED_SECURITY_EVENT    = 0x0C;

    /**
     * Sent during the Device Discovery Process when a device is discovered. This event is sent as an OSAL message defined as gapDeviceInfoEvent_t.
     */
    public static final int GAP_DEVICE_INFO_EVENT                 = 0x0D;

    /**
     * Sent when the bonding(bound) process is complete. This event is sent as an OSAL message defined as gapBondCompleteEvent_t.
     */
    public static final int GAP_BOND_COMPLETE_EVENT               = 0x0E;

    /**
     * Sent when an unexpected Pairing Request is received. This event is sent as an OSAL message defined as gapPairingReqEvent_t.
     */
    public static final int GAP_PAIRING_REQ_EVENT                 = 0x0F;

}
