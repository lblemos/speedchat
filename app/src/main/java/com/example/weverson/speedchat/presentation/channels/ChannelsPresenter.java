package com.example.weverson.speedchat.presentation.channels;

import com.example.weverson.speedchat.domain.channel.Channel;
import com.example.weverson.speedchat.domain.channel.interactor.ListChannelsUseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

class ChannelsPresenter implements ChannelsContract.Presenter {

    private ChannelsContract.View mChannelsView;

    private ListChannelsUseCase mListChannelsUseCase;

    @Inject
    public ChannelsPresenter(ChannelsContract.View channelsView, ListChannelsUseCase listChannelsUseCase) {
        mChannelsView = channelsView;
        mListChannelsUseCase = listChannelsUseCase;
    }

    @Inject
    void setupListeners() {
        mChannelsView.setPresenter(this);
    }

    @Override
    public void start() {
        listChannels();
    }

    @Override
    public void listChannels() {
        mListChannelsUseCase.execute(new ListChannelsUseCase.Request())
                .subscribe(new ChannelsSubscriber());
    }

    private final class ChannelsSubscriber extends Subscriber<List<Channel>>  {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Channel> channels) {
            mChannelsView.displayChannels(channels);
        }
    }
}
