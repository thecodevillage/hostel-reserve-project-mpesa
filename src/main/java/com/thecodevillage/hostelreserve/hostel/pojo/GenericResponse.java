package com.thecodevillage.hostelreserve.hostel.pojo;

public class GenericResponse {

    private int status;

    private String message;

    private Object payload;

    public GenericResponse() {
    }

    public GenericResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public GenericResponse(int status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
