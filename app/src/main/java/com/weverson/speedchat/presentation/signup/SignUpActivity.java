package com.weverson.speedchat.presentation.signup;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.weverson.speedchat.MainApplication;
import com.weverson.speedchat.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.frame_sign_up)
    FrameLayout mFrameSignUp;

    @Inject
    SignUpPresenter mSignUpPresenter;

    private SignUpFragment mSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        initializeFragment();
        initializeDagger();
    }

    private void initializeDagger() {
        DaggerSignUpComponent.builder()
                .mainComponent(((MainApplication) getApplication()).getMainComponent())
                .repositoryComponent(((MainApplication) getApplication()).getRepositoryComponent())
                .signUpModule(new SignUpModule(mSignUpFragment)).build()
                .inject(this);

    }

    private void initializeFragment() {

        mSignUpFragment = (SignUpFragment) getSupportFragmentManager().findFragmentById(R.id.frame_sign_up);

        if (mFrameSignUp != null && mSignUpFragment == null) {
            mSignUpFragment = new SignUpFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_sign_up, mSignUpFragment).commit();
        }
    }


}
