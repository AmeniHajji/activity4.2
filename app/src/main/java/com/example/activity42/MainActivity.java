package com.example.activity42;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.activity42.loginandregistersqlite.DatabaseHelper;
import com.example.activity42.loginandregistersqlite.Login;

public class MainActivity extends AppCompatActivity {
    com.example.activity42.loginandregistersqlite.DatabaseHelper db ;
    EditText email , password , pass ;
    Button button , button2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        email = (EditText) findViewById(R.id.email) ;
        password = (EditText) findViewById(R.id.password) ;
        pass= (EditText) findViewById(R.id.pass) ;
        button = (Button) findViewById(R.id.button) ;
        button2 = (Button) findViewById(R.id.button2) ;

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, Login.class) ;
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = email.getText().toString() ;
                String s2 = password.getText().toString() ;
                String s3 = pass.getText().toString() ;
                if(s1.equals("")|| s2.equals("") || s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                } else {
                    if (s2.equals(s3)){
                        Boolean chkemail = db.chkemail(s1) ;
                        if (chkemail){
                            Boolean insert= db.insert(s1,s2) ;
                            if (insert){
                                Toast.makeText(getApplicationContext(),"Register successfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Email Already exists",Toast.LENGTH_SHORT).show();

                        }
                    }
                    Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
