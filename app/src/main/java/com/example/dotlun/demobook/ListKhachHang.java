package com.example.dotlun.demobook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListKhachHang extends AppCompatActivity {
    final String DATABASE_NAME = "BookDB.sqlite";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<KhachHang> list;
    AdapterKhachHang adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_khach_hang);
        //Back
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        addControls();
        readData();
    }
    private void addControls(){

        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new AdapterKhachHang(this,list);
        listView.setAdapter(adapter);
    }
    private void readData(){
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM ListBook",null);
        list.clear();
        for (int i = 0; i < cursor.getCount(); i ++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String email = cursor.getString(2);
            String sdt = cursor.getString(3);
            String ngaydatban = cursor.getString(4);
            String giodatban = cursor.getString(5);
            String songuoilon = cursor.getString(6);
            String sotreem = cursor.getString(7);
            String noidung = cursor.getString(8);

            list.add(new KhachHang(id,ten,email,sdt,ngaydatban,giodatban,songuoilon,sotreem,noidung));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
