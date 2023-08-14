package com.uralkali.common.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RegistrationResult implements Serializable {
    private Long appId;
    private String token;
    private String serverName;
    private String message;
}
