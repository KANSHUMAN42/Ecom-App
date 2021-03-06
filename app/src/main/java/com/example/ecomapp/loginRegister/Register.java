package com.example.ecomapp.loginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomapp.Customer.MainActivity;
import com.example.ecomapp.R;
import com.example.ecomapp.TestActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText tvname, tvemail, tvmobile, tvcpassword,tvusername;
    Button btnreg, btnsignin;
   private FirebaseDatabase rootnode;
   private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvname = findViewById(R.id.tvrname);
        tvemail = findViewById(R.id.tvremail);
        tvmobile= findViewById(R.id.tvrmobile);
        tvusername=findViewById(R.id.tvrusername);
        tvcpassword = findViewById(R.id.tvrconfirmp);
        btnreg = findViewById(R.id.btnrregister);

        btnsignin = findViewById(R.id.btnrlogin);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("users");
                String name = tvname.getText().toString();
                String email = tvemail.getText().toString();
                String password = tvcpassword.getText().toString();
                String number = tvmobile.getText().toString();
                String username=tvusername.getText().toString();
                //create user in database
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || number.isEmpty() || username.isEmpty()) {
                    tvname.setText("No field to remain empty");
                    return;
                } else {
                    Userdetails userdetails = new Userdetails(name, email, number, password,username);
                    reference.child(number).setValue(userdetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                               // Toast.makeText(getApplicationContext(),"Registration copleted successfully",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Register.this, MainActivity.class);
                                startActivity(i);
                                finish();
                                Toast.makeText(getApplicationContext(),"Registration completed",Toast.LENGTH_SHORT).show();
                            }else{

                                Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            }
        });
    }

}
