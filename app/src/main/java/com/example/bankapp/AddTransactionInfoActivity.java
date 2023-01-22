package com.example.bankapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankapp.helper.DBManager;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AddTransactionInfoActivity extends Activity implements OnClickListener {

    private ImageView backHomeImageView;
    private Button sendBtn;
    private TextView bankIdTxtView;
    private EditText fromBankNameEditText, fromBranchNameEditText, fromAccountNumberEditText,
            toBankNameEditText, toBranchNameEditText, toAccountNumberEditText, amountEditText;
    public String from_bank_name, from_branch_name, from_account_number, to_bank_name,
            to_branch_name, to_account_number, amount, tarnsactionID, transactionDate;


    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bank_transactions_info);
        generateView();

        //database initiate.
        dbManager = new DBManager(this);
        dbManager.open();
    }

    public void generateView(){
        backHomeImageView = (ImageView) findViewById(R.id.back_home);
        fromBankNameEditText = (EditText) findViewById(R.id.et_from_bank_name);
        fromBranchNameEditText = (EditText) findViewById(R.id.et_from_branch_name);
        fromAccountNumberEditText = (EditText) findViewById(R.id.et_from_account_number);
        toBankNameEditText = (EditText) findViewById(R.id.et_to_bank_name);
        toBranchNameEditText = (EditText) findViewById(R.id.et_to_branch_name);
        toAccountNumberEditText = (EditText) findViewById(R.id.et_to_account_number);
        amountEditText = (EditText) findViewById(R.id.et_amount);
        sendBtn = (Button) findViewById(R.id.btn_send_money);

        sendBtn.setOnClickListener(this);
        backHomeImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_money:
                from_bank_name = fromBankNameEditText.getText().toString();
                from_branch_name = fromBranchNameEditText.getText().toString();
                from_account_number = fromAccountNumberEditText.getText().toString();
                to_bank_name = toBankNameEditText.getText().toString();
                to_branch_name = toBranchNameEditText.getText().toString();
                to_account_number = toAccountNumberEditText.getText().toString();
                amount = amountEditText.getText().toString();

                tarnsactionID = getSaltString() ;
                transactionDate = java.text.DateFormat.getDateTimeInstance().format(new Date());

                //insert into database.
                dbManager.insert(tarnsactionID, from_bank_name, from_branch_name, from_account_number,
                        to_bank_name, to_branch_name, to_account_number, amount, transactionDate);

                Intent main = new Intent(this, TransactionListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                finish();
                break;

            case R.id.back_home:
                finish();
                break;

            default:
                break;

        }
    }

    protected String getSaltString() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}