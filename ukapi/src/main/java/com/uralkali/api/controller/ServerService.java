package com.uralkali.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Операции сервером.
 */
public interface ServerService {

    String API_PATH = "/v1/server";

    @RequestMapping(
            path = API_PATH + "/restart",
            headers = "Accept=application/json")
    @ResponseBody
    String restart(String mode);

    @RequestMapping(
            path = API_PATH + "/info",
            headers = "Accept=application/json")
    @ResponseBody
    String info(String mode);

    @RequestMapping(
            path = API_PATH + "/sleep",
            headers = "Accept=application/json")
    @ResponseBody
    String sleep(int mills);

    @RequestMapping(
            path = API_PATH + "/longOperation",
            headers = "Accept=application/json")
    @ResponseBody
    String longOperation(int seconds);
}
