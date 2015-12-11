package com.example.thomasroehl.shopadminandroid.container;

import java.sql.Date;

/**
 * Created by Thomas Roehl on 08.12.2015.
 */
public class Session {
    private Date lastAction;
    private String username;
    private int userID;
    private int sessionID;

    public Session(){
        super();
    }

    public Session(Date lastAction, String username, int userID, int sessionID){
        this.sessionID = sessionID;
        this.lastAction = lastAction;
        this.username = username;
        this.userID = userID;
    }

    public Session(int sessionID){
        this.sessionID = sessionID;
    }

    public Date getLastAction() {
        return lastAction;
    }

    public String getUsername() {
        return username;
    }

    public int getUserID() {
        return userID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setLastAction(Date lastAction) {
        this.lastAction = lastAction;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }
}