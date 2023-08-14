package com.uralkali.common.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SessionResult {

    /**
     * Номер сессии.
     */
    private String sid;

    /**
     * Версия сервера.
     */
    private AppVersion version;

    /**
     * Допустимое расхождение во времени.
     */
    private Long allowDiffTime;

    /**
     * Время жизни сессии без обновления.
     */
    private Long sessionLifetime;
}
