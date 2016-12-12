package com.example.weverson.speedchat.data.firebase;

import com.example.weverson.speedchat.data.firebase.listeners.FirebaseObservableListeners;
import com.example.weverson.speedchat.data.repository.ChannelRepository;
import com.example.weverson.speedchat.domain.channel.Channel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import rx.Observable;
import rx.functions.Func1;

public class ChannelFirebaseRepository implements ChannelRepository {

    DatabaseReference mReference;
    FirebaseObservableListeners mFirebaseObservableListeners;


    public ChannelFirebaseRepository(DatabaseReference databaseReference, FirebaseObservableListeners firebaseObservableListeners) {
        mReference = databaseReference;
        mFirebaseObservableListeners = firebaseObservableListeners;
    }


    @Override
    public Observable<Channel> getChannel(String channel) {
        Query query = mReference.child("channels").orderByKey().equalTo(channel);
        return mFirebaseObservableListeners.listenToValueEvents(query, toChannels());
    }

    private static Func1<DataSnapshot, Channel> toChannels() {

        return dataSnapshot -> {

            Channel channel = null;
            if (dataSnapshot.hasChildren()) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    channel = data.getValue(Channel.class);
                }
            }
            return channel;
        };

    }

}
