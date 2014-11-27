package sg.edu.nyp.silvervitality.Safety;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.util.Scanner;

import sg.edu.nyp.silvervitality.R;


public class MyActivity extends Activity {

    private String phoneNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my);

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);

        if(sp.contains("phoneNumber"))
        {
            phoneNumber = sp.getString("phoneNumber", phoneNumber);
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(MyActivity.this);

            alert.setTitle("Please type in your phone number");

            final EditText input = new EditText(MyActivity.this);
            alert.setView(input);

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("phoneNumber", input.getText().toString());
                    editor.commit();
                    Toast.makeText(getApplicationContext(),"Your phone number has been saved as " + input.getText().toString(),
                            Toast.LENGTH_LONG).show();
                }
            });


            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

            alert.show();
        }

    }

}
