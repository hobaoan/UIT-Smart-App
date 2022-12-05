package com.example.uitsmart.View;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uitsmart.MainActivity;
import com.example.uitsmart.R;

import org.json.JSONException;
import org.json.JSONObject;


public class HomeFragment extends Fragment{

    RelativeLayout map, weather, healthy;
    TextView tvWeather, tvHumidity, tvCloud, tvWindy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetCurrentWeatherData();


        // setClickMap
        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MapFragment mapFragment = new MapFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.homeFragment, mapFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                ((MainActivity) getActivity()).setActionBarTitle("Map");
            }
        });

        weather = view.findViewById(R.id.weather);
        tvWeather = view.findViewById(R.id.tvWeather);
        tvHumidity = view.findViewById(R.id.tvHumidity);
        tvCloud = view.findViewById(R.id.tvCloud);
        tvWindy = view.findViewById(R.id.tvWindy);

        // setClickWeather
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Weather_Fragment weatherFragment = new  Weather_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.homeFragment,weatherFragment );
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

    }

    public void GetCurrentWeatherData () {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Saigon&units=metric&appid=ac212f5768bf3e2f84201adbd2bc7961";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("MyRespond",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");

                            //get and set temp
                            String strTemp = jsonObjectMain.getString("temp");
                            Double a = Double.valueOf(strTemp);
                            String temp = String.valueOf(a.intValue());
                            Log.e("MyRespond Temp",temp);
                            tvWeather.setText(temp + "°");

                            //get and set humidity
                            String humidity = jsonObjectMain.getString("humidity");
                            Log.e("MyRespond humidity",humidity);
                            tvHumidity.setText(humidity + "%");

                            //get and set cloud
                            String cloud = jsonObjectCloud.getString("all");
                            Log.e("MyRespond Cloud",cloud);
                            tvCloud.setText(cloud + "%");

                            //get and set windy
                            String strWindy = jsonObjectWind.getString("speed");
                            Double b = Double.valueOf(strWindy);
                            String windy = String.valueOf(b.intValue());
                            Log.e("MyRespond Windy",windy);
                            tvWindy.setText(windy + "m/s");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MyRespond","fail");
                    }
                });
        requestQueue.add(stringRequest);

    }
}
