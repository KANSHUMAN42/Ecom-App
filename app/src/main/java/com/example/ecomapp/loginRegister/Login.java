package com.example.ecomapp.loginRegister;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomapp.Admin.Admin;
import com.example.ecomapp.R;
import com.example.ecomapp.TestActivity;
import com.example.ecomapp.Customer.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class Login extends AppCompatActivity {
Button btnregister,btnlogin,btnadminlogin;
    EditText tvemail,tvlpassword;
    ProgressBar Progressbar;
    CheckBox rememberme;

    static boolean isuser=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       btnregister=findViewById(R.id.btnlregister);
       btnlogin=findViewById(R.id.btnlogin);
       tvemail=findViewById(R.id.tvlemail);
       tvlpassword=findViewById(R.id.tvlpassword);
       Progressbar=findViewById(R.id.progressbar);
       rememberme=findViewById(R.id.check);

       Paper.init(this);
       btnadminlogin=findViewById(R.id.btnadminlogin);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
        btnadminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isuser) {
                    btnlogin.setText(R.string.Admin_login);
                    btnadminlogin.setText("User?");
                    isuser=false;
                }else{
                    btnlogin.setText("Users Login");
                    btnadminlogin.setText("Admin?");
                    isuser=true;
                }
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Number=tvemail.getText().toString();
                 String password= tvlpassword.getText().toString();
                if(TextUtils.isEmpty(Number)){
                    Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isuser){
                    Progressbar.setVisibility(View.VISIBLE);
                    verifyuserlogin(Number,password);
                }else{
                    Progressbar.setVisibility(View.VISIBLE);
                    verifyadminlogin(Number,password);
                }

    }
    });

    }
    public void verifyuserlogin(final String Number,final String pass){
        final String userhai="yes";

        if(rememberme.isChecked()){
            Paper.book().write(prevalent.userphonekey,Number);
            Paper.book().write(prevalent.userpassword,pass);

        }

        final DatabaseReference rootref=FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("users").exists()){
                    Userdetails userdetails=snapshot.child("users").child(Number).getValue(Userdetails.class);
                    assert userdetails != null;
                    if(userdetails.getNumber().equals(Number) && userdetails.getPassword().equals(pass)){
                        Intent i=new  Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(Login.this,"Reenter details",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Login.this,"acaount with this number doesnot exists",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void verifyadminlogin(final String Number, final String pass){
         // FirebaseDatabase firebaseDatabase;



          final DatabaseReference rootref=FirebaseDatabase.getInstance().getReference();
          rootref.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  if(snapshot.child("Admins").child(Number).exists()){
                   Userdetails userdetails=snapshot.child("Admins").child(Number).getValue(Userdetails.class);
                   if(userdetails.getNumber().equals(Number) && userdetails.getPassword().equals(pass)){
                       Intent i=new  Intent(getApplicationContext(), Admin.class);
                       startActivity(i);
                   }else{
                       Toast.makeText(Login.this,"Reenter details",Toast.LENGTH_SHORT).show();
                   }
                  }
                  else{
                      Toast.makeText(Login.this,"acaount with this number doesnot exists",Toast.LENGTH_SHORT).show();
                  }
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });
    }

}