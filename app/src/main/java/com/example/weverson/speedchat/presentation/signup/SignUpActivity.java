package com.example.weverson.speedchat.presentation.signup;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.weverson.speedchat.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.frame_content) FrameLayout mFrameContent;

    @Inject SignUpPresenter mSignUpPresenter;

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
                .signUpModule(new SignUpModule(mSignUpFragment))
                .build().inject(this);

    }

    private void initializeFragment() {
        mSignUpFragment = new SignUpFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_content, mSignUpFragment).commit();
    }
}
