package sg.edu.nyp.silvervitality;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


import sg.edu.nyp.silvervitality.Appointment.*;
import sg.edu.nyp.silvervitality.MedBook.*;


public class MainActivity extends Activity {
    ImageButton button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        addListenerOnButton();
        addListenerOnButton1();

    }


    public void addListenerOnButton() {


        button = (ImageButton) findViewById(R.id.btn_medbook);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), MedBook.class);
                startActivity(intent);

            }
        });
    }


    public void addListenerOnButton1() {


        button = (ImageButton) findViewById(R.id.btn_appointment);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), ReminderListActivity.class);
                startActivity(intent);

            }
        });
    }
}