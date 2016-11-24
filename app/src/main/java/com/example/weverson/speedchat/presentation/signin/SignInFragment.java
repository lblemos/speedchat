package com.example.weverson.speedchat.presentation.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weverson.speedchat.R;
import com.example.weverson.speedchat.presentation.signup.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.weverson.speedchat.presentation.utils.Animations.animateForm;
import static com.example.weverson.speedchat.presentation.utils.FormValidation.checkEmailValid;
import static com.example.weverson.speedchat.presentation.utils.FormValidation.checkInputEmpty;
import static com.google.common.base.Preconditions.checkNotNull;


public class SignInFragment extends Fragment implements SignInContract.View {

    @BindView(R.id.text_sign_in)
    TextView textLogin;

    @BindView(R.id.linear_sign_in)
    LinearLayout mLinearSignIn;

    @BindView(R.id.edit_email)
    EditText mEditEmail;

    @BindView(R.id.edit_password)
    EditText mEditPassword;

    @BindView(R.id.button_sign_in)
    Button mButtonSignUp;

    private SignInContract.Presenter mSignInPresenter;

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
        animateForm(mLinearSignIn);
    }

    @Override
    public void setPresenter(@NonNull SignInContract.Presenter presenter) {
        mSignInPresenter = checkNotNull(presenter, "the presenter can not be null");
    }

    @OnClick(R.id.button_sign_in)
    public void signIn() {
        if (checkForm()) {
            mSignInPresenter.signIn(getEmail(), getPassword());
        }
    }

    private boolean checkForm(){

        final String email = getEmail();
        final String password = getPassword();
        boolean isValid = true;

        if(checkInputEmpty(email)){
            mEditEmail.setError(getString(R.string.error_email_empty));
            isValid = false;
        } else if(checkEmailValid(email)) {
            mEditEmail.setError(getString(R.string.error_email_invalid));
            isValid = false;
        }

        if(checkInputEmpty(password)){
            mEditPassword.setError(getString(R.string.error_password_empty));
            isValid = false;
        }

        return isValid;
    }

    private String getEmail() {
        return mEditEmail.getText().toString().trim();
    }


    private String getPassword() {
        return mEditPassword.getText().toString().trim();
    }

}
