package mx.com.isc.itsc.hp_abner.pimacit_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Negocio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negocio);
        new DameMensaje().execute();
    }



    public void llamaMensajeWS(View v){
        new DameMensaje().execute();
    }


//CLASE


    public class DameMensaje extends AsyncTask<Void, Void, List<Negocios>> {


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
        protected List<Negocios> doInBackground(Void... params) {
            Map<String, String> vars = new HashMap<String, String>();
            vars.put("estado", "SONORA");
            vars.put("ciudad","CANANEA");
            vars.put("tipoNegocio", "Estética");

            try {
                final String uri = "http://187.216.79.40/Servicio/Service1.svc/getNegocios";
                //final String uri = "http://10.0.2.2:61999/RestServiceImpl.svc/LlamadaSencilla";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                JsonNegocios resultado = restTemplate.postForObject(uri, vars, JsonNegocios.class);
                return resultado.getNegociosResult();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Negocios> resultado) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */
            try {
                Temporales.NegocioActual=resultado.get(0);

                TextView nombre =(TextView) findViewById(R.id.txtNombreNegocio);
                TextView direccion= (TextView) findViewById(R.id.tvDireccion2);
                TextView telefono=(TextView) findViewById(R.id.tvTelefono);
                TextView tipocitas=(TextView) findViewById(R.id.tvTiposCitas2);
                TextView horario=(TextView) findViewById(R.id.tvHorario2);

                nombre.setText(Temporales.NegocioActual.Nombre);
                direccion.setText(Temporales.NegocioActual.Direccion);
                telefono.setText((Temporales.NegocioActual.Tel));
                tipocitas.setText(Temporales.NegocioActual.TiposCita);
                horario.setText(Temporales.NegocioActual.Horarios);
              //  TextView Mensaje = (TextView) findViewById(R.id.textViewMensaje);
             //   Mensaje.setText(resultado);
            }
            catch(Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }
    }
}
