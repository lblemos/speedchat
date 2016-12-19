package com.weverson.speedchat.presentation.channels;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weverson.speedchat.R;
import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.presentation.addChannel.AddChannelActivity;
import com.weverson.speedchat.presentation.messages.MessagesActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChannelsFragment extends Fragment implements ChannelsContract.View, ChannelsAdapter.OnClickChannel {

    @BindView(R.id.Recycler_channels)
    RecyclerView mRecyclerChannels;

    @BindView(R.id.floating_add_channel)
    FloatingActionButton mFloatingAddChannel;


    private ChannelsContract.Presenter mChannelsPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channels, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(@NonNull ChannelsContract.Presenter presenter) {
        mChannelsPresenter = checkNotNull(presenter, "the presenter can not be null");
    }


    @Override
    public void displayChannels(List<Channel> channels) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ChannelsAdapter channelsAdapter = new ChannelsAdapter(channels, this);
        mRecyclerChannels.setLayoutManager(linearLayoutManager);
        mRecyclerChannels.setAdapter(new AlphaInAnimationAdapter(channelsAdapter));
        mRecyclerChannels.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void openAddChannel() {
        Intent it = new Intent(getContext(), AddChannelActivity.class);
        startActivity(it);
    }

    @Override
    public void onStart() {
        super.onStart();
        mChannelsPresenter.start();
    }

    public static ChannelsFragment getInstance() {
        return new ChannelsFragment();
    }


    @OnClick(R.id.floating_add_channel)
    public void onClick() {
        mChannelsPresenter.addChannel();
    }

    @Override
    public void openChannel(Channel channel) {
        Intent it = new Intent(getContext(), MessagesActivity.class);
        it.putExtra(MessagesActivity.EXTRA_CHANNEL, channel);
        startActivity(it);
    }
}
