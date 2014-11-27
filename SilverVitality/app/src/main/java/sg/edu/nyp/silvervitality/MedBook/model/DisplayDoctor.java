package sg.edu.nyp.silvervitality.MedBook.model;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import sg.edu.nyp.silvervitality.MedBook.AppActivity4;
import sg.edu.nyp.silvervitality.R;

/**
 * Created by Kasdeyae on 11/4/2014.
 */
public class DisplayDoctor extends Activity {

    int from_Where_I_Am_Coming = 0;
    private SQLiteDoctor mydb ;
    TextView note ;
    TextView doctor;
    TextView date;
    int id_To_Update = 0;

    private int year;
    private int month;
    private int day;
    private DatePicker dpResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_doctor);
        note = (TextView) findViewById(R.id.editTextNote);

        date = (TextView) findViewById(R.id.editTextDate);
        doctor = (TextView) findViewById(R.id.editTextDoctor);



        mydb = new SQLiteDoctor(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getDoctor(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String not = rs.getString(rs.getColumnIndex(SQLiteDoctor.KEY_NOTE));
                String dat = rs.getString(rs.getColumnIndex(SQLiteDoctor.KEY_DATE));
                String docto = rs.getString(rs.getColumnIndex(SQLiteDoctor.KEY_DOCTOR));

                if (!rs.isClosed())
                {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                note.setText((CharSequence)not);
                note.setFocusable(false);
                note.setClickable(false);

                date.setText((CharSequence)dat);
                date.setFocusable(false);
                date.setClickable(false);

                doctor.setText((CharSequence) docto);
                doctor.setFocusable(false);
                doctor.setClickable(false);

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_doctor, menu);
            }
            else{
                getMenuInflater().inflate(R.menu.main, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.Edit_Doctor:
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                note.setEnabled(true);
                note.setFocusableInTouchMode(true);
                note.setClickable(true);

                date.setEnabled(true);
                date.setFocusableInTouchMode(true);
                date.setClickable(true);

                doctor.setEnabled(true);
                doctor.setFocusableInTouchMode(true);
                doctor.setClickable(true);


                return true;

            case R.id.Delete_Doctor:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteDoctor)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteDoctor(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),AppActivity4.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view)
    {
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateDoctor(id_To_Update, note.getText().toString(), date.getText().toString(), doctor.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),AppActivity4.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(mydb.addDoctor(note.getText().toString(), date.getText().toString(), doctor.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),AppActivity4.class);
                startActivity(intent);
            }
        }
    }


}
