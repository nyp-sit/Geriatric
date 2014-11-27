package sg.edu.nyp.silvervitality.MedBook.model;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.List;

import sg.edu.nyp.silvervitality.R;

/**
 * Created by Kasdeyae on 11/19/2014.
 */
public class AlarmService extends Service {

    public static String TAG = AlarmService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String title = "Eat Medication";
        String body = "Reminder to eat Medication!";
        Intent alarmIntent = new Intent(getBaseContext(), AlarmScreen.class);
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent.putExtras(intent);
        getApplication().startActivity(alarmIntent);

        AlarmManagerHelper.setAlarms(this);
        triggerNotification(title, body);

        return super.onStartCommand(intent, flags, startId);




    }

    private void triggerNotification(String title, String body) {



        //We get a reference to the NotificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String MyText = "Reminder";
        Notification mNotification = new Notification(R.drawable.ic_launcher, MyText, System.currentTimeMillis() );
        //The three parameters are: 1. an icon, 2. a title, 3. time when the notification appears

        String MyNotificationTitle = title;
        String MyNotificationText  = body;

        Intent MyIntent = new Intent(Intent.ACTION_VIEW);
        PendingIntent StartIntent = PendingIntent.getActivity(getApplicationContext(),0,MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        //A PendingIntent will be fired when the notification is clicked. The FLAG_CANCEL_CURRENT flag cancels the pendingintent

        mNotification.setLatestEventInfo(getApplicationContext(), MyNotificationTitle, MyNotificationText, StartIntent);

        int NOTIFICATION_ID = 1;
        notificationManager.notify(NOTIFICATION_ID , mNotification);
        //We are passing the notification to the NotificationManager with a unique id.
    }

}