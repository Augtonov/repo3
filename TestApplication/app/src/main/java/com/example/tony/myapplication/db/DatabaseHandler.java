package com.example.tony.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tony.myapplication.ui.pojo.frag1.Item;
import com.example.tony.myapplication.ui.pojo.frag1.StackOverflowQuestions;

/**
 * Created by tony on 12/31/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String MYDATABASE_NAME = "sample_app";
    public static final int MYDATABASE_VERSION = 1;

    SQLiteDatabase db;



    /******************firat table**************************************/
    public static final String TABLE_LIST = "fragment_list";
    public static final String KEY_ID = "_id";
    public static final String KEY_URL = "_url";
//    public static final String KEY_TITLE = "_title";
    public static final String KEY_CONTENT = "_content";
    public static final String KEY_EXPIRY_TIME = "_expiry_time";
//    public static final String KEY_

    public DatabaseHandler(Context context) {
        super(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SCRIPT_CREATE_TABLE_LIST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    /*********************Table Creation************************************/

    private static final String SCRIPT_CREATE_TABLE_LIST = "create table "+TABLE_LIST
            +" ("+KEY_ID+" integer primary key autoincrement, "
            +KEY_URL+" text not null, "+KEY_EXPIRY_TIME+" text not null, "+KEY_CONTENT+" text not null);";


    public void open() {
        db = this.getWritableDatabase();
    }

    public void close() {
        db.close();
    }


    /***************************Inserting Methods**************************************************/

    public void insertValueToTableList(String stackOverFlowObject, String url, String expiryTime) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_URL, url);
        cv.put(KEY_CONTENT, stackOverFlowObject);
        cv.put(KEY_EXPIRY_TIME, expiryTime);
        db.insert(TABLE_LIST, null, cv);

    }


    /**************************************getValues***********************************/

    public boolean ifURLexists(String url) {
        boolean isExists = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String column[] = new String[]{KEY_URL};
        Cursor cursor = db.query(TABLE_LIST, column, KEY_URL+"="+"'"+url+"'", null, null, null, null);

//        int index_content = cursor.getColumnIndex(KEY_URL);

        if (cursor.getCount() > 0) {
            isExists = true;
        }

        /*for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            if (cursor.getString(index_content).contentEquals(url)) {
                isExists = true;
                break;
            }
        }*/

        return isExists;
    }

    public boolean isNeedNetWorkFetch(String url) {
        boolean isExists = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String column[] = new String[]{KEY_EXPIRY_TIME};
        Cursor cursor = db.query(TABLE_LIST, column, KEY_URL+"="+"'"+url+"'", null, null, null, null);

//        int index_content = cursor.getColumnIndex(KEY_URL);

        if (cursor.getCount() > 0) {
            isExists = true;
        }

        /*for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            if (cursor.getString(index_content).contentEquals(url)) {
                isExists = true;
                break;
            }
        }*/

        return isExists;
    }

    public String getExpiryTime(String url) {

        String string = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String column[] = new String[]{KEY_URL, KEY_EXPIRY_TIME};
        Cursor cursor = db.query(TABLE_LIST, column, KEY_URL+" = '"+url+"'", null, null, null, null);

        int index_contentExpiryTime = cursor.getColumnIndex(KEY_EXPIRY_TIME);


        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            string = cursor.getString(index_contentExpiryTime);

        }

        return string;
    }

    public String getJsonTableList(String url) {

        String string = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String column[] = new String[]{KEY_URL, KEY_CONTENT};
        Cursor cursor = db.query(TABLE_LIST, column, KEY_URL+" = '"+url+"'", null, null, null, null);

        int index_contentUrl = cursor.getColumnIndex(KEY_URL);
        int index_contentJson = cursor.getColumnIndex(KEY_CONTENT);


        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            string = cursor.getString(index_contentJson);

        }

        return string;
    }

    public void update(String url, String stackoverflowquestions, String expiryTime) {
//        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_URL, url);
        cv.put(KEY_CONTENT, stackoverflowquestions);
        cv.put(KEY_EXPIRY_TIME, expiryTime);

        db.update(TABLE_LIST, cv, KEY_URL+" = "+"'"+url+"'", null);
//        db.close();


    }


}
