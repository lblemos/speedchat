package com.example.weverson.speedchat.presentation.channels;

import com.example.weverson.speedchat.domain.channel.Channel;
import com.example.weverson.speedchat.presentation.BasePresenter;
import com.example.weverson.speedchat.presentation.BaseView;

import java.util.List;

public interface ChannelsContract {

    interface View extends BaseView<Presenter> {

        void displayChannels(List<Channel> channels);

        void displayRefreshing(boolean enable);

    }

    interface Presenter extends BasePresenter {

        void listChannels();

    }

}
