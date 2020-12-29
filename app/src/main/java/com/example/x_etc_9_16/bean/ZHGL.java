package com.example.x_etc_9_16.bean;

public class ZHGL {

    /**
     *             "id": 1,
     *             "number": 1,
     *             "owner": "张三",
     *             "balance": 81,
     *             "plate": "鲁A10001",
     *             "brand": "奔驰",
     *             "user": "user1"
     */

    private String id,number,owner,plate,brand,user;
    private int balance;
    private boolean xz;

    public boolean isXz() {
        return xz;
    }

    public void setXz(boolean xz) {
        this.xz = xz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ZHGL{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", owner='" + owner + '\'' +
                ", plate='" + plate + '\'' +
                ", brand='" + brand + '\'' +
                ", user='" + user + '\'' +
                ", balance=" + balance +
                ", xz=" + xz +
                '}';
    }
}
