package com.example.dotlun.demobook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    final String DATABASE_NAME = "BookDB.sqlite";
    EditText edtTen, edtSdt, edtEmail, edtNgaydatban, edtGiodatban, edtSonguoilon, edtSotreem, edtGhichu;
    Button btnAdd;
    ImageButton bfecha, bhora;
    private int dia, mes, ano, hora, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        bfecha=(ImageButton) findViewById(R.id.btnDate);
        bhora=(ImageButton)findViewById(R.id.btnTime);
        bfecha.setOnClickListener(this);
        bhora.setOnClickListener(this);
        addControls();
        addEvents();
    }

    private void addControls() {

        btnAdd = (Button) findViewById(R.id.btnAdd);


        edtTen = (EditText) findViewById(R.id.edtTen);
        edtSdt = (EditText) findViewById(R.id.edtPhone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtNgaydatban = (EditText) findViewById(R.id.edtDate);
        edtGiodatban = (EditText) findViewById(R.id.edtTime);
        edtSonguoilon = (EditText) findViewById(R.id.edtSongoilon);
        edtSotreem = (EditText) findViewById(R.id.edtSotreem);
        edtGhichu = (EditText) findViewById(R.id.edtNoidung);

    }

    private void addEvents() {


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    private void insert() {
        String ten = edtTen.getText().toString();
        String sdt = edtSdt.getText().toString();
        String email = edtEmail.getText().toString();
        String ngaydatban = edtNgaydatban.getText().toString();
        String giodatban = edtGiodatban.getText().toString();
        String songuoilon = edtSonguoilon.getText().toString();
        String sotreem = edtSotreem.getText().toString();
        String ghichu = edtGhichu.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Ten", ten);
        contentValues.put("SDT", sdt);
        contentValues.put("Email", email);
        contentValues.put("Ngaydatban", ngaydatban);
        contentValues.put("Giodatban", giodatban);
        contentValues.put("Songuoilon", songuoilon);
        contentValues.put("Sotreem", sotreem);
        contentValues.put("Ghichu", ghichu);


        SQLiteDatabase database = Database.initDatabase(this, "BookDB.sqlite");
        database.insert("ListBook", null, contentValues);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        if (v == bfecha) {
            final java.util.Calendar c = java.util.Calendar.getInstance();
            dia = c.get(java.util.Calendar.DAY_OF_MONTH);
            mes = c.get(java.util.Calendar.MONTH);
            ano = c.get(java.util.Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    edtNgaydatban.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();
        }
        if (v == bhora) {
            final java.util.Calendar c = java.util.Calendar.getInstance();
            hora = c.get(java.util.Calendar.HOUR_OF_DAY);
            minutos = c.get(java.util.Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    edtGiodatban.setText(hourOfDay + ":" + minute);
                }
            }, hora, minutos, false);
            timePickerDialog.show();
        }
    }
}
