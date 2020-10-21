package com.example.ecomapp.loginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ecomapp.Admin.Admin;
import com.example.ecomapp.R;
import com.example.ecomapp.Customer.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class Splash_firstActivity extends AppCompatActivity {
   Button splash_button,btn_adminenter;
   ProgressBar pgbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_first);
        splash_button =findViewById(R.id.btn_splash_enter);
        pgbar=findViewById(R.id.pgbar);
        btn_adminenter=findViewById(R.id.btn_adminenter);
      Paper.init(this);
      //Paper.book().destroy()
        pgbar.setVisibility(View.GONE);



        splash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userphonenum= Paper.book().read(prevalent.userphonekey);
                String userpassword=Paper.book().read(prevalent.userpassword);
                if(userphonenum !="" && userpassword != ""){
                    if(!TextUtils.isEmpty(userphonenum) && !TextUtils.isEmpty(userpassword)){
                        Allowexcess(userphonenum, userpassword);
                    }
                }
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);


            }
        });
        btn_adminenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),Admin_login.class);
                startActivity(intent);

            }
        });


    }

    private void Allowexcess(final String Number, final String pass) {


        final DatabaseReference rootref= FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("users").exists()){
                    Userdetails userdetails=snapshot.child("users").child(Number).getValue(Userdetails.class);
                    assert userdetails != null;
                    if(userdetails.getNumber().equals(Number) && userdetails.getPassword().equals(pass)){
                        pgbar.setVisibility(View.GONE);
                        Intent i=new  Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();

                    }else{
                        Intent i=new Intent(getApplicationContext(),Login.class);
                        startActivity(i);
                    }
                }
                else{
                    Intent i=new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}