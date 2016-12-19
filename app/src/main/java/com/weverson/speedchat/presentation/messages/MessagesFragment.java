package com.weverson.speedchat.presentation.messages;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weverson.speedchat.R;

public class MessagesFragment extends Fragment {


    public MessagesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    public static MessagesFragment getInstance() {
        return new MessagesFragment();
    }
}
