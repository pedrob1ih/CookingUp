package com.dam.cookingup;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Objects;

public class Fichero {
    private String nombre;
    private Context context;

    public Fichero(Context context, String nombre) {
        this.context = context;
        this.nombre = nombre;
    }

    public void guardar(ArrayList lPlato){
        String imputFichero="";
        for(Object o: lPlato){
            imputFichero+=((Plato)o).getNombre();
            imputFichero+=",";
            imputFichero+=((Plato)o).getTiempo();
            imputFichero+=",";
            imputFichero+=((Plato)o).getDescripcion();
            imputFichero+=",";
        }
        try
        {
            OutputStreamWriter fout=
                    new OutputStreamWriter(this.context.openFileOutput(nombre, Context.MODE_PRIVATE));
            fout.write(imputFichero);
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("guardar", ex.getMessage());
        }
    }
    public ArrayList cargar(){
        ArrayList lPlato= new ArrayList();
        String platos="";
        try {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    context.openFileInput(nombre)));

            platos = fin.readLine();
            fin.close();
        } catch (Exception ex) {
            Log.e("cargar", ex.getMessage());
        }
        try{
            String aPlatos[]=platos.split(",");
            if(aPlatos.length>2)
                for(int i=0;i<aPlatos.length;i++){
                    Plato p= new Plato();
                    p.setNombre(aPlatos[i]);
                    i++;
                    p.setTiempo(Double.parseDouble(aPlatos[i]));
                    i++;
                    p.setDescripcion(aPlatos[i]);
                    lPlato.add(p);
                }
        }
        catch (Exception e){
            ;
        }

        return lPlato;
    }
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getNombre() {return nombre;}
}
