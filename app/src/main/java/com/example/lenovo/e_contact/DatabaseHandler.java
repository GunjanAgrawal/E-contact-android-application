package com.example.lenovo.e_contact;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 27-07-2018.
 */
    public class DatabaseHandler extends SQLiteOpenHelper  {
        // All Static variables
        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        public static final String DATABASE_NAME = "contactsManager";

        // Contacts table name
        public static final String TABLE_CONTACTS = "contacts";

        // Contacts Table Columns names
        public static final String KEY_ID = "id";
        public static final String COL_1 = "name";
        public static final String COL_2 = "phone";//phone no
        public static final String COL_3 = "ename1";//name1
        public static final String COL_4= "enum1";//number1
        public static final String COL_5 = "ename2";//name2
        public static final String COL_6= "enum2";//number2

        public DatabaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(" create table " + TABLE_CONTACTS + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, ename1 TEXT, enum1 TEXT, ename2 TEXT, enum2 TEXT)");
        }

        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            // Create tables again
            onCreate(db);
        }
        public boolean insertData(String name,String p,String n1,String num1,String n2,String num2){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues values = new ContentValues();
            // Contact Name
            values.put(COL_1, name);
            values.put(COL_2,p);
            values.put(COL_3,n1);
            values.put(COL_4,num1);
            values.put(COL_5,n2);
            values.put(COL_6,num2);
            long result= db.insert(TABLE_CONTACTS, null,values);
            if(result==-1)
                return false;
            else
                return true;

        }

        // Getting All Contacts


   public boolean getUser(String name,String num)
   {
       String selquery = "select * from " + TABLE_CONTACTS + " where " + COL_1 + " = " + "'"+name+"'" + " and " + COL_2 + " = " + "'"+num+"'";
       SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor=db.rawQuery(selquery,null);
       cursor.moveToFirst();
       if(cursor.getCount()>0)
       {
           return true;
       }
       cursor.close();
       db.close();
       return false;
   }
   public boolean updateData(String name,String p,String n1,String num1,String n2,String num2)
   {
       SQLiteDatabase db=this.getWritableDatabase();
       ContentValues values=new ContentValues();
       values.put(COL_1,name);
       values.put(COL_2,p);
       values.put(COL_3,n1);
       values.put(COL_4,num1);
       values.put(COL_5,n2);
       values.put(COL_6,num2);

       db.update(TABLE_CONTACTS,values," id = 1 ",null);
       return true;

   }

    }


