package com.myapp.countapi.models;

import java.math.BigInteger;

public class CountResponse {
    private String key;
    private BigInteger value;
    private String error;
    private String warning;

    public CountResponse() {
    }

    public CountResponse(String key, BigInteger value, String error, String warning) {
        this.key = key;
        this.value = value;
        this.error = error;
        this.warning = warning;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
