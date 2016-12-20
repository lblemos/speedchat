package com.weverson.speedchat.presentation.messages;

import dagger.Module;
import dagger.Provides;

@Module
public class MessagesModule {

    private MessagesContract.View mView;

    public MessagesModule(MessagesContract.View view) {
        mView = view;
    }

    @Provides
    public MessagesContract.View providerMessagesView() {
        return mView;
    }

}
