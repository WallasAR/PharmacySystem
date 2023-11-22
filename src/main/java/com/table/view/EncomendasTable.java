package com.table.view;

public class EncomendasTable {
    private int id;
    private String user;
    private String medicine;
    private int amount;
    private float price;
    private String date;
    private String phone;
    private String status;

    public EncomendasTable(int id, String user, String medicine, int amount, float price, String date, String phone, String status) {
        this.id = id;
        this.user = user;
        this.medicine = medicine;
        this.amount = amount;
        this.price = price;
        this.date = date;
        this.phone = phone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
