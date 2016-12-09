package com.example.weverson.speedchat.domain.abstraction;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase<T, R extends UseCase.Request> {

    private Subscription mSubscription = Subscriptions.empty();

    public abstract Observable<T> createObservable(R request);


    public Observable<T> execute(R request) {
        return createObservable(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    public static abstract class Request {
    }
}
