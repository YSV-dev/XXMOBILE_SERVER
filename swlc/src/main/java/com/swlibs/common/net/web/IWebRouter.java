package com.swlibs.common.net.web;

/**
 *
 * @author brzsmg
 */
public interface IWebRouter {
    void route(WebRequest webRequest, int httpStatus);
}
