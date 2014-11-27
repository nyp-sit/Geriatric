package sg.edu.nyp.silvervitality.MedBook.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nyp.silvervitality.MedBook.Doctor;

/**
 * Created by Kasdeyae on 11/3/2014.
 */
public class SQLiteDoctor extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "DoctorDB";
    // Doctor table name
    private static final String TABLE_DOCTOR = "doctor";



    public SQLiteDoctor(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_DOCTOR_TABLE = "CREATE TABLE doctor ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "doctor TEXT, "+
                "note TEXT," +
                "date TEXT )";

        // create books table
        db.execSQL(CREATE_DOCTOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS doctor");

        // create fresh books table
        this.onCreate(db);
    }

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Doctors Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NOTE = "note";
    public static final String KEY_DOCTOR = "doctor";
    public static final String KEY_DATE = "date";

    private static final String[] COLUMNS = {KEY_ID,KEY_NOTE,KEY_DOCTOR,KEY_DATE};

    public boolean addDoctor(String note, String date, String doctor){


        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("note", note);
        values.put("date", date);
        values.put("doctor", doctor);
        // 3. insert
        db.insert("doctor", null, values);
        return true;

    }



    public Cursor getDoctor(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.rawQuery("Select * from doctor where id ="+id+"", null);
        return cursor;
    }

    public ArrayList getAllDoctor() {

        ArrayList arrayList = new ArrayList();

        // 1. build the query
        String query = "SELECT  * FROM doctor";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        cursor.moveToFirst();
            while(cursor.isAfterLast() == false) {
                arrayList.add(cursor.getString(cursor.getColumnIndex(KEY_NOTE)));
                cursor.moveToNext();
            }

        return arrayList;
    }

    public boolean updateDoctor(int id, String note, String date, String doctor) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("note", note); // get Note
        values.put("date", date); // get date
        values.put("doctor", doctor); // get doctor

        db.update("doctors", values, "id = ? ", new String[] { Integer.toString(id) } );
        return true;

    }

    public int deleteDoctor(int id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("doctor",
                "id = ? ",
                new String[] { Integer.toString(id) });

    }
}