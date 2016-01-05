package com.example.thomasroehl.shopadminandroid.container;

import java.sql.Date;

/**
 * Created by Thomas Roehl on 08.12.2015.
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Date lastLogin;
    private String group;

    public User(){

    }

    public User(int id, String name, String email, String password){
        //Katia & Iuliia 04.01.2016
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        //Katia & Iuliia 04.01.2016
    }

    public User(String name, String email, String password){
        //Katia  & Iuliia 04.01.2016
        this.name = name;
        this.email = email;
        this.password = password;
        //Katia & Iuliia 04.01.2016
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Katia  & Iuliia 04.01.2016
    public String toString() {
        return "User{" +
                ", name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", password=" + this.password + '\'' +
                '}';
    }
    //Katia  & Iuliia 04.01.2016
}
