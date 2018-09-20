package com.example.lenovo.e_contact;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView register;
    Button login;
    EditText name, num;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatabaseHandler dbh;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHandler(this);
        db = openHelper.getReadableDatabase();
        dbh=new DatabaseHandler(this);

        session=new Session(this);

        register = (TextView) findViewById(R.id.tv1);
        login = (Button) findViewById(R.id.bt1);
        name = (EditText) findViewById(R.id.et1);
        num = (EditText) findViewById(R.id.et2);

        if(session.loggedin()){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na = name.getText().toString();
                String no = num.getText().toString();
                if(dbh.getUser(na,no)){
                    session.setLoggedin(true);
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this,"Login unsuccessful",Toast.LENGTH_LONG).show();;

                }
                /*Cursor cursor=db.rawQuery("SELECT * FROM " + DatabaseHandler.TABLE_CONTACTS + " WHERE " + DatabaseHandler.COL_1 + "=? AND " + DatabaseHandler.COL_2 + "=?" ,new String[]{na,no});

                if(cursor!=null){
                    if(cursor.getCount()>0)
                    {
                        cursor.moveToNext();
                        Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Login unsuccessful",Toast.LENGTH_LONG).show();
                    }
                }
            }*/
            }

        });
    }
}