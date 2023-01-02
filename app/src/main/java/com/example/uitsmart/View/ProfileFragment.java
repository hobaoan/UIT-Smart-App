package com.example.uitsmart.View;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uitsmart.Model.User;
import com.example.uitsmart.R;

public class ProfileFragment extends Fragment{
    TextView name, id, classU;
    Button btnLogout, btnChange, btnDCghange;
    EditText etPW1, etPW2, etPWV;

    String gtPW1;
    String gtPW2;
    String gtPWV;

    SQLiteDatabase dbUser = SQLiteDatabase.openDatabase("/data/data/com.example.uitsmart/databases/QLND.db", null, SQLiteDatabase.OPEN_READWRITE);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.name);
        id = view.findViewById(R.id.id);
        classU = view.findViewById(R.id.classU);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnChange = view.findViewById(R.id.btnChange);


        name.setText(User.getName());
        id.setText(String.valueOf(User.getID()));
        classU.setText(User.getClassU());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentMainActivity);
                Toast.makeText(getActivity(),
                                "Logged out", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(getActivity());
                View dialogChange = li.inflate(R.layout.dialog_changepass, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(),R.style.CustomDialogLogin);
                alertDialogBuilder.setView(dialogChange);
                AlertDialog alertDialogLogin = alertDialogBuilder.create();

                etPW1 = dialogChange.findViewById(R.id.etPW1);
                etPW2 = dialogChange.findViewById(R.id.etPW2);
                etPWV = dialogChange.findViewById(R.id.etPWV);
                btnDCghange = dialogChange.findViewById(R.id.btnChangeDL);

                btnDCghange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gtPW1 = etPW1.getText().toString();
                        gtPW2 = etPW2.getText().toString();
                        gtPWV = etPWV.getText().toString();

                        String DBpw = User.getPassWord();

                        if (DBpw.equals(gtPW1) == true && DBpw.equals(gtPW2) == true) {
                            String sql = "UPDATE USER SET PASSWORD = "+gtPWV+"  ";
                            dbUser.execSQL(sql);

                            Toast.makeText(getActivity(),
                                            "Change password successfully", Toast.LENGTH_SHORT)
                                    .show();
                        }
                        else {
                            Toast.makeText(getActivity(),
                                            "Incorrect password", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
                alertDialogLogin.show();
            }
        });
    }
}
