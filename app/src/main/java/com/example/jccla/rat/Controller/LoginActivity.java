package com.example.jccla.rat.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jccla.rat.Model.DatabaseHelper;
import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.R;

import java.io.InputStream;
import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {
    //DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //db = new DatabaseHelper(this);
        EditText etLogin_username = (EditText) findViewById(R.id.etLogin_username);
        EditText etLogin_password = (EditText) findViewById(R.id.etLogin_password);
        Button bLogin = (Button) findViewById(R.id.bLogin_login);
        Button bCancel = (Button) findViewById(R.id.bLogin_cancel);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomePage();
            }
        });
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWelcomePage();
            }
        });
    }

    private void goToHomePage() {
        InputStream is = getResources().openRawResource(R.raw.rat_sightings);
        Toast.makeText(this,"Loading Rat Data from CSV",Toast.LENGTH_LONG).show();
        Model.getInstance().readCSV(is);

        startActivity(new Intent(this, HomeActivity.class));
    }

    private void goToWelcomePage() {
        startActivity(new Intent(this, WelcomeActivity.class));
    }

}
