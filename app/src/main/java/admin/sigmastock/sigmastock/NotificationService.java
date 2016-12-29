package admin.sigmastock.sigmastock;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by HuyPD on 10/20/2016.
 */
public class NotificationService extends IntentService {
    public NotificationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
       /* if (Intent.BOOT_COMPLETED.equals(action)) {
            //write your code to process BOOT_COMPLETED intent here
        }
        else if(Intent.SMS_RECEIVED.equals(action)) {
            //Write your code for processing SMS intent here
        }*/
    }
}
