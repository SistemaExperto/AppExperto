package com.example.ejemploappexperto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static String correo = "email";
    public static String nombre = "nombre";
    public static String telefono = "telefono";
    public static String estado = "estado";
    public static String amaterno = "amaterno";
    public static String apaterno = "apaterno";
    public static String fecha = "fechanac";

    TextView obtnerCorreo,obtnombre,obtapaterno,obtamaterno,obttelefono,obtfechanac,obtestado;
    Button btnCerrarSesion;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        obtnombre = findViewById(R.id.obtnombre);
        obtapaterno = findViewById(R.id.obtapaterno);
        obtamaterno = findViewById(R.id.obtamaterno);
        obtnerCorreo = findViewById(R.id.obtCorreo2);
        obttelefono = findViewById(R.id.obttelefono);
        obtfechanac = findViewById(R.id.obtfechanac);
        obtestado = findViewById(R.id.obtestado);

        /*String correo = getIntent().getStringExtra("email");
        String nombre = getIntent().getStringExtra("nombre");
        String apaterno = getIntent().getStringExtra("apaterno");
        String amaterno = getIntent().getStringExtra("amaterno");
        String telefono = getIntent().getStringExtra("telefono");
        String fecha = getIntent().getStringExtra("fechanac");
        String estado = getIntent().getStringExtra("estado");
*/
        GuardarPreferencias();

        /*obtnerCorreo.setText(correo);
        obtnombre.setText(nombre);
        obtapaterno.setText(apaterno);
        obtamaterno.setText(amaterno);
        obttelefono.setText(telefono);
        obtfechanac.setText(fecha);
        obtestado.setText(estado);
*/
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


        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("prefrencias", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();

                SharedPreferences sharedPreferencesusuario = getSharedPreferences("prefre", Context.MODE_PRIVATE);
                sharedPreferencesusuario.edit().clear().commit();

                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();

            }
        });

        RecuperarPreferencias();

    }


    //Metodo guardar Preferencias
    private void GuardarPreferencias(){
        SharedPreferences sharedPreferencesusuario = getSharedPreferences("prefre", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesusuario.edit();

        String correo = getIntent().getStringExtra("email");
        String nombre = getIntent().getStringExtra("nombre");
        String apaterno = getIntent().getStringExtra("apaterno");
        String amaterno = getIntent().getStringExtra("amaterno");
        String telefono = getIntent().getStringExtra("telefono");
        String fecha = getIntent().getStringExtra("fechanac");
        String estado = getIntent().getStringExtra("estado");

        editor.putString("correo",correo);
        editor.putString("nombre",nombre);
        editor.putString("apaterno",apaterno);
        editor.putString("amaterno",amaterno);
        editor.putString("telefono",telefono);
        editor.putString("fechanac",fecha);
        editor.putString("estado",estado);
        editor.putBoolean("usuario", true);
        editor.commit();
    }

    private void RecuperarPreferencias() {
        SharedPreferences sharedPreferencesuario = getSharedPreferences("prefre", Context.MODE_PRIVATE);

        obtnerCorreo.setText(sharedPreferencesuario.getString("correo",""));
        obtnombre.setText(sharedPreferencesuario.getString("nombre",""));
        obtapaterno.setText(sharedPreferencesuario.getString("apaterno",""));
        obtamaterno.setText(sharedPreferencesuario.getString("amaterno",""));
        obttelefono.setText(sharedPreferencesuario.getString("telefono",""));
        obtfechanac.setText(sharedPreferencesuario.getString("fechanac",""));
        obtestado.setText(sharedPreferencesuario.getString("estado",""));

    }
/*

        private void buscarUsuario(String URL){
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
    }*/
}
