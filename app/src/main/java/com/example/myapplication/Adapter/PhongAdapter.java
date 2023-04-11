package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.DTO.Phong;
import com.example.myapplication.EditMainActivity;
import com.example.myapplication.PhongActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class PhongAdapter extends ArrayAdapter<Phong>     {
    Activity context;
    int resource;

    List<Phong> objects;
    ArrayList<Phong> databackup;
    public PhongAdapter(@NonNull Activity context, int resource, @NonNull List<Phong> objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource= resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtId = (TextView) row.findViewById(R.id.txtID);
        TextView txtLoai = (TextView) row.findViewById(R.id.txtLoai);
        ImageButton btnEdt = row.findViewById(R.id.btnEdt);
        ImageButton btnT = row.findViewById(R.id.btnT);
        ImageButton btnKT = row.findViewById(R.id.btnKT);




        Phong phong =this.objects.get(position);
        txtId.setText(phong.getId());
        txtLoai.setText(phong.getKieuPhong());
         if(phong.isTrangThai())
         {
             btnKT.setVisibility(View.INVISIBLE);
             btnT.setVisibility(View.VISIBLE);
         }
         else {
             btnKT.setVisibility(View.VISIBLE);
             btnT.setVisibility(View.INVISIBLE);
         }

         btnT.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 xuLyTrong(phong);
             }
         });

        btnKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyKhongTrong(phong);
            }
        });

        btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intent = new Intent(context, EditMainActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("ID",phong.getId());
                bundle.putString("Kieu",phong.getKieuPhong());
                int TT ;
                if(phong.isTrangThai())
                {
                    TT = 1;
                } else  TT =0;
                bundle.putInt("TT",TT);
                intent.putExtras(bundle);

           startActivityForResult(context,intent,200,bundle);
            }
        });




        return row;

    }



    private void xuLyKhongTrong(Phong phong) {
        ContentValues  row = new ContentValues();
        row.put("TrangThai",1);

        PhongActivity.database.update("tPhong",row,"ID=?",new String[]{phong.getId()});
        

        phong.setTrangThai(true);
    }

    private void xuLyTrong(Phong phong) {
        ContentValues  row = new ContentValues();
        row.put("TrangThai",0);

        PhongActivity.database.update("tPhong",row,"ID=?",new String[]{phong.getId()});
        phong.setTrangThai(false);

    }
}
