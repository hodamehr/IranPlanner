package com.iranplanner.tourism.iranplanner.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.DaggerGetTokenComponent;

import com.iranplanner.tourism.iranplanner.di.GetTokenModule;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.presenter.GetTokenPresenter;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.GetTokenContract;

import javax.inject.Inject;

import entity.ResultPhoneVerify;
import entity.ResultSendPhone;
import login.SmsListener;
import tools.Util;

/**
 * Created by h.vahidimehr on 02/06/2017.
 */

public class GetTokenActivity extends StandardActivity implements GetTokenContract.View {
    EditText token1, token2, token3, token4, token5;
    Button btnInsertCode, btnSendCodeAgain;
    String phoneNumber;
    tools.SmsListener smsListener;
    @Inject
    GetTokenPresenter getTokenPresenter;




    public static final int MY_PERMISSIONS_REQUEST_SMS = 100;

    public boolean checkSMSPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSIONS_REQUEST_SMS);


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSIONS_REQUEST_SMS);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.RECEIVE_MMS)
                            == PackageManager.PERMISSION_GRANTED) {
                        Intent i = new Intent("android.provider.Telephony.SMS_RECEIVED");
                        i.setClass(this, SmsListener.class);
                        this.sendBroadcast(i);
//                        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
//                        intentFilter.setPriority(MY_PERMISSIONS_REQUEST_SMS);
//                        this.registerReceiver(smsListener, intentFilter);


                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "اجازه دسترسی داده نشد", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    //------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token1 = (EditText) findViewById(R.id.token1);
        btnInsertCode = (Button) findViewById(R.id.btnInsertCode);
        btnSendCodeAgain = (Button) findViewById(R.id.btnSendCodeAgain);
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSMSPermission();
        }




//        this.sendBroadcast(new Intent("android.provider.Telephony.SMS_RECEIVED"));
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        DaggerGetTokenComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .getTokenModule(new GetTokenModule(this))
                .build().inject(this);
        btnInsertCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTokenPresenter.getToken("phoneverify", phoneNumber, token1.getText().toString(), Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));

            }
        });


//        input_tel = (TextView) findViewById(R.id.input_tel);
//        btnSendPhone = (Button) findViewById(R.id.btnSendPhone);


    }


    @Override
    protected int getLayoutId() {
        return R.layout.get_token_number;
    }

    public boolean validate() {
        boolean valid = true;
//        String phoneNumber = input_tel.getText().toString();
//        if (!TextUtils.isEmpty(phoneNumber)) {
//
//            if (phoneNumber.trim().length() < 10
//                    || phoneNumber.trim().length() > 11
//                    || (phoneNumber.trim().length() == 11 && !phoneNumber.trim().startsWith("09"))
//                    || (phoneNumber.trim().length() == 10 && !phoneNumber.trim().startsWith("9"))) {
//                String message = "شماره تلفن همراه وارد شده صحیح نیست.";
//                input_tel.setError(message);
//                valid = false;
//            }
//        } else if (TextUtils.isEmpty(phoneNumber)) {
//            String message = "ثبت شماره تلفن اجباری است";
//            input_tel.setError(message);
//            valid = false;
//        }
        return valid;
    }


    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void getTokenResult(ResultPhoneVerify resultPhoneVerify) {

    }
}
