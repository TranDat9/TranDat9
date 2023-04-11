package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditMainActivity extends AppCompatActivity {
    EditText edtID, edtKieu , edtTT ;
    Button btnSua , btnHuy ,btnXoa;
    String ID;
    int TTInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_main);
        addControls();
        addEvents();
        Intent intent = getIntent();
        Bundle bundle  = intent.getExtras();

            ID = bundle.getString("ID");

           edtID.setText(bundle.getString("ID"));
            edtKieu.setText(bundle.getString("Kieu"));
          String TT = bundle.getInt("TT") +" ";
            edtTT.setText(TT);
            TTInt = bundle.getInt("TT");


    }

    private void addEvents() {
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues row = new ContentValues();
               row.put("ID",edtID.getText().toString() );
                row.put("KieuPhong",edtKieu.getText().toString() );

                row.put("TrangThai", TTInt);
              long r=  PhongActivity.database.update("tPhong",row,"ID=?",new String[]{ID});
                Toast.makeText(EditMainActivity.this,"Đã sửa"+r,Toast.LENGTH_LONG).show();
            }
        });
  btnXoa.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          long r= PhongActivity.database.delete("tPhong","ID=?",new String[]{ID});
          Toast.makeText(EditMainActivity.this,"Đã xóa"+r,Toast.LENGTH_LONG).show();
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
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);


    }
}