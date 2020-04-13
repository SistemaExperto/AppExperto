package com.example.ejemploappexperto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class EditarFamiliar extends AppCompatActivity {

    EditText edFamiliar,edNombre,edApaterno, edAmaterno, edCorreo, edFechanac, edMunicipio, edTelefono;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_familiar);

        edFamiliar = findViewById(R.id.ed_idfamiliar);
        edNombre = findViewById(R.id.ed_nombre);
        edApaterno = findViewById(R.id.ed_apaterno);
        edAmaterno = findViewById(R.id.ed_amaterno);
        edCorreo = findViewById(R.id.ed_correo);
        edFechanac = findViewById(R.id.ed_fecha);
        edMunicipio = findViewById(R.id.ed_municipio);
        edTelefono = findViewById(R.id.ed_telefono);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edFamiliar.setText(Familiares.familiarArrayList.get(position).getIdfamiliar());
        edNombre.setText(Familiares.familiarArrayList.get(position).getNombre());
        edApaterno.setText(Familiares.familiarArrayList.get(position).getApaterno());
        edAmaterno.setText(Familiares.familiarArrayList.get(position).getAmaterno());
        edCorreo.setText(Familiares.familiarArrayList.get(position).getCorreo());
        edFechanac.setText(Familiares.familiarArrayList.get(position).getFechanac());
        edMunicipio.setText(Familiares.familiarArrayList.get(position).getMunicipio());
        edTelefono.setText(Familiares.familiarArrayList.get(position).getTelefono());

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
                        startActivity(new Intent(getApplicationContext()
                                ,AgregarVivienda.class) );
                        overridePendingTransition(0,0);
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

    public  void btn_actulizar_familiar(View view){

        final String nombre = edNombre.getText().toString();
        final String apaterno = edApaterno.getText().toString();
        final String amaterno = edAmaterno.getText().toString();
        final String correo = edCorreo.getText().toString();
        final String fechanac = edFechanac.getText().toString();
        final String municipio = edMunicipio.getText().toString();
        final String telefono = edTelefono.getText().toString();

        final String idfamiliar = edFamiliar.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://anamnestic-mat.000webhostapp.com/editarfamiliar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(EditarFamiliar.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Familiares.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EditarFamiliar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();


                params.put("idfamiliar",idfamiliar);
                params.put("nombre",nombre);
                params.put("apaterno",apaterno);
                params.put("amaterno",amaterno);
                params.put("correo",correo);
                params.put("fechanac",fechanac);
                params.put("municipio",municipio);
                params.put("telefono",telefono);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditarFamiliar.this);
        requestQueue.add(request);

    }

 }

