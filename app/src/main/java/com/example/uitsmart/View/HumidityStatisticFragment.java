package com.example.uitsmart.View;

import static com.example.uitsmart.SSLHandle.SSLHandle.handleSSLHandshake;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HumidityStatisticFragment extends Fragment{

    RequestQueue mRequestQueue;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;
    JSONArray assetBounds;
    ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
    XYPlot plot;
    String example = "0";
    SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.uitsmart/databases/Weather Asset", null, SQLiteDatabase.OPEN_READWRITE);
    ArrayList<Double> temperatures = new ArrayList<>();
    ArrayList<Double> humidities = new ArrayList<>();
    ArrayList<Double> winds = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_humidity_statistic, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        plot = (XYPlot) view.findViewById(R.id.plot);
        handleSSLHandshake();
        mRequestQueue = Volley.newRequestQueue(getActivity());

        Cursor cursor = db.rawQuery("SELECT * FROM WEATHERASSET", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String time = cursor.getString(3);
            long l1 = Long.valueOf(time);
            Date date1 = new Date(l1*1000L);
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM");
            String formatDays = simpleDateFormat1.format(date1);
            if (formatDays.equals(example) == false) {
                temperatures.add(cursor.getDouble(0));
                humidities.add(cursor.getDouble(1));
                winds.add(cursor.getDouble(2));
                times.add(formatDays);
                example = formatDays;
            }
            cursor.moveToNext();
        }
        cursor.close();

        XYSeries series1 = new SimpleXYSeries(
                humidities, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, null);

        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(getActivity(), R.xml.line_point_formatter_with_labels);

        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        plot.addSeries(series1, series1Format);

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(times.get(i));
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });
    }
}