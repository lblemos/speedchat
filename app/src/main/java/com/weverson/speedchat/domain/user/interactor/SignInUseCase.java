package com.weverson.speedchat.domain.user.interactor;

import android.support.annotation.NonNull;

import com.weverson.speedchat.data.repository.UserRepository;
import com.weverson.speedchat.domain.abstraction.Authenticable;
import com.weverson.speedchat.domain.abstraction.UseCase;
import com.weverson.speedchat.utils.dagger.qualifier.Firebase;

import javax.inject.Inject;

import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

public class SignInUseCase extends UseCase<Void, SignInUseCase.Request> {


    private UserRepository mRepository;

    @Inject
    public SignInUseCase(@Firebase UserRepository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<Void> createObservable(Request request) {

        return mRepository.SignIn(request.getAuthenticable());

    }

    public static final class Request extends UseCase.Request {

        private Authenticable mAuth;

        public Request() {

        }

        public Request(@NonNull Authenticable auth) {
            mAuth = checkNotNull(auth, "the authenticable can not be null");
        }

        public Authenticable getAuthenticable() {
            return mAuth;
        }

    }

}
