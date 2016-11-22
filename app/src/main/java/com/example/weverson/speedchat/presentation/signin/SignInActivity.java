package com.example.weverson.speedchat.presentation.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.weverson.speedchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.frame_sign_in)
    FrameLayout mFrameSignIn;

    private SignInFragment mSignInFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        initializeFragment();

    }

    private void initializeFragment() {
        if(mFrameSignIn != null) {
            mSignInFragment = new SignInFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_sign_in, mSignInFragment)
                    .commit();
        }
    }
}
