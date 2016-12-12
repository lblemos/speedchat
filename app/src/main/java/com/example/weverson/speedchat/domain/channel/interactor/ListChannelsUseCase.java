package com.example.weverson.speedchat.domain.channel.interactor;

import com.example.weverson.speedchat.dagger.qualifier.Firebase;
import com.example.weverson.speedchat.data.repository.ChannelRepository;
import com.example.weverson.speedchat.data.repository.UserRepository;
import com.example.weverson.speedchat.domain.abstraction.Authenticable;
import com.example.weverson.speedchat.domain.abstraction.UseCase;
import com.example.weverson.speedchat.domain.channel.Channel;
import com.example.weverson.speedchat.domain.user.User;

import javax.inject.Inject;

import rx.Observable;

public class ListChannelsUseCase extends UseCase<Channel, ListChannelsUseCase.Request> {


    UserRepository mUserRepository;
    ChannelRepository mChannelRepository;

    @Inject
    public ListChannelsUseCase(@Firebase UserRepository userRepository,
                               @Firebase ChannelRepository channelRepository) {
        mUserRepository = userRepository;
        mChannelRepository = channelRepository;
    }

    @Override
    public Observable<Channel> createObservable(Request request) {
        Authenticable auth = mUserRepository.getCurrentUser(new User());
        return mUserRepository.getUserChannels(auth)
                .flatMap(Observable::from)
                .flatMap(mChannelRepository::getChannel);
    }

    public static class Request extends UseCase.Request {
    }


}
