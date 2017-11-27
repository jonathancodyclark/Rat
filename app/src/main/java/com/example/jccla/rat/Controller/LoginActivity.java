package com.example.jccla.rat.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.jccla.rat.Model.DatabaseHelper;
import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.R;

import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText etLogin_username;
    private EditText etLogin_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        etLogin_username = (EditText) findViewById(R.id.etLogin_username);
        etLogin_password = (EditText) findViewById(R.id.etLogin_password);
        final CircularProgressButton bLogin = (CircularProgressButton) findViewById(R.id.bLogin_login);
        CircularProgressButton bCancel = (CircularProgressButton) findViewById(R.id.bLogin_cancel);
        bLogin.setIndeterminateProgressMode(true);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bLogin.getProgress() == 0) {
                    bLogin.setProgress(30);
                } else if (bLogin.getProgress() == -1) {
                    bLogin.setProgress(0);
                } else if (bLogin.getProgress() == 100) {
                    startActivity(new Intent(LoginActivity.this, ButtonActivity.class));
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (db.checkUser(etLogin_username.getText().toString(), etLogin_password.getText().toString())) {
                            bLogin.setProgress(100);
                            View view = LoginActivity.this.getCurrentFocus();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }

                            if (Model.getInstance().getItems().size() == 0) {
                                InputStream is = getResources().openRawResource(R.raw.rat_sightings);
                                Model.getInstance().readCSV(is);
                            }
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        } else {
                            bLogin.setProgress(-1);
                        }
                    }
                }, 3000);
            }
        }
        );
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWelcomePage();
            }
        });
    }

    private void goToWelcomePage() {
        startActivity(new Intent(this, WelcomeActivity.class));
    }

}
