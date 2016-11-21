package com.example.weverson.speedchat.presentation.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weverson.speedchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;


public class SignUpFragment extends Fragment implements SignUpContract.View  {

    @BindView(R.id.linear_sign_up) LinearLayout mLinearSignUp;
    @BindView(R.id.text_sign_up) TextView mTextSignUp;
    @BindView(R.id.edit_nickname)  EditText mEditNickname;
    @BindView(R.id.edit_email) EditText mEditEmail;
    @BindView(R.id.edit_password) EditText mEditPassword;
    @BindView(R.id.edit_confirm_password) EditText mEditConfirmPassword;
    @BindView(R.id.button_sign_up) Button mButtonSignUp;
    @BindView(R.id.text_login) TextView mTextLogin;

    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;


    private SignUpContract.Presenter mSignUpPresenter;

    public SignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(@NonNull SignUpContract.Presenter presenter) {
        mSignUpPresenter = checkNotNull(presenter);
    }

    @Override
    public void showNicknameErrorMessage(String error) {
        mEditNickname.setError(error);
    }

    @Override
    public void showEmailErrorMessage(String error) {
        mEditEmail.setError(error);
    }

    @Override
    public void showPasswordErrorMessage(String error) {
        mEditPassword.setError(error);
    }

    @Override
    public void showConfirmPasswordErrorMessage(String error) {
        mEditConfirmPassword.setError(error);
    }

    @Override
    public void showConfirmationMessage(String message) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }


    @OnClick(R.id.button_sign_up)
    public void signUp(View view){

        String nickname = mEditNickname.getText().toString().trim();
        String email = mEditEmail.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        String confirmPassword = mEditConfirmPassword.getText().toString().trim();

        mSignUpPresenter.createNewAccount(nickname, email, password, confirmPassword);

    }

    @Override
    public void onResume() {
        super.onResume();
        showAnimateForm();
    }

    private void showAnimateForm() {

        ViewGroup container = mLinearSignUp;

        ViewCompat.animate(mTextSignUp)
                .translationY(200)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION)
                .setInterpolator(new DecelerateInterpolator(1.2f))
                .start();


        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            if (!(v instanceof Button || v instanceof LinearLayout)) {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(70).alpha(1)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((100 * i) + 500)
                        .setDuration(ITEM_DELAY);

            } else {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(70).alpha(1)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((100 * i) + 500)
                        .setDuration(ITEM_DELAY);

            }

            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();

        }
    }

}
