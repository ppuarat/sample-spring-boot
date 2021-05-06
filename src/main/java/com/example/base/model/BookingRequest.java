package com.example.base.model;

public class BookingRequest {
    private long userId;
    private String day;
    private int time;

    public BookingRequest(long userId, String day, int time) {
        this.userId = userId;
        this.day = day;
        this.time = time;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
