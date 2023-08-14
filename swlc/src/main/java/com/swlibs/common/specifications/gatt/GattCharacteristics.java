package com.swlibs.common.specifications.gatt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Gatt characteristics
 *
 * @link https://www.bluetooth.com/specifications/gatt/characteristics/
 */
public class GattCharacteristics {

    public final static String DEVICE_NAME = "00002a00-0000-1000-8000-00805f9b34fb";
    public final static String APPEARANCE = "00002a00-0000-1000-8000-00805f9b34fb";

    private static Map<UUID, GatIdentifier> sMap;

    public static void init() {
        if (sMap != null) {
            return;
        }
        sMap = new HashMap<>();

        add(DEVICE_NAME, new GatIdentifier("Device Name", "org.bluetooth.characteristic.gap.device_name", 0x1800, "Core 4.0"));
        add(APPEARANCE, new GatIdentifier("Appearance", "org.bluetooth.characteristic.gap.appearance", 0x1801, "Core 4.0"));
        //...

    }

    private static void add(UUID uuid, GatIdentifier characteristic) {
        sMap.put(uuid, characteristic);
    }

    private static void add(String value, GatIdentifier characteristic) {
        add(UUID.fromString(value), characteristic);
    }
}
