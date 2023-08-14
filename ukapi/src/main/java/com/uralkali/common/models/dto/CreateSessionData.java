package com.uralkali.common.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreateSessionData implements Serializable {

    /**
     * УН приложения полученый при регистрации.
     */
    private long appId;

    /**
     * Токен полученый при регистрации.
     */
    private String token;

    /**
     * Версия клиентского приложения.
     */
    private int version;

    /**
     * Включен ли режим разработки.
     */
    private boolean devMode;

    /**
     * Текущие параметры устройства.
     */
    private ClientStatus status;
}
