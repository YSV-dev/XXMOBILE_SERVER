package com.swlibs.common.net.web;

/**
 *
 * @author brzsmg
 */
public class ClientCanceledConnection extends RuntimeException {
    public ClientCanceledConnection(String m) {
        super(m);
    }
}