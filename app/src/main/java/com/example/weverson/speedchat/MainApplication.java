package com.example.weverson.speedchat;

import android.app.Application;

import com.example.weverson.speedchat.data.firebase.DaggerFirebaseComponent;
import com.example.weverson.speedchat.data.firebase.FirebaseComponent;
import com.example.weverson.speedchat.data.firebase.FirebaseModule;

public class MainApplication extends Application {

    private FirebaseComponent mFirebaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mFirebaseComponent = DaggerFirebaseComponent.builder()
                .mainModule(new MainModule(getApplicationContext()))
                .firebaseModule(new FirebaseModule())
                .build();

    }

    public FirebaseComponent getFirebaseComponent() {
        return mFirebaseComponent;
    }
}
