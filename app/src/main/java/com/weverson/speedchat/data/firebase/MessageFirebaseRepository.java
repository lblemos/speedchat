package com.weverson.speedchat.data.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.weverson.speedchat.data.firebase.listeners.FirebaseObservableListeners;
import com.weverson.speedchat.data.repository.MessageRepository;
import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.message.Message;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class MessageFirebaseRepository implements MessageRepository {

    DatabaseReference mReference;
    FirebaseObservableListeners mFirebaseObservableListeners;


    public MessageFirebaseRepository(DatabaseReference databaseReference, FirebaseObservableListeners firebaseObservableListeners) {
        mReference = databaseReference;
        mFirebaseObservableListeners = firebaseObservableListeners;
    }

    @Override
    public Observable<List<Message>> fetchMessageBy(Channel channel) {
        Query query = mReference.child("messages").child("channels").child(channel.getId());
        return mFirebaseObservableListeners.listenToValueEvents(query, toMessages());
    }

    private static Func1<DataSnapshot, List<Message>> toMessages() {
        return dataSnapshot -> {
            List<Message> messages = new ArrayList<>();
            if (dataSnapshot.hasChildren()) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    messages.add(child.getValue(Message.class));
                }
            }
            return messages;
        };
    }
}
