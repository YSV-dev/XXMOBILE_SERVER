package com.swlibs.common.specifications.gap;

/**
 * GAP_ADTYPE_DEFINES
 *
 * Data Types.
 * These are the data type identifiers for the data tokens in the advertisement data field.
 */
public class AdvertisementDataType {

    /**
     * Discovery Mode: @ref GAP_ADTYPE_FLAGS_MODES
     */
    public static final byte GAP_ADTYPE_FLAGS                        = 0x01;

    /**
     * Service: More 16-bit UUIDs available
     */
    public static final byte GAP_ADTYPE_16BIT_MORE                   = 0x02;

    /**
     * Service: Complete list of 16-bit UUIDs
     */
    public static final byte GAP_ADTYPE_16BIT_COMPLETE               = 0x03;

    /**
     * Service: More 32-bit UUIDs available
     */
    public static final byte GAP_ADTYPE_32BIT_MORE                   = 0x04;

    /**
     * Service: Complete list of 32-bit UUIDs
     */
    public static final byte GAP_ADTYPE_32BIT_COMPLETE               = 0x05;

    /**
     * Service: More 128-bit UUIDs available
     */
    public static final byte GAP_ADTYPE_128BIT_MORE                  = 0x06;

    /**
     * Service: Complete list of 128-bit UUIDs
     */
    public static final byte GAP_ADTYPE_128BIT_COMPLETE              = 0x07;

    /**
     * Shortened local name
     *
     * Короткое наименование устройства
     */
    public static final byte GAP_ADTYPE_LOCAL_NAME_SHORT             = 0x08;

    /**
     * Complete local name
     *
     * Полное наименование устройства
     */
    public static final byte GAP_ADTYPE_LOCAL_NAME_COMPLETE          = 0x09;

    /**
     * TX Power Level: 0xXX: -127 to +127 dBm
     */
    public static final byte GAP_ADTYPE_POWER_LEVEL                  = 0x0A;

    /**
     * Simple Pairing OOB Tag: Class of device (3 octets)
     */
    public static final byte GAP_ADTYPE_OOB_CLASS_OF_DEVICE          = 0x0D;

    /**
     * Simple Pairing OOB Tag: Simple Pairing Hash C (16 octets)
     */
    public static final byte GAP_ADTYPE_OOB_SIMPLE_PAIRING_HASHC     = 0x0E;

    /**
     * Simple Pairing OOB Tag: Simple Pairing Randomizer R (16 octets)
     */
    public static final byte GAP_ADTYPE_OOB_SIMPLE_PAIRING_RANDR     = 0x0F;

    /**
     * Security Manager TK Value
     */
    public static final byte GAP_ADTYPE_SM_TK                        = 0x10;

    /**
     * Secutiry Manager OOB Flags
     */
    public static final byte GAP_ADTYPE_SM_OOB_FLAG                  = 0x11;

    /**
     * Min and Max values of the connection interval (2 octets Min, 2 octets Max) (0xFFFF indicates no conn interval min or max)
     */
    public static final byte GAP_ADTYPE_SLAVE_CONN_INTERVAL_RANGE    = 0x12;

    /**
     * Signed Data field
     */
    public static final byte GAP_ADTYPE_SIGNED_DATA                  = 0x13;

    /**
     * Service Solicitation: list of 16-bit Service UUIDs
     */
    public static final byte GAP_ADTYPE_SERVICES_LIST_16BIT          = 0x14;

    /**
     * Service Solicitation: list of 128-bit Service UUIDs
     */
    public static final byte GAP_ADTYPE_SERVICES_LIST_128BIT         = 0x15;

    /**
     * Service Data
     */
    public static final byte GAP_ADTYPE_SERVICE_DATA                 = 0x16;

    /**
     * Appearance
     */
    public static final byte GAP_ADTYPE_APPEARANCE                   = 0x19;

    /**
     * Manufacturer Specific Data: first 2 octets contain the Company Identifier Code followed by the additional manufacturer specific data
     *
     * Специфические данные производителя: первые два байта содержат идентификатор компании за которыми слудуют дополнительные данные
     */
    public static final byte GAP_ADTYPE_MANUFACTURER_SPECIFIC        = (byte)0xFF;
}
