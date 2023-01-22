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

import java.util.concurrent.ThreadLocalRandom;

public class AddBankInfoActivity extends Activity implements OnClickListener {

    private ImageView backHomeImageView;
    private Button saveBtn;
    private TextView bankIdTxtView;
    private EditText bankNameEditText, branchNameEditText, routingNumberEditText;


    private DBManager dbManager;
    int bank_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_bank_info);
        generateView();

        bank_id = ThreadLocalRandom.current().nextInt(1000, 10000);
        bankIdTxtView.setText(Integer.toString(bank_id));

        dbManager = new DBManager(this);
        dbManager.open();
    }

    public void generateView(){
        backHomeImageView = (ImageView) findViewById(R.id.back_home);
        bankIdTxtView = (TextView) findViewById(R.id.txt_bank_id);
        bankNameEditText = (EditText) findViewById(R.id.et_bank_name);
        branchNameEditText = (EditText) findViewById(R.id.et_branch_name);
        routingNumberEditText = (EditText) findViewById(R.id.et_routing_number);
        saveBtn = (Button) findViewById(R.id.btn_save_bank_info);

        saveBtn.setOnClickListener(this);
        backHomeImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_bank_info:
                final String bank_name = bankNameEditText.getText().toString();
                final String branch_name = branchNameEditText.getText().toString();
                final String routing_number = routingNumberEditText.getText().toString();

                dbManager.insert(Integer.toString(bank_id), bank_name, branch_name, routing_number);

                Intent main = new Intent(this, BankListActivity.class)
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

}