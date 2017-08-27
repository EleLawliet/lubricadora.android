package com.grupopulpo.lubriacadora.duty;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.grupopulpo.lubriacadora.duty.finals.ConfigEssentials;
import com.grupopulpo.lubriacadora.duty.http.RequestApp;
import com.grupopulpo.lubriacadora.duty.tools.SirSessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    TextView info;
    EditText cedula;
    EditText contrasenia;
    TextView welcome, sub_welcome,sigup,request,promo;
    EditText fono;
    Button btningresar ;
    TextInputLayout lyfono,lyusuario,lypassword;
    TextView textviewfono;
    private Gson gson = new Gson();
    LoginActivity THIS;
    String paso = "paso1";
    LinearLayout layout1,layout2;
    private CallbackManager callbackManager;
    Animation animation1 = null;
    Animation animation2 = null;
    SirSessionManager variablesSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle parametros = new Bundle();
        parametros.putInt("layout", R.layout.activity_login);
        parametros.putInt("verbtnatras", 0);
        parametros.putInt("vertoolbar", 0);
        super.onCreate(parametros);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        welcome = (TextView)findViewById(R.id.welcome);
        request = (TextView)findViewById(R.id.request);
        cedula = (EditText)findViewById(R.id.cedula);
        sub_welcome = (TextView)findViewById(R.id.sub_welcome);
        sigup = (TextView)findViewById(R.id.sigup);
        sigup.setOnClickListener(this);
        promo = (TextView)findViewById(R.id.promos);
        promo.setOnClickListener(this);
        request.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            welcome.setText(Html.fromHtml(getString(R.string.welcome), Html.FROM_HTML_MODE_COMPACT));
            sub_welcome.setText(Html.fromHtml(getString(R.string.sub_welcome), Html.FROM_HTML_MODE_COMPACT));
        }else{
            welcome.setText(Html.fromHtml(getString(R.string.welcome)));
            sub_welcome.setText(Html.fromHtml(getString(R.string.sub_welcome)));
        }
        info = (TextView)findViewById(R.id.info);
        loginButton.setReadPermissions("email");
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.packagename",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                );
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                String message = info.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

            }

            @Override
            public void onCancel() {
                // App code
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                info.setText("Login attempt failed.");
            }
        });
        THIS = this;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Nombre: redireccionarmenuprincipalPrueba
     * Descripción: Este metodo se encarga de simular la creacion del modelo de control de ingreso, donde guardamos datos del
     * usuario que son relevantes para el aplicativo movil, ademas de crear el redireccionamiento
     * a la pantalla principal del sistema.
     * */
    public void redireccionarmenuprincipalPrueba(){
        Intent i = new Intent(getBaseContext(), MenuActivity.class);
        /*Controlingreso cingreso = new Controlingreso();
        //cingreso.setUsuario("1715516520");//1715516520
        cingreso.setUsuario(usuario.getText().toString().trim());
        cingreso.setNombre("Usuario de Prueba");
        getPreferenciacb().crearPreferenciasControlIngreso(cingreso);
        i.putExtra("jsonusuario", gson.toJson(cingreso));*/
        startActivity(i);
        THIS.finish();
    }
    /*
        * Nombre: validarlogin
        * Descripción: Metodo utilizado para la validacion de datos obligatorios en el inicio de sesión (ced y contraseña).
        * */
    private boolean validarlogin(){
        boolean valido = true;
        if(cedula.getText().toString().trim().isEmpty()&&( cedula.getText().length()<11||cedula.getText().length()>5))
        {cedula.setError("Dato Obligatorio"); valido=false;}
        return valido;
    }

    /**
     * Nombre: realizaringreso
     * Descripción: Este metodo se encarga de crear un control de ingreso, donde guardamos datos del
     * usuario que son relevantes para el aplicativo movil, ademas de crear el redireccionamiento
     * a la pantalla principal del sistema.
     * */
    private void getUbication(){
// Creates an Intent that will load a map of San Francisco
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/place/2%C2%B006'11.1%22S+79%C2%B058'13.0%22W" +
                                     "/@-2.1029117,-79.9706132,19.34z/data=!4m5!3m4!1s0x0:0x0!8m2!3d-2.1030838!4d-79.9702723?hl=es");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void realizaringreso(final String cedulaUsuario) {
        final String urlRequest = ConfigEssentials.API_URL_LOGIN+cedulaUsuario;

        Log.i("LubricadoraDuty","URL: "+urlRequest);
        RequestApp request = new RequestApp(Request.Method.GET,urlRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("duty","JSON RESPUESTA: "+response.toString());
                String exito = response.optString("codigo");
                JSONObject array;
                Log.i("duty","JSON BOOLEAN: "+exito);
                if(exito.equalsIgnoreCase("000")){
                    array = response.optJSONObject("datos");
                    Log.i("duty","JSON RESPUESTA: "+array.toString());
                    if (array.length() > 0) {
                        redireccionarmenuprincipal(array);
                        onConnectionFinished();
                    }
                }else {
                    try {
                        onConnectionFailed(response.get("mensaje").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error);
            }
        });
       // Utilidades.ocultarTeclado(THIS);
        agregarPeticionHttp(request);
    }
    public void redireccionarmenuprincipal(JSONObject jsonObject){
        Intent i = new Intent(getBaseContext(), MenuActivity.class);
        Log.i("DatosLogin->",jsonObject.toString());
        String cedula="", nombre="", email="";
        int idCliente;
        try {
            cedula = jsonObject.getString("cedula").toString();
            nombre =jsonObject.getString("nombre").toString().toUpperCase().concat(" "+jsonObject.getString("apellido").toUpperCase().toString());
            email =jsonObject.getString("email").toString();

            if(variablesSesion == null){
                variablesSesion = new SirSessionManager(this);
            }
            variablesSesion.createLoginSession(cedula,nombre,email);
            startActivity(i);
            THIS.finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.sigup){
            getUbication();
        }
        if(id == R.id.request){
           if (validarlogin()){
               realizaringreso(cedula.getText().toString().trim());
           }
        }
    }
}
