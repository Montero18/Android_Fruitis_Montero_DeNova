package com.example.thefruitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class BaseDatos extends AppCompatActivity {

    String txt1, txt2, txt3, txt4, concat;

    ListView tabla;
    ArrayList <String> lista;

    SQLiteDatabase db;
    Helper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos);

        tabla = findViewById(R.id.tablaDatos);
        lista = new ArrayList<String>();
        helper = new Helper(this);

        db = helper.getReadableDatabase();
        Cursor cursor = db.query("fruitis", null, null, null, null, null, null);
        cursor.moveToFirst();

        concat = "";

        for (int i = 0; i < cursor.getCount(); i++) {
            concat = concat + cursor.getString(1) + "        -->    |        ";
            concat = concat + cursor.getString(2) + " g       |    ";
            concat = concat + cursor.getString(3) + "      |      ";
            concat = concat + cursor.getString(4) + "     |      ";

            lista.add(concat);

            cursor.moveToNext();

            concat = " ";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        tabla.setAdapter(adapter);
    }

   /* public void mostrarLista(){

        db = helper.getReadableDatabase();
        Cursor cursor = db.query("fruitis", null, null, null, null, null, null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            lista[0] = cursor.getString(1);
            lista[1] = cursor.getString(2);
            lista[2] = cursor.getString(3);
            lista[3] = cursor.getString(4);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
            tabla.setAdapter(adapter);

            cursor.moveToNext();
        }
    }*/

}
