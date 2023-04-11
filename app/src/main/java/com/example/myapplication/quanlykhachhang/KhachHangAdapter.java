package com.example.myapplication.quanlykhachhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.quanlykhachhang.KhachHang;

import java.util.List;

public class KhachHangAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<KhachHang> khachHangList;

    public KhachHangAdapter(MainActivity context, int layout, List<KhachHang> khachHangList) {
        this.context = context;
        this.layout = layout;
        this.khachHangList = khachHangList;
    }

    @Override
    public int getCount() {
        return khachHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

private class ViewHolder{
           TextView tvName,tvSoCC,tvDiaChi,tvSoDT;
           ImageView imgDelete, imgUpdate;
}
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tvName =(TextView)view.findViewById(R.id.tv_username);
            holder.tvSoCC = (TextView)view.findViewById(R.id.tv_userCCCD);
            holder.tvDiaChi = (TextView)view.findViewById(R.id.tv_useraddress);
            holder.tvSoDT = (TextView)view.findViewById(R.id.tv_userphone);
            holder.imgDelete = (ImageView) view.findViewById(R.id.img_remove);
            holder.imgUpdate = (ImageView) view.findViewById(R.id.img_update);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        KhachHang khachHang = khachHangList.get(i);
        // xet text =  doi tuong khachHang
        holder.tvName.setText(khachHang.getTenKH());
        holder.tvSoCC.setText(khachHang.getSoCCCD());
        holder.tvDiaChi.setText(khachHang.getDiachi());
        holder.tvSoDT.setText(khachHang.getSoDT());
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.dialogSua(khachHang.getIdKH(),khachHang.getTenKH(),khachHang.getSoCCCD(),khachHang.getDiachi(),khachHang.getSoDT());
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaKH(khachHang.getTenKH(),khachHang.getIdKH());
            }
        });

        return view;

    }
}
