package com.iranplanner.tourism.iranplanner.ui.activity.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.iranplanner.tourism.iranplanner.ui.activity.mainActivity.MainActivity;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.activity.register.RegisterActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import entity.GoogleLoginReqSend;
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
    boolean block = false;


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
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        ButterKnife.inject(this);
        if (Util.getUseRIdFromShareprefrence(getApplicationContext()) == null || Util.getUseRIdFromShareprefrence(getApplicationContext()) == "") {
            setContentView(R.layout.login);
            _emailText = (EditText) findViewById(R.id.input_email);
            _passwordText = (EditText) findViewById(R.id.input_password);
            _loginButton = (TextView) findViewById(R.id.btn_login);
            _signupLink = (TextView) findViewById(R.id.link_signup);
            loginCommand = (TextView) findViewById(R.id.loginCommand);
            accountInputHolder = (LinearLayout) findViewById(R.id.accountInputHolder);
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
//            setLoginName();

            _emailText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!block) {
                        set_loginButton(true, null);

                    }

//                    _loginButton.setEnabled(true);
//                    _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
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
                    if (!block) {
                        set_loginButton(true, null);

                    }
//                    _loginButton.setEnabled(true);
//                    _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
                }
            });

            _loginButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    if (counter >= 3) {
                        counter = 0;
                        block = true;
//                        loginCommand.setText("");
//                        v.setClickable(false);
//                        v.setBackgroundColor(getResources().getColor(R.color.greyLight));
//                        Toast.makeText(getApplicationContext(), "چند دقیقه بعد مجددا تلاش کنید", Toast.LENGTH_LONG).show();
//                        _loginButton.setEnabled(false);
//                        _emailText.setText("");
//                        _passwordText.setText("");
//                        _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_grey_stroke));
                        loginCommand.setText("تلاش ناموفق برای سه بار! چند دقیقه دیگر امتحان کنید");

                        set_loginButton(false, v);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
//                                v.setEnabled(true);
//                                v.setClickable(true);
//                                v.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
                                cleaner();
                                set_loginButton(true, v);
                            }
                        }, 150000);
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
            });
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    }

    private void cleaner() {
        loginCommand.setText("");
        _emailText.setText("");
        _passwordText.setText("");
    }

    private void set_loginButton(boolean setEnable, View v) {
        if (setEnable) {
            block = false;
            _loginButton.setEnabled(true);
            _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
            _loginButton.setClickable(true);

        } else {
            _loginButton.setEnabled(false);
            _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_grey_stroke));
            _loginButton.setClickable(false);
        }

    }

    public void login() {

        if (!validate()) {
            Toast.makeText(getApplicationContext(), "اشکال در مقادیر ورودی", Toast.LENGTH_SHORT).show();
            set_loginButton(false, null);
//            _loginButton.setEnabled(false);
//            _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_grey_stroke));
            return;
        }
//        _loginButton.setEnabled(true);
//        _loginButton.setBackground(getResources().getDrawable(R.drawable.button_corner_blue_stroke));
//        accountInputHolder.setVisibility(View.INVISIBLE);
        set_loginButton(true, null);
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

    private void requestGoogleLogin(GoogleLoginReqSend googleLoginReqSend) {
        DaggerLoginComponent.builder().netComponent(((App) getApplicationContext().getApplicationContext()).getNetComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
        loginPresenter.getGoogleLoginPostResult(googleLoginReqSend, Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
    }

    @Override
    public void showLoginResult(LoginResult loginResult) {
        ResultUserLogin resultUserLogin = loginResult.getResultUserLogin();
        setSaveDataInShareprefrence(resultUserLogin.getUserEmail(), resultUserLogin.getUserFname(), resultUserLogin.getUserLname(), resultUserLogin.getUserUid().toString());
    }

    private void setSaveDataInShareprefrence(String email, String name, String lName, String userId) {
        Util.saveDataINShareprefrence(getApplicationContext(), email, name, lName, userId);
//        _loginButton.setEnabled(true);
//        signupInputHolder.setVisibility(View.INVISIBLE);
//        accountInputHolder.setVisibility(View.VISIBLE);
//        setLoginName();
        loginCommand.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(String message) {
        counter++;
        if (message.equals("HTTP 400 BAD REQUEST")) {
            loginCommand.setVisibility(View.VISIBLE);
            loginCommand.setText("نام کاربری یا کلمه عبور اشتباه است.");
//            accountInputHolder.setVisibility(View.VISIBLE);
//            _loginButton.setEnabled(true);

        } else {
//            Toast.makeText(getApplicationContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
//            progressDialog.dismiss();
            loginCommand.setVisibility(View.VISIBLE);
            loginCommand.setText("نام کاربری یا کلمه عبور اشتباه است.");
//            accountInputHolder.setVisibility(View.VISIBLE);
//            _loginButton.setEnabled(true);
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
        if (progressDialog.isShowing() == true || progressDialog != null) {
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
            GoogleLoginReqSend googleLoginReqSend = new GoogleLoginReqSend("googleoauth2", acct.getPhotoUrl().toString(), acct.getId(), acct.getFamilyName(), acct.getGivenName(), acct.getEmail());
            requestGoogleLogin(googleLoginReqSend);
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
