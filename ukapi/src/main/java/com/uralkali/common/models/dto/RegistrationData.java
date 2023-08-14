package com.uralkali.common.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author brzsmg
 */
@Data
@NoArgsConstructor
public class RegistrationData implements Serializable {
    private String appName;
    private Integer appVersion;
    private String softwareId;
    private String imei;
    private String deviceName;
    private String systemName;
    private String systemVersion;
}
