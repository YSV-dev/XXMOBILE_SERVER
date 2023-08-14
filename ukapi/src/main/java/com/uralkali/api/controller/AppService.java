package com.uralkali.api.controller;

import com.uralkali.common.models.dto.RegistrationData;
import com.uralkali.common.models.dto.RegistrationResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Операции с приложениями.
 */
public interface AppService {

    String API_PATH = "/v1/app";

    /**
     * Регистрация нового приложения на сервере.
     *
     * @param rd Данные для регистрации
     * @return Результат регистрации
     */
    @RequestMapping(
            path = API_PATH + "/registration/",
            method = RequestMethod.POST,
            headers="Accept=application/json")
    @ResponseBody
    public RegistrationResult registration(@RequestBody RegistrationData rd);
}
