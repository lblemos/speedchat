package com.example.weverson.speedchat.domain.channel.interactor;

import com.example.weverson.speedchat.dagger.qualifier.Firebase;
import com.example.weverson.speedchat.data.repository.ChannelRepository;
import com.example.weverson.speedchat.data.repository.UserRepository;
import com.example.weverson.speedchat.domain.abstraction.Authenticable;
import com.example.weverson.speedchat.domain.abstraction.UseCase;
import com.example.weverson.speedchat.domain.channel.Channel;
import com.example.weverson.speedchat.domain.user.User;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class ListChannelsUseCase extends UseCase<List<Channel>, ListChannelsUseCase.Request> {


    UserRepository mUserRepository;
    ChannelRepository mChannelRepository;

    @Inject
    public ListChannelsUseCase(@Firebase UserRepository userRepository,
                               @Firebase ChannelRepository channelRepository) {
        mUserRepository = userRepository;
        mChannelRepository = channelRepository;
    }

    @Override
    public Observable<List<Channel>> createObservable(Request request) {
        Authenticable auth = mUserRepository.getCurrentUser(new User());

        return mChannelRepository.fetchChannelsBy(auth)
                .flatMap(Observable::from)
                .flatMap(channelName -> mChannelRepository.fetchChannelBy(channelName))
                .toList();
    }

    public static class Request extends UseCase.Request {
    }


}
