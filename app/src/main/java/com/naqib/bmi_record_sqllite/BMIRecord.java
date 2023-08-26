package com.naqib.bmi_record_sqllite;

public class BMIRecord {
    String date;
    double weight;
    double height;
    double bmi;
    String status;

    public BMIRecord(String date, double weight, double height, double bmi, String status) {
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getBMI() {
        return bmi;
    }

    public String getStatus() {
        return status;
    }
}