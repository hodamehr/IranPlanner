package tools;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import tools.widget.PersianDatePicker;

/**
 * Created by Hoda on 18/02/2017.
 */

public class CustomDialogTravele extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public TextView yes, no;
    PersianDatePicker persianDatePickr;

    public CustomDialogTravele(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_date_travel);
        persianDatePickr= (PersianDatePicker) findViewById(R.id.travelDate);
        yes = (TextView) findViewById(R.id.txtOk);
        no = (TextView) findViewById(R.id.txtNo);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtOk:
                 persianDatePickr.getDisplayDate();
                dismiss();
//                break;
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
