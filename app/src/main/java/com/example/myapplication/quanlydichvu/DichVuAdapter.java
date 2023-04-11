package com.example.myapplication.quanlydichvu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.DichVuActivity;
import com.example.myapplication.R;

import java.util.List;

public class DichVuAdapter extends BaseAdapter {
    private DichVuActivity context;
    private int layout;
    private List<DichVu> dichVuList;
    public DichVuAdapter(DichVuActivity context, int layout, List<DichVu> dichVuList) {
        this.context = context;
        this.layout = layout;
        this.dichVuList = dichVuList;
    }

    @Override
    public int getCount() {
        return dichVuList.size();
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
        TextView tv_TenDichVu;
        ImageView imgSua, imgXoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tv_TenDichVu =(TextView)view.findViewById(R.id.tv_TenDichVu);
            holder.imgSua =(ImageView) view.findViewById(R.id.img_Sua);
            holder.imgXoa =(ImageView) view.findViewById(R.id.img_Xoa);
            view.setTag(holder);
        }
        else
        {
           holder = (ViewHolder) view.getTag();
        }
        DichVu dichVu = dichVuList.get(i);
        // xet text =  doi tuong dich vu
        holder.tv_TenDichVu.setText(dichVu.getTenDichVu());
        holder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.diaLogSuaDichVu(dichVu.getIdDichVu(),dichVu.getTenDichVu());
            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaDV(dichVu.getTenDichVu(),dichVu.getIdDichVu());
            }
        });
        return view;
    }
}
