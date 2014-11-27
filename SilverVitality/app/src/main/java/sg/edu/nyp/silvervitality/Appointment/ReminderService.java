package sg.edu.nyp.silvervitality.Appointment;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import sg.edu.nyp.silvervitality.R;


/**
 * Created by Student on 11/24/2014.
 */
public class ReminderService extends WakeReminderIntentService {

    public ReminderService() {
        super("ReminderService");
    }

    @Override
    void doReminderWork(Intent intent) {
        Log.d("ReminderService", "Doing work.");
        Long rowId = intent.getExtras().getLong(RemindersDbAdapter.KEY_ROWID);

        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(this, ReminderEditActivity.class);
        notificationIntent.putExtra(RemindersDbAdapter.KEY_ROWID, rowId);

        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        Notification note=new Notification(android.R.drawable.stat_sys_warning, getString(R.string.notify_new_task_message), System.currentTimeMillis());
        note.setLatestEventInfo(this, getString(R.string.notify_new_task_title), getString(R.string.notify_new_task_message), pi);
        note.defaults |= Notification.DEFAULT_SOUND;
        note.flags |= Notification.FLAG_AUTO_CANCEL;

        // An issue could occur if user ever enters over 2,147,483,647 tasks. (Max int value).
        // I highly doubt this will ever happen. But is good to note.
        int id = (int)((long)rowId);
        mgr.notify(id, note);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        // Start Massive Code Here:
        //
        //
        //
        //
        URLConnection conn = null;
        URL url = null;
        String urlParameters = "postal=";


        try {

            // When alarm is triggered
            url = new URL("http://172.20.131.228/PGCS/ReceiveNewAppointmentGeoLocation.jsp");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            conn = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setDoOutput(true);




        try {
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(urlParameters);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }




        String line = "";



        String output = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {output += line.toString();}

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.close();

            reader.close();



        } catch (Exception e) {e.printStackTrace();}



        System.out.println("Sending 'POST' request to URL : " + url);
        System.out.println("Connect Timeout : " + conn.getConnectTimeout());
        System.out.println("Content Length  : " + conn.getContentLength());
        System.out.println("Content Type    : " + conn.getContentType());


        System.out.println("===================================================");
        System.out.println(output);
        System.out.println("===================================================");


    }
}
