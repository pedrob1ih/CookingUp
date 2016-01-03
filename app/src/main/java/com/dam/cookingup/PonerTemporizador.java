package com.dam.cookingup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PonerTemporizador extends AppCompatActivity {
    private TextView tVNombrePlato,tVDescripcion,tVNumeroTiempo;
    private EditText eTCantidad;
    private Plato p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poner_temporizador);
        Bundle b=getIntent().getExtras();
        p = new Plato();
        p.setNombre(b.getString("nombre"));
        p.setTiempo(b.getDouble("tiempo"));
        p.setDescripcion(b.getString("descripcion"));

        tVNombrePlato= (TextView) findViewById(R.id.tVNombrePlato);
        tVDescripcion= (TextView) findViewById(R.id.tVDescripcion);
        eTCantidad= (EditText) findViewById(R.id.eTCantidad);
        tVNumeroTiempo= (TextView) findViewById(R.id.tVNumeroTiempo);
        eTCantidad.setOnKeyListener(onKeyListener);
        tVNombrePlato.setText(p.getNombre());
        tVDescripcion.setText(p.getDescripcion());
    }
    View.OnKeyListener onKeyListener= new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            try{
                tVNumeroTiempo.setText(String.valueOf(p.getTiempo() * Double.parseDouble(eTCantidad.getText().toString())));
            }
            catch (Exception e){
                ; 
            }

            return false;
        }
    };
}
