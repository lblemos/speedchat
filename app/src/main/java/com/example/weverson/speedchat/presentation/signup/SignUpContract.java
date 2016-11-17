package com.example.weverson.speedchat.presentation.signup;

import android.support.annotation.NonNull;

import com.example.weverson.speedchat.presentation.BasePresenter;
import com.example.weverson.speedchat.presentation.BaseView;

public interface SignUpContract {

    interface Presenter extends BasePresenter {

        void createNewAccount(@NonNull String nickname, @NonNull String email,
                              @NonNull String password, @NonNull String confirmPassword);

    }

    interface View extends BaseView<Presenter> {

        void showNicknameErrorMessage(String error);

        void showEmailErrorMessage(String error);

        void showPasswordErrorMessage(String error);

        void showConfirmPasswordErrorMessage(String error);

    }

}
