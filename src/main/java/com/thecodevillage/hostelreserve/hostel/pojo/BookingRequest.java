package com.thecodevillage.hostelreserve.hostel.pojo;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class BookingRequest implements Serializable {

    private String mobileNumber;

    private double amount;

    private Long roomId;

    private String studentNumber;

    private String callbackurl;

    private String timeouturl;

    public BookingRequest() {
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getCallbackurl() {
        return callbackurl;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl;
    }

    public String getTimeouturl() {
        return timeouturl;
    }

    public void setTimeouturl(String timeouturl) {
        this.timeouturl = timeouturl;
    }
}
