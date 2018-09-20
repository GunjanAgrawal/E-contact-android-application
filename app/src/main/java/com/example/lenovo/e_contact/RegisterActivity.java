package com.example.lenovo.e_contact;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button b1;
    EditText t1,t2,t3,t4,t5,t6;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHandler(this);
        b1 = (Button) findViewById(R.id.b1);
        t1=(EditText)findViewById(R.id.t1);
        t2=(EditText)findViewById(R.id.t2);
        t3=(EditText)findViewById(R.id.t3);
        t4=(EditText)findViewById(R.id.t4);
        t5=(EditText)findViewById(R.id.t5);
        t6=(EditText)findViewById(R.id.t6);


        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        AddData();



    }
    public void AddData(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=t1.getText().toString();
                String s2=t2.getText().toString();
                String s3=t3.getText().toString();
                String s4=t4.getText().toString();
                String s5=t5.getText().toString();
                String s6=t6.getText().toString();
                if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("")){
                    Toast.makeText(RegisterActivity.this,"please fill all the fields",Toast.LENGTH_LONG).show();
                }
                else {
                    boolean isInsertedto = db.insertData(s1, s2, s3, s4, s5, s6);

                    if (isInsertedto == true) {
                        Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();
                    } else
                        Toast.makeText(RegisterActivity.this, "Registration not successful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

