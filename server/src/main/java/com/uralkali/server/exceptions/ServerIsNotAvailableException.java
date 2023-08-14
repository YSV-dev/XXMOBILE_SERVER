/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.exceptions;

/**
 *
 * @author brzsmg
 */
public class ServerIsNotAvailableException extends RuntimeException {
    public ServerIsNotAvailableException(String m) {
        super(m);
    }
}
