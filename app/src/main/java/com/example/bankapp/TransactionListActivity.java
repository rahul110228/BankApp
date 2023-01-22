package com.example.bankapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import com.example.bankapp.helper.DatabaseHelper;

public class TransactionListActivity extends AppCompatActivity implements View.OnClickListener {

    private com.example.bankapp.helper.DBManager dbManager;
    Button sendBtn;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelper.TRANSACTIONID, DatabaseHelper.AMOUNT, DatabaseHelper.TRANSACTIONDATE};
    final int[] to = new int[] { R.id.transaction_id, R.id.amount, R.id.transaction_date};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_transaction_list);
        generateview();

        dbManager = new com.example.bankapp.helper.DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch_tr();

        listView = (ListView) findViewById(R.id.list_view_tr);
        listView.setEmptyView(findViewById(R.id.empty_tr));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_transaction_info_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }

    public void generateview(){
        sendBtn = (Button) findViewById(R.id.btn_send_money);

        sendBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_money:

                Intent main = new Intent(this, AddTransactionInfoActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

            case R.id.back_home:
                finish();
                break;

            default:
                break;

        }
    }

}