package com.swlibs.common.specifications.gap;

/**
 * GAP_PARAMETER_ID_DEFINES
 *
 * Parameter IDs
 */
public class Parameter {

    // Timers

    /**
     * Minimum time to remain advertising, when in Discoverable mode (mSec).  Setting this parameter to 0 turns off the timeout (default).
     */
    public static final int TGAP_GEN_DISC_ADV_MIN          = 0;

    /**
     * Maximum time to remain advertising, when in Limited Discoverable mode. In seconds (default 180 seconds)
     */
    public static final int TGAP_LIM_ADV_TIMEOUT           = 1;

    /**
     * Minimum time to perform scanning, when performing General Discovery proc (mSec)
     */
    public static final int TGAP_GEN_DISC_SCAN             = 2;

    /**
     * Minimum time to perform scanning, when performing Limited Discovery proc (mSec)
     */
    public static final int TGAP_LIM_DISC_SCAN             = 3;

    /**
     * Advertising timeout, when performing Connection Establishment proc (mSec)
     */
    public static final int TGAP_CONN_EST_ADV_TIMEOUT      = 4;

    /**
     * Link Layer connection parameter update notification timer, connection parameter update proc (mSec)
     */
    public static final int TGAP_CONN_PARAM_TIMEOUT        = 5;


    // Constants

    /**
     * Minimum advertising interval, when in limited discoverable mode (n * 0.625 mSec)
     */
    public static final int TGAP_LIM_DISC_ADV_INT_MIN      = 6;

    /**
     * Maximum advertising interval, when in limited discoverable mode (n * 0.625 mSec)
     */
    public static final int TGAP_LIM_DISC_ADV_INT_MAX      = 7;

    /**
     * Minimum advertising interval, when in General discoverable mode (n * 0.625 mSec)
     */
    public static final int TGAP_GEN_DISC_ADV_INT_MIN      = 8;

    /**
     * Maximum advertising interval, when in General discoverable mode (n * 0.625 mSec)
     */
    public static final int TGAP_GEN_DISC_ADV_INT_MAX      = 9;

    /**
     * Minimum advertising interval, when in Connectable mode (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_ADV_INT_MIN         = 10;

    /**
     * Maximum advertising interval, when in Connectable mode (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_ADV_INT_MAX         = 11;

    /**
     * Scan interval used during Link Layer Initiating state, when in Connectable mode (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_SCAN_INT            = 12;

    /**
     * Scan window used during Link Layer Initiating state, when in Connectable mode (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_SCAN_WIND           = 13;

    /**
     * Scan interval used during Link Layer Initiating state, when in Connectable mode, high duty scan cycle scan paramaters (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_HIGH_SCAN_INT       = 14;

    /**
     * Scan window used during Link Layer Initiating state, when in Connectable mode, high duty scan cycle scan paramaters (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_HIGH_SCAN_WIND      = 15;

    /**
     * Scan interval used during Link Layer Scanning state, when in General Discovery proc (n * 0.625 mSec)
     */
    public static final int TGAP_GEN_DISC_SCAN_INT        = 16;

    /**
     * Scan window used during Link Layer Scanning state, when in General Discovery proc (n * 0.625 mSec)
     */
    public static final int TGAP_GEN_DISC_SCAN_WIND       = 17;

    /**
     * Scan interval used during Link Layer Scanning state, when in Limited Discovery proc (n * 0.625 mSec)
     */
    public static final int TGAP_LIM_DISC_SCAN_INT        = 18;

    /**
     * Scan window used during Link Layer Scanning state, when in Limited Discovery proc (n * 0.625 mSec)
     */
    public static final int TGAP_LIM_DISC_SCAN_WIND       = 19;

    /**
     * Advertising interval, when using Connection Establishment proc (n * 0.625 mSec). Obsolete - Do not use.
     */
    public static final int TGAP_CONN_EST_ADV             = 20;

    /**
     * Minimum Link Layer connection interval, when using Connection Establishment proc (n * 1.25 mSec)
     */
    public static final int TGAP_CONN_EST_INT_MIN         = 21;

    /**
     * Maximum Link Layer connection interval, when using Connection Establishment proc (n * 1.25 mSec)
     */
    public static final int TGAP_CONN_EST_INT_MAX         = 22;

    /**
     * Scan interval used during Link Layer Initiating state, when using Connection Establishment proc (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_EST_SCAN_INT        = 23;

    /**
     * Scan window used during Link Layer Initiating state, when using Connection Establishment proc (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_EST_SCAN_WIND       = 24;

    /**
     * Link Layer connection supervision timeout, when using Connection Establishment proc (n * 10 mSec)
     */
    public static final int TGAP_CONN_EST_SUPERV_TIMEOUT  = 25;

    /**
     * Link Layer connection slave latency, when using Connection Establishment proc (in number of connection events)
     */
    public static final int TGAP_CONN_EST_LATENCY         = 26;

    /**
     * Local informational parameter about min len of connection needed, when using Connection Establishment proc (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_EST_MIN_CE_LEN      = 27;

    /**
     * Local informational parameter about max len of connection needed, when using Connection Establishment proc (n * 0.625 mSec)
     */
    public static final int TGAP_CONN_EST_MAX_CE_LEN      = 28;


    /**
     * Minimum Time Interval between private (resolvable) address changes. In minutes (default 15 minutes)
     */
    public static final int TGAP_PRIVATE_ADDR_INT         = 29;


    // Proprietary

    /**
     * SM Message Timeout (milliseconds). Default 30 seconds.
     */
    public static final int TGAP_SM_TIMEOUT               = 30;

    /**
     * SM Minimum Key Length supported. Default 7.
     */
    public static final int TGAP_SM_MIN_KEY_LEN           = 31;

    /**
     * SM Maximum Key Length supported. Default 16.
     */
    public static final int TGAP_SM_MAX_KEY_LEN           = 32;

    /**
     * Filter duplicate advertising reports. Default TRUE.
     */
    public static final int TGAP_FILTER_ADV_REPORTS       = 33;

    /**
     * Minimum RSSI required for scan responses to be reported to the app. Default -127.
     */
    public static final int TGAP_SCAN_RSP_RSSI_MIN        = 34;


    /**
     * Task ID override for Task Authentication control (for stack internal use only)
     */
    public static final int TGAP_AUTH_TASK_ID           = 35;
    /**
     * ID MAX-valid Parameter ID
     */
    public static final int TGAP_PARAMID_MAX            = 36;

    //Test

    /**
     * GAP TestCodes - puts GAP into a test mode
     */
    public static final int TGAP_GAP_TESTCODE           = 35;

    /**
     * SM TestCodes - puts SM into a test mode
     */
    public static final int TGAP_SM_TESTCODE            = 36;

    /**
     * Task ID override for Task Authentication control (for stack internal use only)
     */
    public static final int TGAP_AUTH_TASK_ID_TESTCODE           = 37;

    /**
     * ID MAX-valid Parameter ID
     */
    public static final int TGAP_PARAMID_MAX_TESTCODE            = 38;

    /**
     * GATT TestCodes - puts GATT into a test mode (paramValue maintained by GATT)
     */
    public static final int TGAP_GATT_TESTCODE          = 100;

    /**
     * ATT TestCodes - puts ATT into a test mode (paramValue maintained by ATT)
     */
    public static final int TGAP_ATT_TESTCODE           = 101;

    /**
     * GGS TestCodes - puts GGS into a test mode (paramValue maintained by GGS)
     */
    public static final int TGAP_GGS_TESTCODE           = 102;

}
