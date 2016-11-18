package com.example.weverson.speedchat.domain;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class UseCase<R extends UseCase.RequestValues, T> {

    public Observable<T> execute(R requestValues){
        return run(requestValues)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected abstract Observable<T> run(R requestValues);

    public static abstract class RequestValues {
    }

}
