package tools.widget;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;


/**
 * 8/3/2016
 *
 * @author Hoda
 */
public class ConfirmAlertDialogBuilder extends AlertDialog.Builder {

    public TextView yesBtn, noBtn;

    public ConfirmAlertDialogBuilder(Activity activity,String title, String message) {
        super(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert, null);
        TextView tv = (TextView) dialogView.findViewById(R.id.alertDescription);
        TextView titleView= (TextView) dialogView.findViewById(R.id.txtAlertTitle);
        titleView.setText(title);
        tv.setText(message);
        setView(dialogView);
        noBtn = (TextView) dialogView.findViewById(R.id.txtNo);
        yesBtn = (TextView) dialogView.findViewById(R.id.txtOk);
    }
}
