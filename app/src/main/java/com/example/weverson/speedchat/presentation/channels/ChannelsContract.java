package com.example.weverson.speedchat.presentation.channels;

import com.example.weverson.speedchat.domain.channel.Channel;
import com.example.weverson.speedchat.presentation.BasePresenter;
import com.example.weverson.speedchat.presentation.BaseView;

public interface ChannelsContract {

    interface View extends BaseView<Presenter> {

        void createChannel();
        void addChannel(Channel channel);

    }

    interface Presenter extends BasePresenter {

        void listChannels();

    }

}
