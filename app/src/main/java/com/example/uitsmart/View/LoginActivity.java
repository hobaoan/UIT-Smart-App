package com.example.uitsmart.View;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uitsmart.MainActivity;
import com.example.uitsmart.Model.User;
import com.example.uitsmart.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnLoginDL;
    private EditText etUserName;
    private EditText etPassWord;
    String gtUserName;
    String gtPassWord;
    SQLiteDatabase dbUser = SQLiteDatabase.openDatabase("/data/data/com.example.uitsmart/databases/QLND.db", null, SQLiteDatabase.OPEN_READWRITE);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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