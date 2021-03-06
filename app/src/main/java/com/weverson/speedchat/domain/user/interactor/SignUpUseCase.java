package com.weverson.speedchat.domain.user.interactor;

import com.weverson.speedchat.data.repository.UserRepository;
import com.weverson.speedchat.domain.abstraction.Authenticable;
import com.weverson.speedchat.domain.abstraction.UseCase;
import com.weverson.speedchat.utils.dagger.qualifier.Firebase;

import javax.inject.Inject;

import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;


public class SignUpUseCase extends UseCase<Void, SignUpUseCase.Request> {

    private UserRepository mAuth;

    @Inject
    public SignUpUseCase(@Firebase UserRepository auth) {
        mAuth = auth;
    }

    @Override
    public Observable<Void> createObservable(Request request) {
        return mAuth.createNewUser(request.getAuthenticable());
    }

    public static final class Request extends UseCase.Request {

        private Authenticable mAuthenticable;

        public Request(Authenticable authenticable) {
            mAuthenticable = checkNotNull(authenticable, "the authenticable can not be null");
        }

        public Authenticable getAuthenticable() {
            return mAuthenticable;
        }
    }

}
