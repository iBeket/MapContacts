package com.example.milos.mapcontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Milos on 25-Jul-17.
 */

public class SglHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "phoneBook";
    private static final String TABLE_CONTACTS = "contacts";

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL_ADDRESS = "email_address";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";


    public SglHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_NAME + " TEXT,"
                + KEY_EMAIL_ADDRESS + " TEXT, " + KEY_LATITUDE + " TEXT, " + KEY_LONGITUDE + " TEXT " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    void addContact(InfoModel infoModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, infoModel.getName_()); // Contact Name
        values.put(KEY_EMAIL_ADDRESS, infoModel.getEmailAddress_()); // Contact Phone
        values.put(KEY_LATITUDE, infoModel.getLatitude_());
        values.put(KEY_LONGITUDE, infoModel.getLongitude_());
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

  //  Getting single item
    public InfoModel getSingleItem(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {KEY_NAME}, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        try {
            InfoModel infomodel = new InfoModel(cursor.getString(0));
            // return single item
            return infomodel;
        } catch (Exception e){
            return null;
        }
    }

    // Getting All Contacts
    public List<InfoModel> getAllInfo() {
        List<InfoModel> contactList = new ArrayList<InfoModel>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                InfoModel information = new InfoModel();
                // contactPhone.setID(Integer.parseInt(cursor.getString(0)));
                information.setName_(cursor.getString(0));
                information.setEmailAddress_(cursor.getString(1));
                information.setLatitude_(cursor.getString(2));
                information.setLongitude_(cursor.getString(3));

                // Adding contact to list
                contactList.add(information);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}


