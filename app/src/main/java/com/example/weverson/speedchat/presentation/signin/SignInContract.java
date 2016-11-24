package com.example.weverson.speedchat.presentation.signin;

import android.support.annotation.NonNull;

import com.example.weverson.speedchat.presentation.BasePresenter;
import com.example.weverson.speedchat.presentation.BaseView;

public interface SignInContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter {
        void signIn(@NonNull String email, @NonNull String password);
    }

}
