package com.uralkali.common.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientStatus implements Serializable {
    /**
     * УН аутентифицированного пользователя либо 0.
     */
    private long userId;

    /**
     * Заряд устройства.
     */

    private int battery;
    /**
     * Освещение рядом с устройством.
     */
    private int light;
}
