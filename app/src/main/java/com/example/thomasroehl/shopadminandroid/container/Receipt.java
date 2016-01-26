package com.example.thomasroehl.shopadminandroid.container;

/**
 * Created by Katia & Iuliia on 04.01.2016.
 */
public class Receipt {
    private int id;
    private String shopname;
    private String category;
    private double amount;
    private String date;
    private int userId;

    public Receipt(){

    }

    public Receipt(String shopname, String category, double amount, String date){
        this.shopname = shopname;
        this.category = category;
        this.amount = amount;
        this.date = date;

    }


    public Receipt(String shopname, String category, double amount, String date, int userId ){
        this.shopname = shopname;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.userId = userId;
    }

    public Receipt(int id, String shopname, String category, double amount, String date ){
        this.id = id;
        this.shopname = shopname;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public int getId() { return id; }

    public int getuserId() {
        return userId;
    }

    public String getShopname() {
        return shopname;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUserId (int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", shopname='" + shopname + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
