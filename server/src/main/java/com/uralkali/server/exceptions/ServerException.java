package com.uralkali.server.exceptions;

/**
 *
 * @author brzsmg
 */
public class ServerException extends RuntimeException {
    
    private final Integer code;
    
    public ServerException(Integer code, String m) {
        super(m);
        this.code = code;
    }
    
    public ServerException(Integer code, Exception e) {
        super("[" + e.getClass().getSimpleName() + "] " + e.getMessage());
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
