package sg.edu.nyp.silvervitality.MedBook.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


import sg.edu.nyp.silvervitality.MedBook.AppActivity5;
import sg.edu.nyp.silvervitality.R;

/**
 * Created by Student on 11/7/2014.
 */
public class DisplayHistory extends Activity {

    int from_Where_I_Am_Coming = 0;
    private SQLiteHistory mydb ;
    TextView condition ;
    TextView date;
    int id_To_Update = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_history);
        condition = (TextView) findViewById(R.id.editTextCondition);

        date = (TextView) findViewById(R.id.editTextDate);



        mydb = new SQLiteHistory(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getHistory(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String cond = rs.getString(rs.getColumnIndex(SQLiteHistory.KEY_CONDITION));
                String dat = rs.getString(rs.getColumnIndex(SQLiteHistory.KEY_DATE));

                if (!rs.isClosed())
                {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                condition.setText((CharSequence)cond);
                condition.setFocusable(false);
                condition.setClickable(false);

                date.setText((CharSequence)dat);
                date.setFocusable(false);
                date.setClickable(false);


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
                getMenuInflater().inflate(R.menu.display_history, menu);
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
            case R.id.Edit_History:
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                condition.setEnabled(true);
                condition.setFocusableInTouchMode(true);
                condition.setClickable(true);

                date.setEnabled(true);
                date.setFocusableInTouchMode(true);
                date.setClickable(true);




                return true;

            case R.id.Delete_History:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteHistory)
                        .setPositiveButton(R.string.agree, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteHistory(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),AppActivity5.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.disagree, new DialogInterface.OnClickListener() {
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
                if(mydb.updateHistory(id_To_Update, condition.getText().toString(), date.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),AppActivity5.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(mydb.addHistory(condition.getText().toString(), date.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),AppActivity5.class);
                startActivity(intent);
            }
        }
    }


}
