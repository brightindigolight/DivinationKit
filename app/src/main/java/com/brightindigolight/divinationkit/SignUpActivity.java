package com.brightindigolight.divinationkit;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private TextView mTextLoginHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mTextLoginHere= findViewById(R.id.textLoginHere);

        SpannableString ss = new SpannableString(getString(R.string.already_a_member_login_here));
        //ForegroundColorSpan fcs = new ForegroundColorSpan(Color.RED);

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
}
