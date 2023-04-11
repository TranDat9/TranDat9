package com.example.myapplication;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMainActivity extends AppCompatActivity {

    EditText edtID, edtKieu , edtTT ;
    Button btnXN , btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues row = new ContentValues();
                row.put("ID",edtID.getText().toString());
                row.put("KieuPhong",edtKieu.getText().toString());
                int TT = Integer.parseInt(edtTT.getText().toString());
                row.put("TrangThai",TT==1 );

                long r =  PhongActivity.database.insert("tPhong",null,row);

                if(r>0) Toast.makeText(AddMainActivity.this,"vua them moi"+r,Toast.LENGTH_LONG).show();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void addControls() {
        edtID = findViewById(R.id.edtID);
        edtKieu = findViewById(R.id.edtKieu);
        edtTT = findViewById(R.id.edtTT);

        btnHuy = findViewById(R.id.btnHuy);
        btnXN = findViewById(R.id.btnXN);


    }
}