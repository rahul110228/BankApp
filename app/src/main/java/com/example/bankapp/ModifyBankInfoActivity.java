package com.example.bankapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankapp.helper.DBManager;

public class ModifyBankInfoActivity extends Activity implements OnClickListener {
    private ImageView backHomeImageView;
    private TextView bankIdTxtView;
    private EditText bankNameEditText, branchNameEditText, routingNumberEditText;
    private Button updateBtn, deleteBtn;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modify_bank_info);
        generateView();

        dbManager = new DBManager(this);
        dbManager.open();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String bank_id = intent.getStringExtra("bank_id");
        String bank_name = intent.getStringExtra("bank_name");
        String branch_name = intent.getStringExtra("branch_name");
        String routing_number = intent.getStringExtra("routing_number");

        _id = Long.parseLong(id);
        bankIdTxtView.setText(bank_id);
        bankNameEditText.setText(bank_name);
        branchNameEditText.setText(branch_name);
        routingNumberEditText.setText(routing_number);
    }

    public void generateView(){
        backHomeImageView = (ImageView) findViewById(R.id.back_home);
        bankIdTxtView = (TextView) findViewById(R.id.txt_bank_id);
        bankNameEditText = (EditText) findViewById(R.id.et_bank_name);
        branchNameEditText = (EditText) findViewById(R.id.et_branch_name);
        routingNumberEditText = (EditText) findViewById(R.id.et_routing_number);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        backHomeImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String bank_id = bankIdTxtView.getText().toString();
                String bank_name = bankNameEditText.getText().toString();
                String branch_name = branchNameEditText.getText().toString();
                String routing_number = routingNumberEditText.getText().toString();

                //update data.
                dbManager.update(_id, bank_id, bank_name, branch_name, routing_number);
                this.returnHome();
                finish();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id); //delete specific data.
                this.returnHome();
                finish();
                break;

            case R.id.back_home:
                finish();
                break;

            default:
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), BankListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
