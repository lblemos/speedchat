package com.weverson.speedchat.presentation.signup;

import android.support.annotation.NonNull;

import com.weverson.speedchat.presentation.BasePresenter;
import com.weverson.speedchat.presentation.BaseView;

public interface SignUpContract {

    interface Presenter extends BasePresenter {

        void createNewAccount(@NonNull String email,
                              @NonNull String password);

    }

    interface View extends BaseView<Presenter> {

        void openChannels();

        void showFailMessage(String message);

        void showMessageErrorEmailEmpty();

        void showMessageErrorPasswordEmpty();

    }

}
