package com.example.ejemploappexperto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {

    // Variables
    private EditText etcorreo, etpas, encriptarpass;
    private Button btnlog;
    private AsyncHttpClient cliente;
    private CheckBox verpass;
    String correo, pass , validar;
    int tipo_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Incializacion de las variables
        etcorreo = (EditText) findViewById(R.id.etcorreo);
        etpas = (EditText) findViewById(R.id.etpas);
        btnlog= (Button) findViewById(R.id.btnlog);
        verpass = (CheckBox) findViewById(R.id.verpass);
        //encriptarpass = (EditText) findViewById(R.id.etpas);
        cliente = new AsyncHttpClient();


        //Método para visualizar la contraseña
        verpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b){
                    etpas.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    etpas.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        botonLogin();
        RecuperarPreferencias();

    }

    // Método para iniciar Sesión en la aplicación
    private void botonLogin() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando....");

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etcorreo.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Faltan el correo", Toast.LENGTH_SHORT).show();
                    etcorreo.setText("");
                } else if ( etpas.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Faltan la contraseña", Toast.LENGTH_SHORT).show();
                    etpas.setText("");
                } else {

                    correo = etcorreo.getText().toString().replace(" ","%20");
                    pass = etpas.getText().toString().replace(" ","%20");
                    String url = "https://anamnestic-mat.000webhostapp.com/comprobarUsuario.php?Correo="+correo+"&Password="+pass;
                    cliente.post(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if(statusCode == 200){
                                String respuesta = new String(responseBody);
                                if (respuesta.equalsIgnoreCase("null")){
                                    Toast.makeText(Login.this, "Correo y/o Contraseña icorrectos", Toast.LENGTH_SHORT).show();
                                    etcorreo.setText("");
                                    etpas.setText("");
                                }else {
                                    GuardarPreferencias();
                                    try {
                                        JSONObject jsonObject = new JSONObject(respuesta);
                                        Usuario u = new Usuario();
                                        u.setId(jsonObject.getInt("idusuario"));
                                        u.setNombreUsuario(jsonObject.getString("nombre"));
                                        u.setApaternoUsuario(jsonObject.getString("apaterno"));
                                        u.setAmaternoUsuario(jsonObject.getString("amaterno"));
                                        u.setCorreo(jsonObject.getString("correo"));
                                        u.setContraseña(jsonObject.getString("contra"));
                                        u.setTelefonoUsuario(jsonObject.getString("telefono"));
                                        u.setFechaNacUsuario(jsonObject.getString("fechanac"));
                                        u.setEstado(jsonObject.getString("estado"));
                                        u.setIdtipo(jsonObject.getInt("tipo_idusuario"));
                                        Intent intent = null;
                                        if(u.getEstado().equalsIgnoreCase("Desactivada")){
                                            intent = new Intent(Login.this,MainSinAcceso.class);
                                            intent.putExtra(MainSinAcceso.correo, u.getCorreo());
                                        }else {

                                            switch (u.getIdtipo()){
                                                case 1:
                                                    u.setNombre_tipo("Administrador");
                                                    intent = new Intent(Login.this, MainSinAcceso.class);
                                                    intent.putExtra(MainSinAcceso.correo, u.getCorreo());
                                                    intent.putExtra(MainSinAcceso.nombre,u.getNombreUsuario());
                                                    intent.putExtra(MainSinAcceso.apaterno, u.getApaternoUsuario());
                                                    intent.putExtra(MainSinAcceso.amaterno, u.getAmaternoUsuario());
                                                    progressDialog.dismiss();
                                                    break;
                                                case 2:
                                                    u.setNombre_tipo("Capturista");
                                                    intent = new Intent(Login.this, MainActivity.class);
                                                    intent.putExtra(MainActivity.correo, u.getCorreo());
                                                    intent.putExtra(MainActivity.nombre,u.getNombreUsuario());
                                                    intent.putExtra(MainActivity.apaterno, u.getApaternoUsuario());
                                                    intent.putExtra(MainActivity.amaterno, u.getAmaternoUsuario());
                                                    intent.putExtra(MainActivity.telefono,u.getTelefonoUsuario());
                                                    intent.putExtra(MainActivity.estado, u.getEstado());
                                                    intent.putExtra(MainActivity.fecha, u.getFechaNacUsuario());
                                                    progressDialog.dismiss();
                                                    break;
                                                case 3:
                                                    u.setNombre_tipo("General");
                                                    intent = new Intent(Login.this, MainSinAcceso.class);
                                                    intent.putExtra(MainSinAcceso.correo,u.getCorreo());
                                                    intent.putExtra(MainSinAcceso.nombre,u.getNombreUsuario());
                                                    intent.putExtra(MainSinAcceso.apaterno, u.getApaternoUsuario());
                                                    intent.putExtra(MainSinAcceso.amaterno, u.getAmaternoUsuario());
                                                    progressDialog.dismiss();
                                                    break;

                                            }
                                        }

                                        intent.putExtra("u", u);
                                        startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(Login.this, "Error, Intentalo de Nuevo", Toast.LENGTH_SHORT).show();
                            etcorreo.setText("");
                            etpas.setText("");
                        }
                    });
                }
            }
        });


    }// Cierra el metodo botonLogin

    //Metodo guardar Preferencias
    private void GuardarPreferencias(){
        SharedPreferences sharedPreferences = getSharedPreferences("prefrencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("correo",correo);
        editor.putString("password",pass);
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void RecuperarPreferencias() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefrencias", Context.MODE_PRIVATE);
        etcorreo.setText(sharedPreferences.getString("correo",""));
        etpas.setText(sharedPreferences.getString("password",""));
    }



}
