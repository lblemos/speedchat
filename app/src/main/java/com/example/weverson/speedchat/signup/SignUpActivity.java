package com.example.weverson.speedchat.signup;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.weverson.speedchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.frame_content)
    FrameLayout mFrameContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initFragment();
    }

    private void initFragment() {
        SignUpFragment signUpFragment = new SignUpFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_content, signUpFragment).commit();
    }
}
