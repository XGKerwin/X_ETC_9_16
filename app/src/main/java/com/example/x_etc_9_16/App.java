package com.example.x_etc_9_16;

import android.app.Application;

import org.litepal.LitePal;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
