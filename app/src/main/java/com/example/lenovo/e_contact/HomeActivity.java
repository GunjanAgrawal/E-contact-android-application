package com.example.lenovo.e_contact;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    ImageButton button1,button2,button3,button4,button5,button6;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button1=(ImageButton)findViewById(R.id.ambulance_button);
        button2=(ImageButton)findViewById(R.id.firestation_button);
        button3=(ImageButton)findViewById(R.id.police_button);
        button4=(ImageButton)findViewById(R.id.bloodbank_button);
        button5=(ImageButton)findViewById(R.id.emergency_button);
        button6=(ImageButton)findViewById(R.id.Location_button);

        session=new Session(this);

        if(!session.loggedin())
        {
            logout();
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+102));
                if(ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+101));
                if(ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+100));
                if(ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }
                startActivity(intent);

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(HomeActivity.this,EmergencyCallActivity.class));
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MapsActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
               startActivity(new Intent(HomeActivity.this,EditActivity.class));
                return true;
            case R.id.logout:
              logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
 public void logout(){
     session.setLoggedin(false);
     finish();
     startActivity(new Intent(HomeActivity.this,MainActivity.class));
 }
}
