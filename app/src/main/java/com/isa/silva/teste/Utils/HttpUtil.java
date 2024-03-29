package com.isa.silva.teste.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.microsoft.appcenter.utils.HandlerUtils.runOnUiThread;

public class HttpUtil {

        public static String Jsn;

        private static final int CONNECTION_TIMEOUT = 15000;
        private static final int DATARETRIEVAL_TIMEOUT = 10000;

        public static String getExecute(String urlString) {

            URL url = null;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);
            try {
                urlConnection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoInput(true);
            try {
                urlConnection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            InputStream is = null;
            try {
                is = urlConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return getStringFromInputStream(is);
        }

        public static String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return sb.toString();
        }

        public static String get(String urlString) throws IOException {

            String url = urlString;

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(url).build();

            Response response = null;


            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(final Call call, IOException e) {
                            // Error

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            Jsn = response.body().string();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(Jsn);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                Jsn = jsonObject.getString("value");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            return Jsn;
        }
}
