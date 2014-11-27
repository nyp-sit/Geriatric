package sg.edu.nyp.silvervitality.Safety;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.Switch;
import android.graphics.Color;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;
import android.widget.SeekBar;
import android.os.CountDownTimer;
import android.location.Geocoder;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.location.Address;
import android.telephony.SmsManager;
import android.media.AudioManager;

import sg.edu.nyp.silvervitality.GPS;

import sg.edu.nyp.silvervitality.R;

/**
 * Created by Student on 11/26/2014.
 */
public class safetyActivity extends Activity implements SensorEventListener {
    private float mLastX, mLastY, mLastZ;
    private boolean mInitialized;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private final float NOISE = (float) 2.0;

    private TextView tvX, tvY, tvZ;
    private TextView results, Sensitivity;
    private ImageButton imageButton1, imageButton2, imageButton3;
    private Switch switch1, switch2, switch4;
    private SeekBar seekBar;

    private int progress;
    private float a, b, c;
    private final Context context = this;
    private boolean confirmed = false;
    private boolean finished = false;
    private boolean bswitch1, bswitch2, bswitch4;
    AlertDialog.Builder popDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my1);
        mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch4 = (Switch) findViewById(R.id.switch4);
        Sensitivity = (TextView) findViewById(R.id.textView3);
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);

        progress = sp.getInt("progress", progress);

        a = sp.getFloat("a", a);
        b = sp.getFloat("b", b);
        c = sp.getFloat("c", c);

        if (a <= 0 || b <= 0 || c <= 0) {
            a = 12;
            b = 16;
            c = 14;
        }
        Sensitivity.setTextColor(Color.parseColor("#ffff4444"));
        Sensitivity.setText("X : " + Float.toString(a) + " " + "Y: " + Float.toString(b) + " " + "Z: " + Float.toString(c));

        bswitch1 = sp.getBoolean("bswitch1", bswitch1);
        bswitch2 = sp.getBoolean("bswitch2", bswitch2);
        bswitch4 = sp.getBoolean("bswitch4", bswitch4);

        switch1.setChecked(bswitch1);
        switch2.setChecked(bswitch2);
        switch4.setChecked(bswitch4);

        addListenerOnButton();
        addListenerOnButtonIncrease();
        addListenerOnButtonDecrease();
        addSwitchListener1();
        addSwitchListener2();
        addSwitchListener4();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        tvX = (TextView) findViewById(R.id.x_axis);
        tvY = (TextView) findViewById(R.id.y_axis);
        tvZ = (TextView) findViewById(R.id.z_axis);
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if (!mInitialized) {
            mLastX = x;
            mLastY = y;
            mLastZ = z;
            tvX.setText("0.0");
            tvY.setText("0.0");
            tvZ.setText("0.0");
            mInitialized = true;
        } else {
            float deltaX = Math.abs(mLastX - x);
            float deltaY = Math.abs(mLastY - y);
            float deltaZ = Math.abs(mLastZ - z);
            if (deltaX < NOISE) deltaX = (float) 0.0;
            if (deltaY < NOISE) deltaY = (float) 0.0;
            if (deltaZ < NOISE) deltaZ = (float) 0.0;
            mLastX = x;
            mLastY = y;
            mLastZ = z;
            tvX.setText(Float.toString(deltaX));
            tvY.setText(Float.toString(deltaY));
            tvZ.setText(Float.toString(deltaZ));
            alert(deltaX, deltaY, deltaZ);
            System.out.println(deltaX);
        }
    }

    public void alert(float X, float Y, float Z) {
        if (X > a && Y > b && Z > c) {

            onPause();

            results = (TextView) findViewById(R.id.results);
            results.setText("Results: Fall Detected!");
            results.setTextColor(Color.parseColor("#ffff4444"));

            phase1();
        }
    }

    public void phase1() {
        final MyCounter timer = new MyCounter(3000, 1000);
        confirmed = false;
        timer.start();
        ShowDialog();
    }

    public void phase2() {
        if (confirmed == false) {
            sendSmsByManager();
        }

    }

    public void ShowDialog() {
        final SeekBar seek = new SeekBar(this);
        seek.setMax(100);
        popDialog = new AlertDialog.Builder(this);

        popDialog.setTitle("You have 5 seconds to slide");
        popDialog.setView(seek);
        popDialog.setCancelable(false);

        final AlertDialog dialog = popDialog.create();
        dialog.show();

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 75) {
                    onResume();
                    resume();
                    confirmed = true;
                    dialog.dismiss();
                    exitApp();
                }
            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }

    public void emergencyCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + "6591688466"));
        startActivity(callIntent);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_CALL);
        audioManager.setSpeakerphoneOn(true);
        onResume();
    }

    public void sendSmsByManager() {
        GPS gps = new GPS(this);
        String lat = Double.toString(gps.getLatitude());
        String lon = Double.toString(gps.getLongitude());
        try {
            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+6591688466",
                    null,
                    getMyLocationAddress(),
                    null,
                    null);
            smsManager.sendTextMessage("+6591688466",
                    null,
                    lat + "," + lon,
                    null,
                    null);
            Toast.makeText(getApplicationContext(), "Your sms has successfully sent!",
                    Toast.LENGTH_LONG).show();
            emergencyCall();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Your sms has failed...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void resume() {
        results.setTextColor(Color.parseColor("#ff000000"));
        results.setText("Results:");
        onResume();
    }

    public void addListenerOnButton() {

        imageButton1 = (ImageButton) findViewById(R.id.imageButton2);

        imageButton1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Settings Saved", Toast.LENGTH_SHORT).show();
                SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("progress", progress);
                editor.putFloat("a", a);
                editor.putFloat("b", b);
                editor.putFloat("c", c);
                editor.putBoolean("bswitch1", bswitch1);
                editor.putBoolean("bswitch2", bswitch2);
                editor.putBoolean("bswitch4", bswitch4);
                editor.commit();
            }

        });

    }

    public void addListenerOnButtonIncrease() {

        imageButton2 = (ImageButton) findViewById(R.id.imageButton3);

        imageButton2.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                if (a <= 2 || b <= 6 || c <= 4) {
                    Toast.makeText(getApplicationContext(), "Minimum Threshold Reached", Toast.LENGTH_SHORT).show();
                } else {
                    a = a - 2;
                    b = b - 2;
                    c = c - 2;
                    Sensitivity.setText("X : " + Float.toString(a) + " " + "Y: " + Float.toString(b) + " " + "Z: " + Float.toString(c));
                }
            }

        });

    }

    public void addListenerOnButtonDecrease() {

        imageButton2 = (ImageButton) findViewById(R.id.imageButton);

        imageButton2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if (a >= 26 || b >= 34 || c >= 32) {
                    Toast.makeText(getApplicationContext(), "Maximum Threshold Reached", Toast.LENGTH_SHORT).show();
                } else {
                    a = a + 2;
                    b = b + 2;
                    c = c + 2;
                    Sensitivity.setText("X : " + Float.toString(a) + " " + "Y: " + Float.toString(b) + " " + "Z: " + Float.toString(c));
                }
            }

        });

    }

    public void addSwitchListener1() {

        switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    bswitch1 = true;
                } else {
                    bswitch1 = false;
                }
            }
        });
    }

    public void addSwitchListener2() {

        switch2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    bswitch2 = true;
                } else {
                    bswitch2 = false;
                }
            }
        });
    }

    public void addSwitchListener4() {

        switch4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    bswitch4 = true;
                } else {
                    bswitch4 = false;
                }
            }
        });
    }

    public void exitApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public class MyCounter extends CountDownTimer {

        public MyCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {
            phase2();
        }

        public void onTick(long millisUntilFinished) {
            System.out.println("Timer  : " + (millisUntilFinished / 1000));
        }
    }

    public String getMyLocationAddress() {

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        String address = null;

        try {
            GPS gps = new GPS(this);

            List<Address> addresses = geocoder.getFromLocation(gps.getLatitude(), gps.getLongitude(), 1);

            if (addresses != null) {

                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();

                for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
                }
                address = strAddress.toString();

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return address;
    }
}
