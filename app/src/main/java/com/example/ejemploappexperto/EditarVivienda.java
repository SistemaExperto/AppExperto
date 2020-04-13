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

public class EditarVivienda extends AppCompatActivity {

    EditText edidregistrovivienda,edidregistrofamiliar,edmaterialpiso,edmaterialpared,edacabadopared,edmaterialtecho,edtiposanitario,eddrenaje;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_vivienda);

        edidregistrovivienda = findViewById(R.id.ed_idregistrovivienda);
        edidregistrofamiliar = findViewById(R.id.ed_idregistrofamiliar);
        edmaterialpiso       = findViewById(R.id.ed_materialpiso);
        edmaterialpared      = findViewById(R.id.ed_materialpared);
        edacabadopared       = findViewById(R.id.ed_acabadopared);
        edmaterialtecho      = findViewById(R.id.ed_materialtecho);
        edtiposanitario      = findViewById(R.id.ed_tiposanitario);
        eddrenaje            = findViewById(R.id.ed_drenaje);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edidregistrovivienda.setText(Viviendas.viviendaArrayList.get(position).getIdvivienda());
        edidregistrofamiliar.setText(Viviendas.viviendaArrayList.get(position).getIdfamiliar());

        edmaterialpiso.setText(Viviendas.viviendaArrayList.get(position).getMaterialpiso());
        edmaterialpared.setText(Viviendas.viviendaArrayList.get(position).getMaterialpared());
        edacabadopared.setText(Viviendas.viviendaArrayList.get(position).getAcabadopared());
        edmaterialtecho.setText(Viviendas.viviendaArrayList.get(position).getMaterialtecho());
        edtiposanitario.setText(Viviendas.viviendaArrayList.get(position).getTiposanitario());
        eddrenaje.setText(Viviendas.viviendaArrayList.get(position).getDrenaje());


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

    public  void btn_actulizar_vivienda(View view){



        final String materialpiso = edmaterialpiso.getText().toString();
        final String materialpared = edmaterialpared.getText().toString();
        final String acabadopared = edacabadopared.getText().toString();
        final String materialtecho = edmaterialtecho.getText().toString();
        final String tiposanitario = edtiposanitario.getText().toString();
        final String drenaje = eddrenaje.getText().toString();

        final String registrovivienda = edidregistrovivienda.getText().toString();
        final String registrofamiliar = edidregistrofamiliar.getText().toString();





        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://anamnestic-mat.000webhostapp.com/editarvivienda.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(EditarVivienda.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Viviendas.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EditarVivienda.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();


                params.put("idvivienda",registrovivienda);
                params.put("material_piso",materialpiso);
                params.put("material_pared",materialpared);
                params.put("acabado_pared",acabadopared);
                params.put("material_techo",materialtecho);
                params.put("tipo_sanitario",tiposanitario);
                params.put("drenaje",drenaje);
                params.put("idfamiliar",registrofamiliar);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditarVivienda.this);
        requestQueue.add(request);






    }
}
