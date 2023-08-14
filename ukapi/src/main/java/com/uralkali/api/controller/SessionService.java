package com.uralkali.api.controller;

import com.uralkali.common.models.dto.ClientStatus;
import com.uralkali.common.models.dto.CreateSessionData;
import com.uralkali.common.models.dto.ServerStatus;
import com.uralkali.common.models.dto.SessionResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Операции с сессиями приложений.
 */
public interface SessionService {
    String API_PATH = "/v1/session";

    /**
     * Создание новой сессии.
     *
     * @param data Данные для создания сессии
     * @return Результат создания сессии
     */
    @RequestMapping(
            path = API_PATH + "/create/",
            method = RequestMethod.POST,
            headers="Accept=application/json")
    @ResponseBody
    SessionResult create(@RequestBody CreateSessionData data);

    /**
     * Обновление сессии.
     *
     * @param sid Номер сессии
     * @param data Данные для создания сессии
     * @return Статус сервера
     */
    @RequestMapping(
            path = API_PATH + "/status/",
            method = RequestMethod.POST,
            headers="Accept=application/json")
    @ResponseBody
    ServerStatus status(String sid, @RequestBody ClientStatus data);
}
