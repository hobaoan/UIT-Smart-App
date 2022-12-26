package com.example.uitsmart.Service;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidplot.xy.XYPlot;
import com.example.uitsmart.SQLite.DatabaseHelper;
import com.example.uitsmart.access.AccessAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BroadcastReceiver extends android.content.BroadcastReceiver {

    RequestQueue mRequestQueue;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;
    JSONArray assetBounds;
    ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
    XYPlot plot;
    int Count = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (isNetworkAvailable(context)) {
                //Create Database
                DatabaseHelper databaseHelper = new DatabaseHelper(context);

                RequestQueue aRequestQueue = Volley.newRequestQueue(context);

                jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, AccessAPI.getUrlUserCurrent(), null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.e("MyResponseData",""+ response);
                                for (int i = 0; i < 84; i ++) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(String.valueOf(response.get(i)));
                                        JSONObject jsonObjectAttributes = jsonObject.getJSONObject("attributes");
                                        JSONObject jsonObjectLocation = jsonObjectAttributes.getJSONObject("location");
                                        JSONObject jsonObjectValue = jsonObjectLocation.getJSONObject("value");
                                        JSONObject jsonObjectWeatherData = jsonObjectAttributes.getJSONObject("weatherData");
                                        JSONObject jsonObjectValueWeather = jsonObjectWeatherData.getJSONObject("value");
                                        JSONObject jsonObjectMain = jsonObjectValueWeather.getJSONObject("main");
                                        JSONObject jsonObjectWind = jsonObjectValueWeather.getJSONObject("wind");
                                        assetBounds = jsonObjectValue.getJSONArray("coordinates");

                                        if (String.valueOf(assetBounds).isEmpty()) {
                                        }
                                        else {
                                            Count ++;
                                            if (Count == 1) {
                                                String temp =  jsonObjectMain.getString("temp");
                                                String humidity = jsonObjectMain.getString("humidity");
                                                String speed = jsonObjectWind.getString("speed");

                                                String time = jsonObjectValueWeather.getString("dt");
                                                long l1 = Long.valueOf(time);
                                                Date date1 = new Date(l1*1000L);
                                                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                                                String formatDays = simpleDateFormat1.format(date1);
                                                Log.e("Days",formatDays );

                                                databaseHelper.QueryData("INSERT INTO WEATHERASSET VALUES("+temp+","+humidity+","+speed+","+time+")");
                                                //databaseHelper.QueryData("INSERT INTO WEATHERASSET (temperature, humidity, wind, time) VALUES('14.98', '48','2.11','1671030731')");
                                                //databaseHelper.QueryData("INSERT INTO WEATHERASSET (temperature, humidity, wind, time) VALUES('13.98', '56','1.11','1671039957')");
                                                //databaseHelper.QueryData("INSERT INTO WEATHERASSET (temperature, humidity, wind, time) VALUES('21.98', '44','2.98','1671092437')");
                                                //databaseHelper.QueryData("INSERT INTO WEATHERASSET (temperature, humidity, wind, time) VALUES('19.98', '58','4.39','1671106486')");
                                                //databaseHelper.QueryData("INSERT INTO WEATHERASSET (temperature, humidity, wind, time) VALUES('17.98', '64','3.3','1671113830')");1671113830

                                            }


                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("MyErrorDataBroadcast", "" + error);
                            }
                        }) {
                    @Override
                    public java.util.Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("accept", "application/json;");
                        params.put("Authorization", "Bearer " + AccessAPI.getToken());
                        return params;
                    }
                };
                aRequestQueue.add(jsonArrayRequest);
                SQLiteDatabase database = databaseHelper.getReadableDatabase();
                database.close();

            } else {
                Toast.makeText(context, "No Connect Internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) {
                return false;
            }
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }

    }
}
