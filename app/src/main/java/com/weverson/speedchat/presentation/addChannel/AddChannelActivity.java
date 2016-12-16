package com.weverson.speedchat.presentation.addChannel;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.weverson.speedchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddChannelActivity extends AppCompatActivity {


    @BindView(R.id.frame_add_channel)
    FrameLayout mFrameAddChannel;

    @BindView(R.id.toolbar_add_channel)
    Toolbar mToolbarAddChannel;

    private AddChannelFragment addChannelFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_channel);
        ButterKnife.bind(this);
        initializeFragment();
        initializeToolbar();
    }

    private void initializeFragment() {
        addChannelFragment = (AddChannelFragment) getSupportFragmentManager().findFragmentById(R.id.frame_add_channel);
        if (mFrameAddChannel != null && addChannelFragment == null) {
            addChannelFragment = AddChannelFragment.getInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_add_channel, addChannelFragment)
                    .commit();
        }
    }

    private void initializeToolbar() {

        setSupportActionBar(mToolbarAddChannel);
        ActionBar toolbar = getSupportActionBar();

        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.title_add_channel));
            toolbar.setDisplayHomeAsUpEnabled(true);
            toolbar.setDisplayShowHomeEnabled(true);
        }

    }

}
