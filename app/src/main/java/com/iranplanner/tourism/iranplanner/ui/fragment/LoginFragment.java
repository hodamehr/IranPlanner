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
import com.iranplanner.tourism.iranplanner.ui.activity.SignupActivity;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;

import butterknife.ButterKnife;
import entity.LoginResult;
import entity.ResultUserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;
import tools.Util;

/**
 * Created by h.vahidimehr on 04/02/2017.
 */

public class LoginFragment extends StandardFragment implements Callback<LoginResult> {

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupLink, loginCommand, logout;
    ProgressDialog progressDialog;
    LinearLayout accountInputHolder;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

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

        setLOginName();


        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {

                clearSharedprefrence();
                _loginButton.setEnabled(true);
                accountInputHolder.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    _loginButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner));

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner));
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
                    counter=0;
                    v.setClickable(false);
                    v.setBackgroundColor(getResources().getColor(R.color.greyLight));
                    Toast.makeText(getContext(),"چند دقیقه بعد مجددا تلاش کنید",Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            v.setClickable(true);
                            v.setBackgroundColor(getResources().getColor(R.color.pink));
                        }
                    }, 50000);
                }else {
                    login();
                }

            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                SignInFragment signInFragment = new SignInFragment();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.accountHolder, signInFragment);
//                ft.addToBackStack(null);
//                ft.commit();
                Intent intent = new Intent(getContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setLOginName() {
        if (getDataFromShareprefrence() != "") {
            loginCommand.setVisibility(View.VISIBLE);
            loginCommand.setText(getDataFromShareprefrence() + " خوش آمدید");
            logout.setVisibility(View.VISIBLE);
            _loginButton.setEnabled(false);
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
//            onLoginFailed();
            return;
        }

//        _loginButton.setEnabled(false);
        accountInputHolder.setVisibility(View.INVISIBLE);


        showProgress();
        String email = _emailText.getText().toString();
        String password = Util.md5(_passwordText.getText().toString());
         getLoginResponce(email, password);
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("احراز هویت...");
        progressDialog.show();
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
//        if (password.isEmpty() || password.length() < 6 ) {
//            _passwordText.setError("کلمه عبور می بایست بیشتر از 6 حرف باشد");
//            valid = false;
//        } else {
//            _passwordText.setError(null);
//        }
        return valid;
    }

    public void getLoginResponce(String email, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<LoginResult> callc = getJsonInterface.getLoginResult("login", email, password, Util.getTokenFromSharedPreferences(getContext()),Util.getAndroidIdFromSharedPreferences(getContext()));
        callc.enqueue(this);
    }

    int counter = 0;

    @Override
    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
        if (response.body() != null) {
            LoginResult jsonResponse = response.body();
            ResultUserLogin resultUserLogin = jsonResponse.getResultUserLogin();
            saveDataINShareprefrence(resultUserLogin.getUserEmail(), resultUserLogin.getUserFname(), resultUserLogin.getUserLname(), resultUserLogin.getUserUid().toString());
            progressDialog.dismiss();
            _loginButton.setEnabled(true);
            accountInputHolder.setVisibility(View.VISIBLE);
            setLOginName();
        } else {
            progressDialog.dismiss();
            loginCommand.setVisibility(View.VISIBLE);
            loginCommand.setText("نام کاربری یا کلمه عبور اشتباه است.");
            accountInputHolder.setVisibility(View.VISIBLE);
            counter++;

        }
    }

    @Override
    public void onFailure(Call<LoginResult> call, Throwable t) {
        Toast.makeText(getContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_SHORT).show();
        Log.e("error", t.toString());
        progressDialog.dismiss();
        _loginButton.setEnabled(true);
        accountInputHolder.setVisibility(View.VISIBLE);


    }

    private void saveDataINShareprefrence(String email, String lastName, String gender, String userId) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("fname", lastName);
        editor.putString("gender", gender);
        editor.putString("gender", gender);
        editor.putString("userId", userId);
        editor.apply();
    }

    private void clearSharedprefrence() {
//        SharedPreferences preferences = getSharedPreferences("Mypref", 0);
//        preferences.edit().remove("shared_pref_key").commit();
        SharedPreferences settings = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }

    private String getDataFromShareprefrence() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String ShareprefrenceName = preferences.getString("fname", "");
        return ShareprefrenceName;
//        if(ShareprefrenceName!=null){
//            return true;
//        }else {
//            return false;
//        }

    }

//    @Override
//    public void fragmentBecameInvisible() {
//        super.fragmentBecameInvisible();
//        accountInputHolder.setVisibility(View.INVISIBLE);
//
//    }
//
//    @Override
//    public void fragmentBecameVisible() {
//        super.fragmentBecameVisible();
//        accountInputHolder.setVisibility(View.VISIBLE);
//    }
}
