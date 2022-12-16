package com.example.uitsmart.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uitsmart.Adapter.WeatherAdapter;
import com.example.uitsmart.Model.Weather;
import com.example.uitsmart.R;
import com.example.uitsmart.access.AccessAPI;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Weather_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Weather_Fragment extends Fragment {

    TextView tvTemp, tvMinT, tvMaxT, tvHumidityW, tvCloudW, tvWindW, tvTime, tvLocation;
    ImageView imgStatusW;
    EditText edtLocation;
    ImageButton btnSearch;

    TextView itemDay, itemDays, itemTemp;
    ImageView itemStatus;
    ListView listWeather;
    WeatherAdapter weatherAdapter;
    ArrayList <Weather> weatherList;

    String example = "0";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Weather_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Weather_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Weather_Fragment newInstance(String param1, String param2) {
        Weather_Fragment fragment = new Weather_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvLocation = view.findViewById(R.id.tvLocation);
        tvTime = view.findViewById(R.id.tvTime);
        tvTemp = view.findViewById(R.id.tvTemp);
        tvMinT = view.findViewById(R.id.tvMinT);
        tvMaxT = view.findViewById(R.id.tvMaxT);
        tvHumidityW = view.findViewById(R.id.tvHumidityW);
        tvCloudW = view.findViewById(R.id.tvCloudW);
        tvWindW = view.findViewById(R.id.tvWinddW);
        imgStatusW = view.findViewById(R.id.imgStatusW);
        edtLocation = view.findViewById(R.id.edtLocation);
        btnSearch = view.findViewById(R.id.btnSearch);

        listWeather =  view.findViewById(R.id.listWeather);
        weatherList = new ArrayList<Weather>();
        weatherAdapter = new WeatherAdapter(getActivity(), weatherList);
        listWeather.setAdapter(weatherAdapter);

        GetCurrentWeatherData("Saigon");
        GetDataWeather5days("Saigon");


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edtLocation.getText().toString();
                GetCurrentWeatherData(city);
                GetDataWeather5days(city);
            }
        });

    }
    public void GetCurrentWeatherData(String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AccessAPI.getUrlGoogleWeather(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("MyRespond",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");

                            //get Location
                            String nameCity = jsonObject.getString("name");
                            tvLocation.setText(nameCity);

                            //get Time
                            String day = jsonObject.getString("dt");
                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
                            String formatDay = simpleDateFormat.format(date);
                            tvTime.setText(formatDay);

                            //get and set temp
                            String strTemp = jsonObjectMain.getString("temp");
                            Double a = Double.valueOf(strTemp);
                            String temp = String.valueOf(a.intValue());
                            tvTemp.setText(temp + "°C");

                            //getStatusWeather
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("main");
                            String icon = jsonObjectWeather.getString("icon");

                            Picasso.with(getActivity()).load("http://openweathermap.org/img/wn/" + icon + ".png").into(imgStatusW);

                            //get and set tempmin
                            String strTempMin = jsonObjectMain.getString("temp_min");
                            Double b = Double.valueOf(strTempMin);
                            String tempMin = String.valueOf(b.intValue());
                            tvMinT.setText(tempMin + "°C");

                            //get and set tempMax
                            String strTempMax = jsonObjectMain.getString("temp_max");
                            Double c = Double.valueOf(strTempMax);
                            String tempMax = String.valueOf(c.intValue());
                            tvMaxT.setText(tempMax + "°C");

                            //get and set humidity
                            String humidity = jsonObjectMain.getString("humidity");
                            tvHumidityW.setText(humidity + "%");

                            //get and set cloud
                            String cloud = jsonObjectCloud.getString("all");
                            tvCloudW.setText(cloud + "%");

                            //get and set windy
                            String windy = jsonObjectWind.getString("speed");
                            tvWindW.setText(windy + " m/s");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MyRespond", "fail");
                    }
                });
        requestQueue.add(stringRequest);
    }

    public void GetDataWeather5days (String data) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+data+"&units=metric&appid=ac212f5768bf3e2f84201adbd2bc7961";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("MyRespond2",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArrayList = jsonObject.getJSONArray("list");
                            for (int i = 0; i < jsonArrayList.length(); i ++) {
                                JSONObject jsonObjectList = jsonArrayList.getJSONObject(i);

                                String strDay = jsonObjectList.getString("dt");
                                long l = Long.valueOf(strDay);
                                Date date = new Date(l*1000L);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                                String formatDay = simpleDateFormat.format(date);

                                String strDays = jsonObjectList.getString("dt");
                                long l1 = Long.valueOf(strDays);
                                Date date1 = new Date(l1*1000L);
                                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                                String formatDays = simpleDateFormat1.format(date1);

                                JSONObject jsonObjectMain = jsonObjectList.getJSONObject("main");
                                String strTemp = jsonObjectMain.getString("temp");
                                Double a = Double.valueOf(strTemp);
                                String temp = String.valueOf(a.intValue());

                                JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                                JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                                String icon = jsonObjectWeather.getString("icon");

                                if (formatDays.equals(example) == false) {
                                    weatherList.add(new Weather(formatDay,formatDays,temp,icon));
                                    example = formatDays;
                                }
                            }
                            weatherAdapter.notifyDataSetChanged();

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