package com.example.weverson.speedchat.presentation.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weverson.speedchat.R;
import com.example.weverson.speedchat.presentation.signup.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignInFragment extends Fragment {

    @BindView(R.id.text_sign_in)
    TextView textLogin;

    @BindView(R.id.linear_sign_in)
    LinearLayout mLinearSignIn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.text_sign_in)
    public void openSignUp() {
        Intent it = new Intent(getContext(), SignUpActivity.class);
        startActivity(it);
    }

    @Override
    public void onStart() {
        super.onStart();
        showAnimateForm();
    }

    private void showAnimateForm() {

        final int itemDelay = 300;

        ViewGroup container = mLinearSignIn;

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator = ViewCompat.animate(v)
                    .scaleY(1).scaleX(1)
                    .setStartDelay((100 * i) + 500)
                    .setDuration(itemDelay);

            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();

        }
    }
}
