package docent.example.com.smsforward;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsMonitor extends BroadcastReceiver{

    public static final String TAG = "SmsMonitor";

    public static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received sms");
        Log.d(TAG, intent.getExtras().toString());
        if (!SMS_ACTION.equalsIgnoreCase(intent.getAction())) {
            Log.d(TAG, "Not ours action: " + intent.getAction());
            return;
        }

        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        if (pdus == null) {
            Log.d(TAG, "Sms array is null");
            return;
        }
        SmsMessage[] smsMessages = new SmsMessage[pdus.length];

        for (int i = 0; i < smsMessages.length; i++) {
            smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], intent.getExtras().getString("format"));
        }
        SmsManager aDefault = SmsManager.getDefault();
//        aDefault.sendTextMessage();
    }
}
