package com.weverson.speedchat.domain.channel.interactor;

import com.weverson.speedchat.data.repository.ChannelRepository;
import com.weverson.speedchat.data.repository.UserRepository;
import com.weverson.speedchat.domain.abstraction.UseCase;
import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.user.User;
import com.weverson.speedchat.utils.dagger.qualifier.Firebase;

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
        return mUserRepository.fetchCurrentUser(new User())
                .flatMap(authenticable -> mChannelRepository.fetchChannelsBy(authenticable)
                        .flatMap(Observable::from)
                        .flatMap(mChannelRepository::fetchChannelBy)
                        .toList());

    }

    public static class Request extends UseCase.Request {
    }


}
