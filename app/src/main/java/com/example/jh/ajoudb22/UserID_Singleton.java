package com.example.jh.ajoudb22;

public class UserID_Singleton {

    private static UserID_Singleton singleton=new UserID_Singleton();
    private String UserID;

    public static UserID_Singleton getInstance(){
        return singleton;
    }
    public void setUserID(String ID){
        UserID=ID;
    }
    public String getUserID(){
        return UserID;
    }
}