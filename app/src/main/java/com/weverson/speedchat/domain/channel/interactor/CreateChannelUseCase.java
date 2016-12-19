package com.weverson.speedchat.domain.channel.interactor;


import android.support.annotation.NonNull;

import com.weverson.speedchat.data.repository.ChannelRepository;
import com.weverson.speedchat.data.repository.UserRepository;
import com.weverson.speedchat.domain.abstraction.UseCase;
import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.user.User;
import com.weverson.speedchat.utils.dagger.qualifier.Firebase;

import javax.inject.Inject;

import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreateChannelUseCase extends UseCase<Boolean, CreateChannelUseCase.Request> {

    private ChannelRepository mChannelRepository;
    private UserRepository mUserRepository;

    @Inject
    public CreateChannelUseCase(@Firebase UserRepository userRepository, @Firebase ChannelRepository channelRepository) {
        mUserRepository = userRepository;
        mChannelRepository = channelRepository;
    }

    @Override
    public Observable<Boolean> createObservable(Request request) {
        return mUserRepository.fetchCurrentUser(new User())
                .flatMap(authenticable -> {
                    Channel channel = request.getChannel();
                    channel.setAdmin(authenticable.getUid());
                    return mChannelRepository.createChannel(channel);
                }).flatMap(mUserRepository::addChannel);
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
