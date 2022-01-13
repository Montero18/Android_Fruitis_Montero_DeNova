package com.example.thefruitis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables
    EditText nombre, peso, buscarNombre;
    Spinner listaSabores;
    CheckBox podrido;

    SQLiteDatabase db;
    Helper helper;



    ListView tabla = findViewById(R.id.tablaDatos);
    String nombreElegido;
    String txt1, txt2, txt3, txt4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent i1 = new Intent(this, ActividadDelete.class);
                startActivity(i1);
                break;
            case R.id.item2:
                Intent i2 = new Intent(this, BaseDatos.class);
                startActivity(i2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void añadir (View view){

        nombre = findViewById(R.id.nombre);
        peso = findViewById(R.id.peso);
        listaSabores = findViewById(R.id.spinner);
        podrido = findViewById(R.id.podrido);

        if(nombre.getText().toString().isEmpty() || peso.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Rellene los campos de textos", Toast.LENGTH_LONG);
            toast.show();
        }else{
            db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("Nombre",nombre.getText().toString());
            values.put("Peso",peso.getText().toString() + " ");
            values.put("Sabor",listaSabores.getSelectedItem().toString() + " ");

            if(podrido.isChecked()){
                values.put("Podrido", " Si ");
            }else{
                values.put("Podrido", " No ");
            }

            db.insert("fruitis", null, values);

            Toast.makeText(this,"Insert Realizado", Toast.LENGTH_SHORT).show();

        }
        nombre.setText("");
        peso.setText("");


    }

    public void listarUltimo (View view) {

        db = helper.getReadableDatabase();
        Cursor cursor = db.query("fruitis", null, null, null, null, null, null);
        cursor.moveToLast();

            txt1 = cursor.getString(1);
            txt2 = cursor.getString(2);
            txt3 = cursor.getString(3);
            txt4 = cursor.getString(4);


            tabla.append("\n" + txt1 + " " + txt2 + " " + txt3 + " " + txt4);
            cursor.moveToNext();

    }

    public void listarNombre (View view) {

        buscarNombre = findViewById(R.id.nombreListar);

        db = helper.getReadableDatabase();
        Cursor cursor = db.query("fruitis", null, null, null, null, null, null);
        cursor.moveToFirst();


        for (int i = 0; i < cursor.getCount(); i++) {

            if(txt1.equalsIgnoreCase(buscarNombre.getText().toString())){
                txt1 = cursor.getString(1);
                txt2 = cursor.getString(2);
                txt3 = cursor.getString(3);
                txt4 = cursor.getString(4);

                tabla.append("\n" + txt1 + " " + txt2 + " " + txt3 + " " + txt4);
                cursor.moveToNext();
            }
        }

    }

}