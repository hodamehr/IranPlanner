package com.iranplanner.tourism.iranplanner.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;
import com.iranplanner.tourism.iranplanner.ui.activity.EditProfileActivity;

import tools.Util;

/**
 * Created by Hoda on 10/01/2017.
 */
public class SettingFragment extends StandardFragment implements View.OnClickListener{

    TextView txtProfileName,btnEditProfile;

    public static SettingFragment newInstance() {

        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        txtProfileName = (TextView) view.findViewById(R.id.txtProfileName);
        btnEditProfile = (TextView) view.findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(this);
        setLoginName();
        return view;
    }

    private void setLoginName() {
        if (!Util.getUseRIdFromShareprefrence(getContext()).equals("")) {
            txtProfileName.setText(Util.getUserNameFromShareprefrence(getContext()) + " عزیز خوش آمدید");
        }
    }

    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnEditProfile:
                Intent intent=new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
        }
    }
}
