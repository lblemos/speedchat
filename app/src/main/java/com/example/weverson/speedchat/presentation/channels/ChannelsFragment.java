package com.example.weverson.speedchat.presentation.channels;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weverson.speedchat.R;
import com.example.weverson.speedchat.domain.channel.Channel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChannelsFragment extends Fragment implements ChannelsContract.View {

    @BindView(R.id.Recycler_channels)
    RecyclerView mRecyclerChannels;

    private ChannelsAdapter mChannelsAdapter;
    private ChannelsContract.Presenter mChannelsPresenter;
    private List<Channel> mChannels;

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
    public void createChannel() {
        mChannels = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mChannelsAdapter = new ChannelsAdapter(mChannels);
        mRecyclerChannels.setLayoutManager(linearLayoutManager);
        mRecyclerChannels.setAdapter(mChannelsAdapter);
    }

    @Override
    public void addChannel(Channel channel) {
        if(!mChannels.contains(channel)) {
            mChannels.add(channel);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mChannelsPresenter.start();
    }
}
