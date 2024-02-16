package com.example.pm1e12921;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pm1e12921.Configuracion.SQLiteConexion;
import com.example.pm1e12921.Configuracion.Transaciones;
import com.example.pm1e12921.Models.Personas;

import java.util.ArrayList;

public class ActivitySalvados extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listpersonas;
    ArrayList<Personas> Lista;
    ArrayList<String>Arreglo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvados);
        conexion = new SQLiteConexion(this, Transaciones.DBName, null, 1); //amara o castea las clases
        listpersonas = (ListView) findViewById(R.id.listpersonas);

        ObtenerInfo();

        listpersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personas personaSeleccionada = Lista.get(position);
                Intent intent = new Intent(ActivitySalvados.this, MainActivity.class);

                intent.putExtra("id", personaSeleccionada.getId().toString());
                intent.putExtra("pais", personaSeleccionada.getPais());
                intent.putExtra("nombres", personaSeleccionada.getNombre());
                intent.putExtra("telefono", personaSeleccionada.getTelefono());
                intent.putExtra("nota", personaSeleccionada.getNota());

                startActivity(intent);

            }

        });



        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,Arreglo);
        listpersonas.setAdapter(adp);


        listpersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String ElementoSeleccionado =(String) parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),
                        "Seleccionaste " + ElementoSeleccionado, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void ObtenerInfo() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas person = null;
        Lista = new ArrayList<>();
        Arreglo = new ArrayList<>();

        Cursor cursor = db.rawQuery(Transaciones.SelectAllPersonas, null);

        while(cursor.moveToNext()) {
            person = new Personas();
            person.setId(cursor.getInt(0));
            person.setPais(cursor.getString(1));
            person.setNombre(cursor.getString(2));
            person.setTelefono(cursor.getInt(3));
            person.setNota(cursor.getString(4));

            Lista.add(person);
            Arreglo.add(person.getId() + " - " + person.getPais()+ " - " + person.getNombre()+ " - " + person.getTelefono() + " - " + person.getNota());
        }
        cursor.close();

        ArrayAdapter adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arreglo);
        listpersonas.setAdapter(adp);
    }


}