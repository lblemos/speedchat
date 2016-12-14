package com.example.weverson.speedchat.data.firebase.listeners;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

class ListenToSingleValueOnSubscribe<T> implements Observable.OnSubscribe<T> {

    private final Query mQuery;
    private final Func1<DataSnapshot, T> mMarshaller;

    public ListenToSingleValueOnSubscribe(Query query, Func1<DataSnapshot, T> marshaller) {
        mQuery = query;
        mMarshaller = marshaller;
    }

    @Override
    public void call(Subscriber<? super T> subscriber) {

        mQuery.addListenerForSingleValueEvent(new RxSingleValueListener<>(subscriber, mMarshaller));

    }

    private static class RxSingleValueListener<T> implements ValueEventListener {

        private final Subscriber<? super T> subscriber;
        private final Func1<DataSnapshot, T> marshaller;

        RxSingleValueListener(Subscriber<? super T> subscriber, Func1<DataSnapshot, T> marshaller) {
            this.subscriber = subscriber;
            this.marshaller = marshaller;
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.hasChildren() && !subscriber.isUnsubscribed()) {
                subscriber.onNext(marshaller.call(dataSnapshot));
            }

            subscriber.onCompleted();

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            subscriber.onError(databaseError.toException());
        }
    }
}
