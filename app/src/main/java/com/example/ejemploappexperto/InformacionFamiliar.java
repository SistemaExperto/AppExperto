package com.example.ejemploappexperto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InformacionFamiliar extends AppCompatActivity {

    TextView tvidfamiliar,tvnombre,tvapaterno,tvamaterno,tvcorreo,tvfechanac,tvmunicipio,tvtelefono;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_familiar);


        tvidfamiliar = findViewById(R.id.txtidfamiliar);
        tvnombre = findViewById(R.id.txtnombre);
        tvapaterno = findViewById(R.id.txtapaterno);
        tvamaterno = findViewById(R.id.txtamaterno);
        tvcorreo = findViewById(R.id.txcorreo);
        tvfechanac = findViewById(R.id.txtfecha);
        tvmunicipio = findViewById(R.id.txtmunicipio);
        tvtelefono = findViewById(R.id.txtelefono);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvidfamiliar.setText(Familiares.familiarArrayList.get(position).getIdfamiliar());
        tvnombre.setText(Familiares.familiarArrayList.get(position).getNombre());
        tvapaterno.setText(Familiares.familiarArrayList.get(position).getApaterno());
        tvamaterno.setText(Familiares.familiarArrayList.get(position).getAmaterno());
        tvcorreo.setText(Familiares.familiarArrayList.get(position).getCorreo());
        tvfechanac.setText(Familiares.familiarArrayList.get(position).getFechanac());
        tvmunicipio.setText(Familiares.familiarArrayList.get(position).getMunicipio());
        tvtelefono.setText(Familiares.familiarArrayList.get(position).getTelefono());
    }
}
