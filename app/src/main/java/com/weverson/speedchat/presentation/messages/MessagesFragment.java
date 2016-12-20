package com.weverson.speedchat.presentation.messages;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weverson.speedchat.R;
import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.message.Message;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class MessagesFragment extends Fragment implements MessagesContract.View {

    @BindView(R.id.recycler_messages)
    RecyclerView mRecyclerMessages;
    private MessagesContract.Presenter mMessagesPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static MessagesFragment getInstance() {
        return new MessagesFragment();
    }

    @Override
    public void setPresenter(@NonNull MessagesContract.Presenter presenter) {
        mMessagesPresenter = checkNotNull(presenter, "the presenter can not be null");
    }

    @Override
    public void onStart() {
        super.onStart();
        Channel channel = (Channel) getActivity().getIntent()
                .getSerializableExtra(MessagesActivity.EXTRA_CHANNEL);

        mMessagesPresenter.listMessages(channel);
    }

    @Override
    public void displayMessage(List<Message> message) {

        MessagesAdapter adapter = new MessagesAdapter(message);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        mRecyclerMessages.setLayoutManager(linearLayoutManager);
        mRecyclerMessages.setAdapter(adapter);

    }
}
