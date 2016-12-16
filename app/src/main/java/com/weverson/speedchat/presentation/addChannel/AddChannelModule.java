package com.weverson.speedchat.presentation.addChannel;

import dagger.Module;
import dagger.Provides;

@Module
public class AddChannelModule {

    private AddChannelContract.View mView;

    public AddChannelModule(AddChannelContract.View view) {
        mView = view;
    }

    @Provides
    public AddChannelContract.View providerAddChannelView(){
        return mView;
    }
}
