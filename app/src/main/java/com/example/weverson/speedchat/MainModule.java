package com.example.weverson.speedchat;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private final Context mContext;

    MainModule(Context context){
        mContext = context;
    }

    @Provides
    Context providerContext(){
        return mContext;
    }


}
