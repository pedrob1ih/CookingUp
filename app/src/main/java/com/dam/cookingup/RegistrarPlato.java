package com.dam.cookingup;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrarPlato extends AppCompatActivity {

    private EditText eTNombre,eTiempo,eTPeso,eTDescripcion;
    private Button bRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_plato);

        eTNombre= (EditText) findViewById(R.id.eTNombre);
        eTiempo= (EditText) findViewById(R.id.eTTiempo);
        eTPeso= (EditText) findViewById(R.id.eTPeso);
        eTDescripcion= (EditText) findViewById(R.id.eTDescripcion);
        bRegistrar= (Button) findViewById(R.id.bRegistrar);
        bRegistrar.setOnClickListener(listRegistrar);


    }

    View.OnClickListener listRegistrar= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            i.putExtra("nombre",eTNombre.getText().toString());
            i.putExtra("tiempo",String.valueOf(Double.parseDouble(eTPeso.getText().toString())
                    /
                    (Double.parseDouble(eTiempo.getText().toString()))));
            i.putExtra("descripcion", eTDescripcion.getText().toString());
            setResult(Activity.RESULT_OK,i);
            finish();
        }
    };
}
