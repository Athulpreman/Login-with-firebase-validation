package com.example.lginvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedIn extends AppCompatActivity {
    TextView t1;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String s1=sharedPreferences.getString("namee",null);


        t1=(TextView)findViewById(R.id.name);
        b=(Button)findViewById(R.id.logout);


        t1.setText(s1);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor=getSharedPreferences("login",MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();

                Intent in=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
        });
    }
}
