package com.mgckaled.sqlitebasicapp;

// Purpose: connection, queries, commands

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {

    // Class Attributes
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    // Constructor
    public DBManager(Context c) {
        this.context = c;
    }

    // Class Methods
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context); // assing instance of DatabaseHelper class
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close(); // inheritage SQLiteOpenHelper method -> close()
    }

    public void insert(String name, String desc) {
        // Create a instance o ContentValues class to properly manage set of values
        ContentValues contentValues = new ContentValues();
        // put() --> ContentValues class method.
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    // Create a public cursor:
    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper._ID,
                DatabaseHelper.SUBJECT, DatabaseHelper.DESC};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);

        int i = database.update(DatabaseHelper.TABLE_NAME,
                contentValues, DatabaseHelper._ID +
                        " = " + _id, null);

        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper._ID +
                        " = " + _id, null);
    }

}