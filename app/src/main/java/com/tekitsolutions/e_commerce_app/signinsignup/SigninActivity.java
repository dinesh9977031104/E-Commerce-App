package com.tekitsolutions.e_commerce_app.signinsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekitsolutions.e_commerce_app.R;
import com.tekitsolutions.e_commerce_app.home.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_signup)
    TextView tvSignup;
    @BindView(R.id.iv_view_password)
    ImageView ivViewPassword;
    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.tv_login)
    TextView tvLogin;

    private boolean isPasswordVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ButterKnife.bind(this);

        setClickListener();
    }

    private void setClickListener(){
        tvSignup.setOnClickListener(this);
        ivViewPassword.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_signup:
                Intent it = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(it);
                finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                break;

            case R.id.iv_view_password:
                if (isPasswordVisible) {
                    ivViewPassword.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isPasswordVisible = false;
                } else {
                    ivViewPassword.setImageResource(R.drawable.ic_visibility_black_24dp);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isPasswordVisible = true;
                }
                break;

            case R.id.tv_login:
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
