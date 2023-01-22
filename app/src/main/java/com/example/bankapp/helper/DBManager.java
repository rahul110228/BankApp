package com.example.bankapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String bank_id, String bank_name, String branch_name, String routing_number) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.BANKID, bank_id);
        contentValue.put(DatabaseHelper.BANKNAME, bank_name);
        contentValue.put(DatabaseHelper.BRANCHNAME, branch_name);
        contentValue.put(DatabaseHelper.ROUTINGNUMBER, routing_number);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public void insert(String transaction_id, String from_bank_name, String from_branch_name,
                          String from_account_number, String to_bank_name, String to_branch_name,
                          String to_account_number, String amount, String transactin_date) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TRANSACTIONID, transaction_id);
        contentValue.put(DatabaseHelper.FROMBANKNAME, from_bank_name);
        contentValue.put(DatabaseHelper.FROMBRANCHNAME, from_branch_name);
        contentValue.put(DatabaseHelper.FROMACCOUNTNUMBER, from_account_number);
        contentValue.put(DatabaseHelper.TOBANKNAME, to_bank_name);
        contentValue.put(DatabaseHelper.TOBRANCHNAME, to_branch_name);
        contentValue.put(DatabaseHelper.TOACCOUNTNUMBER, to_account_number);
        contentValue.put(DatabaseHelper.AMOUNT, amount);
        contentValue.put(DatabaseHelper.TRANSACTIONDATE, transactin_date);
        database.insert(DatabaseHelper.TABLE_NAME_TR, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.BANKID, DatabaseHelper.BANKNAME,
                DatabaseHelper.BRANCHNAME, DatabaseHelper.ROUTINGNUMBER };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetch_tr() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TRANSACTIONID, DatabaseHelper.FROMBANKNAME,
                DatabaseHelper.FROMBRANCHNAME, DatabaseHelper.FROMACCOUNTNUMBER, DatabaseHelper.TOBANKNAME,
                DatabaseHelper.TOBRANCHNAME, DatabaseHelper.TOACCOUNTNUMBER, DatabaseHelper.AMOUNT, DatabaseHelper.TRANSACTIONDATE};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_TR, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public int update(long _id, String bank_id, String bank_name, String branch_name, String routing_number) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.BANKID, bank_id);
        contentValues.put(DatabaseHelper.BANKNAME, bank_name);
        contentValues.put(DatabaseHelper.BRANCHNAME, branch_name);
        contentValues.put(DatabaseHelper.ROUTINGNUMBER, routing_number);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
