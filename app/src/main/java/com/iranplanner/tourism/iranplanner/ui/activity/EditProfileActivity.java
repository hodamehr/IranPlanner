package com.iranplanner.tourism.iranplanner.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import tools.Util;

/**
 * Created by h.vahidimehr on 12/06/2017.
 */

public class EditProfileActivity extends StandardActivity {
    TextView email_address;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email_address = (TextView) findViewById(R.id.email_address);
        email_address.setText(Util.getEmailFromShareprefrence(getApplicationContext()));
    }
}
