package com.example.ejemploappexperto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Viviendas extends AppCompatActivity {

    ListView listViewVivienda;
    MyAdapterVivienda adapterVivienda;
    public static ArrayList<Vivienda> viviendaArrayList = new ArrayList<>();
    String url = "https://anamnestic-mat.000webhostapp.com/retrievev.php";
    Vivienda vivienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viviendas);


        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);

        //-----------------------------
        bottomNavigationView.setSelectedItemId(R.id.viviendas);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.viviendas:
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


        listViewVivienda = findViewById(R.id.myListViewVivienda);
        adapterVivienda = new MyAdapterVivienda(this, viviendaArrayList);
        listViewVivienda.setAdapter((ListAdapter) adapterVivienda);

        //Cargar Registros
        listViewVivienda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"Ver Vivienda", "Editar Vivienda", "Eliminar Vivienda"};
                builder.setTitle(viviendaArrayList.get(position).getIdfamiliar());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i) {
                            case 0:
                                startActivity(new Intent(getApplicationContext(), InformacionVivienda.class)
                                        .putExtra("position", position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(), EditarVivienda.class)
                                        .putExtra("position", position));
                                break;
                            case 2:
                                eliminarVivienda(viviendaArrayList.get(position).getIdvivienda());
                                break;
                        }

                    }
                });
                builder.create().show();

            }
        });

        ObtenerVivienda();

    }

    private void ObtenerVivienda() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        viviendaArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(sucess.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String idvivienda = object.getString("idvivienda");
                                    String material_piso = object.getString("material_piso");
                                    String material_pared = object.getString("material_pared");
                                    String acabado_pared = object.getString("acabado_pared");
                                    String material_techo = object.getString("material_techo");
                                    String tipo_sanitario = object.getString("tipo_sanitario");
                                    String drenaje = object.getString("drenaje");
                                    String idfamiliar = object.getString("idfamiliar");

                                    vivienda = new Vivienda(idvivienda,material_piso,material_pared,acabado_pared,material_techo,tipo_sanitario,drenaje,idfamiliar);
                                    viviendaArrayList.add(vivienda);
                                    adapterVivienda.notifyDataSetChanged();

                                }



                            }

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Viviendas.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void eliminarVivienda(final String idvivienda) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://anamnestic-mat.000webhostapp.com/eliminarvivienda.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Vivienda Eliminada")){
                            Toast.makeText(Viviendas.this, response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Viviendas.class));
                            finish();
                        }
                        else{
                            Toast.makeText(Viviendas.this, response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Viviendas.class));
                            finish();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Viviendas.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Viviendas.class));
                finish();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("idvivienda", idvivienda);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void btn_registrar_vivienda(View view) {
        startActivity(new Intent(getApplicationContext(),AgregarVivienda.class));
    }




}
