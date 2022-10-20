package com.example.project3app;

public class appointmentDomain {
    private String Time,Date,id;

    public appointmentDomain() {
    }

    public appointmentDomain(String id, String date, String time) {
        Time = time;
        Date = date;
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "appointmentDomain{" +
                "Time='" + Time + '\'' +
                ", Date='" + Date + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
