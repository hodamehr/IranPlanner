package com.iranplanner.tourism.iranplanner.ui.activity;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.MainActivity;
import com.iranplanner.tourism.iranplanner.R;

import com.iranplanner.tourism.iranplanner.di.DaggerRegisterComponent;
import com.iranplanner.tourism.iranplanner.di.RegisterModule;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.presenter.RegisterPresenter;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.RegisterContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.RegisterReqSend;
import entity.ResultRegister;
import entity.ResultUserRegister;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.Config;
import server.getJsonInterface;
import tools.Util;

/**
 * Created by h.vahidimehr on 03/02/2017.
 */

public class SignupActivity extends StandardActivity implements RegisterContract.View {
    private static final String TAG = "SignupActivity";
    ProgressDialog progressDialog;
    @InjectView(R.id.input_name)
    EditText nameText;
    @InjectView(R.id.input_tel)
    EditText input_tel;
    @InjectView(R.id.input_family)
    EditText input_family;
    @InjectView(R.id.input_email)
    EditText editText;
    @InjectView(R.id.input_password)
    EditText passwordText;
    @InjectView(R.id.input_password_repeat)
    EditText input_password_repeat;
    @InjectView(R.id.btn_signup)
    Button _signupButton;
    @InjectView(R.id.link_login)
    TextView _loginLink;
    @InjectView(R.id.radioWoman)
    RadioButton radioWoman;
    @InjectView(R.id.radioMan)
    RadioButton radioMan;

    @Inject
    RegisterPresenter registerPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);


        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
//                Bundle bundle = new Bundle();


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("viewpager_position", 1);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_signup;
    }

    @Override
    public void showRegisterMessage(ResultRegister resultRegister) {

        ResultUserRegister result = resultRegister.getResultUserRegister();
        if (result.getStatus().equals("Succesfull")) {
            Toast.makeText(getApplicationContext(), "حساب کاربری با موفقیت انجام شد", Toast.LENGTH_LONG).show();

        } else if (result.getStatus().equals("Duplicate Phone")) {
            Toast.makeText(getApplicationContext(), "حساب کاربری با این شماره قبلاایجاد شده است ", Toast.LENGTH_LONG).show();

        } else if (result.getStatus().equals("Duplicate email")) {
            Toast.makeText(getApplicationContext(), "حساب کاربری با این ایمیل قبلاایجاد شده است ", Toast.LENGTH_LONG).show();

        } else if (result.getStatus().equals("Invalid info")) {
            Toast.makeText(getApplicationContext(), "اشکال در مقادیر ورودی", Toast.LENGTH_LONG).show();

        }
        progressDialog.dismiss();

        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);

    }

    @Override
    public void showError(String message) {
        Log.e("error", message);
        progressDialog.dismiss();
    }

    @Override
    public void showComplete() {
        progressDialog.dismiss();
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("ساخت حساب کاربری");
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }

    public void signup() {
        Log.d(TAG, "Signup");
        if (!validate()) {
            Toast.makeText(getApplicationContext(),"اشکال در مقادیر ورودی",Toast.LENGTH_SHORT).show();
            _signupButton.setEnabled(true);
            return;
        }
        _signupButton.setEnabled(false);

        String name = nameText.getText().toString();
        String lastName = input_family.getText().toString();
        String email = editText.getText().toString();
        String phoneNumber = input_tel.getText().toString();
        String password = Util.md5(passwordText.getText().toString());

        String gender = "0";
        if (radioMan.isChecked()) {
            gender = "1";
        } else if (radioWoman.isChecked()) {
            gender = "0";
        }


        DaggerRegisterComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .registerModule(new RegisterModule(this))
                .build().inject(this);
        RegisterReqSend registerReqSend = new RegisterReqSend("register", email, password, name, lastName, gender, Util.getTokenFromSharedPreferences(getApplicationContext()), phoneNumber, Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
        registerPresenter.getRegisterLoginResult(registerReqSend,Util.getTokenFromSharedPreferences(getApplicationContext()),Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = editText.getText().toString();
        String password = passwordText.getText().toString();
        String passwordRepeat = input_password_repeat.getText().toString();
        String phoneNumber = input_tel.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("حداقل سه حرف وارد شود");
            valid = false;
        } else {
            nameText.setError(null);
        }
        if (name.isEmpty() || name.length() < 2) {
            input_family.setError("حداقل دو حرف وارد شود");
            valid = false;
        } else {
            input_family.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText.setError("فرمت ایمیل اشتباه است");
            valid = false;
        } else {
            editText.setError(null);
        }

        if (password.isEmpty() || password.length() < 8) {
            passwordText.setError("کلمه عبور باید بیشتر از 8 حرف و ترکیبی از حرف و عدد باشد");


            valid = false;
        } else if (password.isEmpty() || password.length() >= 8) {
            int digitSize = 0;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isDigit(password.charAt(i))) {
                    digitSize = digitSize + 1;
                }
            }
            if (digitSize == password.length() || digitSize == 0) {
                passwordText.setError("کلمه عبور باید شامل حرف و عدد باشد");
                valid = false;
            }
        } else {

            passwordText.setError(null);
        }
        if (password.isEmpty() || password.length() < 8) {
            input_password_repeat.setError("کلمه عبور می بایست بیشتر از 8 حرف باشد");
            valid = false;
        } else {
            input_password_repeat.setError(null);
        }
        if (!passwordRepeat.equals(password)) {
            passwordText.setError("کلمه عبور و تکرار آن یکسان نیستند");
            valid = false;
        } else {
            input_password_repeat.setError(null);
        }
        if (!TextUtils.isEmpty(phoneNumber)) {

            if (phoneNumber.trim().length() < 10
                    || phoneNumber.trim().length() > 11
                    || (phoneNumber.trim().length() == 11 && !phoneNumber.trim().startsWith("09"))
                    || (phoneNumber.trim().length() == 10 && !phoneNumber.trim().startsWith("9"))) {
                String message = "شماره تلفن همراه وارد شده صحیح نیست.";
                input_tel.setError(message);
                valid = false;
            }
        }else if(TextUtils.isEmpty(phoneNumber)){
            String message = "ثبت شماره تلفن اجباری است";
            input_tel.setError(message);
            valid = false;
        }
        return valid;
    }

}
