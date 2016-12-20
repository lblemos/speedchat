package com.weverson.speedchat.presentation.messages;


import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.message.Message;
import com.weverson.speedchat.presentation.BasePresenter;
import com.weverson.speedchat.presentation.BaseView;

import java.util.List;

public interface MessagesContract {

    interface View extends BaseView<Presenter>{

        void displayMessage(List<Message> message);

    }

    interface Presenter extends BasePresenter {

        void listMessages(Channel channel);

    }

}
