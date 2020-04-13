package com.example.ejemploappexperto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InformacionVivienda extends AppCompatActivity {
    TextView tvidvivienda,tvmaterialpiso,tvmaterialdepared,tvacabadopared,tvmaterialtecho,tvtiposanitario,tvdrenaje,tvidfamiliar;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_vivienda);

        tvidvivienda = findViewById(R.id.txtidvivienda);
        tvmaterialpiso = findViewById(R.id.txtmaterialpiso);
        tvmaterialdepared = findViewById(R.id.txtmaterialdepared);
        tvacabadopared = findViewById(R.id.txtacabadopared);
        tvmaterialtecho = findViewById(R.id.txtmaterialtecho);
        tvtiposanitario = findViewById(R.id.txttiposanitario);
        tvdrenaje = findViewById(R.id.txtdrenaje);
        tvidfamiliar = findViewById(R.id.txtidfamiliar);


        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvidvivienda.setText(Viviendas.viviendaArrayList.get(position).getIdvivienda());
        tvmaterialpiso.setText(Viviendas.viviendaArrayList.get(position).getMaterialpiso());
        tvmaterialdepared.setText(Viviendas.viviendaArrayList.get(position).getMaterialpared());
        tvacabadopared.setText(Viviendas.viviendaArrayList.get(position).getAcabadopared());
        tvmaterialtecho.setText(Viviendas.viviendaArrayList.get(position).getMaterialtecho());
        tvtiposanitario.setText(Viviendas.viviendaArrayList.get(position).getTiposanitario());
        tvdrenaje.setText(Viviendas.viviendaArrayList.get(position).getDrenaje());
        tvidfamiliar.setText(Viviendas.viviendaArrayList.get(position).getIdfamiliar());
    }
}
