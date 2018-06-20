package com.brightindigolight.divinationkit;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView mTextViewRegisterHere;
    private TextInputLayout mTextInputEmail, mTextInputPassword;
    private Button mButtonLogin;

    String mEmail, mPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextViewRegisterHere = findViewById(R.id.editTextRegister);
        mTextInputEmail = findViewById(R.id.text_input_email);
        mTextInputPassword = findViewById(R.id.text_input_password);
        mButtonLogin = findViewById(R.id.btnLogin);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateUserInput()) {
                    //todo firebase login process

                }
            }
        });


        SpannableString ss = new SpannableString(getString(R.string.not_a_member_yet_register_here));
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                gotoSignUpActivity();
            }
        };

        ss.setSpan(cs, 18, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTextViewRegisterHere.setText(ss);
        mTextViewRegisterHere.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void gotoSignUpActivity() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }


    private boolean validateUserInput() {
        mEmail = mTextInputEmail.getEditText().getText().toString().trim();
        mPwd = mTextInputPassword.getEditText().getText().toString().trim();
        if (mEmail.isEmpty()) {

            mTextInputEmail.getEditText().setError("Please enter your registered email");
            return false;
        }

        if (mPwd.isEmpty()) {
            mTextInputPassword.getEditText().setError("Please enter your password");
            return false;
        }
        return true;
    }

}
