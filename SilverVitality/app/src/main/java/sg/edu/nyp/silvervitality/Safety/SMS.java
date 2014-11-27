package sg.edu.nyp.silvervitality.Safety;

import android.telephony.SmsManager;

public class SMS {

    protected void sendSMSMessage(String phone, String address) {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phone, null, address, null, null);
        System.out.println(phone);
        System.out.println(address);
    }
}
