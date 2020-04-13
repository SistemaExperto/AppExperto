package com.example.ejemploappexperto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainSinAcceso extends AppCompatActivity {
//Datos del Usuario
    public static String correo = "email";
    public static String nombre = "nombre";
    public static String amaterno = "amaterno";
    public static String apaterno = "apaterno";

    TextView obtnerCorreo,obtnombre,obtapaterno,obtamaterno;
    Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sin_acceso);

        btnSalir = findViewById(R.id.btnSalir);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("prefrencias", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();

                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();

            }
        });

        obtnombre = findViewById(R.id.obtnombre);
        obtapaterno = findViewById(R.id.obtapaterno);
        obtamaterno = findViewById(R.id.obtamaterno);
        obtnerCorreo = findViewById(R.id.obtCorreo2);
        String correo = getIntent().getStringExtra("email");
        String nombre = getIntent().getStringExtra("nombre");
        String apaterno = getIntent().getStringExtra("apaterno");
        String amaterno = getIntent().getStringExtra("amaterno");
        obtnerCorreo.setText(correo);
        obtnombre.setText(nombre);
        obtapaterno.setText(apaterno);
        obtamaterno.setText(amaterno);




    }
}
