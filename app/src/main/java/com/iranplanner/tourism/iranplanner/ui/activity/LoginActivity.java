package com.iranplanner.tourism.iranplanner.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iranplanner.tourism.iranplanner.MainActivity;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.DaggerLoginComponent;
import com.iranplanner.tourism.iranplanner.di.LoginModule;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.presenter.LoginPresenter;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.LoginContract;

import javax.inject.Inject;

import butterknife.ButterKnife;
import entity.LoginReqSend;
import entity.LoginResult;
import entity.ResultUserLogin;
import server.Config;
import tools.Util;

/**
 * Created by h.vahidimehr on 04/02/2017.
 */

public class LoginActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LoginContract.View {

    EditText _emailText;
    EditText _passwordText;
    TextView _loginButton;
    TextView _signupLink, loginCommand, logout;
    ProgressDialog progressDialog;
    LinearLayout accountInputHolder, signupInputHolder;
    int counter = 0;


    //==========google signin
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleApiClient mGoogleApiClient;

    //  =============
    public static LoginActivity newInstance() {
        LoginActivity fragment = new LoginActivity();
        return fragment;
    }

    @Inject
    LoginPresenter loginPresenter;

    @Override


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getApplicationContext().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.login);
        ButterKnife.inject(this);

        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (TextView) findViewById(R.id.btn_login);
        _signupLink = (TextView) findViewById(R.id.link_signup);
        loginCommand = (TextView)findViewById(R.id.loginCommand);
        accountInputHolder = (LinearLayout) findViewById(R.id.accountInputHolder);
        logout = (TextView) findViewById(R.id.logout);
        loginCommand.setText("");
        signupInputHolder = (LinearLayout) findViewById(R.id.signupInputHolder);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        findViewById(R.id.btnSignInGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        // login?
        setLoginName();

        _emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                _loginButton.setEnabled(true);
                _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
            }
        });
        _passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                _loginButton.setEnabled(true);
                _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {

                clearSharedprefrence();
                _loginButton.setEnabled(true);
                accountInputHolder.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
                    }
                }
                loginCommand.setVisibility(View.GONE);
                logout.setVisibility(View.GONE);
                signupInputHolder.setVisibility(View.VISIBLE);
            }
        });
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (counter >= 3) {
                    counter = 0;
                    v.setClickable(false);
                    v.setBackgroundColor(getResources().getColor(R.color.greyLight));
                    Toast.makeText(getApplicationContext(), "چند دقیقه بعد مجددا تلاش کنید", Toast.LENGTH_LONG).show();
                    _loginButton.setEnabled(false);
                    _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_grey_stroke));
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            v.setEnabled(true);
                            v.setClickable(true);
                            v.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
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
//                Intent intent = new Intent(getContext(), SignupActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });    }

    private void setLoginName() {
        if (!Util.getUseRIdFromShareprefrence(getApplicationContext()).equals("")) {
            loginCommand.setVisibility(View.VISIBLE);
            loginCommand.setText(Util.getUserNameFromShareprefrence(getApplicationContext()) + " عزیز خوش آمدید");
            logout.setVisibility(View.VISIBLE);
            accountInputHolder.setVisibility(View.INVISIBLE);
            signupInputHolder.setVisibility(View.INVISIBLE);
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
            Toast.makeText(getApplicationContext(),"اشکال در مقادیر ورودی",Toast.LENGTH_SHORT).show();
            _loginButton.setEnabled(false);
            _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_grey_stroke));
            return;
        }
        _loginButton.setEnabled(true);
        _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
        accountInputHolder.setVisibility(View.INVISIBLE);

        requestLogin(_emailText.getText().toString(), Util.md5(_passwordText.getText().toString()));
    }

    private void requestLogin(String email, String password) {
        DaggerLoginComponent.builder().netComponent(((App) getApplicationContext().getApplicationContext()).getNetComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
        String cid = Util.getTokenFromSharedPreferences(getApplicationContext());
        String andId = Util.getAndroidIdFromSharedPreferences(getApplicationContext());
        loginPresenter.getLoginPostResul(new LoginReqSend("login", email, password, cid, andId), cid, andId);

    }

    @Override
    public void showLoginResult(LoginResult loginResult) {
        ResultUserLogin resultUserLogin = loginResult.getResultUserLogin();
        setSaveDataInShareprefrence(resultUserLogin.getUserEmail(), resultUserLogin.getUserFname(), resultUserLogin.getUserLname(), resultUserLogin.getUserUid().toString());
    }

    private void setSaveDataInShareprefrence(String email,String name,String lName,String userId){
        Util.saveDataINShareprefrence(getApplicationContext(), email,name, lName, userId);
        _loginButton.setEnabled(true);
        signupInputHolder.setVisibility(View.INVISIBLE);
        accountInputHolder.setVisibility(View.VISIBLE);
        setLoginName();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void showError(String message) {
        if (message.equals("HTTP 400 BAD REQUEST")) {
            loginCommand.setVisibility(View.VISIBLE);
            loginCommand.setText("نام کاربری یا کلمه عبور اشتباه است.");
            accountInputHolder.setVisibility(View.VISIBLE);
            _loginButton.setEnabled(true);
            counter++;
        } else {
            Toast.makeText(getApplicationContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
//            progressDialog.dismiss();
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
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("احراز هویت...");
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (progressDialog.isShowing() == true|| progressDialog!=null) {
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
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_USER, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", email);
        editor.putString("fname", lastName);
        editor.putString("gender", gender);
        editor.putString("gender", gender);
        editor.putString("userId", userId);
        editor.commit();
    }

    private void clearSharedprefrence() {
//        SharedPreferences preferences = getSharedPreferences("Mypref", 0);
//        preferences.edit().remove("shared_pref_key").commit();
        SharedPreferences settings = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_USER, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
//        SharedPreferences settings = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
//        settings.edit().clear().commit();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            setSaveDataInShareprefrence(acct.getEmail(),acct.getDisplayName(),acct.getFamilyName(),"");
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }
    // [END handleSignInResult]


    // [START signin]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
}
