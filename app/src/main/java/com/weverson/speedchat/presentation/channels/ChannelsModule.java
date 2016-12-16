package com.weverson.speedchat.presentation.channels;

import dagger.Module;
import dagger.Provides;

@Module
public class ChannelsModule {

    private ChannelsContract.View mView;

    public ChannelsModule(ChannelsContract.View view) {
        mView = view;
    }

    @Provides
    ChannelsContract.View providerChannelsView() {
        return mView;
    }
}
