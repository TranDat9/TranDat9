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
import android.widget.TabHost;
import android.widget.Toast;

import com.example.myapplication.Adapter.PhongAdapter;
import com.example.myapplication.DTO.Phong;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PhongActivity extends AppCompatActivity {
       ListView lvPhong;
       ArrayList<Phong> dsPhong;
       PhongAdapter adapterDSPhong;

    TabHost tabHost;


    ListView lvPhongT;
    ArrayList<Phong> dsPhongT;
    PhongAdapter adapterDSPhongT;

    ListView lvPhongKT;
    ArrayList<Phong> dsPhongKT;
    PhongAdapter adapterDSPhongKT;

    public static String DATABASE_NAME = "qlPhong.sqlite";
    String DB_PATH_SUFFIX ="/databases/";
   public static SQLiteDatabase database = null; // de tuong tac voi cau lenh





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong);
        xuLySaoChepCSDLVaoHeThongMobile();
        addControls();
        addEvents();

    }

    private void addEvents() {

           tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
               @Override
               public void onTabChanged(String tabId) {
                   if(tabId.equalsIgnoreCase("t1"))
                   {
                       xuLyDSPhong();
                   }
                   if(tabId.equalsIgnoreCase("t2"))
                   {
                       xuLyDSPhongT();
                   }
                   if(tabId.equalsIgnoreCase("t3"))
                   {
                       xuLyDSPhongKT();
                   }
               }
           });
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

    private void xuLyDSPhongKT() {
   /*     dsPhongKT.clear(); // xoa du lieu
        for (Phong phong : dsPhong) {
            if(phong.isTrangThai())
            {
                dsPhongKT.add(phong);
            }
        }
         adapterDSPhongKT.notifyDataSetChanged();*/
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        if(database==null) Toast.makeText(PhongActivity.this,"rong",Toast.LENGTH_LONG).show();
        // Cursor cursor = database.query("tsach",null,null,null,null,null,null);
        Cursor cursor = database.rawQuery(" SELECT * FROM tPhong WHERE TrangThai==0 ",null);
        dsPhongKT.clear();
        while (cursor.moveToNext())
        {
            String Id = cursor.getString(0);
            String kieuPhong = cursor.getString(1);
            int trong = cursor.getInt(2);

            Phong phong = new Phong();
            phong.setId(Id);
            phong.setKieuPhong(kieuPhong);
            phong.setTrangThai(trong==1);

            dsPhongKT.add(phong);
        }
        cursor.close();
        adapterDSPhongKT.notifyDataSetChanged();
    }

    private void xuLyDSPhongT() {

     /*   dsPhongT.clear(); // xoa du lieu
        for (Phong phong : dsPhong) {
            if(!phong.isTrangThai())
            {
                dsPhongT.add(phong);
            }
        }
        adapterDSPhongT.notifyDataSetChanged();*/
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        if(database==null) Toast.makeText(PhongActivity.this,"rong",Toast.LENGTH_LONG).show();
        // Cursor cursor = database.query("tsach",null,null,null,null,null,null);
        Cursor cursor = database.rawQuery(" SELECT * FROM tPhong WHERE TrangThai==1 ",null);
        dsPhongT.clear();
        while (cursor.moveToNext())
        {
            String Id = cursor.getString(0);
            String kieuPhong = cursor.getString(1);
            int trong = cursor.getInt(2);

            Phong phong = new Phong();
            phong.setId(Id);
            phong.setKieuPhong(kieuPhong);
            phong.setTrangThai(trong==1);

            dsPhongT.add(phong);
        }
        cursor.close();
        adapterDSPhongT.notifyDataSetChanged();

    }

    private void xuLyDSPhong() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        if(database==null) Toast.makeText(PhongActivity.this,"rong",Toast.LENGTH_LONG).show();
        // Cursor cursor = database.query("tsach",null,null,null,null,null,null);
        Cursor cursor = database.rawQuery(" select *from tPhong ",null);
         dsPhong.clear();
         while (cursor.moveToNext())
         {
             String Id = cursor.getString(0);
             String kieuPhong = cursor.getString(1);
             int trong = cursor.getInt(2);

             Phong phong = new Phong();
             phong.setId(Id);
             phong.setKieuPhong(kieuPhong);
             phong.setTrangThai(trong==1);

             dsPhong.add(phong);
         }
         cursor.close();
         adapterDSPhong.notifyDataSetChanged();

    }

    private void addControls() {
         tabHost = findViewById(R.id.tabHost);
          tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Danh sach phong");
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Danh sach phong khong trong");
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 = tabHost.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("danh sach phong trong");
        tabHost.addTab(tab3);

        lvPhong = findViewById(R.id.lvPhong);
        dsPhong = new ArrayList<>();
        adapterDSPhong = new PhongAdapter(PhongActivity.this,R.layout.item,dsPhong );
        lvPhong.setAdapter(adapterDSPhong);

        lvPhongT = findViewById(R.id.lvPhongT);
        dsPhongT = new ArrayList<>();
        adapterDSPhongT = new PhongAdapter(PhongActivity.this,R.layout.item,dsPhongT);
        lvPhongT.setAdapter(adapterDSPhongT);

        lvPhongKT = findViewById(R.id.lvPhongKT);
        dsPhongKT = new ArrayList<>();
        adapterDSPhongKT = new PhongAdapter(PhongActivity.this,R.layout.item,dsPhongKT);
        lvPhongKT.setAdapter(adapterDSPhongKT);
         // giaLapPhong();

    }

    private void giaLapPhong() {
        dsPhong.add(new Phong("101","Phong Don",true));
        dsPhong.add(new Phong("102","Phong Don",false));
        dsPhong.add(new Phong("103","Phong Don",true));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.them)
        {

            Intent intent = new Intent(PhongActivity.this, AddMainActivity.class);

            startActivity(intent);

        } else if(item.getItemId()==R.id.sx) {

            Intent intent = new Intent(PhongActivity.this, TTActivity.class);

            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}