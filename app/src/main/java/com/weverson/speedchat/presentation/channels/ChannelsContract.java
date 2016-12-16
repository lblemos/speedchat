package com.weverson.speedchat.presentation.channels;

import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.presentation.BasePresenter;
import com.weverson.speedchat.presentation.BaseView;

import java.util.List;

public interface ChannelsContract {

    interface View extends BaseView<Presenter> {

        void displayChannels(List<Channel> channels);

        void openAddChannel();

    }

    interface Presenter extends BasePresenter {

        void listChannels();

        void addChannel();

    }

}
