package com.uralkali.server.exceptions;

/**
 *
 * @author brzsmg
 */
public class ClientCanceledConnection extends RuntimeException {
    public ClientCanceledConnection(String m) {
        super(m);
    }
}
