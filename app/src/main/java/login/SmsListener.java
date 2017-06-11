
package login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.coinpany.core.android.widget.Utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsListener extends BroadcastReceiver {

    private SharedPreferences preferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
//            if (!Utils.isWaitingForSms()) {
//                return;
//            }
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs;
            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    String wholeString = "";
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        wholeString += msgs[i].getMessageBody();
                    }

                    try {
                        Pattern pattern = Pattern.compile("[0-9]+");
                        final Matcher matcher = pattern.matcher(wholeString);
                        if (matcher.find()) {
                            String str = matcher.group(0);
//                            if (str.length() >= 3) {
//                                AndroidUtilities.runOnUIThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        CallbackCenter.getInstance().notifyObservers(CallbackCenter.didReceiveSmsCode, matcher.group(0));
//                                    }
//                                });
//                            }
                        }
                    } catch (Throwable e) {
                        Log.e("tmessages", e.getMessage());
                    }

                } catch (Throwable e) {
                    Log.e("tmessages", e.getMessage());
                }
            }
        }
    }
}
