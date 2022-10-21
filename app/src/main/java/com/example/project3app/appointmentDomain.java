package com.example.project3app;

public class appointmentDomain {
    private String Time,Date,PatientId ,DrId ;
    private String AppId;

    public appointmentDomain() {
    }

    public appointmentDomain(String appId ,String patientId, String date,String time) {
        Time = time;
        Date = date;
        PatientId = patientId;
        AppId = appId;
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

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    @Override
    public String toString() {
        return "appointmentDomain{" +
                "Time='" + Time + '\'' +
                ", Date='" + Date + '\'' +
                ", PatientId='" + PatientId + '\'' +
                ", AppId=" + AppId +
                '}';
    }
}
