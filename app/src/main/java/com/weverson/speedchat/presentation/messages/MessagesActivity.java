package com.weverson.speedchat.presentation.messages;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.weverson.speedchat.MainApplication;
import com.weverson.speedchat.R;
import com.weverson.speedchat.domain.channel.Channel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesActivity extends AppCompatActivity {

    public static final String EXTRA_CHANNEL = "com.weverson.speedchat.extras.EXTRA_CHANNEL";

    @BindView(R.id.frame_messages)
    FrameLayout mFrameMessages;

    @BindView(R.id.toolbar_messages)
    Toolbar mToolbarMessages;

    @Inject
    MessagesPresenter mMessagesPresenter;

    private MessagesFragment mMessagesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        ButterKnife.bind(this);
        initializeFragment();
        initializeDagger();
        initializeToolbar();

    }

    private void initializeFragment() {

        mMessagesFragment = (MessagesFragment) getSupportFragmentManager().findFragmentById(R.id.frame_messages);

        if (mMessagesFragment == null && mFrameMessages != null) {
            mMessagesFragment = MessagesFragment.getInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_messages, mMessagesFragment)
                    .commit();

        }

    }

    private void initializeDagger() {

        DaggerMessagesComponent.builder()
                .mainComponent(((MainApplication) getApplication()).getMainComponent())
                .repositoryComponent(((MainApplication) getApplication()).getRepositoryComponent())
                .messagesModule(new MessagesModule(mMessagesFragment)).build()
                .inject(this);

    }

    private void initializeToolbar() {

        Channel channel = (Channel) getIntent().getSerializableExtra(EXTRA_CHANNEL);
        setSupportActionBar(mToolbarMessages);
        ActionBar toolbar = getSupportActionBar();

        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
            toolbar.setDisplayShowHomeEnabled(true);
            toolbar.setTitle(getString(R.string.title_messages));
            if (channel != null && channel.getName() != null) {
                toolbar.setSubtitle(channel.getName());
            }

        }

    }


}
