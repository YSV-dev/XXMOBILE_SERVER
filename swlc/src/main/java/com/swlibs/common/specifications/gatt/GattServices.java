package com.swlibs.common.specifications.gatt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Gatt services
 *
 * @link https://www.bluetooth.com/specifications/gatt/services/
 */
public class GattServices {

    public final static String GENERIC_ACCESS = "00001800-0000-1000-8000-00805f9b34fb";
    public final static String GENERIC_ATTRIBUTE = "00001801-0000-1000-8000-00805f9b34fb";
    public final static String IMMEDIATE_ALERT = "00001802-0000-1000-8000-00805f9b34fb";
    public final static String LINK_LOSS = "00001803-0000-1000-8000-00805f9b34fb";
    public final static String TX_POWER = "00001804-0000-1000-8000-00805f9b34fb";
    public final static String CURRENT_TIME_SERVICE = "00001805-0000-1000-8000-00805f9b34fb";
    public final static String REFERENCE_TIME_UPDATE_SERVICE = "00001806-0000-1000-8000-00805f9b34fb";

    private static Map<UUID, GatIdentifier> sMap;

    public static void init() {
        if (sMap != null) {
            return;
        }
        sMap = new HashMap<>();

        add(GENERIC_ACCESS, new GatIdentifier("Generic Access", "org.bluetooth.service.generic_access", 0x1800, "GSS"));
        add(GENERIC_ATTRIBUTE, new GatIdentifier("Generic Attribute", "org.bluetooth.service.generic_attribute", 0x1801, "GSS"));
        add(IMMEDIATE_ALERT, new GatIdentifier("Immediate Alert", "org.bluetooth.service.immediate_alert", 0x1802, "GSS"));
        add(LINK_LOSS, new GatIdentifier("Link Loss", "org.bluetooth.service.link_loss", 0x1803, "GSS"));
        add(TX_POWER, new GatIdentifier("Tx Power", "org.bluetooth.service.tx_power", 0x1804, "GSS"));
        add(CURRENT_TIME_SERVICE, new GatIdentifier("Current Time Service", "org.bluetooth.service.current_time", 0x1805, "GSS"));
        add(REFERENCE_TIME_UPDATE_SERVICE, new GatIdentifier("Reference Time Update Service", "org.bluetooth.service.reference_time_update", 0x1806, "GSS"));
        add("00001807-0000-1000-8000-00805f9b34fb", new GatIdentifier("Next DST Change Service", "org.bluetooth.service.next_dst_change", 0x1807, "GSS"));
        add("00001808-0000-1000-8000-00805f9b34fb", new GatIdentifier("Glucose", "org.bluetooth.service.glucose", 0x1808, "GSS"));
        add("00001809-0000-1000-8000-00805f9b34fb", new GatIdentifier("Health Thermometer", "org.bluetooth.service.health_thermometer", 0x1809, "GSS"));
        add("0000180a-0000-1000-8000-00805f9b34fb", new GatIdentifier("Device Information", "org.bluetooth.service.device_information", 0x180A, "GSS"));
        add("0000180d-0000-1000-8000-00805f9b34fb", new GatIdentifier("Heart Rate", "org.bluetooth.service.heart_rate", 0x180D, "GSS"));
        add("0000180e-0000-1000-8000-00805f9b34fb", new GatIdentifier("Phone Alert Status Service", "org.bluetooth.service.phone_alert_status", 0x180E, "GSS"));
        add("0000180f-0000-1000-8000-00805f9b34fb", new GatIdentifier("Battery Service", "org.bluetooth.service.battery_service", 0x180F, "GSS"));
        add("00001810-0000-1000-8000-00805f9b34fb", new GatIdentifier("Blood Pressure", "org.bluetooth.service.blood_pressure", 0x1810, "GSS"));
        add("00001811-0000-1000-8000-00805f9b34fb", new GatIdentifier("Alert Notification Service", "org.bluetooth.service.alert_notification", 0x1811, "GSS"));
        add("00001812-0000-1000-8000-00805f9b34fb", new GatIdentifier("Human Interface Device", "org.bluetooth.service.human_interface_device", 0x1812, "GSS"));
        add("00001813-0000-1000-8000-00805f9b34fb", new GatIdentifier("Scan Parameters", "org.bluetooth.service.scan_parameters", 0x1813, "GSS"));
        add("00001814-0000-1000-8000-00805f9b34fb", new GatIdentifier("Running Speed and Cadence", "org.bluetooth.service.running_speed_and_cadence", 0x1814, "GSS"));
        add("00001815-0000-1000-8000-00805f9b34fb", new GatIdentifier("Automation IO", "org.bluetooth.service.automation_io", 0x1815, "GSS"));
        add("00001816-0000-1000-8000-00805f9b34fb", new GatIdentifier("Cycling Speed and Cadence", "org.bluetooth.service.cycling_speed_and_cadence", 0x1816, "GSS"));
        add("00001818-0000-1000-8000-00805f9b34fb", new GatIdentifier("Cycling Power", "org.bluetooth.service.cycling_power", 0x1818, "GSS"));
        add("00001819-0000-1000-8000-00805f9b34fb", new GatIdentifier("Location and Navigation", "org.bluetooth.service.location_and_navigation", 0x1819, "GSS"));
        add("0000181a-0000-1000-8000-00805f9b34fb", new GatIdentifier("Environmental Sensing", "org.bluetooth.service.environmental_sensing", 0x181A, "GSS"));
        add("0000181b-0000-1000-8000-00805f9b34fb", new GatIdentifier("Body Composition", "org.bluetooth.service.body_composition", 0x181B, "GSS"));
        add("0000181c-0000-1000-8000-00805f9b34fb", new GatIdentifier("User Data", "org.bluetooth.service.user_data", 0x181C, "GSS"));
        add("0000181d-0000-1000-8000-00805f9b34fb", new GatIdentifier("Weight Scale", "org.bluetooth.service.weight_scale", 0x181D, "GSS"));
        add("0000181e-0000-1000-8000-00805f9b34fb", new GatIdentifier("Bond Management Service", "org.bluetooth.service.bond_management", 0x181E, "GSS"));
        add("0000181f-0000-1000-8000-00805f9b34fb", new GatIdentifier("Continuous Glucose Monitoring", "org.bluetooth.service.continuous_glucose_monitoring", 0x181F, "GSS"));
        add("00001820-0000-1000-8000-00805f9b34fb", new GatIdentifier("Internet Protocol Support Service", "org.bluetooth.service.internet_protocol_support", 0x1820, "GSS"));
        add("00001821-0000-1000-8000-00805f9b34fb", new GatIdentifier("Indoor Positioning", "org.bluetooth.service.indoor_positioning", 0x1821, "GSS"));
        add("00001822-0000-1000-8000-00805f9b34fb", new GatIdentifier("Pulse Oximeter Service", "org.bluetooth.service.pulse_oximeter", 0x1822, "GSS"));
        add("00001823-0000-1000-8000-00805f9b34fb", new GatIdentifier("HTTP Proxy", "org.bluetooth.service.http_proxy", 0x1823, "GSS"));
        add("00001824-0000-1000-8000-00805f9b34fb", new GatIdentifier("Transport Discovery", "org.bluetooth.service.transport_discovery", 0x1824, "GSS"));
        add("00001825-0000-1000-8000-00805f9b34fb", new GatIdentifier("Object Transfer Service", "org.bluetooth.service.object_transfer", 0x1825, "GSS"));
        add("00001826-0000-1000-8000-00805f9b34fb", new GatIdentifier("Fitness Machine", "org.bluetooth.service.fitness_machine", 0x1826, "GSS"));
        add("00001827-0000-1000-8000-00805f9b34fb", new GatIdentifier("Mesh Provisioning Service", "org.bluetooth.service.mesh_provisioning", 0x1827, "GSS"));
        add("00001828-0000-1000-8000-00805f9b34fb", new GatIdentifier("Mesh Proxy Service", "org.bluetooth.service.mesh_proxy", 0x1828, "GSS"));
        add("00001829-0000-1000-8000-00805f9b34fb", new GatIdentifier("Reconnection Configuration", "org.bluetooth.service.reconnection_configuration", 0x1829, "GSS"));
        add("0000183a-0000-1000-8000-00805f9b34fb", new GatIdentifier("Insulin Delivery", "org.bluetooth.service.insulin_delivery", 0x183A, "GSS"));
        add("0000183b-0000-1000-8000-00805f9b34fb", new GatIdentifier("Binary Sensor", "GATT Service UUID", 0x183B, "BSS"));
        add("0000183c-0000-1000-8000-00805f9b34fb", new GatIdentifier("Emergency Configuration", "GATT Service UUID", 0x183C, "EMCS"));

    }

    private static void add(UUID uuid, GatIdentifier service) {
        sMap.put(uuid, service);
    }

    private static void add(String value, GatIdentifier service) {
        add(UUID.fromString(value), service);
    }

    public static String getServiceName(UUID id) {
        init();
        if(sMap.containsKey(id)) {
            GatIdentifier service = sMap.get(id);
            Objects.requireNonNull(service);
            return service.getName();
        } else {
            return null;
        }
    }

}