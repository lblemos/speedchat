package com.example.weverson.speedchat;

import android.app.Application;

import com.example.weverson.speedchat.data.firebase.RepositoryFirebaseModule;
import com.example.weverson.speedchat.data.repository.DaggerRepositoryComponent;
import com.example.weverson.speedchat.data.repository.RepositoryComponent;

public class MainApplication extends Application {

    private MainComponent mMainComponent;
    private RepositoryComponent mRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mMainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(getApplicationContext()))
                .build();

        mRepositoryComponent = DaggerRepositoryComponent.builder()
                .repositoryFirebaseModule(new RepositoryFirebaseModule())
                .build();

    }

    public MainComponent getMainComponent() {
        return mMainComponent;
    }

    public RepositoryComponent getRepositoryComponent() {
        return  mRepositoryComponent;
    }
}
