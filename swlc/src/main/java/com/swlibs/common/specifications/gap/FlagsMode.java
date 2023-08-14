package com.swlibs.common.specifications.gap;

public class FlagsMode {

    /**
     * LE Limited Discoverable Mode
     */
    public static final byte GAP_ADTYPE_FLAGS_LIMITED                = 0x01;

    /**
     * LE General Discoverable Mode
     */
    public static final byte GAP_ADTYPE_FLAGS_GENERAL                = 0x02;

    /**
     * BR/EDR Not Supported
     */
    public static final byte GAP_ADTYPE_FLAGS_BREDR_NOT_SUPPORTED    = 0x04;

    /**
     * LE and BR/ERD Capable (Controller)
     *
     * TODO: Не точное название флага
     */
    public static final byte GAP_ADTYPE_FLAGS_BRERD_CAPABLE_CTRL    = 0x08;

    /**
     * LE and BR/ERD Capable (Host)
     *
     * TODO: Не точное название флага
     */
    public static final byte GAP_ADTYPE_FLAGS_BRERD_CAPABLE_HOST    = 0x10;

    /**
     * Reserved 1
     *
     * Резервный флаг 1
     *
     * TODO: Не точное название флага
     */
    public static final byte GAP_ADTYPE_FLAGS_RESERVED1    = 0x20;

    /**
     * Reserved 2
     *
     * Резервный флаг 2
     *
     * TODO: Не точное название флага
     */
    public static final byte GAP_ADTYPE_FLAGS_RESERVED2    = 0x40;

    /**
     * Reserved 3
     *
     * Резервный флаг 3
     *
     * TODO: Не точное название флага
     */
    public static final byte GAP_ADTYPE_FLAGS_RESERVED3    = (byte)0x80;
}
