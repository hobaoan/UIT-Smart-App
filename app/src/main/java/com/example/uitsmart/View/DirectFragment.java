package com.example.uitsmart.View;

import static com.example.uitsmart.SSLHandle.SSLHandle.handleSSLHandshake;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class DirectFragment extends Fragment implements OnMapReadyCallback {


    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;
    JSONObject jsonOptions, jsonDefault;
    JSONArray center, bounds, assetBounds;
    double zoom, maxZoom, minZoom;
    boolean boxZoom;
    String geocodeUrl;
    int index;
    Button btna, btnb, btnc, btne;

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
                        Log.e("MyResponse", "" + response);
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
                                            Log.e("MyResponseUserCurrent", "" + response);
                                            for (int i = 0; i < 84; i++) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(String.valueOf(response.get(i)));
                                                    JSONObject jsonObjectAttributes = jsonObject.getJSONObject("attributes");
                                                    JSONObject jsonObjectLocation = jsonObjectAttributes.getJSONObject("location");
                                                    JSONObject jsonObjectValue = jsonObjectLocation.getJSONObject("value");
                                                    assetBounds = jsonObjectValue.getJSONArray("coordinates");

                                                    String nameMarker = jsonObject.getString("name");

                                                    if (String.valueOf(assetBounds).isEmpty()) {
                                                    } else {
                                                        LatLng locationUserCurrent = new LatLng((Double) assetBounds.get(1), (Double) assetBounds.get(0));
                                                        googleMap.addMarker(new MarkerOptions().position(locationUserCurrent).title(nameMarker));
                                                    }

                                                    Log.e("MyTest", "" + assetBounds);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            LatLng B_A = new LatLng(10.870278919123411, 106.80295270479344);
                                            LatLng B_B = new LatLng(10.870167914043385, 106.80381878819092);
                                            LatLng B_C = new LatLng(10.869504706653828, 106.80310165581015);
                                            LatLng B_E = new LatLng(10.869796662639732, 106.80262607394883);
                                            LatLng point1 = new LatLng(10.87072947426334, 106.80251851354039);
                                            LatLng point2 = new LatLng(10.870376562301479, 106.80215875430015);
                                            LatLng point3 = new LatLng(10.870168686815887, 106.80233763340405);
                                            LatLng point4 = new LatLng(10.869502302043843, 106.80235211120933);
                                            LatLng point5 = new LatLng(10.869353911381467, 106.80249678285853);
                                            LatLng point6 = new LatLng(10.869304167584604, 106.8036760136343);
                                            LatLng point7 = new LatLng(10.869212591544864, 106.80385562332302);
                                            LatLng point8 = new LatLng(10.869613859393198, 106.80390576947677);
                                            LatLng point9 = new LatLng(10.869648854094187, 106.80419593620394);
                                            LatLng point10 = new LatLng(10.869925949275558, 106.80423751570254);
                                            LatLng point11 = new LatLng(10.870149488642511, 106.80411793920334);
                                            LatLng point12 = new LatLng(10.870271068631371, 106.80385938956732);
                                            LatLng point13 = new LatLng(10.870250527893193, 106.80322493120428);
                                            LatLng point14 = new LatLng(10.87141051836525, 106.80321881331716);
                                            LatLng point_a3 = new LatLng(10.869296887115464, 106.8031115506875);
                                            LatLng point_e3 = new LatLng(10.869811919423809, 106.80233667397982);


                                            //set onclick marker
                                            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                                @Override
                                                public boolean onMarkerClick(Marker marker) {

                                                    String tempName = marker.getTitle();
                                                    for (index = 0; index < 84; index++) {
                                                        try {
                                                            JSONObject jsonObject = new JSONObject(String.valueOf(response.get(index)));

                                                            JSONObject jsonObjectAttributes = jsonObject.getJSONObject("attributes");
                                                            JSONObject jsonObjectLocation = jsonObjectAttributes.getJSONObject("location");
                                                            JSONObject jsonObjectValue = jsonObjectLocation.getJSONObject("value");
                                                            assetBounds = jsonObjectValue.getJSONArray("coordinates");
                                                            LatLng locationUserCurrent = new LatLng((Double) assetBounds.get(1), (Double) assetBounds.get(0));

                                                            String nameMarker = jsonObject.getString("name");

                                                            //check click marker
                                                            if (tempName.equals(nameMarker) == true) {
                                                                View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_direct, null);
                                                                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                                                                bottomSheetDialog.setContentView(viewDialog);
                                                                bottomSheetDialog.show();

                                                                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tvNameAssetDirect = viewDialog.findViewById(R.id.tvNameAssetDirect);
                                                                tvNameAssetDirect.setText(nameMarker);
                                                                //event click marker

                                                                btna = viewDialog.findViewById(R.id.btna);
                                                                btnb = viewDialog.findViewById(R.id.btnb);
                                                                btnc = viewDialog.findViewById(R.id.btnc);
                                                                btne = viewDialog.findViewById(R.id.btne);



                                                                btna.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        String name1 = "Weather Asset";
                                                                        String name2 = "Weather Asset 2";
                                                                        if (nameMarker.equals(name1) == true) {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_A,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                        else if (nameMarker.equals(name2) == true) {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_A,
                                                                                                    point1,
                                                                                                    point14,
                                                                                                    point13,
                                                                                                    point12,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                        else {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_A,
                                                                                                    point1,
                                                                                                    point2,
                                                                                                    point3,
                                                                                                    point4,
                                                                                                    point5,
                                                                                                    point_a3,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                    }
                                                                });
                                                                btnb.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        String name1 = "Weather Asset";
                                                                        String name2 = "Weather Asset 2";
                                                                        if (nameMarker.equals(name1) == true) {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_B,
                                                                                                    point12,
                                                                                                    point13,
                                                                                                    point14,
                                                                                                    point1,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                        else if (nameMarker.equals(name2) == true) {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_B,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                        else {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_B,
                                                                                                    point12,
                                                                                                    point11,
                                                                                                    point10,
                                                                                                    point9,
                                                                                                    point8,
                                                                                                    point7,
                                                                                                    point6,
                                                                                                    point_a3,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                    }
                                                                });
                                                                btnc.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        String name1 = "Weather Asset";
                                                                        String name2 = "Weather Asset 2";
                                                                        if (nameMarker.equals(name1) == true) {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_C,
                                                                                                    point_a3,
                                                                                                    point5,
                                                                                                    point4,
                                                                                                    point3,
                                                                                                    point2,
                                                                                                    point1,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                        else if (nameMarker.equals(name2) == true) {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_C,
                                                                                                    point_a3,
                                                                                                    point6,
                                                                                                    point7,
                                                                                                    point8,
                                                                                                    point9,
                                                                                                    point10,
                                                                                                    point11,
                                                                                                    point12,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                        else {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_C,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                    }
                                                                });
                                                                btne.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        String name1 = "Weather Asset";
                                                                        String name2 = "Weather Asset 2";
                                                                        if (nameMarker.equals(name1) == true) {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_E,
                                                                                                    point_e3,
                                                                                                    point3,
                                                                                                    point2,
                                                                                                    point1,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                        else if (nameMarker.equals(name2) == true) {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_E,
                                                                                                    point_e3,
                                                                                                    point3,
                                                                                                    point2,
                                                                                                    point1,
                                                                                                    point14,
                                                                                                    point13,
                                                                                                    point12,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
                                                                        else {
                                                                            googleMap.clear();
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870464609989156,106.80273973945748
                                                                            )).title("Weather Asset"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.870537960015568, 106.80390988702072
                                                                            )).title("Weather Asset 2"));
                                                                            googleMap.addMarker(new MarkerOptions().position(new LatLng(10.869792504441904, 106.80308672274282
                                                                            )).title("Weather Asset 3"));
                                                                            googleMap.addPolyline(new PolylineOptions().add(
                                                                                                    B_E,
                                                                                                    point_e3,
                                                                                                    point4,
                                                                                                    point5,
                                                                                                    point_a3,
                                                                                                    locationUserCurrent
                                                                                            )
                                                                                            .width(15)
                                                                                            .color(Color.BLUE)
                                                                            );
                                                                        }
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

                                                LatLng UIT = new LatLng((Double) center.get(1), (Double) center.get(0));

                                                LatLngBounds UITBounds = new LatLngBounds(
                                                        new LatLng(10.868475091219045, 106.80238412611375),
                                                        new LatLng(10.871065625539076, 106.80469880695267)
                                                );
                                                googleMap.setMapType(MAP_TYPE_NORMAL);
                                                //add Marker
                                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UIT, (float) maxZoom));
                                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UIT, (float) maxZoom));
                                                googleMap.setLatLngBoundsForCameraTarget(UITBounds);
                                                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                                    // TODO: Consider calling
                                                    //    ActivityCompat#requestPermissions
                                                    // here to request the missing permissions, and then overriding
                                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                    //                                          int[] grantResults)
                                                    // to handle the case where the user grants the permission. See the documentation
                                                    // for ActivityCompat#requestPermissions for more details.
                                                    return;
                                                }
                                                //getMylocation
                                                googleMap.setMyLocationEnabled(true);
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