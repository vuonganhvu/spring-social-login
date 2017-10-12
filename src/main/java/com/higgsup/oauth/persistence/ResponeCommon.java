package com.higgsup.oauth.persistence;

public class ResponeCommon<T> {
    private String code;
    private String message;
    private T object;

    public ResponeCommon() {
    }

    public ResponeCommon(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponeCommon(String code, String message, T object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
