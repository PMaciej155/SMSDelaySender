package com.example.maciej.smsdelaysender;

/**
 * Created by maciej on 5/7/18.
 */

public class SMessage {
    private String number;
    private String message;
    private String dayOfSend;
    private String timeOfSend;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDayOfSend() {
        return dayOfSend;
    }

    public void setDayOfSend(String dayOfSend) {
        this.dayOfSend = dayOfSend;
    }

    public String getTimeOfSend() {
        return timeOfSend;
    }

    public void setTimeOfSend(String timeOfSend) {
        this.timeOfSend = timeOfSend;
    }
}
