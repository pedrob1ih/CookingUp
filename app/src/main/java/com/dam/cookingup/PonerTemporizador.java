package com.dam.cookingup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PonerTemporizador extends AppCompatActivity {
    private TextView tVNombrePlato,tVDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poner_temporizador);
        Bundle b=getIntent().getExtras();
        Plato p = new Plato();
        p.setNombre(b.getString("nombre"));
        p.setTiempo(b.getDouble("tiempo"));
        p.setDescripcion(b.getString("descripcion"));

        tVNombrePlato= (TextView) findViewById(R.id.tVNombrePlato);
        tVDescripcion= (TextView) findViewById(R.id.tVDescripcion);
        tVNombrePlato.setText(p.getNombre());
        tVDescripcion.setText(p.getDescripcion());
    }
}
