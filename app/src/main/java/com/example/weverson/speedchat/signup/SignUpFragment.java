package com.example.weverson.speedchat.signup;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weverson.speedchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends Fragment {

    public final String TAG = getClass().getSimpleName();

    @BindView(R.id.edit_nickname)  EditText mEditNickname;
    @BindView(R.id.text_nickname)  TextInputLayout mTextNickname;
    @BindView(R.id.edit_email) EditText mEditEmail;
    @BindView(R.id.text_email) TextInputLayout mTextEmail;
    @BindView(R.id.edit_password) EditText mEditPassword;
    @BindView(R.id.text_password) TextInputLayout mTextPassword;
    @BindView(R.id.button_sign_up) Button mButtonSignUp;
    @BindView(R.id.text_login) TextView mTextLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_sign_up)
    public void signUp(View view) {
        Log.d(TAG, "signUp: Teste");
        Toast.makeText(getContext(), "Sign Up", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.text_login)
    public void login(View view){
        Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();

    }
}
