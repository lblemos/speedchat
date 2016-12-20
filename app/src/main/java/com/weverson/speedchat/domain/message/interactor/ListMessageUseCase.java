package com.weverson.speedchat.domain.message.interactor;

import android.support.annotation.NonNull;

import com.weverson.speedchat.data.repository.MessageRepository;
import com.weverson.speedchat.data.repository.UserRepository;
import com.weverson.speedchat.domain.abstraction.UseCase;
import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.message.Message;
import com.weverson.speedchat.utils.dagger.qualifier.Firebase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

public class ListMessageUseCase extends UseCase<List<Message>, ListMessageUseCase.Request> {

    MessageRepository mMessageRepository;
    UserRepository mUserRepository;

    @Inject
    public ListMessageUseCase(@Firebase UserRepository userRepository,
                               @Firebase MessageRepository messageRepository) {
        mUserRepository = userRepository;
        mMessageRepository = messageRepository;
    }

    @Override
    public Observable<List<Message>> createObservable(ListMessageUseCase.Request request) {
       return mMessageRepository.fetchMessageBy(request.getChannel());
    }

    public static final class Request extends UseCase.Request {

        private Channel mChannel;

        public Request(@NonNull Channel channel) {
            mChannel = checkNotNull(channel, "the channel can not be null");
        }

        public Channel getChannel() {
            return mChannel;
        }
    }

}
