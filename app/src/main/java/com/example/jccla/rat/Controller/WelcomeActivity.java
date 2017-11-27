package com.example.jccla.rat.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dd.CircularProgressButton;
import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.R;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        CircularProgressButton loginButton = (CircularProgressButton) findViewById(R.id.login_button);
        CircularProgressButton registerButton = (CircularProgressButton) findViewById(R.id.register_button);
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
