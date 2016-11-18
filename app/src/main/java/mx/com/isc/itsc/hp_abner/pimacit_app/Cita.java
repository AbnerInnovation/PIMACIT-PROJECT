package mx.com.isc.itsc.hp_abner.pimacit_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);
        new getTipoCita().execute();
    }


    public class getTipoCita extends AsyncTask<Void, Void, List<String>> {


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
        protected List<String> doInBackground(Void... params) {
            Map<String, Integer> vars = new HashMap<String, Integer>();
            vars.put("idNegocio",4);


            try {
                final String uri = "http://187.216.79.40/Servicio/Service1.svc/getTiposCita";
                //final String uri = "http://10.0.2.2:61999/RestServiceImpl.svc/LlamadaSencilla";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                JsonTiposCita resultado = restTemplate.postForObject(uri, vars, JsonTiposCita.class);
                return resultado.getTiposCitaResultResult();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<String> resultado) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */
            try {
                String[] stockArr=new String[resultado.size()];
                stockArr=resultado.toArray(stockArr);
                    cargarSpinnerEstados(stockArr
                    );

                //  TextView Mensaje = (TextView) findViewById(R.id.textViewMensaje);
                //   Mensaje.setText(resultado);
            }
            catch(Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }
    }
String Tipocita;
    void cargarSpinnerEstados(String [] stockArr)
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTipoCita);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stockArr));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                //Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                Tipocita=(String) adapterView.getItemAtPosition(position);
                new getPersonal().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });
    }

    void cargarSpinnerEstados2(String [] stockArr)
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerPersonal);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stockArr));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                //Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                //Tipocita=(String) adapterView.getItemAtPosition(position);
               // new getPersonal().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });
    }


    public class getPersonal extends AsyncTask<Void, Void, List<String>> {


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
        protected List<String> doInBackground(Void... params) {

            Map<String, String> vars = new HashMap<String, String>();
            vars.put("idNegocio","4");
            vars.put("tipoCita", Tipocita);


            try {
                final String uri = "http://187.216.79.40/Servicio/Service1.svc/getPersonal";
                //final String uri = "http://10.0.2.2:61999/RestServiceImpl.svc/LlamadaSencilla";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                JsonPersonal resultado = restTemplate.postForObject(uri, vars, JsonPersonal.class);
                return resultado.getPersonalResult();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<String> resultado) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */
            try {
                String[] stockArr=new String[resultado.size()];
                stockArr=resultado.toArray(stockArr);
                cargarSpinnerEstados2(stockArr
                );


                //  TextView Mensaje = (TextView) findViewById(R.id.textViewMensaje);
                //   Mensaje.setText(resultado);
            }
            catch(Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }

    }
}
