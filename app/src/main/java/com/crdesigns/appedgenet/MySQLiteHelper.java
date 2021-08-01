package com.crdesigns.appedgenet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "UsersDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create table
        String CREATE_TABLE = "CREATE TABLE tbaUsers ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT, "+
                "password TEXT," +
                "points TEXT)";
        // create table
        db.execSQL(CREATE_TABLE);
        String CREATE_TABLE_I = "CREATE TABLE tbaInterests ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT)";
        db.execSQL(CREATE_TABLE_I);

        String CREATE_TABLE_IU = "CREATE TABLE tbaInterestsUsers ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "iduser TEXT, interest TEXT)";
        db.execSQL(CREATE_TABLE_IU);

        String CREATE_TABLE_VIDEOUSERS = "CREATE TABLE tbaVideoUsers ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "iduser TEXT, url TEXT, url_edge TEXT, watched TEXT)";
        db.execSQL(CREATE_TABLE_VIDEOUSERS);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS tbaUsers");
        // create fresh  table
        this.onCreate(db);
    }

    private static final String TABLE_USERS = "tbaUsers";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_POINTS = "points";
    private static final String[] COLUMNS = {KEY_ID,KEY_EMAIL,KEY_PASSWORD,KEY_POINTS};

    private static final String TABLE_INTERESTS = "tbaInterests";
    private static final String KEY_ID_I = "id";
    private static final String KEY_NAME = "name";
    private static final String[] COLUMNS_I = {KEY_ID,KEY_NAME};

    private static final String TABLE_INTERESTSUSERS = "tbaInterestsUsers";
    private static final String KEY_ID_IU = "id";
    private static final String KEY_IDUSER = "iduser";
    private static final String KEY_INTEREST = "interest";

    private static final String TABLE_VIDEOUSERS = "tbaVideoUsers";
    private static final String KEY_ID_VU = "id";
    private static final String KEY_IDUSER_VU = "iduser";
    private static final String KEY_URL = "url";
    private static final String KEY_URL_EDGE = "url_edge";
    private static final String KEY_WATCHED = "watched";

    public static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public void addUsers(Users users){
        Log.d("addUsers", users.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, users.getEmail()); // get title
        values.put(KEY_PASSWORD, users.getPassword()); // get author
        values.put(KEY_POINTS, users.getPoints()); // get author
        db.insert(TABLE_USERS, null, values); // key/value -> keys = column names/ values = column values
        db.close();
    }
    public List<String> getUser(String email, String password) {
        List<String> labels = new ArrayList<String>();
        // 1. build the query
        String query = "SELECT id, email, points FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = '" + email + "' and " + KEY_PASSWORD + "='" + password + "'";
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0) + "|" + cursor.getString(1) + "|" + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return labels;
    }
    public List<String> getPoints(String idUser) {
        List<String> labels = new ArrayList<String>();
        // 1. build the query
        String query = "SELECT points FROM " + TABLE_USERS + " WHERE " + KEY_ID + " = " + idUser;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return labels;
    }
    public String updatePoints(String idUser, String points) {
        String query = "UPDATE  " + TABLE_USERS + " Set points = '" + points + "' WHERE " + KEY_ID + " = " + idUser;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);
        //Cursor cursor = db.rawQuery(query, null);
        //cursor.close();
        db.close();
        return "";
    }

    public void addInterests(Interests interests){
        Log.d("addInterests", interests.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, interests.getName()); // get title
        db.insert(TABLE_INTERESTS, null, values); // key/value -> keys = column names/ values = column values
        db.close();
    }

    public List<String> getInterests() {
        List<String> labels = new ArrayList<String>();
        // 1. build the query
        String query = "SELECT name FROM " + TABLE_INTERESTS;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return labels;
    }

    public void addInterestsUser(InterestsUsers interests){
        Log.d("addInterestsUser", interests.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_INTEREST, interests.getInterest()); // get title
        values.put(KEY_IDUSER, interests.getIduser()); // get title
        db.insert(TABLE_INTERESTSUSERS, null, values); // key/value -> keys = column names/ values = column values
        db.close();
    }

    public List<String> getInterestsUser(String idUser, String interest) {
        List<String> labels = new ArrayList<String>();
        // 1. build the query
        String query = "SELECT interest FROM " + TABLE_INTERESTSUSERS + " WHERE " + KEY_IDUSER + "=" + idUser + " AND " + KEY_INTEREST + "='" + interest + "'";
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return labels;
    }

    public void addVideo(VideoUsers videoUsers){
        Log.d("addvideoUsers", videoUsers.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IDUSER_VU, videoUsers.getIduser());
        values.put(KEY_URL, videoUsers.getUrl());
        values.put(KEY_URL_EDGE, videoUsers.getUrl_edge());
        values.put(KEY_WATCHED, videoUsers.getWatched());
        db.insert(TABLE_VIDEOUSERS, null, values);
        db.close();
    }


    public List<String> getVideo(String idUser) {
        List<String> labels = new ArrayList<String>();
        // 1. build the query
        String query = "SELECT url, url_edge FROM " + TABLE_VIDEOUSERS + " WHERE " + KEY_IDUSER_VU + " = " + idUser + " AND " + KEY_WATCHED +  "= '0'" ;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0) + "|" + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return labels;
    }

    public String updateViewed(String idUser, String url) {
        String query = "UPDATE  " + TABLE_VIDEOUSERS + " Set watched = '1' WHERE " + KEY_IDUSER + " = '" + idUser + "' AND " + KEY_URL + "= '" + url + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);
        db.close();
        return "";
    }



}
