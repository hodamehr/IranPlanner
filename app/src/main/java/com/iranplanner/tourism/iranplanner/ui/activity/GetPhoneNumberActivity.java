package com.iranplanner.tourism.iranplanner.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//import com.digits.sdk.android.AuthCallback;
//import com.digits.sdk.android.Digits;
//import com.digits.sdk.android.DigitsException;
//import com.digits.sdk.android.DigitsSession;
import com.iranplanner.tourism.iranplanner.R;
//import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
//import com.twitter.sdk.android.core.TwitterCore;
//import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import io.fabric.sdk.android.Fabric;
import login.DigitsRegisterButton;
import tools.Util;

/**
 * Created by h.vahidimehr on 08/05/2017.
 */

public class GetPhoneNumberActivity extends StandardActivity {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//    private static final String TWITTER_KEY = "9ReBYxJ9ldP2AhMJStWyXF94Y";
//    private static final String TWITTER_SECRET = "U6t7AUdJcuYKevlwNgCm3QpwWGQKxwOTNTltAbeIQQgkqGcW0C";
//    TwitterAuthClient mTwitterAuthClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Digits digits = new Digits.Builder()
//                .withTheme(R.style.CustomDigitsTheme).build();
//
//
//        Fabric.with(this, new TwitterCore(authConfig), digits);
        setContentView(R.layout.activity_get_phonenumber_layout);

        Util.displayFirebaseRegId(this);


//---------------------------------------------------

//        DigitsRegisterButton digitsButton = (DigitsRegisterButton) findViewById(R.id.signup_button);
//        digitsButton.setCallback(new AuthCallback() {
//            @Override
//            public void success(DigitsSession session, String phoneNumber) {
//                // TODO: associate the session userID with your user model
//                Toast.makeText(getApplicationContext(), "Authentication successful for "
//                        + phoneNumber, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void failure(DigitsException exception) {
//                Log.d("Digits", "Sign in with Digits failure", exception);
//            }
//        });



    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_phonenumber_layout;
    }

}
