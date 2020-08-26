package com.example.ecomapp.loginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText tvname,tvemail,tvpassword,tvcpassword;
   Button btnreg,btnsignin;
   private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvname = findViewById(R.id.tvrname);
        tvemail = findViewById(R.id.tvremail);
        tvpassword = findViewById(R.id.tvrpassword);
        tvcpassword = findViewById(R.id.tvrconfirmp);
        btnreg = findViewById(R.id.btnrregister);
        mAuth = FirebaseAuth.getInstance();
        btnsignin = findViewById(R.id.btnrlogin);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=tvname.getText().toString().trim();
                String email = tvemail.getText().toString().trim();
                String password = tvcpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //create user in database
                //createaccount();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                     Toast.makeText(getApplicationContext(), "User id is created successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Register.this, Login.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
//    public void createaccount(){
//        String name=tvname.getText().toString();
//        String email= tvemail.getText().toString();
//        String password=tvpassword.getText().toString();
//        validatename(name,email,password);
//    }
//    public void validatename(final String name, final String email, final String password){
//        final DatabaseReference RootRef;
//        RootRef= FirebaseDatabase.getInstance().getReference();
//        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(!(snapshot.child("Users").child(email).exists())){
//                    HashMap<String,Object>userdatamap=new HashMap<>();
//                    userdatamap.put("name",name);
//                    userdatamap.put("email",email);
//                    userdatamap.put("password",password);
//                    RootRef.child("Users").child(email).updateChildren(userdatamap)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(task.isSuccessful()) {
//                                        Toast.makeText(getApplicationContext(), "User id created in database", Toast.LENGTH_SHORT).show();
//                                    Intent i =new Intent(getApplicationContext(),Login.class);
//                                    startActivity(i);
//                                    }
//                                else{
//                                        Toast.makeText(getApplicationContext(),"Some error occured in datababe",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//
//                }else{
//                    Toast.makeText(Register.this,"User already exists",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//              Toast.makeText(Register.this,"error occured ",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }


}