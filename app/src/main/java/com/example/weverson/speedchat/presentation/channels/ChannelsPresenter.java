package com.example.weverson.speedchat.presentation.channels;

import com.example.weverson.speedchat.domain.channel.Channel;
import com.example.weverson.speedchat.domain.channel.interactor.ListChannelsUseCase;

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
        mChannelsView.createChannel();
        listChannels();
    }

    @Override
    public void listChannels() {
        mListChannelsUseCase.execute(new ListChannelsUseCase.Request())
                .subscribe(new ChannelsSubscriber());
    }

    private final class ChannelsSubscriber extends Subscriber<Channel> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Channel channel) {
            mChannelsView.addChannel(channel);
        }
    }
}
