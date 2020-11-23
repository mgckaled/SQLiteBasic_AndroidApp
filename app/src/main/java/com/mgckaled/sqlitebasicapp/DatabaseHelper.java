package com.mgckaled.sqlitebasicapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Our class application must be a child of SQLiteOpenHelper
// many methods will be used from class parent
public class DatabaseHelper extends SQLiteOpenHelper {

    /*-- BEGIN ATTRIBUTES --*/
    /* Table name: private for encapsulation
    * static for assigning this attribute to the class itself
    * final -> CONSTANT  */
    public static final String TABLE_NAME = "COUNTRIES";

    // Table columns:
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";

    // Database information
    static final String DB_NAME = "SQLITE_BASIC_APP.DB";

    // Database Version
    static final int DB_VERSION = 1;

    // Creating Table Query
    private static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT +
            " TEXT NOT NULL, " + DESC + " TEXT);";

    /*-- END ATTRIBUTES --*/

    /*-- BEGIN METHODS --*/
    // Constructor:
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the CREATE TABLE Query --> USE DEFINED ATTRIBUTE
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db); // execute the onCreate Method
    }
}
