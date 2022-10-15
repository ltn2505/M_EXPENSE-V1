package com.jovanovic.stefan.sqlitetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "M_Expense.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "trip";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name_trip";
    private static final String COLUMN_DES = "des_trip";
    private static final String COLUMN_DATE = "dot_trip";
    private static final String COLUMN_DESC = "desc_trip";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DES + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_DESC + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropAndRecreate(db);
    }

    private void dropAndRecreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public long add(Trip trip) {
        long insertId;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, trip.getName());
        values.put(COLUMN_DES, trip.getDes());
        values.put(COLUMN_DATE, trip.getDate());
        values.put(COLUMN_DESC, trip.getDesc());

        // Inserting Row
        insertId = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
        return insertId;
    }

    public List<Trip> getAll() {
        final String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        final List<Trip> list = new ArrayList<>();
        final Cursor cursor;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Trip trip = new Trip();
                    trip.setId(cursor.getInt(0));
                    trip.setName(cursor.getString(1));
                    trip.setDes(cursor.getString(2));
                    trip.setDate(cursor.getString(3));
                    trip.setDesc(cursor.getString(4));
                    // Adding object to list
                    list.add(trip);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public long update(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, trip.getName());
        values.put(COLUMN_DES, trip.getDes());
        values.put(COLUMN_DATE, trip.getDate());
        values.put(COLUMN_DESC, trip.getDesc());

        return db.update(TABLE_NAME, values, "_id=?", new String[]{String.valueOf(trip.getId())});
    }

    public long delete(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
    }

    void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM " + TABLE_NAME);
        dropAndRecreate(db);
    }

}
