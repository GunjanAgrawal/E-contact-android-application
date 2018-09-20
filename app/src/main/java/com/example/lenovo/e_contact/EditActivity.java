package com.example.lenovo.e_contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    Button edit;
    TextView t1, t2, t3, t4, t5, t6;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edit = (Button) findViewById(R.id.b1);
        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);
        t3 = (EditText) findViewById(R.id.t3);
        t4 = (EditText) findViewById(R.id.t4);
        t5 = (EditText) findViewById(R.id.t5);
        t6 = (EditText) findViewById(R.id.t6);
        db = new DatabaseHandler(this);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = t1.getText().toString();
                String s2 = t2.getText().toString();
                String s3 = t3.getText().toString();
                String s4 = t4.getText().toString();
                String s5 = t5.getText().toString();
                String s6 = t6.getText().toString();
                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("")) {
                    Toast.makeText(EditActivity.this, "please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    boolean isUpdatedto = db.updateData(s1, s2, s3, s4, s5, s6);

                    if (isUpdatedto == true) {
                        Toast.makeText(EditActivity.this, "Details updated successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(EditActivity.this, HomeActivity.class));
                        finish();
                    } else
                        Toast.makeText(EditActivity.this, "Details not updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
