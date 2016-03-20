package com.contraslash.android.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.contraslash.android.pabloadventurequest.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ma0 on 9/1/15.
 */
public abstract class BaseActivity extends ActionBarActivity {

    protected String TAG;

    private SharedPreferences preferencias;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                        .setDefaultFontPath("fonts/Dosis/Dosis-Regular.ttf")
//                        .setFontAttrId(R.attr.fontPath)
//                        .build()
//        );

        setContentView(getLayoutResourceId());
        TAG = getLocalClassName();
        preferencias = PreferenceManager.getDefaultSharedPreferences(this);

        preferencias.edit().putInt("ID_USUARIO",1).putString("NOMBRE_USUARIO","MA0").apply();

        SharedPreferences.Editor editor = preferencias.edit();

        editor.putInt("ID_USUARIO",1);

        editor.putString("NOMBRE_USUARIO","MA0");

        editor.apply();

        int idUsuario = preferencias.getInt("ID_USUARIO",-1);

        mapGUI();

        loadEvents();
    }

    protected abstract int getLayoutResourceId();

    public SharedPreferences getPreferences() {
        return preferencias;
    }

    protected abstract void mapGUI();


    protected abstract void loadEvents();

    public void changeActivity(Class destino)
    {
        Intent cambioDeActividad = new Intent(this, destino);
        startActivity(cambioDeActividad);
    }

    public void changeActivity(Class destino, Bundle extras)
    {
        Intent cambioDeActividad = new Intent(this, destino);
        cambioDeActividad.putExtras(extras);
        startActivity(cambioDeActividad);
    }

    public void addListenerToChangeActivity(View view, final Class destino)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(destino);
            }
        });
    }
}
