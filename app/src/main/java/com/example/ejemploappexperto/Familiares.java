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

public class Familiares extends AppCompatActivity {

    ListView listView;
    MyAdapterFamiliar adapter;
    public static ArrayList<Familiar> familiarArrayList = new ArrayList<>();
    String url = "https://anamnestic-mat.000webhostapp.com/retrievef.php";
    Familiar familiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familiares);

        listView = findViewById(R.id.myListView);
        adapter = new MyAdapterFamiliar(this, familiarArrayList);
        listView.setAdapter( adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);

        //-----------------------------
        bottomNavigationView.setSelectedItemId(R.id.familiares);

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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"Ver Familiar", "Editar Familiar", "Eliminar Familiar"};
                builder.setTitle(familiarArrayList.get(position).getNombre());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i) {
                            case 0:
                                startActivity(new Intent(getApplicationContext(), InformacionFamiliar.class)
                                        .putExtra("position", position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(), EditarFamiliar.class)
                                        .putExtra("position", position));
                                break;
                            case 2:
                                eliminarFamiliar(familiarArrayList.get(position).getIdfamiliar());
                                break;
                        }
                    }
                });

                builder.create().show();
            }
        });
        ObtenerFamiliar();
    }

    private void ObtenerFamiliar() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        familiarArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(sucess.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String idfamiliar = object.getString("idfamiliar");
                                    String nombre = object.getString("nombre");
                                    String apaterno = object.getString("apaterno");
                                    String amaterno = object.getString("amaterno");
                                    String correo = object.getString("correo");
                                    String fechanac = object.getString("fechanac");
                                    String municipio = object.getString("municipio");
                                    String telefono = object.getString("telefono");

                                    familiar = new Familiar(idfamiliar,nombre,apaterno,amaterno,correo,fechanac,municipio,telefono);
                                    familiarArrayList.add(familiar);
                                    adapter.notifyDataSetChanged();



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
                Toast.makeText(Familiares.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void eliminarFamiliar(final String idfamiliar) {



        StringRequest request = new StringRequest(Request.Method.POST, "https://anamnestic-mat.000webhostapp.com/eliminarfamiliar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Familiar Eliminado")){
                            Toast.makeText(Familiares.this, response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Familiares.class));
                            finish();
                        }
                        else{
                            Toast.makeText(Familiares.this, response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Familiares.class));
                            finish();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Familiares.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Familiares.class));
                finish();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("idfamiliar", idfamiliar);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
}
