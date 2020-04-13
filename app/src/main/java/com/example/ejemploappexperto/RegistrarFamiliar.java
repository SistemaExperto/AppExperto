package com.example.ejemploappexperto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class RegistrarFamiliar extends AppCompatActivity {

    EditText txtNombre,txtApaterno,txtAmaterno,txtCorreo,txtFechanac,txtMunicipio,txtTelefono;
    Button btn_Registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_familiar);

        txtNombre     = findViewById(R.id.edtNombre);
        txtApaterno    = findViewById(R.id.edtPaterno);
        txtAmaterno  = findViewById(R.id.edtMaterno);
        txtCorreo  = findViewById(R.id.edtCorreo);
        txtFechanac    = findViewById(R.id.edtFecha);
        txtMunicipio  = findViewById(R.id.edtMunicipio);
        txtTelefono  = findViewById(R.id.edtTelefono);
        btn_Registrar = findViewById(R.id.btnRegistrar);

        btn_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegistrarFamiliar();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);

        //-----------------------------
        bottomNavigationView.setSelectedItemId(R.id.viviendas);

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
                        return true;

                }

                return false;
            }
        });

    }

    private void RegistrarFamiliar() {

        final String nombre = txtNombre.getText().toString().trim();
        final String apaterno = txtApaterno.getText().toString().trim();
        final String amaterno = txtAmaterno.getText().toString().trim();
        final String correo = txtCorreo.getText().toString().trim();
        final String fechanac = txtFechanac.getText().toString().trim();
        final String municipio = txtMunicipio.getText().toString().trim();
        final String telefono = txtTelefono.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando...");

        if(nombre.isEmpty()){
            Toast.makeText(this, "Falta campo Nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(apaterno.isEmpty()){
            Toast.makeText(this, "Falta campo Apellido Paterno", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(amaterno.isEmpty()){
            Toast.makeText(this, "Falta campo Apellido Materno", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(correo.isEmpty()){
            Toast.makeText(this, "Falta campo Correo", Toast.LENGTH_SHORT).show();
            return;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(this, "Ingresa un correo valido", Toast.LENGTH_SHORT).show();
            return ;

        }else if(fechanac.isEmpty()){
            Toast.makeText(this, "Falta campo Fecha de nacimiento", Toast.LENGTH_SHORT).show();
            return;
        }else if(municipio.isEmpty()){
            Toast.makeText(this, "Falta campo Municipio", Toast.LENGTH_SHORT).show();
            return;
        }else if(telefono.isEmpty()){
            Toast.makeText(this, "Falta campo Teléfono", Toast.LENGTH_SHORT).show();
            return;
        }else if(telefono.length()< 10){
            Toast.makeText(this, "Teléfono Invalido", Toast.LENGTH_SHORT).show();
            return;
        }

        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://anamnestic-mat.000webhostapp.com/registrarfamiliar.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Familiar Registrado")){
                                Toast.makeText(RegistrarFamiliar.this, response, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Familiares.class));
                                finish();
                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(RegistrarFamiliar.this, response, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Familiares.class));
                                finish();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrarFamiliar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Familiares.class));
                    finish();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

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


            RequestQueue requestQueue = Volley.newRequestQueue(RegistrarFamiliar.this);
            requestQueue.add(request);



        }

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
