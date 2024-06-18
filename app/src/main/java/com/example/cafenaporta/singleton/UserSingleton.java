package com.example.cafenaporta.singleton;

public class UserSingleton {

    private static UserSingleton instance;
    private long userId;

    public static synchronized UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

