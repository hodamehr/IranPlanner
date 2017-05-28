package com.iranplanner.tourism.iranplanner.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.CommentModule;
import com.iranplanner.tourism.iranplanner.di.DaggerLoginComponent;
import com.iranplanner.tourism.iranplanner.di.LoginModule;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.activity.SignupActivity;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.ui.presenter.LoginPresenter;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.LoginContract;

import javax.inject.Inject;

import butterknife.ButterKnife;
import entity.LoginReqSend;
import entity.LoginResult;
import entity.ResultUserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.Config;
import server.getJsonInterface;
import tools.Util;

/**
 * Created by h.vahidimehr on 04/02/2017.
 */

public class LoginFragment extends StandardFragment implements LoginContract.View {

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupLink, loginCommand, logout;
    ProgressDialog progressDialog;
    LinearLayout accountInputHolder;
    int counter = 0;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Inject
    LoginPresenter loginPresenter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View view = inflater.inflate(R.layout.login, container, false);
        ButterKnife.inject(getActivity());
        _emailText = (EditText) view.findViewById(R.id.input_email);
        _passwordText = (EditText) view.findViewById(R.id.input_password);
        _loginButton = (Button) view.findViewById(R.id.btn_login);
        _signupLink = (TextView) view.findViewById(R.id.link_signup);
        loginCommand = (TextView) view.findViewById(R.id.loginCommand);
        accountInputHolder = (LinearLayout) view.findViewById(R.id.accountInputHolder);
        logout = (TextView) view.findViewById(R.id.logout);
        loginCommand.setText("");

        // login?
        setLoginName();


        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {

                clearSharedprefrence();
                _loginButton.setEnabled(true);
                accountInputHolder.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_pink_stroke));

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_pink_stroke));
                    }

                }
                loginCommand.setVisibility(View.GONE);
                logout.setVisibility(View.GONE);
            }
        });
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (counter >= 3) {
                    counter = 0;
                    v.setClickable(false);
                    v.setBackgroundColor(getResources().getColor(R.color.greyLight));
                    Toast.makeText(getContext(), "چند دقیقه بعد مجددا تلاش کنید", Toast.LENGTH_LONG).show();
                    _loginButton.setEnabled(false);
                    _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_grey_stroke));
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            v.setEnabled(true);
                            v.setClickable(true);
                            v.setBackground(getResources().getDrawable(R.drawable.button_corner_pink_stroke));
                        }
                    }, 1000);
                } else {
                    login();
                }

            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setLoginName() {
        if (!Util.getUseRIdFromShareprefrence(getContext()).equals("") ) {
            loginCommand.setVisibility(View.VISIBLE);
            loginCommand.setText(Util.getUserNameFromShareprefrence(getContext()) + " عزیز خوش آمدید");
            logout.setVisibility(View.VISIBLE);
            accountInputHolder.setVisibility(View.INVISIBLE);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                _loginButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_cornner_disable));

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    _loginButton.setBackground(getResources().getDrawable(R.drawable.button_cornner_disable));
                }

            }
        }
    }

    public void login() {

        if (!validate()) {
            _loginButton.setEnabled(false);
            _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_grey_stroke));

            return;
        }
        _loginButton.setEnabled(true);
        _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_pink_stroke));
        accountInputHolder.setVisibility(View.INVISIBLE);


//        showProgress();
        String email = _emailText.getText().toString();
        String password = Util.md5(_passwordText.getText().toString());
        DaggerLoginComponent.builder().netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
        String cid = Util.getTokenFromSharedPreferences(getContext());
        String andId = Util.getAndroidIdFromSharedPreferences(getContext());
        loginPresenter.getLoginPostResul(new LoginReqSend("login", email, password, cid, andId), cid, andId);
    }

    @Override
    public void showLoginResult(LoginResult loginResult) {
        ResultUserLogin resultUserLogin = loginResult.getResultUserLogin();
        saveDataINShareprefrence(resultUserLogin.getUserEmail(), resultUserLogin.getUserFname(), resultUserLogin.getUserLname(), resultUserLogin.getUserUid().toString());
        _loginButton.setEnabled(true);

        accountInputHolder.setVisibility(View.VISIBLE);
        setLoginName();
    }

    @Override
    public void showError(String message) {
        if (message.equals("HTTP 400 BAD REQUEST")) {
            progressDialog.dismiss();
            loginCommand.setVisibility(View.VISIBLE);
            loginCommand.setText("نام کاربری یا کلمه عبور اشتباه است.");
            accountInputHolder.setVisibility(View.VISIBLE);
            _loginButton.setEnabled(true);
            counter++;
        } else {
            Toast.makeText(getActivity(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            loginCommand.setVisibility(View.VISIBLE);
            loginCommand.setText("نام کاربری یا کلمه عبور اشتباه است.");
            accountInputHolder.setVisibility(View.VISIBLE);
            _loginButton.setEnabled(true);
        }
    }

    @Override
    public void showComplete() {

    }

    public void showProgress() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("احراز هویت...");
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (progressDialog.isShowing() == true) {
            progressDialog.dismiss();
        }
    }

    public boolean validate() {
        boolean valid = true;
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("فرمت ایمیل اشتباه است");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        return valid;
    }

    private void saveDataINShareprefrence(String email, String lastName, String gender, String userId) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences pref = getContext().getSharedPreferences(Config.SHARED_PREF_USER, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", email);
        editor.putString("fname", lastName);
        editor.putString("gender", gender);
        editor.putString("gender", gender);
        editor.putString("userId", userId);
        editor.commit();
        //----------------------
//
//
//            SharedPreferences pref = getContext().getSharedPreferences(Config.SHARED_PREF, 0);
//            SharedPreferences.Editor editors = pref.edit();
//            editor.putString("regId", token);
//            editor.putString("andId",androidId);
//            editor.commit();


    }

    private void clearSharedprefrence() {
//        SharedPreferences preferences = getSharedPreferences("Mypref", 0);
//        preferences.edit().remove("shared_pref_key").commit();
        SharedPreferences settings = getContext().getSharedPreferences(Config.SHARED_PREF_USER, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
//        SharedPreferences settings = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
//        settings.edit().clear().commit();
    }

    private String getDataFromShareprefrence() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String ShareprefrenceName = preferences.getString("fname", "");
        return ShareprefrenceName;

    }


}
