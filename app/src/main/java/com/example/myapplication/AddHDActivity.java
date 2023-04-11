package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddHDActivity extends AppCompatActivity {
    EditText edtmaPhong, edtTenKH , edtTDV,edtNote ;
    Button btnXN , btnHuy;
    RadioButton rdbDTP,rdbCTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hdactivity);
        addContronls();
        addEvents();
    }

    private void addEvents() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues row = new ContentValues();
                row.put("maPhong",edtmaPhong.getText().toString());
                row.put("tenKH",edtTenKH.getText().toString());
                row.put("tienDV",edtTDV.getText().toString());
                row.put("note",edtNote.getText().toString());
                int TT ;
                if(rdbCTP.isChecked()) TT=1;
                else  TT=0;
                row.put("TraPhong",TT);


                long r =  HoaDonActivity.database.insert("tHoaDon",null,row);

                if(r>0) Toast.makeText(AddHDActivity.this,"vua them moi"+r,Toast.LENGTH_LONG).show();
            }
        });


    }

    private void addContronls() {
                 edtmaPhong =findViewById(R.id.edtIDPhong);
                 edtTenKH  =findViewById(R.id.edtTenKH);
                 edtTDV= findViewById(R.id.edtTienDV);
                 edtNote = findViewById(R.id.edtNote);

                 rdbCTP = findViewById(R.id.rdbCTraPhong);
                 rdbDTP = findViewById(R.id.rdbTra);

                 btnXN = findViewById(R.id.btnLuuHD);
                 btnHuy = findViewById(R.id.btnHuyHD);

    }
}