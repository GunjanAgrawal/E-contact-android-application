package com.example.lenovo.e_contact;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 28-07-2018.
 */

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx)
    {
        this.ctx=ctx;
        prefs=ctx.getSharedPreferences("eContact",Context.MODE_PRIVATE);
        editor=prefs.edit();
    }
    public void setLoggedin(boolean loggedin)
    {
        editor.putBoolean("loggedInmode",loggedin);
        editor.commit();
    }
    public boolean loggedin()
    {
        return prefs.getBoolean("loggedInmode",false);
    }
}
