package com.example.project3app;

public class appointmentDomain {
    private String Time,Date,id;

    public appointmentDomain() {
    }

    public appointmentDomain(String id , String time) {
        Time = time;
        this.id = id;
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
        return "Helper{" +
                "Time='" + Time + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
