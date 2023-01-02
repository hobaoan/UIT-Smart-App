package com.example.uitsmart.View;

import static com.example.uitsmart.SSLHandle.SSLHandle.handleSSLHandshake;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.uitsmart.MainActivity;
import com.example.uitsmart.Model.User;
import com.example.uitsmart.R;
import com.example.uitsmart.access.AccessAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnLoginDL;
    private EditText etUserName;
    private EditText etPassWord;
    String gtUserName;
    String gtPassWord;
    SQLiteDatabase dbUser = SQLiteDatabase.openDatabase("/data/data/com.example.uitsmart/databases/QLND.db", null, SQLiteDatabase.OPEN_READWRITE);

    RequestQueue mRequestQueue;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;
    JSONObject attributes, weatherData, value, main, weatherMain;
    JSONArray weather;
    double temp, feels_like;
    JSONObject weatherDescription;

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bắt đầu code get api
        handleSSLHandshake();
        mRequestQueue = Volley.newRequestQueue(LoginActivity.this);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://103.126.161.199/api/master/asset/6H4PeKLRMea1L0WsRXXWp9", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            attributes = response.getJSONObject("attributes");
                            weatherData = attributes.getJSONObject("weatherData");
                            value = weatherData.getJSONObject("value");
                            main = value.getJSONObject("main");
                            weather = value.getJSONArray("weather");
                            temp = main.getDouble("temp");
                            feels_like = main.getDouble("feels_like");
                            weatherDescription = new JSONObject(String.valueOf(weather.get(0)));
                            String description = weatherDescription.getString("description");
                            // khúc này là để t viết hoa chữ cái đầu cho cái description
                            String firstLetter = description.substring(0, 1);
                            String remainingLetters = description.substring(1, description.length());
                            firstLetter = firstLetter.toUpperCase();
                            description = firstLetter + remainingLetters;
                            Log.e("MyResponse","" + description);

                            // Bắt đầu code gửi thông báo
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                NotificationChannel channel = new NotificationChannel("MyChannel","MyChannel", NotificationManager.IMPORTANCE_DEFAULT);
                                NotificationManager manager = getSystemService(NotificationManager.class);
                                manager.createNotificationChannel(channel);
                            }

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginActivity.this,"MyChannel").
                                    setSmallIcon(R.drawable.ic_launcher_foreground)
                                    .setContentTitle(temp + "° in UIT")
                                    .setContentText("Feels like " + feels_like + "° • " + description);

                            notification = builder.build();

                            notificationManagerCompat = NotificationManagerCompat.from(LoginActivity.this);
                            // Kết thúc code gửi thông báo

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
        mRequestQueue.add(jsonObjectRequest);
        // Kết thúc code get api

        notificationManagerCompat.notify(1,notification);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(LoginActivity.this);
                View dialogLogin = li.inflate(R.layout.dialog_login, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this,R.style.CustomDialogLogin);
                alertDialogBuilder.setView(dialogLogin);
                AlertDialog alertDialogLogin = alertDialogBuilder.create();

                etUserName = dialogLogin.findViewById(R.id.etUserName);
                etPassWord = dialogLogin.findViewById(R.id.etPassWord);


                btnLoginDL = dialogLogin.findViewById(R.id.btnLoginDL);
                btnLoginDL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        gtUserName = etUserName.getText().toString();
                        gtPassWord = etPassWord.getText().toString();

                        if (gtUserName.isEmpty() || gtPassWord.isEmpty()) {
                            Toast.makeText(LoginActivity.this,
                                            "Empty login information", Toast.LENGTH_SHORT)
                                    .show();

                        }
                        else if (CheckLogin(gtUserName, gtPassWord) == true) {
                            Intent intentMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentMainActivity);
                            Toast.makeText(LoginActivity.this,
                                            "Logged in successfully", Toast.LENGTH_SHORT)
                                    .show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this,
                                            "Wrong login information", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
                alertDialogLogin.show();
            }
        });

    }

    public Boolean CheckLogin (String gtUserName, String gtPassWord) {
        Cursor cursor = dbUser.rawQuery("SELECT * FROM USER WHERE MSSV = "+gtUserName+" AND PASSWORD = "+gtPassWord+" ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User.setID(cursor.getInt(0));
            User.setName(cursor.getString(1));
            User.setClassU(cursor.getString(3));
            User.setPassWord(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }
}