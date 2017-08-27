package com.grupopulpo.lubriacadora.duty.entities;

/**
 * Created by eliberio on 14/07/16.
 */
public class ExceptionOAUTH {
    private String error;
    private String error_description;
    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getError_description() {
        return error_description;
    }
    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
