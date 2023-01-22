package com.example.bankapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import com.example.bankapp.helper.DatabaseHelper;

public class BankListActivity extends AppCompatActivity {

    private com.example.bankapp.helper.DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelper.BANKNAME, DatabaseHelper.BRANCHNAME,
            DatabaseHelper.ROUTINGNUMBER, DatabaseHelper.BANKID, DatabaseHelper._ID };

    final int[] to = new int[] { R.id.bank_name, R.id.branch_name, R.id.routing_number, R.id.bank_id, R.id.id};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_bank_list);
        //databse initiate.
        dbManager = new com.example.bankapp.helper.DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();//fetch data from database.

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_bank_info_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView bankNameTextView = (TextView) view.findViewById(R.id.bank_name);
                TextView branchNameTextView = (TextView) view.findViewById(R.id.branch_name);
                TextView routingNumberTextView = (TextView) view.findViewById(R.id.routing_number);
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView bankIdTextView = (TextView) view.findViewById(R.id.bank_id);

                String id = idTextView.getText().toString();
                String bankId=  bankIdTextView.getText().toString();
                String bankName = bankNameTextView.getText().toString();
                String branchName = branchNameTextView.getText().toString();
                String routingNumber = routingNumberTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), com.example.bankapp.ModifyBankInfoActivity.class);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("bank_id", bankId);
                modify_intent.putExtra("bank_name", bankName);
                modify_intent.putExtra("branch_name", branchName);
                modify_intent.putExtra("routing_number", routingNumber);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_add_bank_info) {
            Intent add_bank = new Intent(this, AddBankInfoActivity.class);
            startActivity(add_bank);
        }

        if (id == R.id.action_add_transactions) {
            Intent add_bank_transactions = new Intent(this, TransactionListActivity.class);
            startActivity(add_bank_transactions);
        }
        return super.onOptionsItemSelected(item);
    }

}