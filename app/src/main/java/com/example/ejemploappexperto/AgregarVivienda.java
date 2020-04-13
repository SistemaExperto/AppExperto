package com.example.ejemploappexperto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AgregarVivienda extends AppCompatActivity {

    EditText txtbusqueda,txtmaterialpiso,txtmaterialpared,txtacabadopared,txtmaterialtecho,txttiposanitario,txtdrenaje;
    TextView codigofamiliar, edtregistrofamiliar;
    Button btnbuscar,btnRegistrarVivienda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vivienda);

        edtregistrofamiliar = findViewById(R.id.edtregistrofamiliar);
        txtmaterialpiso = findViewById(R.id.edtmaterialpiso);
        txtmaterialpared = findViewById(R.id.edtmaterialpared);
        txtacabadopared = findViewById(R.id.edtacabadopared);
        txtmaterialtecho = findViewById(R.id.edtmaterialtecho);
        txttiposanitario = findViewById(R.id.edttiposanitario);
        txtdrenaje = findViewById(R.id.edtdrenaje);
        btnRegistrarVivienda = findViewById(R.id.btnRegistrarVivienda);
        ///----------------------------------------------------
        codigofamiliar = findViewById(R.id.codigofamiliar);
        txtbusqueda = findViewById(R.id.txtbusqueda);
        btnbuscar = findViewById(R.id.btnbuscar);

        btnRegistrarVivienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegistrarVivienda();
            }
        });

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarFamiliar("https://anamnestic-mat.000webhostapp.com/buscarfamiliar.php?correo="+txtbusqueda.getText()+"");

            }
        });

        //-----------------------------

        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);

        //-----------------------------
        bottomNavigationView.setSelectedItemId(R.id.usuario);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.viviendas:
                        startActivity(new Intent(getApplicationContext()
                                ,Viviendas.class) );
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.addviviendas:
                        return true;

                    case R.id.usuario:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class) );
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.familiares:
                        startActivity(new Intent(getApplicationContext()
                                ,Familiares.class) );
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.addfamiliares:
                        startActivity(new Intent(getApplicationContext()
                                ,RegistrarFamiliar.class) );
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }

    private void buscarFamiliar(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        codigofamiliar.setText(jsonObject.getString("idfamiliar"));
                        edtregistrofamiliar.setText(jsonObject.getString("idfamiliar"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    private void RegistrarVivienda() {

        final String materialpiso = txtmaterialpiso.getText().toString().trim();
        final String materialpared = txtmaterialpared.getText().toString().trim();
        final String acabadopared = txtacabadopared.getText().toString().trim();
        final String materialtecho = txtmaterialtecho.getText().toString().trim();
        final String tiposanitario = txttiposanitario.getText().toString().trim();
        final String drenaje = txtdrenaje.getText().toString().trim();
        final String idfamiliar = edtregistrofamiliar.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando...");

        if(materialpiso.isEmpty()){
            Toast.makeText(this, "Falta campo Material de Piso", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(materialpared.isEmpty()){
            Toast.makeText(this, "Falta campoMaterial de Pared", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(acabadopared.isEmpty()){
            Toast.makeText(this, "Falta campo Acabado de Pared", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(materialtecho.isEmpty()){
            Toast.makeText(this, "Falta campo Material de Techo", Toast.LENGTH_SHORT).show();
            return;
        }else if(tiposanitario.isEmpty()){
            Toast.makeText(this, "Falta campo Tipo de sanitario", Toast.LENGTH_SHORT).show();
            return;
        }else if(drenaje.isEmpty()){
            Toast.makeText(this, "Falta campo Drenaje", Toast.LENGTH_SHORT).show();
            return;
        }else if(materialpiso.length()< 2){
            Toast.makeText(this, "Opción Ivalida", Toast.LENGTH_SHORT).show();
            return;
        }else if(materialpared.length()< 2){
            Toast.makeText(this, "Opción Ivalida", Toast.LENGTH_SHORT).show();
            return;
        }else if(acabadopared.length()< 2){
            Toast.makeText(this, "Opción Ivalida", Toast.LENGTH_SHORT).show();
            return;
        }else if(materialtecho.length()< 2){
            Toast.makeText(this, "Opción Ivalida", Toast.LENGTH_SHORT).show();
            return;
        }else if(tiposanitario.length()< 2){
            Toast.makeText(this, "Opción Ivalida", Toast.LENGTH_SHORT).show();
            return;
        }else if(drenaje.length()< 2){
            Toast.makeText(this, "Opción Ivalida", Toast.LENGTH_SHORT).show();
            return;
        }else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://anamnestic-mat.000webhostapp.com/registrarvivienda.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Familiar Registrado")){
                                Toast.makeText(AgregarVivienda.this, response, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Viviendas.class));
                                finish();
                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(AgregarVivienda.this, response, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Viviendas.class));
                                finish();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AgregarVivienda.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Viviendas.class));
                    finish();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("material_piso",materialpiso);
                    params.put("material_pared",materialpared);
                    params.put("acabado_pared",acabadopared);
                    params.put("material_techo",materialtecho);
                    params.put("tipo_sanitario",tiposanitario);
                    params.put("drenaje",drenaje);
                    params.put("idfamiliar",idfamiliar);



                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(AgregarVivienda.this);
            requestQueue.add(request);



        }

    }
}
