package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Database.Database;
import com.example.myapplication.quanlykhachhang.KhachHang;
import com.example.myapplication.quanlykhachhang.KhachHangAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     public static Database database;
    public static ListView lvKhachHang;
    public static ArrayList<KhachHang> arrayKhachHang;
    public static KhachHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvKhachHang = (ListView) findViewById(R.id.lvKhachHang);
        arrayKhachHang = new ArrayList<>();
        adapter = new KhachHangAdapter(this,R.layout.khachhanglayout,arrayKhachHang);
        lvKhachHang.setAdapter(adapter);

        // tao database
        database = new Database(this, "khachhang.sqlite", null, 1);
        //tao bang
        database.QueryData("CREATE TABLE IF NOT EXISTS KhachHang(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenKH VARCHAR(200), SoCCCD VARCHAR(200),DiaChi VARCHAR(200),SoDT VARCHAR(200))");
        //insert data
        //database.QueryData("INSERT INTO KhachHang VALUES(NULL,'Nguyen DoTan','0892734','Huong Thinh','09888888')");
        GetDataKhachHang();

    }
    private void GetDataKhachHang()
    {
        Cursor dataKhachHang =database.GetData("SELECT * FROM KhachHang");
        arrayKhachHang.clear();
        while(dataKhachHang.moveToNext())
        {
            int id =dataKhachHang.getInt(0);
            String ten = dataKhachHang.getString(1);
            String soCC = dataKhachHang.getString(2);
            String diaChi = dataKhachHang.getString(3);
            String SoDT = dataKhachHang.getString(4);
            arrayKhachHang.add(new KhachHang(id,ten,soCC,diaChi,SoDT));
        }
        adapter.notifyDataSetChanged();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_khachhang,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.item_add)
        {
            DiaLogThem();
        } else if(item.getItemId()==R.id.sx) {

            Intent intent = new Intent(MainActivity.this, TTActivity.class);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void DiaLogThem()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_addkhachhang);
        EditText edtName = (EditText) dialog.findViewById(R.id.edt_name);
        EditText edtSoCC = (EditText) dialog.findViewById(R.id.edt_citizenidentification);
        EditText edtdiaChi = (EditText) dialog.findViewById(R.id.edt_address);
        EditText edtSoDT = (EditText) dialog.findViewById(R.id.edt_phone);
        Button btnLuu = (Button) dialog.findViewById(R.id.btn_luu);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_huy);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKh = edtName.getText().toString();
                String soCC = edtSoCC.getText().toString();
                String diaChi = edtdiaChi.getText().toString();
                String soDT = edtSoDT.getText().toString();
                if(tenKh.equals(""))
                {
                    Toast.makeText(MainActivity.this, "vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
                }
                else if(soCC.length() != 12)
                {
                    Toast.makeText(MainActivity.this, "vui lòng nhập đúng thông tin số căn cước", Toast.LENGTH_SHORT).show();
                }
                else if(diaChi.equals(""))
                {
                    Toast.makeText(MainActivity.this, "vui lòng nhập vào địa chỉ", Toast.LENGTH_SHORT).show();
                }
                else if(soDT.equals(""))
                {
                    Toast.makeText(MainActivity.this, "vui lòng nhập vào số điẹn thoại", Toast.LENGTH_SHORT).show();
                }
                else{
                    database.QueryData("INSERT INTO KhachHang VALUES(NULL,'"+ tenKh +"','"+ soCC +"','"+ diaChi +"','"+ soDT +"')");
                    Toast.makeText(MainActivity.this, "bạn đã thêm thành công khách hàng mới", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataKhachHang();
                }

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void dialogSua(int id, String Ten, String soCC, String diaChi, String soDT)
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);

        EditText edtTenKH = (EditText) dialog.findViewById(R.id.edt_nameEdit);
        EditText edtSoCC = (EditText) dialog.findViewById(R.id.edt_citizenidentificationEdit);
        EditText edtDiaChi = (EditText) dialog.findViewById(R.id.edt_addressEdit);
        EditText edtSoDT = (EditText) dialog.findViewById(R.id.edt_phoneEdit);

        Button btnXacNhan = (Button) dialog.findViewById(R.id.btn_luuEdit);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_huyEdit);

        //
        edtTenKH.setText(Ten);
        edtSoCC.setText(soCC);
        edtDiaChi.setText(diaChi);
        edtSoDT.setText(soDT);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi = edtTenKH.getText().toString().trim();
                String soCCmoi = edtSoCC.getText().toString().trim();
                String DiaChiMoi = edtDiaChi.getText().toString().trim();
                String soDTMoi = edtSoDT.getText().toString().trim();

                database.QueryData("UPDATE KhachHang SET TenKH = '"+ tenMoi +"',SoCCCD = '"+ soCCmoi + "',DiaChi = '"+ DiaChiMoi +"',SoDT = '"+ soDTMoi +"' WHERE Id = '"+ id +"'");
                Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataKhachHang();
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


    public void DialogXoaKH(String tenKH, int id)
    {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa khách hàng " + tenKH + "khong?");
        dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, final int i) {
                database.QueryData("DELETE FROM KhachHang WHERE Id ='"+ id +"'");
                Toast.makeText(MainActivity.this, "đã xóa thành công!", Toast.LENGTH_SHORT).show();
                GetDataKhachHang();
            }
        });
        dialogXoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialogXoa.show();
    }



}