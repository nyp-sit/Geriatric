package sg.edu.nyp.silvervitality.MedBook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sg.edu.nyp.silvervitality.R;

/**
 * Created by Student on 10/28/2014.
 * Shum, if you see this, you've found the code.
 * I've removed a line of code. Happy Compiling
 */
public class MedBook extends Activity{
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        addListenerOnButton1();
        addListenerOnButton2();
        addListenerOnButton3();

    }



    public void addListenerOnButton1() {


        button = (Button) findViewById(R.id.btn_viewMed);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), AlarmListActivity.class);
                startActivity(intent);

            }
        });
    }




    public void addListenerOnButton2() {


        button = (Button) findViewById(R.id.btn_doctorNotes);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), AppActivity4.class);
                startActivity(intent);

            }
        });
    }

    public void addListenerOnButton3() {


        button = (Button) findViewById(R.id.btn_medHistory);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), AppActivity5.class);
                startActivity(intent);

            }
        });
    }
}

