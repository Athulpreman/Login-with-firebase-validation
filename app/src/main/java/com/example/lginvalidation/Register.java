package com.example.lginvalidation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText name,phone,place,email,password;
    Button b1,b2;
    String rname,rphone,rplace,remail,rpassword;
    Regi regi;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=(EditText)findViewById(R.id.rname);
        phone=(EditText)findViewById(R.id.rphone);
        place=(EditText)findViewById(R.id.rplace);
        email=(EditText)findViewById(R.id.rmail);
        password=(EditText)findViewById(R.id.rpassword);

        b1=(Button)findViewById(R.id.rsignup);
        b2=(Button)findViewById(R.id.signin);

        regi=new Regi();

        reference= FirebaseDatabase.getInstance().getReference().child("Register");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rname=name.getText().toString();
                rphone=phone.getText().toString();
                rplace=place.getText().toString();
                remail=email.getText().toString();
                rpassword=password.getText().toString();

                if (rname.isEmpty())
                {
                    name.setError("name is required");
                    name.requestFocus();
                }
                else if (rphone.isEmpty())
                {
                    phone.setError("name is required");
                    phone.requestFocus();
                }
                else if (rplace.isEmpty())
                {
                    place.setError("name is required");
                    place.requestFocus();
                }
                else if (remail.isEmpty())
                {
                    email.setError("name is required");
                    email.requestFocus();
                }
                else if (rpassword.isEmpty())
                {
                    password.setError("name is required");
                    password.requestFocus();
                }
                else {

                    regi.setName(rname);
                    regi.setPhone(rphone);
                    regi.setPlace(rplace);
                    regi.setEmail(remail);
                    regi.setPassword(rpassword);


                    reference.push().setValue(regi).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"register successfully",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }



            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
