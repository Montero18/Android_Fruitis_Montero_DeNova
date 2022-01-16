package com.example.thefruitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadDelete extends AppCompatActivity {

    EditText eliminarNombre;
    String concat;

    SQLiteDatabase db;
    Helper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_delete);

        helper = new Helper(this);
        db = helper.getWritableDatabase();
    }

    public void eliminarPorNombre (View view) {

        eliminarNombre = findViewById(R.id.editTextEliminarNombre);
        String nombreEliminar = eliminarNombre.getText().toString();
        int cant = db.delete("fruitis", "nombre = '" + nombreEliminar + "'", null);
        db.close();

        if(cant != 0) {
            Toast toast = Toast.makeText(this, "Borrado Completado", Toast.LENGTH_LONG);
            toast.show();
        }else {
            Toast toast = Toast.makeText(this, "No se ha podido borrar", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void pasarActividad (View view) {
        Intent i = new Intent(this, BaseDatos.class);
        startActivity(i);
    }

}