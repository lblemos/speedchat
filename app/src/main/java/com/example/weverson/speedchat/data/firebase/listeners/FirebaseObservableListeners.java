package com.example.weverson.speedchat.data.firebase.listeners;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import rx.Observable;
import rx.functions.Func1;

public class FirebaseObservableListeners {

    public <T> Observable<T> listenToValueEvents(Query query, Func1<DataSnapshot, T> marshaller){
        return Observable.create(new ListenToValueEventsOnSubscribe<>(query, marshaller));
    }

    public <T> Observable<T> listenToSingleValueEvents(Query query, Func1<DataSnapshot, T> marshaller){
        return Observable.create(new ListenToSingleValueOnSubscribe<>(query, marshaller));
    }

}
