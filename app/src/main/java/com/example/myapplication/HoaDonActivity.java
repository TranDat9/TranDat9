package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Adapter.HoaDonAdapter;
import com.example.myapplication.DTO.HoaDon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HoaDonActivity extends AppCompatActivity {
    ListView lvHoaDon;
    ArrayList<HoaDon> dsHoaDon;
    public static HoaDonAdapter adapterDSHoaDon;

    public static String DATABASE_NAME = "qlPhong.sqlite";
    String DB_PATH_SUFFIX ="/databases/";
    public static SQLiteDatabase database = null; // de tuong tac voi cau lenh

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        xuLySaoChepCSDLVaoHeThongMobile();
        addControls();
        addEvents();
    }

    private void addEvents() {
        xuLYDSHD();


    }

    private void xuLYDSHD() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        if(database==null) Toast.makeText(HoaDonActivity.this,"rong",Toast.LENGTH_LONG).show();
        // Cursor cursor = database.query("tsach",null,null,null,null,null,null);
        Cursor cursor = database.rawQuery(" select *from tHoaDon ",null);
        dsHoaDon.clear();
        while (cursor.moveToNext())
        {

            String maPhong = cursor.getString(1);
            String tenKH = cursor.getString(2);
            String tienDV = cursor.getString(3);
            String note = cursor.getString(4);
            int TraPhong = cursor.getInt(5);


            HoaDon hoaDon= new HoaDon();
        hoaDon.setMaPhong(maPhong);
        hoaDon.setTenKH(tenKH);
        hoaDon.setTienDV(tienDV);
            hoaDon.setNote(note);
            hoaDon.setTraPhong(TraPhong==1);

            dsHoaDon.add(hoaDon);
        }
        cursor.close();
        adapterDSHoaDon.notifyDataSetChanged();
    }

    private void addControls() {
        lvHoaDon = findViewById(R.id.lvDSHD);
        dsHoaDon = new ArrayList<>();


        adapterDSHoaDon = new HoaDonAdapter(HoaDonActivity.this,R.layout.item_hoadon,dsHoaDon);
        lvHoaDon.setAdapter(adapterDSHoaDon);

    }
    private void xuLySaoChepCSDLVaoHeThongMobile() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists())
        {
            try {
                saoChepDataBaseTuAsset();
                Toast.makeText(this,"sao chep thanh cong",Toast.LENGTH_LONG).show();
            }
            catch (Exception e )
            {
                Toast.makeText(this,"loi",Toast.LENGTH_LONG).show();
            }
        }


    }

    private void saoChepDataBaseTuAsset() {
        try
        {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFilename = layDuongDanLuuTru();

            File f = new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists())
            {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFilename);
            byte[] buffer = new byte[1024];
            int length;
            while((length  = myInput.read(buffer))>0)
            {
                myOutput.write(buffer,0,length);
            }
            // close the strems
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception e)
        {
            Log.e("Loi sao chep",e.toString()) ;
        }

    }


    private String layDuongDanLuuTru( )
    {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.them)
        {

            Intent intent = new Intent(HoaDonActivity.this, AddHDActivity.class);

            startActivity(intent);

        } else if(item.getItemId()==R.id.sx) {

            Intent intent = new Intent(HoaDonActivity.this, TTActivity.class);

            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hoadon_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}