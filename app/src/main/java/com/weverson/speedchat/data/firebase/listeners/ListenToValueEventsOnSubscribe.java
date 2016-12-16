package com.weverson.speedchat.data.firebase.listeners;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.subscriptions.BooleanSubscription;

class ListenToValueEventsOnSubscribe<T> implements Observable.OnSubscribe<T> {

    private final Query mQuery;
    private final Func1<DataSnapshot, T> mMarshaller;

    public ListenToValueEventsOnSubscribe(Query query, Func1<DataSnapshot, T> marshaller) {
        mQuery = query;
        mMarshaller = marshaller;
    }

    @Override
    public void call(Subscriber<? super T> subscriber) {

        final ValueEventListener eventListener = mQuery.addValueEventListener(new RxValueListener<>(subscriber, mMarshaller));
        subscriber.add(BooleanSubscription.create(() -> mQuery.removeEventListener(eventListener)));

    }

    private static class RxValueListener<T> implements ValueEventListener {

        private final Subscriber<? super T> subscriber;
        private final Func1<DataSnapshot, T> marshaller;

        RxValueListener(Subscriber<? super T> subscriber, Func1<DataSnapshot, T> marshaller) {
            this.subscriber = subscriber;
            this.marshaller = marshaller;
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext(marshaller.call(dataSnapshot));
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            subscriber.onError(databaseError.toException());
        }
    }
}
