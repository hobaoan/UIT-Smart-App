package com.example.uitsmart.View;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uitsmart.MainActivity;
import com.example.uitsmart.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnLoginDL;
    private EditText etUserName;
    private EditText etPassWord;

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

                        String gtUserName = etUserName.getText().toString();
                        String gtPassWord = etPassWord.getText().toString();

                        if (gtUserName.equalsIgnoreCase("1") && gtPassWord.equalsIgnoreCase("1")) {
                            Intent intentMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentMainActivity);
                        }
                        else if (gtUserName.isEmpty() || gtPassWord.isEmpty()) {
                            Toast.makeText(LoginActivity.this,
                                            "Thông tin đăng nhập trống", Toast.LENGTH_SHORT)
                                    .show();

                        }
                        else {
                            Toast.makeText(LoginActivity.this,
                                            "Thông tin đăng nhập sai", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
                alertDialogLogin.show();
            }
        });

    }
}