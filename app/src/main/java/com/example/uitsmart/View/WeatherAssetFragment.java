package com.example.uitsmart.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.uitsmart.Adapter.DetailAssetAdapter;
import com.example.uitsmart.Model.ItemDetail;
import com.example.uitsmart.R;

import java.util.ArrayList;


public class WeatherAssetFragment extends Fragment {

    ListView listView;
    static ArrayList<ItemDetail> arrayList;
    DetailAssetAdapter detailAssetAdapter;
    MapFragment mapFragment;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;
    DetailAssetAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_asset, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.lvItemDetail);


        adapter = new DetailAssetAdapter(getActivity(), R.layout.custom_listassetusercurrent, arrayList);
        listView.setAdapter(adapter);

        /*handleSSLHandshake();
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, AccessAPI.getUrlUserCurrent(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("onResponse: ", String.valueOf(response));
                    }
                },new Response.ErrorListener() {
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
        mRequestQueue.add(jsonObjectRequest);*/

    }

}