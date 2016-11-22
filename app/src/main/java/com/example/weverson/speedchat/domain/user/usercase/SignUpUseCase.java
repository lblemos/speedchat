package com.example.weverson.speedchat.domain.user.usercase;

import com.example.weverson.speedchat.data.AuthenticationException;
import com.example.weverson.speedchat.data.firebase.authentication.FirebaseAuthentication;
import com.example.weverson.speedchat.domain.Authenticable;
import com.example.weverson.speedchat.domain.UseCase;
import com.example.weverson.speedchat.domain.user.User;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

import static com.google.common.base.Preconditions.checkNotNull;


public class SignUpUseCase extends UseCase<SignUpUseCase.RequestValues, Object> {

    private FirebaseAuthentication mAuth;

    @Inject
    public SignUpUseCase(FirebaseAuthentication auth) {
        mAuth = auth;
    }

    @Override
    protected Observable<Object> run(RequestValues requestValues) {
        return Observable.create(subscriber -> {
            mAuth.createNewUser(requestValues.getAuthenticable()).subscribe(new Subscriber<Void>() {
                @Override
                public void onCompleted() {
                    mAuth.updateProfile(requestValues.getAuthenticable())
                            .subscribe(v -> {}, e -> subscriber.onError(new AuthenticationException(e.getMessage())),
                                    () -> subscriber.onCompleted());
                }

                @Override
                public void onError(Throwable e) {
                    subscriber.onError(new AuthenticationException(e.getMessage()));
                }

                @Override
                public void onNext(Void aVoid) {

                }
            });
        });
    }

    public static final class RequestValues extends UseCase.RequestValues{

        private Authenticable mAuthenticable;

        public RequestValues(Authenticable authenticable) {
            mAuthenticable = checkNotNull(authenticable, "the authenticable can not be null");
        }

        public Authenticable getAuthenticable() {
            return mAuthenticable;
        }
    }

}
