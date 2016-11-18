package com.example.weverson.speedchat.domain.user.usercase;

import com.example.weverson.speedchat.domain.UseCase;

import javax.inject.Inject;

import rx.Observable;


public class SignUpUseCase extends UseCase<SignUpUseCase.RequestValues, Object> {

    @Inject
    public SignUpUseCase() {
    }

    @Override
    protected Observable<Object> run(RequestValues requestValues) {
        return Observable.create(subscriber -> {
            subscriber.onNext(new Object());
        });
    }

    public static final class RequestValues extends UseCase.RequestValues{

    }

}
