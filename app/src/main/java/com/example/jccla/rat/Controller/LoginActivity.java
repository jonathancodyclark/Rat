package com.example.jccla.rat.Controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jccla.rat.Model.DatabaseHelper;
import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.R;

import java.io.InputStream;
import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db;
    private EditText etLogin_username;
    private EditText etLogin_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        etLogin_username = (EditText) findViewById(R.id.etLogin_username);
        etLogin_password = (EditText) findViewById(R.id.etLogin_password);
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
       if (db.checkUser(etLogin_username.getText().toString(), etLogin_password.getText().toString())) {
           View view = this.getCurrentFocus();
           if (view != null) {
               InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
               imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
           }

           if (Model.getInstance().getItems().size() == 0) {
               InputStream is = getResources().openRawResource(R.raw.rat_sightings);
               Model.getInstance().readCSV(is);
               Toast.makeText(this,"Loaded Rat Data from CSV",Toast.LENGTH_LONG).show();
           }
           startActivity(new Intent(this, HomeActivity.class));
       } else {
            System.out.println(etLogin_password.getText().toString() + " not in here");
       }
    }

    private void goToWelcomePage() {
        startActivity(new Intent(this, WelcomeActivity.class));
    }

}
