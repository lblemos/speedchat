package com.example.weverson.speedchat.domain.user.usercase;

import com.example.weverson.speedchat.data.firebase.authentication.FirebaseAuthentication;
import com.example.weverson.speedchat.domain.Authenticable;
import com.example.weverson.speedchat.domain.UseCase;

import javax.inject.Inject;

import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;


public class SignUpUseCase extends UseCase<SignUpUseCase.RequestValues, Void> {

    private FirebaseAuthentication mAuth;

    @Inject
    public SignUpUseCase(FirebaseAuthentication auth) {
        mAuth = auth;
    }

    @Override
    protected Observable<Void> run(RequestValues requestValues) {
        return mAuth.createNewUser(requestValues.getAuthenticable());
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
