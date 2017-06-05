package tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by h.vahidimehr on 03/06/2017.
 */

public class SmsListener extends BroadcastReceiver {

    private SharedPreferences preferences;
    final SmsManager sms = SmsManager.getDefault();


    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the object of SmsManager
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                    String substring = message.substring(Math.max(message.length() - 5, 0));
                    int duration = Toast.LENGTH_LONG;

//                    Intent intents = new Intent();
//                    intents.putExtra("code",substring);
//                    intents.setAction("com.tutorialspoint.CUSTOM_INTENT");

                    Intent inn = new Intent("android.intent.action.SmsReceiver").putExtra("incomingSms", message);
                    inn.putExtra("incomingPhoneNumber", substring);
                    context.sendBroadcast(inn);

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }

}


