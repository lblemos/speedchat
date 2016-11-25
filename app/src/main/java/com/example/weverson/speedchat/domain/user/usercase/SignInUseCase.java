package com.example.weverson.speedchat.domain.user.usercase;

import com.example.weverson.speedchat.data.firebase.authentication.FirebaseAuthentication;
import com.example.weverson.speedchat.domain.Authenticable;
import com.example.weverson.speedchat.domain.UseCase;

import javax.inject.Inject;

import rx.Observable;

public class SignInUseCase extends UseCase<SignInUseCase.RequestValues, Void> {

    private FirebaseAuthentication mAuth;

    @Inject
    public SignInUseCase(FirebaseAuthentication auth) {
        this.mAuth = auth;
    }

    @Override
    protected Observable<Void> run(RequestValues requestValues) {

        return mAuth.SignIn(requestValues.getAuthenticable());

    }

    public final static class RequestValues extends UseCase.RequestValues{

        private Authenticable mAuthenticable;

        public RequestValues(Authenticable authenticable) {
            this.mAuthenticable = authenticable;
        }

        public Authenticable getAuthenticable() {
            return mAuthenticable;
        }
    }

}
