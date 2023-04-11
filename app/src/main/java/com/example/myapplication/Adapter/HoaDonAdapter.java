package com.example.myapplication.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DTO.HoaDon;
import com.example.myapplication.DichVuActivity;
import com.example.myapplication.HoaDonActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class HoaDonAdapter extends ArrayAdapter<HoaDon> {

    Activity context;
    int resource;

    List<HoaDon> objects;

    public HoaDonAdapter(@NonNull Activity context, int resource, @NonNull List<HoaDon> objects) {
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
        TextView txtIDPhong = (TextView) row.findViewById(R.id.txtIDPhong);
        TextView txtTenKH = (TextView) row.findViewById(R.id.txttenKH);
        TextView txtDV = (TextView) row.findViewById(R.id.txtDV);
        TextView txtNote = (TextView) row.findViewById(R.id.txtNote);
        TextView txtTraPhong = (TextView) row.findViewById(R.id.txtTraPhong);
        ImageButton btnXoaHD = row.findViewById(R.id.btnDeleteHD);
        ImageButton btnSuaHD = row.findViewById(R.id.btnEditHD);


       HoaDon hoaDon =this.objects.get(position);
        txtIDPhong.setText(hoaDon.getMaPhong());
        txtTenKH.setText(hoaDon.getTenKH());
        txtDV.setText(hoaDon.getTienDV());
        txtNote.setText(hoaDon.getNote());
        if(hoaDon.isTraPhong())
        {
            txtTraPhong.setText("Chua Tra Phong");
        } else   txtTraPhong.setText("Da Tra Phong");

        btnSuaHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_suahd);

                EditText edtIDPhong = (EditText) dialog.findViewById(R.id.edtsuaIDPhong);

                EditText edtTenKH = dialog.findViewById(R.id.edtsuaTenKH);
                EditText edtTienDV = dialog.findViewById(R.id.edtsuaTienDV);

                EditText edtNote = dialog.findViewById(R.id.edtsuaNote);
                RadioButton rdTP = dialog.findViewById(R.id.rdbTra);
                RadioButton rdTCP = dialog.findViewById(R.id.rdbCTraPhong);

                Button btnXacNhan = (Button) dialog.findViewById(R.id.btnSuaHD);
                Button btnHuy = (Button) dialog.findViewById(R.id.btnHuyHD);

                //
                edtIDPhong.setText(txtIDPhong.getText());
                edtTenKH.setText(txtTenKH.getText());
                edtTienDV.setText(txtDV.getText());
                edtNote.setText(txtNote.getText());
                int TT=0 ;
                if(hoaDon.isTraPhong())
                {
                    rdTCP.setChecked(true);

                } else
                {
                    rdTP.setChecked(true);


                }
                //
                ContentValues row = new ContentValues();
                row.put("maPhong",edtIDPhong.getText().toString());
                row.put("tenKH",edtTenKH.getText().toString());
                row.put("tienDV",edtTienDV.getText().toString());
                row.put("maPhong",edtIDPhong.getText().toString());
                row.put("note",edtNote.getText().toString());
            /*    if(rdTCP.isChecked()) TT=1;
                else  TT=0;
                row.put("TraPhong",TT);*/


                btnXacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenCu = txtIDPhong.getText().toString();
                       HoaDonActivity.database.update("tHoaDon",row,"maPhong=?",new String[]{tenCu});
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();


            }
        });
       btnXoaHD.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
               dialogXoa.setMessage("Bạn có muốn xóa hóa đơn phong " +txtIDPhong.getText().toString() + "khong?");
               dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, final int i) {
                     long r =  HoaDonActivity.database.delete("tHoaDon","maPhong=?",new String[]{txtIDPhong.getText().toString()});
                       Toast.makeText(context, "đã xóa thành công!", Toast.LENGTH_SHORT).show();
                       HoaDonActivity.adapterDSHoaDon.notifyDataSetChanged();

                   }
               });
               dialogXoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                   }
               });
               dialogXoa.show();
           }
       });

        return row;

    }




}
