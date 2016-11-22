package com.example.weverson.speedchat.presentation.signup;

import android.content.Intent;
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
import com.example.weverson.speedchat.presentation.signin.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;


public class SignUpFragment extends Fragment implements SignUpContract.View {

    @BindView(R.id.linear_sign_up)
    LinearLayout mLinearSignUp;

    @BindView(R.id.text_sign_up)
    TextView mTextSignUp;

    @BindView(R.id.edit_nickname)
    EditText mEditNickname;

    @BindView(R.id.edit_email)
    EditText mEditEmail;

    @BindView(R.id.edit_password)
    EditText mEditPassword;

    @BindView(R.id.edit_confirm_password)
    EditText mEditConfirmPassword;

    @BindView(R.id.button_sign_up)
    Button mButtonSignUp;

    @BindView(R.id.text_sign_in)
    TextView mTextSignIn;


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
    public void showNicknameErrorMessage() {

        mEditNickname.setError(getContext().getString(R.string.msg_name_empty));
    }

    @Override
    public void showEmailErrorMessage() {
        mEditEmail.setError(getContext().getString(R.string.msg_email_empty));
    }

    @Override
    public void showEmailNotValidErrorMessage() {
        mEditEmail.setError(getContext().getString(R.string.msg_email_invalid));
    }

    @Override
    public void showPasswordErrorMessage() {
        mEditPassword.setError(getContext().getString(R.string.msg_password_empty));
    }

    @Override
    public void showConfirmPasswordErrorMessage() {
        mEditConfirmPassword.setError(getContext().getString(R.string.msg_confirm_password_empty));
    }

    @Override
    public void showPasswordsNotSameErrorMessage() {
        mEditConfirmPassword.setError(getContext().getString(R.string.msg_passwords_same));
    }

    @Override
    public void showConfirmationMessage() {
        String message = getContext().getString(R.string.msg_create_account_success);
        Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showFailMessage(String message) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }


    @OnClick(R.id.button_sign_up)
    public void signUp(View view) {

        String nickname = mEditNickname.getText().toString().trim();
        String email = mEditEmail.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        String confirmPassword = mEditConfirmPassword.getText().toString().trim();

        mSignUpPresenter.createNewAccount(nickname, email, password, confirmPassword);

    }

    @Override
    public void onStart() {
        super.onStart();
        showAnimateForm();
    }

    private void showAnimateForm() {

        final int itemDelay = 300;

        ViewGroup container = mLinearSignUp;

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator = ViewCompat.animate(v)
                    .scaleY(1).scaleX(1)
                    .setStartDelay((100 * i) + 500)
                    .setDuration(itemDelay);

            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();

        }
    }

    @OnClick(R.id.text_sign_in)
    public void openSignIn() {
        Intent it = new Intent(getContext(), SignInActivity.class);
        startActivity(it);
    }

}
