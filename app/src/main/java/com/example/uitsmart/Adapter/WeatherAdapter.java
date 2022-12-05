package com.example.uitsmart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uitsmart.Model.Weather;
import com.example.uitsmart.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherAdapter extends BaseAdapter {
    Context context;
    ArrayList <Weather> arrayList;

    public WeatherAdapter(Context context, ArrayList<Weather> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_listview_weather, null);

        Weather weather = arrayList.get(position);
        TextView tvDay = view.findViewById(R.id.itemDay);
        TextView tvDays = view.findViewById(R.id.itemDays);
        TextView tvTemp = view.findViewById(R.id.itemTemp);
        ImageView imgStatus = view.findViewById(R.id.itemStatus);

        tvDay.setText(weather.day);
        tvDays.setText(weather.days);
        tvTemp.setText(weather.temp + "°C");

        Picasso.with(context).load("http://openweathermap.org/img/wn/" + weather.image + ".png").into(imgStatus);
        return view;
    }
}
