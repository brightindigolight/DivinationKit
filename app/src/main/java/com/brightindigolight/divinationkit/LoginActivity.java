package com.brightindigolight.divinationkit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView mTextViewRegisterHere;
    private TextInputLayout mTextInputEmail, mTextInputPassword;
    private Button mButtonLogin;
    private ProgressBar mPB;

    String mEmail, mPwd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextViewRegisterHere = findViewById(R.id.editTextRegister);
        mTextInputEmail = findViewById(R.id.text_input_email);
        mTextInputPassword = findViewById(R.id.text_input_password);
        mPB = findViewById(R.id.progressBar2);
        mButtonLogin = findViewById(R.id.btnLogin);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateUserInput()) {
                    mPB.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(mEmail, mPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mPB.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                if(mAuth.getCurrentUser().isEmailVerified()) {
                                    Toast.makeText(LoginActivity.this, R.string.login_successfull, Toast.LENGTH_SHORT).show();
                                    gotoMainMenuActivity();
                                }else{
                                    Toast.makeText(LoginActivity.this, R.string.verify_your_email, Toast.LENGTH_LONG).show();
                                    mAuth.signOut();
                                }

                            }else{

                                Toast.makeText(LoginActivity.this, R.string.authentication_failed, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
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

        mAuth = FirebaseAuth.getInstance();
        mPB.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(mAuth.getCurrentUser()!=null){
            gotoMainMenuActivity();
        }
    }

    private void gotoSignUpActivity() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    private void gotoMainMenuActivity(){
        startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
        finish();
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
