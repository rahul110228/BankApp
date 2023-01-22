package com.example.bankapp.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "BANKINFO";
    public static final String TABLE_NAME_TR = "TRANSACTIONINFO";

    // Table columns
    public static final String _ID = "_id";
    public static final String BANKID = "bank_id";
    public static final String BANKNAME = "bank_name";
    public static final String BRANCHNAME = "branch_name";
    public static final String ROUTINGNUMBER = "routing_number";

    public static final String TRANSACTIONID = "transaction_id";
    public static final String FROMBANKNAME = "from_bank_name";
    public static final String FROMBRANCHNAME = "from_branch_name";
    public static final String FROMACCOUNTNUMBER = "from_account_number";
    public static final String TOBANKNAME = "to_bank_name";
    public static final String TOBRANCHNAME = "to_branch_name";
    public static final String TOACCOUNTNUMBER = "to_account_number";
    public static final String AMOUNT = "amount";
    public static final String TRANSACTIONDATE = "transaction_date";

    // Database Information
    static final String DB_NAME = "BANK_INFO.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BANKID + " TEXT NOT NULL, "
            + BANKNAME + " TEXT NOT NULL, "
            + BRANCHNAME + " TEXT NOT NULL, "
            + ROUTINGNUMBER + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_TR = "create table " + TABLE_NAME_TR + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TRANSACTIONID + " TEXT NOT NULL, "
            + FROMBANKNAME + " TEXT NOT NULL, "
            + FROMBRANCHNAME + " TEXT NOT NULL, "
            + FROMACCOUNTNUMBER + " TEXT NOT NULL, "
            + TOBANKNAME + " TEXT NOT NULL, "
            + TOBRANCHNAME + " TEXT NOT NULL, "
            + TOACCOUNTNUMBER + " TEXT NOT NULL, "
            + AMOUNT + " TEXT NOT NULL, "
            + TRANSACTIONDATE + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_TR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TR);
        onCreate(db);
    }
}
