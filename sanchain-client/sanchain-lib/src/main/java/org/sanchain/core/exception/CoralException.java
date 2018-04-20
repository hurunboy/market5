package org.sanchain.core.exception;

/**
 * Created by A
 * since 14/11/14.
 */
public class CoralException extends RuntimeException {
    private int code;
    private String msg;


    public CoralException(String msg){
        super(msg);
        this.msg = msg;
    }

    public CoralException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
