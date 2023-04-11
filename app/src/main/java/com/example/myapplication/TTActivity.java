package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TTActivity extends AppCompatActivity {
    ImageView btnHoaDon , btnDichVu , btnKhachHang , btnPhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttactivity);
        addConrtrols();
        addEvents();
    }

    private void addConrtrols() {
        btnDichVu =findViewById(R.id.btnDichVu);
        btnHoaDon=findViewById(R.id.btnHoaDon);
        btnKhachHang =findViewById(R.id.btnKhachHang);
        btnPhong =findViewById(R.id.btnPhong);

    }

    private void addEvents() {
        btnPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TTActivity.this,PhongActivity.class );
                startActivity(intent);
            }
        });
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TTActivity.this,HoaDonActivity.class );
                startActivity(intent);
            }
        });
        btnDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TTActivity.this,DichVuActivity.class );
                startActivity(intent);
            }
        });
        btnKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TTActivity.this,MainActivity.class );
                startActivity(intent);
            }
        });
    }
}