package com.brightindigolight.divinationkit;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView mTextViewRegisterHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextViewRegisterHere = findViewById(R.id.editTextRegister);
        SpannableString ss = new SpannableString(getString(R.string.not_a_member_yet_register_here));
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcs,18,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                gotoSignupActivity();
            }
        };

        ss.setSpan(cs,18,31,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTextViewRegisterHere.setText(ss);
        mTextViewRegisterHere.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void gotoSignupActivity(){
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

}
