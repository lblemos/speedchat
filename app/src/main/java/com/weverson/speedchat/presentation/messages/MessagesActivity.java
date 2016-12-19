package com.weverson.speedchat.presentation.messages;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.weverson.speedchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesActivity extends AppCompatActivity {

    @BindView(R.id.frame_messages)
    FrameLayout mFrameMessages;

    @BindView(R.id.toolbar_messages)
    Toolbar mToolbarMessages;

    private MessagesFragment mMessagesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        ButterKnife.bind(this);
        initializeFragment();
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


    private void initializeToolbar() {

        setSupportActionBar(mToolbarMessages);
        ActionBar toolbar = getSupportActionBar();

        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
            toolbar.setDisplayShowHomeEnabled(true);
            toolbar.setTitle(getString(R.string.title_messages));
            toolbar.setSubtitle("channel");
        }

    }


}
