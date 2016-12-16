package com.weverson.speedchat.presentation.addChannel;

import android.support.annotation.NonNull;

import com.weverson.speedchat.presentation.BasePresenter;
import com.weverson.speedchat.presentation.BaseView;

public interface AddChannelContract {

    interface Presenter extends BasePresenter {

        void selectImage();

        void createNewChannel(@NonNull String name, String image);

    }

    interface View extends BaseView<Presenter> {

        void showSelectImage();

        void openChannels();

    }

}
