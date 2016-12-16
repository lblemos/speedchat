package com.weverson.speedchat.presentation.addChannel;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.weverson.speedchat.MainApplication;
import com.weverson.speedchat.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddChannelActivity extends AppCompatActivity {


    @BindView(R.id.frame_add_channel)
    FrameLayout mFrameAddChannel;

    @BindView(R.id.toolbar_add_channel)
    Toolbar mToolbarAddChannel;

    @Inject
    AddChannelPresenter mAddChannelPresenter;

    private AddChannelFragment mAddChannelFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_channel);
        ButterKnife.bind(this);
        initializeFragment();
        initializeDaggerDagger();
        initializeToolbar();
    }

    private void initializeFragment() {
        mAddChannelFragment = (AddChannelFragment) getSupportFragmentManager().findFragmentById(R.id.frame_add_channel);
        if (mFrameAddChannel != null && mAddChannelFragment == null) {
            mAddChannelFragment = AddChannelFragment.getInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_add_channel, mAddChannelFragment)
                    .commit();
        }
    }

    private void initializeDaggerDagger() {
        DaggerAddChannelComponent.builder()
                .mainComponent(((MainApplication) getApplication()).getMainComponent())
                .repositoryComponent(((MainApplication) getApplication()).getRepositoryComponent())
                .addChannelModule(new AddChannelModule(mAddChannelFragment)).build()
                .inject(this);
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
