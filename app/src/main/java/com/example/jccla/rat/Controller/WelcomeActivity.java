package com.example.jccla.rat.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.R;

import java.io.Serializable;

public class WelcomeActivity extends AppCompatActivity {
    Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button loginButton = (Button) findViewById(R.id.login_button);
        Button registerButton = (Button) findViewById(R.id.register_button);
        model = new Model();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginScreen();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegisterScreen();
            }
        });
    }

    private void goToLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void goToRegisterScreen() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
