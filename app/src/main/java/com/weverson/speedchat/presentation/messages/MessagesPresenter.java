package com.weverson.speedchat.presentation.messages;

import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.message.Message;
import com.weverson.speedchat.domain.message.interactor.ListMessageUseCase;
import com.weverson.speedchat.domain.user.User;
import com.weverson.speedchat.domain.user.interactor.CurrentUserUseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class MessagesPresenter implements MessagesContract.Presenter {

    private MessagesContract.View mMessagesView;

    private ListMessageUseCase mListMessageUseCase;

    private CurrentUserUseCase mCurrentUserUseCase;

    @Inject
    public MessagesPresenter(MessagesContract.View messagesView, ListMessageUseCase listMessageUseCase,
                             CurrentUserUseCase currentUserUseCase){
        mMessagesView = messagesView;
        mListMessageUseCase = listMessageUseCase;
        mCurrentUserUseCase = currentUserUseCase;
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
        mCurrentUserUseCase.execute(new CurrentUserUseCase.Request()).subscribe(currentUser -> {
            mListMessageUseCase.execute(new ListMessageUseCase.Request(channel))
                    .subscribe(new ListMessageSubscriber((User) currentUser));
        });

    }

    public final class ListMessageSubscriber extends Subscriber<List<Message>> {

        private User mUser;

        public ListMessageSubscriber(User user){
            mUser = user;
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Message> messages) {
            mMessagesView.displayMessage(messages, mUser);
        }
    }
}
