package com.weverson.speedchat.presentation.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weverson.speedchat.R;
import com.weverson.speedchat.presentation.channels.ChannelsActivity;
import com.weverson.speedchat.presentation.signin.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.weverson.speedchat.utils.Animations.animateForm;
import static com.google.common.base.Preconditions.checkNotNull;


public class SignUpFragment extends Fragment implements SignUpContract.View {

    @BindView(R.id.linear_sign_up)
    LinearLayout mLinearSignUp;

    @BindView(R.id.text_sign_up)
    TextView mTextSignUp;

    @BindView(R.id.edit_email)
    EditText mEditEmail;

    @BindView(R.id.edit_password)
    EditText mEditPassword;

    @BindView(R.id.button_sign_in)
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


    @OnClick(R.id.button_sign_in)
    public void signUp() {

        String email = getEmail();
        String password = getPassword();

        mSignUpPresenter.createNewAccount(email, password);


    }

    @OnClick(R.id.text_sign_in)
    public void openSignIn() {
        Intent it = new Intent(getContext(), SignInActivity.class);
        startActivity(it);
    }

    @Override
    public void onStart() {
        super.onStart();
        animateForm(mLinearSignUp);
    }

    private String getEmail() {
        return mEditEmail.getText().toString().trim();
    }

    private String getPassword() {
        return mEditPassword.getText().toString().trim();
    }

    @Override
    public void openChannels() {
        Intent it = new Intent(getContext(), ChannelsActivity.class);
        startActivity(it);
    }

    @Override
    public void showFailMessage(String message) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMessageErrorEmailEmpty() {
        mEditEmail.setError(getContext().getString(R.string.error_email_empty));
    }

    @Override
    public void showMessageErrorPasswordEmpty() {
        mEditPassword.setError(getContext().getString(R.string.error_password_empty));
    }

}
