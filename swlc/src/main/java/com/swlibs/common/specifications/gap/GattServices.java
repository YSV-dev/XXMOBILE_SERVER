package com.swlibs.common.specifications.gap;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GattServices {

    public final static String GENERIC_ACCESS = "00001800-0000-1000-8000-00805f9b34fb";

    public final static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";

    private static Map<UUID, String> sMap;

    public static void init() {
        if (sMap != null) {
            return;
        }
        sMap = new HashMap<>();

        add(GENERIC_ACCESS, "Generic Access");
        add("00001801-0000-1000-8000-00805f9b34fb", "Generic Attribute");
        add("00001802-0000-1000-8000-00805f9b34fb", "Immediate Alert");
        add("00001803-0000-1000-8000-00805f9b34fb", "Link Loss");
        add("00001804-0000-1000-8000-00805f9b34fb", "Tx Power");
        add("00001805-0000-1000-8000-00805f9b34fb", "Current Time Service");
        add("00001806-0000-1000-8000-00805f9b34fb", "Reference Time Update Service");
        add("00001807-0000-1000-8000-00805f9b34fb", "Next DST Change Service");
        add("00001808-0000-1000-8000-00805f9b34fb", "Glucose");
        add("00001809-0000-1000-8000-00805f9b34fb", "Health Thermometer");
        add("0000180a-0000-1000-8000-00805f9b34fb", "Device Information");
        add("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate");
        add("0000180e-0000-1000-8000-00805f9b34fb", "Phone Alert Status Service");
        add("0000180f-0000-1000-8000-00805f9b34fb", "Battery Service");
        add("00001810-0000-1000-8000-00805f9b34fb", "Blood Pressure");
        add("00001811-0000-1000-8000-00805f9b34fb", "Alert Notification Service");
        add("00001812-0000-1000-8000-00805f9b34fb", "Human Interface Device");
        add("00001813-0000-1000-8000-00805f9b34fb", "Scan Parameters");
        add("00001814-0000-1000-8000-00805f9b34fb", "Running Speed and Cadence");
        add("00001815-0000-1000-8000-00805f9b34fb", "Automation IO");
        add("00001816-0000-1000-8000-00805f9b34fb", "Cycling Speed and Cadence");
        add("00001818-0000-1000-8000-00805f9b34fb", "Cycling Power");
        add("00001819-0000-1000-8000-00805f9b34fb", "Location and Navigation");
        add("0000181a-0000-1000-8000-00805f9b34fb", "Environmental Sensing");
        add("0000181b-0000-1000-8000-00805f9b34fb", "Body Composition");
        add("0000181c-0000-1000-8000-00805f9b34fb", "User Data");
        add("0000181d-0000-1000-8000-00805f9b34fb", "Weight Scale");
        add("0000181e-0000-1000-8000-00805f9b34fb", "Bond Management Service");
        add("0000181f-0000-1000-8000-00805f9b34fb", "Continuous Glucose Monitoring");
        add("00001820-0000-1000-8000-00805f9b34fb", "Internet Protocol Support Service");
        add("00001821-0000-1000-8000-00805f9b34fb", "Indoor Positioning");
        add("00001822-0000-1000-8000-00805f9b34fb", "Pulse Oximeter Service");
        add("00001823-0000-1000-8000-00805f9b34fb", "HTTP Proxy");
        add("00001824-0000-1000-8000-00805f9b34fb", "Transport Discovery");
        add("00001825-0000-1000-8000-00805f9b34fb", "Object Transfer Service");
        add("00001826-0000-1000-8000-00805f9b34fb", "Fitness Machine");
        add("00001827-0000-1000-8000-00805f9b34fb", "Mesh Provisioning Service");
        add("00001828-0000-1000-8000-00805f9b34fb", "Mesh Proxy Service");
        add("00001829-0000-1000-8000-00805f9b34fb", "Reconnection Configuration");


        // Sample Characteristics.
        add(HEART_RATE_MEASUREMENT, "Heart Rate Measurement");
        add("00002a29-0000-1000-8000-00805f9b34fb", "Manufacturer Name String");

        add("00002a00-0000-1000-8000-00805f9b34fb", "Device Name");
        add("00002a01-0000-1000-8000-00805f9b34fb", "Appearance");
        add("00002a02-0000-1000-8000-00805f9b34fb", "Peripheral Privacy Flag");
        add("00002a03-0000-1000-8000-00805f9b34fb", "Reconnection Address");
        add("00002a04-0000-1000-8000-00805f9b34fb", "Manufacturer Name String");
        add("00002a05-0000-1000-8000-00805f9b34fb", "Service Changed");
        add("00002A06-0000-1000-8000-00805f9b34fb", "Alert level");

    }

    private static void add(UUID uuid, String name) {
        sMap.put(uuid, name);
    }

    private static void add(String value, String name) {
        add(UUID.fromString(value), name);
    }

    public static String getServiceName(UUID id) {
        init();
        if(sMap.containsKey(id)) {
            return sMap.get(id);
        } else {
            return null;
        }
    }

}
