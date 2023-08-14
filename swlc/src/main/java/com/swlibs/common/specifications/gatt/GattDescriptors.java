package com.swlibs.common.specifications.gatt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Gatt characteristics
 *
 * @link https://www.bluetooth.com/specifications/gatt/descriptors/
 */
public class GattDescriptors {

    public final static String CHARACTERISTIC_EXTENDED_PROPERTIES = "00002900-0000-1000-8000-00805f9b34fb";
    public final static String CHARACTERISTIC_USER_DESCRIPTION = "00002901-0000-1000-8000-00805f9b34fb";

    /**
     * Конфигурация характеристики клиента
     */
    public final static String CLIENT_CHARACTERISTIC_CONFIGURATION = "00002902-0000-1000-8000-00805f9b34fb";

    public final static String SERVER_CHARACTERISTIC_CONFIGURATION = "00002903-0000-1000-8000-00805f9b34fb";
    public final static String CHARACTERISTIC_PRESENTATION_FORMAT = "00002904-0000-1000-8000-00805f9b34fb";

    private static Map<UUID, GatIdentifier> sMap;

    public static void init() {
        if (sMap != null) {
            return;
        }
        sMap = new HashMap<>();

        add(CHARACTERISTIC_EXTENDED_PROPERTIES, new GatIdentifier("Characteristic Extended Properties", "org.bluetooth.descriptor.gatt.characteristic_extended_properties", 0x2900, "GSS"));
        add(CHARACTERISTIC_USER_DESCRIPTION, new GatIdentifier("Characteristic User Description", "org.bluetooth.descriptor.gatt.characteristic_user_description", 0x2901, "GSS"));
        add(SERVER_CHARACTERISTIC_CONFIGURATION, new GatIdentifier("Server Characteristic Configuration", "org.bluetooth.descriptor.gatt.server_characteristic_configuration", 0x2903, "GSS"));
        add(CHARACTERISTIC_PRESENTATION_FORMAT, new GatIdentifier("Characteristic Presentation Format", "org.bluetooth.descriptor.gatt.characteristic_presentation_format", 0x2904, "GSS"));

        //...
    }

    private static void add(UUID uuid, GatIdentifier characteristic) {
        sMap.put(uuid, characteristic);
    }

    private static void add(String value, GatIdentifier characteristic) {
        add(UUID.fromString(value), characteristic);
    }
}
