package com.example.dotlun.demobook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    ImageButton imgSignUp;
    private EditText et_username, et_password;
    private String username,password;
    private Button btnsignin;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar)findViewById(R.id.loginbar);
        setSupportActionBar(toolbar);
        //Back
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //imgSignUp = (ImageButton) findViewById(R.id.imageSignUp);
        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        btnsignin = (Button) findViewById(R.id.btnsignin);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();

            }
        });
      /*  imgSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });*/


    }

    public void signin(){
        intialize();
        if (!validate()){
            Toast.makeText(this,"Sign In Failed",Toast.LENGTH_SHORT).show();
        }else {
            onSigninSucces();
        }
    }
    public void onSigninSucces(){
        // Validates the User name and Password for admin, admin
        if (et_username.getText().toString().equals("admin") && et_password.getText().toString().equals("admin")) {
            //  Toast.makeText(getApplicationContext(), "Login Sc", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),ListKhachHang.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Login fail", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean validate(){
        boolean valid = true;
        if (username.isEmpty()){
            et_username.setError("Please Enter Username");
            valid = false;
        }
        if (password.isEmpty()){
            et_password.setError("Please Enter Password");
            valid = false;
        }
        return valid;
    }
    public void intialize(){
        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    }
