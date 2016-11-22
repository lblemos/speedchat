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

        void showNicknameErrorMessage();

        void showEmailErrorMessage();

        void showEmailNotValidErrorMessage();

        void showPasswordsNotSameErrorMessage();

        void showPasswordErrorMessage();

        void showConfirmPasswordErrorMessage();

        void showConfirmationMessage();

        void showFailMessage(String message);

    }

}
