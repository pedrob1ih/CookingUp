package com.dam.cookingup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Fichero f;
    private ArrayList listaPlatos;
    private final int EDIT_MODE=0;
    private final int READ_MODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.lVPlatos);

        f= new Fichero(getApplicationContext(),"DatosPlatos");

        try{
            listaPlatos=f.cargar();
        }
        catch (Exception e){
            listaPlatos= new ArrayList();
            Plato p= new Plato();
            listaPlatos.add(p);
            f.guardar(listaPlatos);
        }
        ArrayAdapter arrayAdapter= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,listaPlatos);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(listListView);
        listView.setOnItemLongClickListener(oLCListView);
    }

    @Override
    protected void onDestroy() {
        f.guardar(listaPlatos);
        super.onDestroy();
    }

    AdapterView.OnItemClickListener listListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Plato p = (Plato) listaPlatos.get(position);;
            Intent i= new Intent(getApplicationContext(),PonerTemporizador.class);
            i.putExtra("nombre", p.getNombre());
            i.putExtra("tiempo",p.getTiempo());
            i.putExtra("descripcion", p.getDescripcion());
            startActivity(i);
        }
    };
    AdapterView.OnItemLongClickListener oLCListView= new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            PopupMenu popo=new PopupMenu(getApplicationContext(),view);
            MenuInflater inflater= popo.getMenuInflater();
            inflater.inflate(R.menu.menu_onlongclick, popo.getMenu());
            popo.show();
            return false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Plato p = new Plato();
                Bundle b = data.getExtras();
                p.setNombre(b.getString("nombre"));
                p.setTiempo(Double.parseDouble(b.getString("tiempo")));
                p.setDescripcion(b.getString("descripcion"));
                listaPlatos.add(p);
                ArrayAdapter arrayAdapter= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,listaPlatos);
                listView.setAdapter(arrayAdapter);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.registrar) {
            Intent i = new Intent(this,RegistrarPlato.class);
            startActivityForResult(i, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
