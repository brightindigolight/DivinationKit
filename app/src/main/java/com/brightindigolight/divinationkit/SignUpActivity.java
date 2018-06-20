package com.brightindigolight.divinationkit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Debug;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private TextView mTextLoginHere;
    private TextInputLayout mTextInputEmail, mTextInputPassword, mTextInputUsername, mTextInputConfirmPassword;
    private Button mButtonRegister;

    private String mEmail,mUsername,mPassword,mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mTextLoginHere= findViewById(R.id.textLoginHere);
        mTextInputEmail = findViewById(R.id.text_input_email);
        mTextInputUsername= findViewById(R.id.text_input_username);
        mTextInputPassword = findViewById(R.id.text_input_password);
        mTextInputConfirmPassword = findViewById(R.id.text_input_confirm_password);
        mButtonRegister = findViewById(R.id.btnRegister);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateUserInput()){
                    //todo firebase signup process
                }
            }
        });




        SpannableString ss = new SpannableString(getString(R.string.already_a_member_login_here));

        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                gotoLoginActivity();
            }
        };

        ss.setSpan(cs,18,28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTextLoginHere.setText(ss);
        mTextLoginHere.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void gotoLoginActivity(){
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        finish();
    }

    private boolean validateUserInput(){
        mUsername = mTextInputUsername.getEditText().getText().toString().trim();
        mEmail = mTextInputEmail.getEditText().getText().toString().trim();
        mPassword = mTextInputPassword.getEditText().getText().toString().trim();
        mConfirmPassword = mTextInputConfirmPassword.getEditText().getText().toString().trim();

        Log.d("check",mUsername);
        if(mUsername.isEmpty()){
            mTextInputUsername.getEditText().setError(getString(R.string.username_cannot_be_empty));
            mTextInputUsername.getEditText().requestFocus();
            return false;
        }

        if(mUsername.length()>25){
            mTextInputUsername.getEditText().setError(getString(R.string.username_max_length_error));
            mTextInputUsername.getEditText().requestFocus();

            return false;
        }

        if(mEmail.isEmpty()){
            mTextInputEmail.getEditText().setError(getString(R.string.email_cannot_be_empty));
            mTextInputEmail.getEditText().requestFocus();

            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
            mTextInputEmail.getEditText().setError(getString(R.string.enter_valid_email_error));
            mTextInputEmail.getEditText().requestFocus();

            return false;
        }

        if(mPassword.isEmpty()){
            mTextInputPassword.getEditText().setError(getString(R.string.password_cannot_be_empty));
            mTextInputPassword.getEditText().requestFocus();

            return false;
        }

        if(mPassword.length()<6){
            mTextInputPassword.getEditText().setError(getString(R.string.password_min_length_error));
            mTextInputPassword.getEditText().requestFocus();

            return false;
        }

        if(mConfirmPassword.isEmpty()){
            mTextInputConfirmPassword.getEditText().setError(getString(R.string.confirm_password_cannot_be_empty));
            mTextInputConfirmPassword.getEditText().requestFocus();

            return false;
        }

        if(!mPassword.equals(mConfirmPassword)){
            mTextInputConfirmPassword.getEditText().setError(getString(R.string.confirm_password_must_match_the_password));
            mTextInputConfirmPassword.getEditText().requestFocus();

            return false;
        }

        return true;
    }
}
