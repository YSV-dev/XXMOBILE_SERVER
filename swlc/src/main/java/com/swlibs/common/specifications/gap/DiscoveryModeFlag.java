package com.swlibs.common.specifications.gap;

/**
 * GAP_ADTYPE_FLAGS_MODES
 *
 * Flags Discovery Modes
 *
 * Флаги режимов обнаружения
 */
public class DiscoveryModeFlag {

    /**
     * Discovery Mode: LE Limited Discoverable Mode
     *
     * Режим обнаружения: LE Ограниченный режим обнаружения
     */
    public static final int GAP_ADTYPE_FLAGS_LIMITED                = 0x01;

    /**
     * Discovery Mode: LE General Discoverable Mode
     *
     * Режим обнаружения: LE Общий режим обнаружения
     */
    public static final int GAP_ADTYPE_FLAGS_GENERAL                = 0x02;

    /**
     * Discovery Mode: BR/EDR Not Supported
     *
     * Режим обнаружения: BR/EDR не поддерживается
     */
    public static final int GAP_ADTYPE_FLAGS_BREDR_NOT_SUPPORTED    = 0x04;

}
