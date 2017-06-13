package com.iranplanner.tourism.iranplanner.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinpany.core.android.widget.Utils;
import com.coinpany.core.android.widget.calendar.dateutil.PersianCalendar;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.di.DaggerEditProfileComponent;
import com.iranplanner.tourism.iranplanner.di.EditProfileModule;
import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.ui.presenter.EditProfilePresenter;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.AttractionDetailContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.EditProfileContract;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import entity.ResultUpdate;
import entity.updateProfileSend;
import tools.Util;
import tools.widget.PersianDatePicker;

import static com.iranplanner.tourism.iranplanner.R.id.input_email;
import static com.iranplanner.tourism.iranplanner.R.id.year;

/**
 * Created by h.vahidimehr on 12/06/2017.
 */

public class EditProfileActivity extends StandardActivity implements View.OnClickListener, EditProfileContract.View {
    TextView email_address, txtDate, btnEditProfile;
    EditText input_tel, input_name, input_family, input_lodging;
    RelativeLayout changeDateHolder;
    CheckBox checkBoxNews;
    RadioButton radioWoman;
    RadioButton radioMan;
    Date birthday;
    @Inject
    EditProfilePresenter editProfilePresenter;
    DaggerEditProfileComponent.Builder builder;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        email_address = (TextView) findViewById(R.id.email_address);
        email_address.setText(Util.getEmailFromShareprefrence(getApplicationContext()));
        changeDateHolder = (RelativeLayout) findViewById(R.id.changeDateHolder);
        txtDate = (TextView) findViewById(R.id.txtDate);
        btnEditProfile = (TextView) findViewById(R.id.btnEditProfile);
        input_name = (EditText) findViewById(R.id.input_name);
        input_tel = (EditText) findViewById(R.id.input_tel);
        input_family = (EditText) findViewById(R.id.input_family);
        input_lodging = (EditText) findViewById(R.id.input_lodging);
        radioMan= (RadioButton) findViewById(R.id.radioMan);
        radioWoman= (RadioButton) findViewById(R.id.radioWoman);
        changeDateHolder.setOnClickListener(this);
        btnEditProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeDateHolder:
                CustomDialogTravel cdd = new CustomDialogTravel(this);
                cdd.show();
                break;
            case R.id.btnEditProfile:
                builder = DaggerEditProfileComponent.builder()
                        .netComponent(((App) getApplicationContext()).getNetComponent())
                        .editProfileModule(new EditProfileModule(this));
                builder.build().inject(this);

                String gender = "0";
                if (radioMan.isChecked()) {
                    gender = "1";
                } else if (radioWoman.isChecked()) {
                    gender = "0";
                }

                updateProfileSend updateProfileSend = new updateProfileSend(Util.getUseRIdFromShareprefrence(getApplicationContext()).toString(),
                        input_name.getText().toString(),
                        input_family.getText().toString(),
                        gender,
                        email_address.getText().toString(),
                        String.valueOf(birthday.getTime()),
                        "", input_lodging.getText().toString(),
                        "0",
                        "1",
                        input_tel.getText().toString(),
                        "1",
                        Util.getTokenFromSharedPreferences(getApplicationContext()));
                editProfilePresenter.getEditProfilePostResul(updateProfileSend, Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));

                break;
        }
    }

    @Override
    public void showEditProfilePostResul(ResultUpdate resultUpdate) {

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


    public class CustomDialogTravel extends Dialog implements
            View.OnClickListener {

        public Activity c;
        public Dialog d;
        public TextView yes, no;
        PersianDatePicker persianDatePickr;


        public CustomDialogTravel(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //        bara inke keybord bala nayad
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_date_travel);
            persianDatePickr = (PersianDatePicker) findViewById(R.id.travelDate);
            yes = (TextView) findViewById(R.id.txtOk);
            no = (TextView) findViewById(R.id.txtNo);
            yes.setOnClickListener(this);
            no.setOnClickListener(this);

            persianDatePickr.setMinYear(1300);
            Date date = new Date();
            PersianCalendar persianCalendar = new PersianCalendar(date.getTime());


            persianDatePickr.setMaxYear(persianCalendar.getPersianYear());
            persianDatePickr.setOnDateChangedListener(new PersianDatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(int newYear, int newMonth, int newDay) {
                    txtDate.setText(Utils.getSimpleDate(persianDatePickr.getDisplayDate()));
                    birthday = persianDatePickr.getDisplayDate();
                }
            });
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txtOk:
                    persianDatePickr.getDisplayDate();
                    dismiss();
                    break;
                case R.id.txtNo:
                    dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }
    }
}
