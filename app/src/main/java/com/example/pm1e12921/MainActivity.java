package com.example.pm1e12921;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pm1e12921.Configuracion.SQLiteConexion;
import com.example.pm1e12921.Configuracion.Transaciones;

public class MainActivity extends AppCompatActivity {
    EditText nombres, telefono, nota;
    Spinner pais;
    Button btnsalvar,btnsalvados;


    String idPersona = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pais = (Spinner) findViewById(R.id.pais);
        nombres = (EditText) findViewById(R.id.nombre);
        telefono = (EditText) findViewById(R.id.telefono);
        nota = (EditText) findViewById(R.id.nota);
        btnsalvar = (Button) findViewById(R.id.btnsalvar);
        btnsalvados = (Button) findViewById(R.id.btnsalvados);


        String[] paises = {"Honduras(+504)", "Guatemala(+502)", "Costa Rica(+506)","Belize(+501)","Nicaragua(+505)","Panamá(+507)","El Salvador(+503)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pais.setAdapter(adapter);

        btnsalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddPerson();
            }
        });

        btnsalvados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivitySalvados.class);
                startActivity(intent);
            }
        });



    }

    private void AddPerson() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transaciones.DBName, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transaciones.pais, pais.getSelectedItem().toString());
        valores.put(Transaciones.nombre, nombres.getText().toString());
        valores.put(Transaciones.telefono, telefono.getText().toString());
        valores.put(Transaciones.nota, nota.getText().toString());



        Long resultado = db.insert(Transaciones.Tablepersonas, Transaciones.id, valores);


        Toast.makeText(getApplicationContext(), "Registro Ingresado Correctamente "+ resultado.toString(),
                Toast.LENGTH_SHORT).show();

        db.close();
        nombres.setText("");
        telefono.setText("");
        nota.setText("");

    }

}