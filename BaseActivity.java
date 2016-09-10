package com.contraslash.android.base;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


/**
 * Created by ma0 on 9/1/15.
 * Modified by ma0 on 9/9/16
 */
public abstract class BaseActivity extends Activity {

    protected String TAG;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(getLayoutResourceId());
        TAG = getLocalClassName();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        mapGUI();

        loadEvents();
        
    }

    protected abstract int getLayoutResourceId();

    public SharedPreferences getPreferences() {
        return preferences;
    }

    protected abstract void mapGUI();


    protected abstract void loadEvents();

    public void changeActivity(Class destiny)
    {
        Intent changeActivity = new Intent(this, destiny);
        startActivity(changeActivity);
    }

    public void changeActivity(Class destiny, Bundle extras)
    {
        Intent changeActivity = new Intent(this, destiny);
        changeActivity.putExtras(extras);
        startActivity(changeActivity);
    }

    public void addListenerToChangeActivity(View view, final Class destiny)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(destiny);
            }
        });
    }
}
