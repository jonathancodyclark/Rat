package com.example.jccla.rat.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jccla.rat.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText etRegister_username = (EditText) findViewById(R.id.etRegister_username);
        EditText etRegister_password = (EditText) findViewById(R.id.etRegister_password);
        Button bRegister = (Button) findViewById(R.id.bRegister_register);
        Button bCancel = (Button) findViewById(R.id.bRegister_cancel);
        bRegister.setOnClickListener(new View.OnClickListener() {
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
        startActivity(new Intent(this, HomeActivity.class));
    }

    private void goToWelcomePage() {
        startActivity(new Intent(this, WelcomeActivity.class));
    }

}
