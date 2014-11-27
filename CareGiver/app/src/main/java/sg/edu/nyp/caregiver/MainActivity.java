package sg.edu.nyp.caregiver;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {


    private WebView mWebView;
    public  int i = 0;
    public Timer timer;
    public Context context;


    //NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // Notifications part
        // Timer ...... (Every 10 seconds) getNotifications
        Handler m = new Handler();
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {




//=======================================================================================
                // Start Massive Code Here:
                //
                //
                //
                //
                URLConnection conn = null;
                URL url = null;


                try {
                    url = new URL("http://172.20.131.228/PGCS/ReceiveNotifications.jsp");
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
                OutputStreamWriter writer=null;
                try {
                    writer = new OutputStreamWriter(conn.getOutputStream());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String line;
                BufferedReader reader= null;
                try {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                String output = "";

                try {
                    while ((line = reader.readLine()) != null) {output += line.toString();}
                } catch (IOException e) {e.printStackTrace();}

                try {
                    writer.close();
                } catch (IOException e) { e.printStackTrace();}
                try {
                    reader.close();
                } catch (IOException e) {e.printStackTrace(); }


                System.out.println("Sending 'POST' request to URL : " + url);
                System.out.println("Connect Timeout : " + conn.getConnectTimeout());
                System.out.println("Content Length  : " + conn.getContentLength());
                System.out.println("Content Type    : " + conn.getContentType());


                System.out.println("\n");
                System.out.println("===================================================");
                System.out.println(output);
                System.out.println("===================================================");





                if(output.equals("NIL")){
                    System.out.println("No Updates");
                }


                else {
                    // Trigger Notification here
                    triggerNotification("Silver Vitality",output);

                }
























            }
        }, 0, 50000);







        // Web Part
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.loadUrl("http://172.20.131.228/PGCS/Mobile/Home.jsp");

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());






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
