package com.example.weverson.speedchat.presentation.channels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.weverson.speedchat.MainApplication;
import com.example.weverson.speedchat.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelsActivity extends AppCompatActivity {

    @BindView(R.id.frame_channels)
    FrameLayout mFrameChannels;

    private ChannelsFragment mChannelsFragment;

    @Inject
    ChannelsPresenter mChannelsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);
        ButterKnife.bind(this);

        initializeFragment();
        initializeDagger();

    }

    private void initializeFragment() {
        if (mFrameChannels != null) {
            mChannelsFragment = new ChannelsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_channels, mChannelsFragment)
                    .commit();
        }
    }

    private void initializeDagger() {
        DaggerChannelsComponent.builder()
                .mainComponent(((MainApplication) getApplication()).getMainComponent())
                .repositoryComponent(((MainApplication) getApplication()).getRepositoryComponent())
                .channelsModule(new ChannelsModule(mChannelsFragment))
                .build()
                .inject(this);

    }


}
