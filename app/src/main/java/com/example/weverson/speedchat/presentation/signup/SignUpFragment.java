package com.example.weverson.speedchat.presentation.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.util.Patterns;
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

        String email = getEmail();
        String password = getPassword();

        if(chackForm()) {
            mSignUpPresenter.createNewAccount(email, password);
        }


    }

    protected boolean chackForm(){

        final String email = getEmail();
        final String password = getPassword();
        final String confirmPassword = getConfirmPassword();

        if (email.isEmpty()) {
            mEditEmail.setError(getContext().getString(R.string.msg_email_empty));
            return false;
        }

        if (password.isEmpty()) {
            mEditPassword.setError(getContext().getString(R.string.msg_password_empty));
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEditEmail.setError(getContext().getString(R.string.msg_email_invalid));
            return false;
        }

        if (confirmPassword.isEmpty()) {
            mEditConfirmPassword.setError(getContext().getString(R.string.msg_confirm_password_empty));
            return false;

        } else if (!password.equals(confirmPassword)) {
            mEditConfirmPassword.setError(getContext().getString(R.string.msg_passwords_same));
            return false;
        }

        return true;

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


    protected String getEmail(){
        return mEditEmail.getText().toString().trim();
    }

    protected String getPassword(){
        return mEditPassword.getText().toString().trim();
    }

    protected String getConfirmPassword(){
        return mEditConfirmPassword.getText().toString().trim();
    }

    @Override
    public void onStart() {
        super.onStart();
        showAnimateForm();
    }

}
