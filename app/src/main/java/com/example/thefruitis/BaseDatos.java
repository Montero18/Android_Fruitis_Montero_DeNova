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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class BaseDatos extends AppCompatActivity {

    String txt1, txt2, txt3, txt4;

    ListView tabla = findViewById(R.id.tablaDatos);

    SQLiteDatabase db;
    Helper helper = new Helper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void mostrarLista(){

        db = helper.getReadableDatabase();
        Cursor cursor = db.query("fruitis", null, null, null, null, null, null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            txt1 = cursor.getString(1);
            txt2 = cursor.getString(2);
            txt3 = cursor.getString(3);
            txt4 = cursor.getString(4);


            tabla.append("\n" + txt1 + " " + txt2 + " " + txt3 + " " + txt4);
            cursor.moveToNext();
        }
    }

}
