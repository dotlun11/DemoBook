package com.example.dotlun.demobook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final String DATABASE_NAME = "BookDB.sqlite";
    EditText edtTen, edtSdt, edtEmail, edtNgaydatban, edtGiodatban, edtSonguoilon, edtSotreem, edtGhichu;
    Button btnAdd;
    ImageButton bfecha, bhora;
    private int dia, mes, ano, hora, minutos;
    private TextView tvgioithieu;
    ImageView imgtom,imgoc,imgca;
    Toolbar toolbar;
    //Main Email
    private EditText editTextEmail;
    private EditText editTextSubject;
    private EditText editTextMessage;
    private TextView textViewEmail,textViewSubject,textViewMessage;
    //  private Button buttonSend;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bfecha=(ImageButton) findViewById(R.id.btnDate);
        bhora=(ImageButton)findViewById(R.id.btnTime);
        bfecha.setOnClickListener(this);
        bhora.setOnClickListener(this);

        imgtom=(ImageView)findViewById(R.id.imgtom);
        imgoc=(ImageView)findViewById(R.id.imgoc);
        //Gioi thieu
        tvgioithieu = (TextView)findViewById(R.id.tvgioithieu);
        tvgioithieu.setText("ĐỊA ĐIỂM ĂN HẢI SẢN NGON, TƯƠI SỐNG TẠI NHA TRANG");
        Picasso.with(getApplicationContext()).load("http://www.nhahangngoctrai.com/images/mon-ngon/tom-hum-dut-lo.jpg").into(imgtom);
        Picasso.with(getApplicationContext()).load("http://www.nhahangngoctrai.com/images/mon-ngon/oc-thap-cam.jpg").into(imgoc);
        // Picasso.with(getApplicationContext()).load("http://www.nhahangngoctrai.com/images/mon-ngon/IMG_5224.JPG").into(imgca  );
        //Main Email
        editTextEmail=(EditText)findViewById(R.id.edt_REmail);
        editTextSubject=(EditText)findViewById(R.id.edt_Subjectemail);
        editTextMessage=(EditText)findViewById(R.id.edt_Message);
        textViewEmail=(TextView)findViewById(R.id.tvEmail);
        textViewSubject=(TextView)findViewById(R.id.tvSubject);
        textViewMessage=(TextView)findViewById(R.id.tvMeassage);
        //Visibility Edittext Textview
        editTextEmail.setVisibility(View.GONE);
        editTextSubject.setVisibility(View.GONE);
        editTextMessage.setVisibility(View.GONE);
        textViewEmail.setVisibility(View.GONE);
        textViewSubject.setVisibility(View.GONE);
        textViewMessage.setVisibility(View.GONE);
        //Set data Edittext
        editTextEmail.setText("dotlun2411@gmail.com");
        editTextSubject.setText("Thông báo");
        editTextMessage.setText("Bạn có một đơn hàng.");
        Anhxa();
        ActionViewFliper();
        addControls();
        addEvents();
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_Restaurant:
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_Coffe:
                       // Intent icoffee = new Intent(MainActivity.this,Coffee.class);
                        //startActivity(icoffee);
                        break;
                    case R.id.action_add3:
                        //Intent intent2 = new Intent(MainActivity.this,Karaoke.class);
                       // startActivity(intent2);
                        break;
                }
                return true;
            }
        });

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
                sendEmail();
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
    private void sendEmail(){
        String email = editTextEmail.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email , subject , message);

        //Executing sendmail to send email
        sm.execute();
    }
    private void ActionViewFliper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("http://leerit.com/media/blog/uploads/2015/04/08/tu-vung-tieng-anh-ve-nha-hang.jpeg");
        mangquangcao.add("http://johnnytsbistroandblues.com/wp-content/uploads/2014/05/shrimp.jpg");
        mangquangcao.add("https://media-cdn.tripadvisor.com/media/photo-o/0a/56/44/5a/restaurant.jpg");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    public void Anhxa() {
        // toolbar = (Toolbar)findViewById(R.id.toolbarMain);
        viewFlipper = (ViewFlipper) findViewById(R.id.Viewfliper);


    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == R.id.action_settings)
        {
            Intent iLogin = new Intent(getApplicationContext(),Login.class);
            startActivity(iLogin);
        }
        return true;
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


