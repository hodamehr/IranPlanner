package com.iranplanner.tourism.iranplanner.ui.fragment.myaccount;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.ui.activity.editprofile.EditProfileActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.login.LoginActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.ScrollingActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus.HotelReservationStatusActivity;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import entity.GetInfoReqSend;
import entity.GetInfoResult;
import entity.ResultReqCount;
import entity.ResultReservationReqStatus;
import server.Config;
import tools.Util;

/**
 * Created by Hoda on 10/01/2017.
 */
public class SettingFragment extends StandardFragment implements View.OnClickListener, SettingContract.View {

    TextView txtProfileName, btnEditProfile, txtHotelReservationStatus;
    RelativeLayout LayoutShowProfileHolder, exitFromAccount;
    @Inject
    SettingPresenter settingPresenter;
    private String tagFrom;
    ProgressDialog progressDialog;
    String cid;
    String andId;
    String uid;
    View view;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    private void getSharedpreferences() {
        cid = Util.getTokenFromSharedPreferences(getContext());
        andId = Util.getAndroidIdFromSharedPreferences(getContext());
        uid = Util.getUseRIdFromShareprefrence(getContext());
    }

    private void requestGetUser() {

        settingPresenter.getUserInfoPostResult(new GetInfoReqSend(uid), cid, andId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        txtProfileName = (TextView) view.findViewById(R.id.txtProfileName);
        btnEditProfile = (TextView) view.findViewById(R.id.btnEditProfile);
        txtHotelReservationStatus = (TextView) view.findViewById(R.id.txtHotelReservationStatus);
        LayoutShowProfileHolder = (RelativeLayout) view.findViewById(R.id.LayoutShowProfileHolder);
        exitFromAccount = (RelativeLayout) view.findViewById(R.id.exitFromAccount);
        btnEditProfile.setOnClickListener(this);
        LayoutShowProfileHolder.setOnClickListener(this);
        exitFromAccount.setOnClickListener(this);
        txtHotelReservationStatus.setOnClickListener(this);
        setLoginName();
        getSharedpreferences();
        DaggerSettingComponent.builder().netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .settingModule(new SettingModule(this))
                .build().inject(this);

        return view;
    }

    private void setLoginName() {
        if (!Util.getUseRIdFromShareprefrence(getContext()).equals("")) {
            txtProfileName.setText(Util.getUserNameFromShareprefrence(getContext()) + " خوش آمدید");
        }
    }

    private void getRerReservation() {
        settingPresenter.getResultReservationReqStatus("req_user_count", uid, "fa", cid, andId);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnEditProfile:
//                requestGetUser();
//                tagFrom = "editKey";
                Intent intenta = new Intent(getActivity(), ScrollingActivity.class);
                startActivity(intenta);

                break;
            case R.id.LayoutShowProfileHolder:
                requestGetUser();
                tagFrom = "showKey";
                break;
            case R.id.exitFromAccount:
                clearSharedprefrence();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.txtHotelReservationStatus:
                getRerReservation();
                break;

        }
    }



    private void clearSharedprefrence() {
        SharedPreferences settings = getContext().getSharedPreferences(Config.SHARED_PREF_USER, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }


    @Override
    public void showInfoUserResult(GetInfoResult infoResult) {
        Intent intentShowProfile = new Intent(getActivity(), EditProfileActivity.class);
        intentShowProfile.putExtra("from", tagFrom);
        intentShowProfile.putExtra("infoUserResult", (Serializable) infoResult.getResultUserInfo());
        startActivity(intentShowProfile);
    }

    @Override
    public void showError(String message) {
        Log.e("complete", "get attraction list");
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showComplete() {
        Log.e("complete", "get attraction list");
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("لطفا منتظر بمانید");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showResultReservationReqStatus(ResultReservationReqStatus resultReservationReqStatus) {
        List<ResultReqCount> resultReqCountList = resultReservationReqStatus.getResultReqCount();
//        initRequestStatusRecyclerView(view, resultReqCountList);
        Intent intent=new Intent(getActivity(), HotelReservationStatusActivity.class);
        intent.putExtra("resultReqCountList", (Serializable) resultReqCountList);


        startActivity(intent);



    }
}
