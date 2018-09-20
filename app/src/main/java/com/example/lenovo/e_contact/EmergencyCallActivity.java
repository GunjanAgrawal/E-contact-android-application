package com.example.lenovo.e_contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EmergencyCallActivity extends AppCompatActivity {

    TextView t1,t2;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    String n1,n2,n11,n22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);

        openHelper=new DatabaseHandler(this);
        db=openHelper.getReadableDatabase();

        t1=(TextView)findViewById(R.id.name1);
        t2=(TextView)findViewById(R.id.name2);


       Cursor c1 = db.rawQuery("SELECT " + DatabaseHandler.COL_3 + " FROM " + DatabaseHandler.TABLE_CONTACTS , null);
        if(c1.getCount()>0) {
            c1.moveToFirst();
            n1 = c1.getString(c1.getColumnIndex(DatabaseHandler.COL_3));
        }
        t1.setText(n1);

        Cursor c2 = db.rawQuery("SELECT " + DatabaseHandler.COL_5 + " FROM " + DatabaseHandler.TABLE_CONTACTS , null);
        if(c2.getCount()>0) {
            c2.moveToFirst();
             n2 = c2.getString(c2.getColumnIndex(DatabaseHandler.COL_5));
        }
        t2.setText(n2);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c11 = db.rawQuery("SELECT " + DatabaseHandler.COL_4 + " FROM " + DatabaseHandler.TABLE_CONTACTS , null);
                if(c11.getCount()>0) {
                    c11.moveToFirst();
                    n11 = c11.getString(c11.getColumnIndex(DatabaseHandler.COL_4));
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+n11));
                    if(ActivityCompat.checkSelfPermission(EmergencyCallActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                    {
                        return;
                    }
                    startActivity(intent);

                }
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c22 = db.rawQuery("SELECT " + DatabaseHandler.COL_6 + " FROM " + DatabaseHandler.TABLE_CONTACTS , null);
                if(c22.getCount()>0) {
                    c22.moveToFirst();
                    n22 = c22.getString(c22.getColumnIndex(DatabaseHandler.COL_6));
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+n22));
                    if(ActivityCompat.checkSelfPermission(EmergencyCallActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                    {
                        return;
                    }
                    startActivity(intent);

                }
            }
        });
    }
}
