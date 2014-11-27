package sg.edu.nyp.silvervitality.MedBook.model;

/**
 * Created by Student on 11/7/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SQLiteHistory extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "HistoryDB";
    // Doctor table name
    private static final String TABLE_HISTORY = "history";



    public SQLiteHistory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_HISTORY_TABLE = "CREATE TABLE history ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "condition TEXT, "+
                "date TEXT )";

        // create books table
        db.execSQL(CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS history");

        // create fresh books table
        this.onCreate(db);
    }

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Doctors Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_CONDITION = "note";
    public static final String KEY_DATE = "date";

    private static final String[] COLUMNS = {KEY_ID,KEY_CONDITION,KEY_DATE};

    public boolean addHistory(String condition, String date){


        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("condition", condition);
        values.put("date", date);
        // 3. insert
        db.insert("history", null, values);
        return true;

    }



    public Cursor getHistory(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.rawQuery("Select * from history where id ="+id+"", null);
        return cursor;
    }

    public ArrayList getAllHistory() {

        ArrayList arrayList = new ArrayList();

        // 1. build the query
        String query = "SELECT  * FROM history";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery(query, null);


        cursor1.moveToFirst();
        while(cursor1.isAfterLast() == false) {
            arrayList.add(cursor1.getString(cursor1.getColumnIndex(KEY_CONDITION)));
            cursor1.moveToNext();
        }

        return arrayList;
    }

    public boolean updateHistory(int id, String condition, String date) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("condition", condition); // get Note
        values.put("date", date); // get date

        db.update("history", values, "id = ? ", new String[] { Integer.toString(id) } );
        return true;

    }

    public int deleteHistory(int id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("history",
                "id = ? ",
                new String[] { Integer.toString(id) });

    }
}