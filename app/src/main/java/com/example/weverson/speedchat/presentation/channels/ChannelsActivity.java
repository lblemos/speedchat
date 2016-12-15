package com.example.weverson.speedchat.presentation.channels;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.weverson.speedchat.MainApplication;
import com.example.weverson.speedchat.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelsActivity extends AppCompatActivity {

    @BindView(R.id.frame_channels)
    FrameLayout mFrameChannels;

    @BindView(R.id.toolbar_channels)
    Toolbar mToolbarChannels;

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
        initializeToolbar();

    }

    private void initializeFragment() {
        mChannelsFragment = (ChannelsFragment) getSupportFragmentManager().findFragmentById(R.id.frame_channels);
        if (mFrameChannels != null && mChannelsFragment == null) {
            mChannelsFragment = ChannelsFragment.getInstance();
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

    private void initializeToolbar() {

        setSupportActionBar(mToolbarChannels);
        ActionBar toolbar = getSupportActionBar();

        if(toolbar != null) {
            toolbar.setTitle(getString(R.string.title_channels));
        }


    }


}
