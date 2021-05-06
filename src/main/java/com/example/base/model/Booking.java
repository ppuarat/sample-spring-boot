package com.example.base.model;

public class Booking {
    private long id;
    private User user;
    private String bookedDay;
    //0-23
    private int time;

    public Booking(long id, User user, String bookedDay, int time) {
        this.id = id;
        this.user = user;
        this.bookedDay = bookedDay;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookedDay() {
        return bookedDay;
    }

    public void setBookedDay(String bookedDay) {
        this.bookedDay = bookedDay;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
