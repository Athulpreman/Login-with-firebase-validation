package com.example.lginvalidation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText mail,pass;
    TextView tv;
    Button b;
    DatabaseReference ref;
    Regi reee;
    String lmail,lpass;
    String sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String s1=sharedPreferences.getString("namee",null);
        if (s1!=null)
        {
            Intent intent=new Intent(getApplicationContext(),LoggedIn.class);
            startActivity(intent);
        }

        mail=(EditText)findViewById(R.id.lmail);
        pass=(EditText)findViewById(R.id.lpassword);

        b=(Button)findViewById(R.id.login);

        tv=(TextView)findViewById(R.id.lregister);

        reee=new Regi();
        ref= FirebaseDatabase.getInstance().getReference().child("Register");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                lmail=mail.getText().toString();
                lpass=pass.getText().toString();
                Query query=ref.orderByChild("email").equalTo(lmail);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot logindata:dataSnapshot.getChildren())
                        {
                            reee =logindata.getValue(Regi.class);

                            sname=reee.name;
                            String spassword=reee.password;

                            if (spassword.equals(lpass))
                            {
                                Toast.makeText(getApplicationContext(),"Login Sucessfull",Toast.LENGTH_LONG).show();

                                SharedPreferences.Editor editor=getSharedPreferences("login",MODE_PRIVATE).edit();
                                editor.putString("namee",sname);
                                editor.commit();

                                Intent inte=new Intent(getApplicationContext(),LoggedIn.class);
                                inte.putExtra("name",sname);
                                startActivity(inte);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"incorrect password",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"No user exist with this mail id",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
    }
}
