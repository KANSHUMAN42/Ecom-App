package com.example.ecomapp.loginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ecomapp.Admin.Admin;
import com.example.ecomapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_login extends AppCompatActivity {
  EditText tvphonenum,tvpassword;
Button btnadmlogin;
ProgressBar Progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

   tvphonenum=findViewById(R.id.tvadmin_phonenum);
   tvpassword=findViewById(R.id.tvadmin_password);
   btnadmlogin=findViewById(R.id.btn_adminlogin);
   Progressbar=findViewById(R.id.aprogressbar);

   btnadmlogin.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           String Number=tvphonenum.getText().toString();
           String password= tvpassword.getText().toString();
           if(TextUtils.isEmpty(Number)){
               Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
               return ;
           }
           if (TextUtils.isEmpty(password)) {
               Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
               return;
           }else {

               Progressbar.setVisibility(View.VISIBLE);
               verifyadminlogin(Number, password);
               Progressbar.setVisibility(View.GONE);
           }
           }
       
   });
    }

    private void verifyadminlogin(final String Number,final String pass) {
        final DatabaseReference rootref= FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Admins").child(Number).exists()){
                    Userdetails userdetails=snapshot.child("Admins").child(Number).getValue(Userdetails.class);
                    if(userdetails.getNumber().equals(Number) && userdetails.getPassword().equals(pass)){
                        Intent i=new  Intent(getApplicationContext(), Admin.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(Admin_login.this,"Reenter details",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Admin_login.this,"acaount with this number doesnot exists",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}