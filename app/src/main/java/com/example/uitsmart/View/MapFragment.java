package com.example.uitsmart.View;

import static com.example.uitsmart.SSLHandle.SSLHandle.handleSSLHandshake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.uitsmart.Model.ItemDetail;
import com.example.uitsmart.R;
import com.example.uitsmart.access.AccessAPI;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MapFragment extends Fragment implements OnMapReadyCallback{




    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;
    JSONObject jsonOptions, jsonDefault;
    JSONArray center, bounds, assetBounds;
    double zoom, maxZoom, minZoom;
    boolean boxZoom;
    String geocodeUrl;
    int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        handleSSLHandshake();

        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, AccessAPI.getURLMap(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("MyResponse",""+ response);
                        try {
                            jsonOptions = response.getJSONObject("options");
                            jsonDefault = jsonOptions.getJSONObject("default");
                            // Các thông số
                            center = jsonDefault.getJSONArray("center");
                            bounds = jsonDefault.getJSONArray("bounds");
                            zoom = jsonDefault.getDouble("zoom");
                            maxZoom = jsonDefault.getDouble("maxZoom");
                            boxZoom = jsonDefault.getBoolean("boxZoom");
                            geocodeUrl = jsonDefault.getString("geocodeUrl");
                            minZoom = jsonDefault.getDouble("minZoom");

                            // Call API userCurrent
                            RequestQueue aRequestQueue = Volley.newRequestQueue(getActivity());

                            jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, AccessAPI.getUrlUserCurrent(), null,
                                    new Response.Listener<JSONArray>() {
                                        @Override
                                        public void onResponse(JSONArray response) {
                                            Log.e("MyResponseUserCurrent",""+ response);
                                            for (int i = 0; i < 84; i ++) {
                                                try {
                                                   JSONObject jsonObject = new JSONObject(String.valueOf(response.get(i)));
                                                   JSONObject jsonObjectAttributes = jsonObject.getJSONObject("attributes");
                                                   JSONObject jsonObjectLocation = jsonObjectAttributes.getJSONObject("location");
                                                   JSONObject jsonObjectValue = jsonObjectLocation.getJSONObject("value");
                                                   assetBounds = jsonObjectValue.getJSONArray("coordinates");

                                                   String nameMarker = jsonObject.getString("name");

                                                   if (String.valueOf(assetBounds).isEmpty()) {
                                                   }
                                                   else {
                                                       LatLng locationUserCurrent = new LatLng((Double) assetBounds.get(1),(Double)assetBounds.get(0));
                                                       googleMap.addMarker(new MarkerOptions().position(locationUserCurrent).title(nameMarker));
                                                   }

                                                    Log.e("MyTest",""+ assetBounds );
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            //set onclick marker
                                            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                                @Override
                                                public boolean onMarkerClick(Marker marker) {

                                                    String tempName = marker.getTitle();
                                                    for (index = 0; index < 84; index ++) {
                                                        try {
                                                            JSONObject jsonObject = new JSONObject(String.valueOf(response.get(index)));

                                                            JSONObject jsonObjectAttributes = jsonObject.getJSONObject("attributes");
                                                            JSONObject jsonObjectLocation = jsonObjectAttributes.getJSONObject("location");

                                                            String nameMarker = jsonObject.getString("name");

                                                            if (tempName.equals(nameMarker) == true) {
                                                                View viewDialog= getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
                                                                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                                                                bottomSheetDialog.setContentView(viewDialog);
                                                                bottomSheetDialog.show();

                                                                TextView tvNameAsset = viewDialog.findViewById(R.id.tvNameAsset);
                                                                tvNameAsset.setText(nameMarker);

                                                                String ID = jsonObject.getString("id");
                                                                TextView tvID = viewDialog.findViewById(R.id.tvID);
                                                                tvID.setText(ID);

                                                                String version = jsonObject.getString("version");
                                                                TextView tvVersion = viewDialog.findViewById(R.id.tvVersion);
                                                                tvVersion.setText(version);

                                                                String createdOn = jsonObject.getString("createdOn");
                                                                long l1 = Long.valueOf(createdOn);
                                                                Date date1 = new Date(l1*1000L);
                                                                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM-dd");
                                                                String formatDays = simpleDateFormat1.format(date1);
                                                                TextView tvCreatedOn = viewDialog.findViewById(R.id.tvCreatedOn);
                                                                tvCreatedOn.setText(formatDays);

                                                                String type = jsonObject.getString("type");
                                                                TextView tvType = viewDialog.findViewById(R.id.tvType);
                                                                tvType.setText(type);


                                                                JSONObject jsonObjectWeatherData = jsonObjectAttributes.getJSONObject("weatherData");
                                                                JSONObject jsonObjectValue = jsonObjectWeatherData.getJSONObject("value");
                                                                JSONObject jsonObjectMain = jsonObjectValue.getJSONObject("main");
                                                                JSONObject jsonObjectWind = jsonObjectValue.getJSONObject("wind");
                                                                JSONObject jsonObjectCloud = jsonObjectValue.getJSONObject("clouds");

                                                                String temp =  jsonObjectMain.getString("temp");
                                                                String humidity = jsonObjectMain.getString("humidity");
                                                                String pressure = jsonObjectMain.getString("pressure");
                                                                String sea_level = jsonObjectMain.getString("sea_level");
                                                                String feels_like = jsonObjectMain.getString("feels_like");
                                                                String grnd_level = jsonObjectMain.getString("grnd_level");
                                                                String speed = jsonObjectWind.getString("speed");
                                                                String all = jsonObjectCloud.getString("all");



                                                                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnDetail = viewDialog.findViewById(R.id.btnDetail);
                                                                btnDetail.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {

                                                                        WeatherAssetFragment.arrayList = new ArrayList<>();
                                                                        WeatherAssetFragment.arrayList.add(new ItemDetail("Temperature", temp + "°C", R.drawable.weather));
                                                                        WeatherAssetFragment.arrayList.add(new ItemDetail("Humidity", humidity + "%", R.drawable.humidity));
                                                                        WeatherAssetFragment.arrayList.add(new ItemDetail("Pressure", pressure + "Mpa", R.drawable.maxtemp));
                                                                        WeatherAssetFragment.arrayList.add(new ItemDetail("Sea level", sea_level + "m", R.drawable.sealevel));
                                                                        WeatherAssetFragment.arrayList.add(new ItemDetail("Feels like", feels_like + "°C", R.drawable.mintemp));
                                                                        WeatherAssetFragment.arrayList.add(new ItemDetail("Ground level",grnd_level, R.drawable.groundlevel));
                                                                        WeatherAssetFragment.arrayList.add(new ItemDetail("Speed",speed + "m/s", R.drawable.windy));
                                                                        WeatherAssetFragment.arrayList.add(new ItemDetail("Clouds",all + "%", R.drawable.cloud));

                                                                        bottomSheetDialog.dismiss();
                                                                        WeatherAssetFragment weatherAssetFragment = new WeatherAssetFragment();
                                                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                                        transaction.replace(R.id.mapFragment, weatherAssetFragment);
                                                                        transaction.addToBackStack(null);
                                                                        transaction.commit();
                                                                    }
                                                                });
                                                            }

                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }

                                                    }

                                                    return false;
                                                }
                                            });


                                            // Add data on Map
                                            try {

                                                LatLng UIT = new LatLng((Double) center.get(1),(Double)center.get(0));

                                                LatLngBounds UITBounds = new LatLngBounds(
                                                        new LatLng((Double) bounds.get(1),(Double) bounds.get(0)),
                                                        new LatLng((Double) bounds.get(3),(Double) bounds.get(2))
                                                );
                                                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                                                //add Marker
                                                googleMap.addMarker(new MarkerOptions().position(UIT).title("Trường Đại Học Công Nghệ Thông Tin"));
                                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UIT, (float) maxZoom - 2));
                                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UIT, (float)maxZoom - 2));
                                                googleMap.setLatLngBoundsForCameraTarget(UITBounds);
                                            } catch (JSONException e) {
                                                Log.e("MyErrorLoadMakerMap", "" + e);
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("MyErrorUserCurrent", "" + error);
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

                        } catch (JSONException e) {
                            Log.e("MyErrorGetDataMap", "" + e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MyError", "" + error);
                    }
                }) {
            @Override
            public java.util.Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json;");
                params.put("token", "Bearer " + AccessAPI.getToken());
                return params;
            }
        };
        mRequestQueue.add(jsonObjectRequest);
    }
}