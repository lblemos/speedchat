package com.weverson.speedchat.presentation.messages;

import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.message.Message;
import com.weverson.speedchat.domain.message.interactor.ListMessageUseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class MessagesPresenter implements MessagesContract.Presenter {

    private MessagesContract.View mMessagesView;

    private ListMessageUseCase mListMessageUseCase;

    @Inject
    public MessagesPresenter(MessagesContract.View messagesView, ListMessageUseCase listMessageUseCase){
        mMessagesView = messagesView;
        mListMessageUseCase = listMessageUseCase;
    }

    @Inject
    void setupListeners() {
        mMessagesView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void listMessages(Channel channel) {
        mListMessageUseCase.execute(new ListMessageUseCase.Request(channel))
                .subscribe(new ListMessageSubscriber());
    }

    public final class ListMessageSubscriber extends Subscriber<List<Message>> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Message> messages) {
            mMessagesView.displayMessage(messages);
        }
    }
}
