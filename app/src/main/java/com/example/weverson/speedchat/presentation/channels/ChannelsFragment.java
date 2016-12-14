package com.example.weverson.speedchat.presentation.channels;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weverson.speedchat.R;
import com.example.weverson.speedchat.domain.channel.Channel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChannelsFragment extends Fragment implements ChannelsContract.View {

    @BindView(R.id.Recycler_channels)
    RecyclerView mRecyclerChannels;


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
        ChannelsAdapter channelsAdapter = new ChannelsAdapter(channels);
        mRecyclerChannels.setLayoutManager(linearLayoutManager);
        mRecyclerChannels.setAdapter(new AlphaInAnimationAdapter(channelsAdapter));
        mRecyclerChannels.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onStart() {
        super.onStart();
        mChannelsPresenter.start();
    }

    public static ChannelsFragment getInstance() {
        return new ChannelsFragment();
    }


}
