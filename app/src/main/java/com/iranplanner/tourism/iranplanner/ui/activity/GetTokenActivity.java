package com.iranplanner.tourism.iranplanner.ui.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
import entity.ResultUserVerify;
import server.SmsListener;
import tools.Util;

/**
 * Created by h.vahidimehr on 02/06/2017.
 */

public class GetTokenActivity extends StandardActivity implements GetTokenContract.View {
    EditText token1, token2, token3, token4, token5;
    Button btnInsertCode, btnSendCodeAgain;
    String phoneNumber;
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


                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "اجازه دسترسی داده نشد", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

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

    }


    @Override
    protected int getLayoutId() {
        return R.layout.get_token_number;
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
        checkVerify(resultPhoneVerify.getResultUserVerify());
    }

    private void checkVerify(ResultUserVerify resultUserVerify) {
    if(resultUserVerify.getPhoneVerify()==0){
        Log.e("error","retry");
    }else if(resultUserVerify.getPhoneVerify()==1){
        Log.e("ok","token is correct");

        // TODO: 6/4/2017 add phone number to shareprefrences
        if(resultUserVerify.getPhoneRegister()==0){
            Log.e("error","open register activity");

        }
        else if(resultUserVerify.getPhoneRegister()==1){
            Log.e("error","open login ");
        }
    }


    }

    private BroadcastReceiver ReceiveFromService = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //get the data using the keys you entered at the service
            String code = intent.getStringExtra("incomingPhoneNumber");
            token1.setText(code);

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(ReceiveFromService);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Receiver not registered")) {
                Log.i("TAG", "Tried to unregister the reciver when it's not registered");
            } else {
                throw e;
            }
        }
    }

    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SmsReceiver");
        registerReceiver(ReceiveFromService, filter);
        //the first parameter is the name of the inner class we created.
    }

}
