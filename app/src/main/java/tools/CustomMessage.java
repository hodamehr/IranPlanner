package tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;

/**
 * Created by h.vahidimehr on 13/06/2017.
 */

public class CustomMessage extends Dialog implements
        View.OnClickListener {

    public Activity activity;
    public Dialog d;
    public TextView no;
    public TextView yes;
    String title;


    public CustomMessage(Activity a, String title) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        bara inke keybord bala nayad
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_alert);
        TextView textView = (TextView) findViewById(R.id.alertDescription);
        textView.setText(title);
        no = (TextView) findViewById(R.id.txtNo);
        no.setOnClickListener(this);
        yes = (TextView) findViewById(R.id.txtOk);
        yes.setOnClickListener(this);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            WifiManager wifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (!wifiManager.isWifiEnabled()) {

                Log.e("Dd","ddd");
            } else {
                //write your code for any kind of network calling to fetch data
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtOk:
                activity.startActivityForResult(new Intent(
                        Settings.ACTION_WIFI_SETTINGS), 0);


//                if( dialogPick != null ){

//                    dialogPick.finish();
//                }

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


    OnDialogPick dialogPick;

    public interface OnDialogPick {
        void finish(/*return somthing*/);
    }

    public void setDialogResult(OnDialogPick dialogDatePick) {
        this.dialogPick = dialogPick;
    }
}

