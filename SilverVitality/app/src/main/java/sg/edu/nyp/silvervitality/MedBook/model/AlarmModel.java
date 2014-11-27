package sg.edu.nyp.silvervitality.MedBook.model;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import sg.edu.nyp.silvervitality.R;

/**
 * Created by Kasdeyae on 11/19/2014.
 */
public class AlarmModel {

    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRDIAY = 5;
    public static final int SATURDAY = 6;

    public long id = -1;
    public int timeHour;
    public int timeMinute;
    private boolean repeatingDays[];
    public boolean repeatWeekly;
    public Uri alarmTone;
    public String name;
    public boolean isEnabled;

    public AlarmModel() {
        repeatingDays = new boolean[7];
    }

    public void setRepeatingDay(int dayOfWeek, boolean value) {
        repeatingDays[dayOfWeek] = value;
    }

    public boolean getRepeatingDay(int dayOfWeek) {
        return repeatingDays[dayOfWeek];
    }

}