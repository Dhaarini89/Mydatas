package com.android.example.freeblacklist.storage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FreeDBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                APP_TOKEN + " TEXT," +
                SESSION_TOKEN + " TEXT" +
                TRACK_ID + " TEXT" +  ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addName(app_token : String, track_id : String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()
        // we are inserting our values
        // in the form of key-value pair
        values.put(APP_TOKEN, app_token)
        values.put(TRACK_ID, track_id)
        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase
        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)
        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getAppToken(): String? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase
        // below code returns a cursor to
        // read data from the database
       val sql = "SELECT APP_TOKEN FROM " + TABLE_NAME +"where " +ID_COL + "=1"
        return db.rawQuery(sql,null).toString()

    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "FreeDB"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "blacklist_details"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for app_token column
        val APP_TOKEN = "app_token"

        // below is the variable for session_token column
        val SESSION_TOKEN = "session_token"

        val TRACK_ID = "track_id"

    }
}
