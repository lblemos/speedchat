package com.weverson.speedchat.presentation.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.weverson.speedchat.MainApplication;
import com.weverson.speedchat.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.frame_sign_in)
    FrameLayout mFrameSignIn;

    private SignInFragment mSignInFragment;

    @Inject
    SignInPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        initializeFragment();
        initializerDagger();
    }

    private void initializeFragment() {
        mSignInFragment = (SignInFragment) getSupportFragmentManager().findFragmentById(R.id.frame_sign_in);

        if(mFrameSignIn != null && mSignInFragment == null) {
            mSignInFragment = SignInFragment.getInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_sign_in, mSignInFragment)
                    .commit();
        }
    }

    private void initializerDagger() {
        DaggerSignInComponent.builder()
                .mainComponent(((MainApplication) getApplication()).getMainComponent())
                .repositoryComponent(((MainApplication) getApplication()).getRepositoryComponent())
                .signInModule(new SignInModule(mSignInFragment)).build()
                .inject(this);
    }
}
