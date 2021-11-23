package com.example.immofind.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImoDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employees_management.db";
    private static final int DATABASE_VERSION = 1;

    public ImoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

     

        // Create a String that contains the SQL statement to create the user table
        String SQL_CREATE_SUJET_TABLE = "CREATE TABLE " + SujetContrat.TABLE_NAME + "("
                + SujetContrat.SujetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SujetContrat.SujetEntry.COLUMN_SUJET_NAME + " VARCHAR(255) NOT NULL, "
                + SujetContrat.SujetEntry.COLUMN_SUJET_VILLE + "  VARCHAR(255) ,"
                + SujetContrat.SujetEntry.COLUMN_SUJET_LOYER + "  INTEGER ,"
                + SujetContrat.SujetEntry.COLUMN_SUJET_DESCRIPTION + "  VARCHAR(250) ,"
                + SujetContrat.SujetEntry.COLUMN_SUJET_IMAGE + "  BLOB "
                + ");";
        
        db.execSQL(SQL_CREATE_SUJET_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    

    public boolean addSujet(String name, String ville, int loyer,  String description, byte[] image) {
        //adds an user entry to user table

        SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
        ContentValues cv = new ContentValues(); //used for inserting an entry


        if (name != null && !name.isEmpty() && !name.trim().isEmpty()) //checks if field is provided if not it is not added in the query
            cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_NAME, name);
        if (ville != null && !ville.isEmpty() && !ville.trim().isEmpty()) // to be edited
            cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_VILLE, ville);
        if (description != null && !description.isEmpty() && !description.trim().isEmpty())
            cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_LOYER, loyer);
        cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_DESCRIPTION, description);

        // no need to check for null as it is required to be provided
        cv.put(SujetContrat.SujetEntry.COLUMN_SUJET_IMAGE, image);
        long flag = db.insert(SujetContrat.TABLE_NAME, null, cv); //returns a flag to indicate succes of insertion

        return flag != -1; //-1 if insert fails

    }

   
    public Cursor getAllSujets() {
        //gets all departments
        SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db

        //specify the columns to be read
        String[] columns = {
                SujetContrat.SujetEntry._ID,
                SujetContrat.SujetEntry.COLUMN_SUJET_NAME,
                SujetContrat.SujetEntry.COLUMN_SUJET_VILLE,
                SujetContrat.SujetEntry.COLUMN_SUJET_LOYER,
                SujetContrat.SujetEntry.COLUMN_SUJET_DESCRIPTION,
                SujetContrat.SujetEntry.COLUMN_SUJET_IMAGE
        };

        String orderBy = SujetContrat.SujetEntry.COLUMN_SUJET_NAME + " ASC "; //order by statement

        //cursor is a table containing the rows returned form the query
        return db.query(SujetContrat.TABLE_NAME, columns, null, null, null, null, orderBy); //don't forget to close the cursor after usage

    }
}
