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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.Database;
import com.example.myapplication.quanlydichvu.DichVu;
import com.example.myapplication.quanlydichvu.DichVuAdapter;
import com.example.myapplication.quanlykhachhang.KhachHang;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DichVuActivity extends AppCompatActivity {
    public static Database database;
    public static ListView lvDichVu;
    public static ArrayList<DichVu> arrayDichVu;
    public static DichVuAdapter adapter;
    TextView tvAddDV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_vu);
        ImageView imagAdd;
        lvDichVu = (ListView) findViewById(R.id.lvDichVu);
        arrayDichVu = new ArrayList<>();
        adapter = new DichVuAdapter(this,R.layout.dichvu, arrayDichVu);
        lvDichVu.setAdapter(adapter);
        // tao database
        database = new Database(this, "dichvu.sqlite", null, 1);
        //tao bang
        database.QueryData("CREATE TABLE IF NOT EXISTS DichVu(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenDV VARCHAR(200))");
        //insert data
        //database.QueryData("INSERT INTO DichVu VALUES(NULL,'Giặt là quần áo')");
        GetDataDichvu();
    }
    private void GetDataDichvu()
    {
        Cursor dataDichVu =database.GetData("SELECT * FROM DichVu");
        arrayDichVu.clear();
        while(dataDichVu.moveToNext())
        {
            int id =dataDichVu.getInt(0);
            String tenDV = dataDichVu.getString(1);
            arrayDichVu.add(new DichVu(id,tenDV));
        }
        adapter.notifyDataSetChanged();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dv,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.item_addDV)
        {
            DiaLogThemDichVu();
        } else if(item.getItemId()==R.id.sx) {

            Intent intent = new Intent(DichVuActivity.this, TTActivity.class);

            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
    private void DiaLogThemDichVu()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_adddichvu);
        EditText edtName = (EditText) dialog.findViewById(R.id.edt_adddv);
        Button btnLuu = (Button) dialog.findViewById(R.id.btn_luu_dv);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_huy_dv);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDV = edtName.getText().toString();

                if(tenDV.equals(""))
                {
                    Toast.makeText(DichVuActivity.this, "vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
                }

                else{
                    database.QueryData("INSERT INTO DichVu VALUES(NULL,'"+ tenDV +"')");
                    Toast.makeText(DichVuActivity.this, "bạn đã thêm thành công khách hàng mới", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataDichvu();
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
    public void diaLogSuaDichVu(int idDV, String TenDV)
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_suadichvu);

        EditText edtTenDV = (EditText) dialog.findViewById(R.id.edt_suadichvu);

        Button btnXacNhan = (Button) dialog.findViewById(R.id.btn_luuEdit_dichvu);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_huyEdit_dichvu);

        //
        edtTenDV.setText(TenDV);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi = edtTenDV.getText().toString().trim();
                database.QueryData("UPDATE DichVu SET TenDV = '"+ tenMoi +"' WHERE Id = '"+ idDV +"'");
                Toast.makeText(DichVuActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataDichvu();
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
    public void DialogXoaDV(String tenDV, int id)
    {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa dịch vụ " + tenDV + "khong?");
        dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, final int i) {
                database.QueryData("DELETE FROM DichVu WHERE Id ='"+ id +"'");
                Toast.makeText(DichVuActivity.this, "đã xóa thành công!", Toast.LENGTH_SHORT).show();
                GetDataDichvu();
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