package com.example.uitsmart.View;

import static com.example.uitsmart.SSLHandle.SSLHandle.handleSSLHandshake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.example.uitsmart.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StatisticFragment extends Fragment{

    RequestQueue mRequestQueue;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;
    JSONArray assetBounds;
    ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
    XYPlot plot;
    int Count = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //setStatistc
        plot = (XYPlot) view.findViewById(R.id.plot);
        handleSSLHandshake();
        mRequestQueue = Volley.newRequestQueue(getActivity());
        final Number[] domainLabels = {1, 1, 1, 6, 7, 8, 9, 10, 13, 14};
        Number[] series1Numbers = {1, 4, 2, 8, 4, 16, 8, 32, 16, 64};
        ArrayList<Double> winds = new ArrayList<>();
        winds.add(1.0);
        winds.add(3.3);
        winds.add(2.4);
        winds.add(5.6);
        winds.add(2.3);
        winds.add(1.1);

//
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                /*// JSON Object type
                jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, AccessAPI.getUrlUserCurrent(), null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject attributes = response.getJSONObject("attributes");
                                    JSONObject weatherData = attributes.getJSONObject("weatherData");
                                    JSONObject value = weatherData.getJSONObject("value");
                                    JSONObject wind = value.getJSONObject("wind");
                                    speed = wind.getDouble("speed");
                                    winds.add(speed);
                                    Log.e("MyResponse","Wind " + winds);
                                } catch (JSONException e) {
                                    Log.e("MyError", "" + e);
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
                        params.put("accept", "application/json;");
                        params.put("Authorization", "Bearer " + AccessAPI.getToken());
                        return params;
                    }
                };
                mRequestQueue.add(jsonObjectRequest);*/
            }

        }, 0, 5000);

        XYSeries series1 = new SimpleXYSeries(
                winds, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Winds");

        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(getActivity(), R.xml.line_point_formatter_with_labels);

        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        plot.addSeries(series1, series1Format);

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(domainLabels[i]);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });


    }
}
