package tools;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;

import java.util.Date;

import tools.widget.PersianDatePicker;

/**
 * Created by h.vahidimehr on 13/06/2017.
 */

public class CustomDialogDate extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public TextView yes, no;
    PersianDatePicker persianDatePickr;
    Date date;

    public CustomDialogDate(Activity a) {
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
        date = new Date();
        persianDatePickr.setOnDateChangedListener(new PersianDatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(int newYear, int newMonth, int newDay) {
                date= persianDatePickr.getDisplayDate();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtOk:
                if( dialogDatePick != null ){
                    dialogDatePick.finish(date);
                }
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


    OnDialogDatePick dialogDatePick;
    public interface OnDialogDatePick{
        void finish(Date result);
    }

    public void setDialogDateResult(OnDialogDatePick dialogDatePick){
        this.dialogDatePick = dialogDatePick;
    }
}

