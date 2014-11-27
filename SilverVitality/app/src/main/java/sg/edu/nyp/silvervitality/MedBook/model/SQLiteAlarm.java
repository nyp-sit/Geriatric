package sg.edu.nyp.silvervitality.MedBook.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Student on 11/18/2014.
 */
public class SQLiteAlarm extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AlarmDB";
    // Doctor table name
    private static final String TABLE_ALARM = "alarm";



    public SQLiteAlarm(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_DOCTOR_TABLE = "CREATE TABLE alarm ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "notification TEXT, "+
                "alarm TEXT," +
                "alarmtime DATETIME )";

        // create books table
        db.execSQL(CREATE_DOCTOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS alarm");

        // create fresh books table
        this.onCreate(db);
    }

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Doctors Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NOTIFICATION = "notification";
    public static final String KEY_ALARM = "alarm";
    public static final String KEY_ALARMTIME = "alarmtime";

    private static final String[] COLUMNS = {KEY_ID,KEY_NOTIFICATION,KEY_ALARM,KEY_ALARMTIME};

    public boolean addAlarm(String notification, String alarm, String alarmtime){


        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("notification", notification);
        values.put("alarm", alarm);
        values.put("alarmtime", alarmtime);
        // 3. insert
        db.insert("alarm", null, values);
        return true;

    }



    public Cursor getAlarm(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.rawQuery("Select * from alarm where id ="+id+"", null);
        return cursor;
    }

    public ArrayList getAllAlarm() {

        ArrayList arrayList = new ArrayList();

        // 1. build the query
        String query = "SELECT  * FROM alarm";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            arrayList.add(cursor.getString(cursor.getColumnIndex(KEY_NOTIFICATION)));
            cursor.moveToNext();
        }

        return arrayList;
    }

    public boolean updateAlarm(int id, String notification, String alarm, String alarmtime) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("notification", notification);
        values.put("alarm", alarm);
        values.put("alarmtime", alarmtime);

        db.update("alarm", values, "id = ? ", new String[] { Integer.toString(id) } );
        return true;

    }

    public int deleteAlarm(int id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("alarm",
                "id = ? ",
                new String[] { Integer.toString(id) });

    }
}
