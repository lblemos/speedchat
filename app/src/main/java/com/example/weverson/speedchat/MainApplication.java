package com.example.weverson.speedchat;

import android.app.Application;

import com.example.weverson.speedchat.data.firebase.FirebaseModule;

public class MainApplication extends Application {

    private MainComponent mMainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mMainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(getApplicationContext()))
                .firebaseModule(new FirebaseModule())
                .build();

    }

    public MainComponent getMainComponent() {
        return mMainComponent;
    }
}
