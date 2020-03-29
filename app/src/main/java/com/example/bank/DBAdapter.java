package com.example.bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.bank.DBHelper.EMAIL;
import static com.example.bank.DBHelper.JOB;
import static com.example.bank.DBHelper.MONEY;
import static com.example.bank.DBHelper.NAME;
import static com.example.bank.DBHelper.PASSWORD;

public class DBAdapter {

    SQLiteDatabase db;
    DBHelper myhelper;

    public DBAdapter(Context context) {
        myhelper = new DBHelper(context);
        db = myhelper.getWritableDatabase();

    }

    public void insertData(String name, String email, String pass,  String money, String job) {

        ContentValues values = new ContentValues();
        values.put(NAME, name);

        values.put(EMAIL, email);
        values.put(PASSWORD, pass);
        values.put(MONEY, money);
        values.put(JOB, job);

        db.insert(DBHelper.TABLE_NAME, null, values);

        db.close();
    }

    public void updateData(String email, String name, String pass,  String money, String job) {

        ContentValues values = new ContentValues();
        values.put(NAME, name);

        values.put(EMAIL, email);
        values.put(PASSWORD, pass);
        values.put(MONEY, money);
        values.put(JOB, job);

        String[] arrayOfWhere = {email};


        db.update(DBHelper.TABLE_NAME, values, EMAIL + " = ?", arrayOfWhere);
        db.close();
    }

    public String[] getData(String mail) {
        //  final String SQL_SELECT_QUERY = "SELECT * FROM "+ TABLE_USER+" WHERE "+ COLUMN_USER_EMAIL+ "= +"mail;

        db = myhelper.getReadableDatabase();
        String[] columns = {

                JOB,
                MONEY,
                EMAIL,
                PASSWORD,
                NAME};
        String selection = EMAIL + " = ?";

        String[] selectionArgs = {mail};
        Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        String[] buffer = new String[columns.length];
        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String job = cursor.getString(cursor.getColumnIndex(JOB));
            String money = cursor.getString(cursor.getColumnIndex(MONEY));

            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            buffer[0] = email;
            buffer[1] = name;
            buffer[2] = job;
            buffer[3] = money;

            buffer[4] = password;
        }
        cursor.close();
        return buffer;
    }
}