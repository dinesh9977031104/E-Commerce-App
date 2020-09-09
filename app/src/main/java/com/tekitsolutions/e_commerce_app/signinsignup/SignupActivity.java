package com.tekitsolutions.e_commerce_app.signinsignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tekitsolutions.e_commerce_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_signin)
    TextView tvSignin;

    @BindView(R.id.iv_view_password)
    ImageView ivCreatePassword;

    @BindView(R.id.iv_confirm_password)
    ImageView ivConfirmPassword;

    @BindView(R.id.et_password)
    EditText etCreatePassword;

    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;

    @BindView(R.id.tv_create_account)
    TextView tvCreateAccount;

    private boolean isPasswordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);
        setOnclickListener();
    }

    private void setOnclickListener() {
        tvSignin.setOnClickListener(this);
        ivCreatePassword.setOnClickListener(this);
        ivConfirmPassword.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_signin:
                Intent it = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(it);
                finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                break;

            case R.id.iv_view_password:
                if (isPasswordVisible) {
                    ivCreatePassword.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    etCreatePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isPasswordVisible = false;
                } else {
                    ivCreatePassword.setImageResource(R.drawable.ic_visibility_black_24dp);
                    etCreatePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isPasswordVisible = true;
                }
                break;

            case R.id.iv_confirm_password:
                if (isPasswordVisible) {
                    ivConfirmPassword.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isPasswordVisible = false;
                } else {
                    ivConfirmPassword.setImageResource(R.drawable.ic_visibility_black_24dp);
                    etConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isPasswordVisible = true;
                }
                break;

            case R.id.tv_create_account:
                Intent intent = new Intent(SignupActivity.this,SigninActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                break;
        }
    }
}
