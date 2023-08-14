package com.swlibs.common.specifications.gap;

import java.util.HashMap;
import java.util.Map;

/**
 * Company Identifiers
 */
public class Manufacturer {

    private static Map<Short, String> sMap;

    public static void init() {
        if(sMap != null) {
            return;

        }
        sMap = new HashMap<>();

        sMap.put( (short)0x0000, "Ericsson Technology Licensing");
        sMap.put( (short)0x0001, "Nokia Mobile Phones");
        sMap.put( (short)0x0002, "Intel Corp.");
        sMap.put( (short)0x0003, "IBM Corp.");
        sMap.put( (short)0x0004, "Toshiba Corp.");
        sMap.put( (short)0x0005, "3Com");
        sMap.put( (short)0x0006, "Microsoft");
        sMap.put( (short)0x0007, "Lucent");
        sMap.put( (short)0x0008, "Motorola");
        sMap.put( (short)0x0009, "Infineon Technologies AG");
        sMap.put( (short)0x000A, "Qualcomm Technologies International, Ltd. (QTIL)");
        sMap.put( (short)0x000B, "Silicon Wave");
        sMap.put( (short)0x000C, "Digianswer A/S");
        sMap.put( (short)0x000D, "Texas Instruments Inc.");
        sMap.put( (short)0x000E, "Parthus Technologies Inc.");
        sMap.put( (short)0x000F, "Broadcom Corporation");
        sMap.put( (short)0x0010, "Mitel Semiconductor");
        sMap.put( (short)0x0011, "Widcomm, Inc.");
        //...
        sMap.put( (short)0x0045, "Atheros Communications, Inc.");
        sMap.put( (short)0x0046, "MediaTek, Inc.");
        sMap.put( (short)0x0047, "Bluegiga");
        sMap.put( (short)0x0048, "Marvell Technology Group Ltd.");
        sMap.put( (short)0x0049, "3DSP Corporation");
        sMap.put( (short)0x004A, "Accel Semiconductor Ltd.");
        sMap.put( (short)0x004B, "Continental Automotive Systems");
        sMap.put( (short)0x004C, "Apple, Inc.");
        //...
        sMap.put( (short)0x0075, "Samsung Electronics Co. Ltd.");
        sMap.put( (short)0x0076, "Creative Technology Ltd.");
        //...
        sMap.put( (short)0x0157, "Anhui Huami Information Technology Co., Ltd.");
        //...
        sMap.put( (short)0x0175, "Dynamic Controls");
        //...
        sMap.put( (short)0x08FA, "Troo Corporation");
        sMap.put( (short)0x08FB, "Darkglass Electronics Oy");
        sMap.put( (short)0x08FC, "Hill-Rom");
        sMap.put( (short)0x08FD, "BioIntelliSense, Inc.");
        sMap.put( (short)0x08FE, "Ketronixs Sdn Bhd");
        //...
    }

    public static String getName(short id) {
        init();
        if(sMap.containsKey(id)) {
            return sMap.get(id);
        } else {
            return null;
        }
    }

}
