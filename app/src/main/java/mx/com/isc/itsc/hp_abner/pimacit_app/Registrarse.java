package mx.com.isc.itsc.hp_abner.pimacit_app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Registrarse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
    }

    String n,a,t,p,pc;
    boolean mensaje1;
    public void GuardarRegistro(View v)
    {
        EditText Apellido= (EditText) findViewById(R.id.apellido);
        EditText Nombre= (EditText) findViewById(R.id.nombre);
        EditText Telefono= (EditText) findViewById(R.id.tel);
        EditText Password= (EditText) findViewById(R.id.pass);
        EditText PasswordConf= (EditText) findViewById(R.id.pass2);
      n= Nombre.getText().toString();
        a=Apellido.getText().toString();
        t=Telefono.getText().toString();
        p=Password.getText().toString();
        pc=PasswordConf.getText().toString();

        if(t.length()==10) {
           // if (mensaje1 = true) {
                if (p.toString().equals(pc.toString()) ) {

                    new ValidarUsuario().execute();


               } else {
            Context context = getApplicationContext();
                   int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, " Las contraseña con conciden", duration);
                    toast.show();
                }
            //}
        }
        else
        {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, " El numero telefonico debe contener 10 digitos.", duration);
            toast.show();
        }



    }

    public class GuardaUsuario extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
        /*
         *    do things before doInBackground() code runs
         *    such as preparing and showing a Dialog or ProgressBar
        */

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        /*
         *    updating data
         *    such a Dialog or ProgressBar
        */

        }

        @Override
        protected Void doInBackground(Void... params) {


            Map<String, String> vars = new HashMap<String, String>();
            vars.put("nombre",n);
            vars.put("apellido",a);
            vars.put("tel", t);
            vars.put("pswd",p);


            try {
                final String uri = "http://187.216.79.40/Servicio/Service1.svc/insertCliente";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


                Void mensaje = restTemplate.postForObject(uri, vars, Void.class);
                return mensaje;

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
               // return "Error de guardado";
            }
            return null;
        }


@Override
        protected void onPostExecute( Void parem) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */

            try {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, " Registrarse Guardado", duration);
                toast.show();
            }
            catch(Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }
    }


    public class ValidarUsuario extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
        /*
         *    do things before doInBackground() code runs
         *    such as preparing and showing a Dialog or ProgressBar
        */

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        /*
         *    updating data
         *    such a Dialog or ProgressBar
        */

        }

        @Override
        protected Boolean doInBackground(Void... params) {


            Map<String, String> vars = new HashMap<String, String>();

            vars.put("idCliente", t);

            try {
                final String uri = "http://187.216.79.40/Servicio/Service1.svc/clienteExists";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


                Boolean mensaje = restTemplate.postForObject(uri, vars, Boolean.class);
               mensaje1= mensaje;
                return mensaje;

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                 return false;
            }

        }


        @Override
        protected void onPostExecute( Boolean parem) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */

            try {
                if(mensaje1==false)
                {
                        new GuardaUsuario().execute();
                }
                else
                {
                 Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, " El usuario ya exite", duration);
                toast.show();
                }

            }
            catch(Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }
    }



}
