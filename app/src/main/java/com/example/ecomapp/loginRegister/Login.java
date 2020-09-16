package com.example.ecomapp.loginRegister;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomapp.R;
import com.example.ecomapp.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
Button btnregister,btnlogin,btnadminlogin;
    EditText tvemail,tvlpassword;
    ProgressBar Progressbar;

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
                String Number=tvemail.getText().toString().trim();
                final String password= tvlpassword.getText().toString().trim();
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
                    verifyuserlogin( Number,password );
                }else{
                    Progressbar.setVisibility(View.VISIBLE);
                    verifyadminlogin(Number,password);
                }

    }
            public void verifyadminlogin(final String Number, final String pass){

                Query checkuser= FirebaseDatabase.getInstance().getReference("Admins").orderByChild("number").equalTo(Number);
                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            tvemail.setError(null);
                            String givenpassword=snapshot.child(pass).child("password").getValue(String.class);

                            assert givenpassword != null;
                            if(givenpassword.equals(pass)) {
                                tvlpassword.setError(null);
                                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);

                            }
                        }
                        else{
                            Toast.makeText(Login.this,"password doesnt match",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
            public void verifyuserlogin(final String Number, final String pass){


                Query checkuser= FirebaseDatabase.getInstance().getReference("users").orderByChild("number").equalTo(Number);
                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            tvemail.setError(null);
                            // tvemail.setErrorEnabled(false);
                            String givenpassword=snapshot.child(pass).child("password").getValue(String.class);

                            if(givenpassword.equals(pass)) {

                                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }
                        }
                        else{
                            Toast.makeText(Login.this,"password doesnt match",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }

    });

    }

}