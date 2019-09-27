package com.isa.silva.teste.Task;
import android.os.AsyncTask;
import android.util.Log;
import com.isa.silva.teste.Utils.HttpUtil;
import org.json.JSONObject;

import static com.isa.silva.teste.Utils.Constantes.URL_WEBSERVICE;

public class BuscaTask extends AsyncTask<Void, Void, String> {
        private String Nome;


        public BuscaTask(String nome) {
            Nome = nome;
        }

        @Override
        protected String doInBackground(Void... params) {

            String url = URL_WEBSERVICE+ Nome + "/repos" ;

            JSONObject json = new JSONObject();
            try{

                String result =  HttpUtil.getExecute(url);
                return result;

            }catch (Exception e){
                Log.e("ERROR", e.getMessage());
                return null;
            }
        }
}
