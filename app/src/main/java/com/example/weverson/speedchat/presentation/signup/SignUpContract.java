package com.example.weverson.speedchat.presentation.signup;

import android.support.annotation.NonNull;

import com.example.weverson.speedchat.presentation.BasePresenter;
import com.example.weverson.speedchat.presentation.BaseView;

public interface SignUpContract {

    interface Presenter extends BasePresenter {

        void createNewAccount(@NonNull String email,
                              @NonNull String password);

    }

    interface View extends BaseView<Presenter> {

        void showConfirmationMessage();

        void showFailMessage(String message);

    }

}
