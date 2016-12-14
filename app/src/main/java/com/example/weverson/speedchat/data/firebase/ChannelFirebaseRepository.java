package com.example.weverson.speedchat.data.firebase;

import com.example.weverson.speedchat.data.firebase.listeners.FirebaseObservableListeners;
import com.example.weverson.speedchat.data.repository.ChannelRepository;
import com.example.weverson.speedchat.domain.abstraction.Authenticable;
import com.example.weverson.speedchat.domain.channel.Channel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

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
    public Observable<Channel> fetchChannelBy(String channelName) {
        Query query = mReference.child("channels").orderByKey().equalTo(channelName);
        return mFirebaseObservableListeners.listenToSingleValueEvents(query, toChannel());
    }

    public Observable<List<String>> fetchChannelsBy(Authenticable authenticable) {
        Query channels = mReference.child("users").child(authenticable.getUid()).child("channels")
                .orderByValue().equalTo(true);
        return mFirebaseObservableListeners.listenToSingleValueEvents(channels, toChannels());
    }

    private static Func1<DataSnapshot, Channel> toChannel() {
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

    private static Func1<DataSnapshot, List<String>> toChannels() {
        return dataSnapshot -> {
            List<String> channels = new ArrayList<>();
            if (dataSnapshot.hasChildren()) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    channels.add(child.getKey());
                }
            }
            return channels;
        };
    }

}
