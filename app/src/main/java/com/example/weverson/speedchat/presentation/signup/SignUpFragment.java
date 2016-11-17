package com.example.weverson.speedchat.presentation.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weverson.speedchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;


public class SignUpFragment extends Fragment implements SignUpContract.View  {

    @BindView(R.id.edit_nickname)  EditText mEditNickname;
    @BindView(R.id.edit_email) EditText mEditEmail;
    @BindView(R.id.edit_password) EditText mEditPassword;
    @BindView(R.id.edit_confirm_password) EditText mEditConfirmPassword;
    @BindView(R.id.button_sign_up) Button mButtonSignUp;
    @BindView(R.id.text_login) TextView mTextLogin;


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


    @OnClick(R.id.button_sign_up)
    public void signUp(View view){

        String nickname = mEditNickname.getText().toString().trim();
        String email = mEditEmail.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        String confirmPassword = mEditConfirmPassword.getText().toString().trim();

        mSignUpPresenter.createNewAccount(nickname, email, password, confirmPassword);

    }
}
