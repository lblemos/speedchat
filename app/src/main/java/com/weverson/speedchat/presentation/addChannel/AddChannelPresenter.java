package com.weverson.speedchat.presentation.addChannel;

import android.support.annotation.NonNull;

import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.channel.interactor.CreateChannelUseCase;

import javax.inject.Inject;

import rx.Subscriber;

public class AddChannelPresenter implements AddChannelContract.Presenter {

    private AddChannelContract.View mAddChannelView;

    private CreateChannelUseCase mCreateChannelUseCase;


    @Inject
    public AddChannelPresenter(AddChannelContract.View addChannelView, CreateChannelUseCase createChannelUseCase) {
        mAddChannelView = addChannelView;
        mCreateChannelUseCase = createChannelUseCase;
    }

    @Inject
    void setupListeners() {
        mAddChannelView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void selectImage() {
        mAddChannelView.showSelectImage();
    }

    @Override
    public void createNewChannel(@NonNull String name,@NonNull boolean isPrivate, String image) {
        Channel channel = new Channel(name).setPrivate(isPrivate).setImage(image);

        if (channel.getName().isEmpty()) {
            mAddChannelView.showMessageErrorNameEmpty();
        } else {
            mCreateChannelUseCase.execute(new CreateChannelUseCase.Request(channel))
                    .subscribe(new CreateChannelSubscriber());
        }


    }

    private final class CreateChannelSubscriber extends Subscriber<Boolean> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Boolean success) {
            if (success) {
                mAddChannelView.openChannels();
            }
        }
    }
}
