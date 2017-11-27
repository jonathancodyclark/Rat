package com.example.jccla.rat.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.jccla.rat.Model.DatabaseHelper;
import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.R;

import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText etRegister_username;
    private EditText etRegister_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        etRegister_username = (EditText) findViewById(R.id.etRegister_username);
        etRegister_password = (EditText) findViewById(R.id.etRegister_password);
        CircularProgressButton bRegister = (CircularProgressButton) findViewById(R.id.bRegister_register);
        CircularProgressButton bCancel = (CircularProgressButton) findViewById(R.id.bRegister_cancel);
        CircularProgressButton bViewUsers = (CircularProgressButton) findViewById(R.id.bRegister_viewUsers);
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
        bViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewUsers();
            }
        });
    }

    private void viewUsers() {
        Cursor cursor = db.getAllData();
        if(cursor.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append("Id :").append(cursor.getString(0)).append("\n");
            buffer.append("Username :").append(cursor.getString(1)).append("\n");
            buffer.append("Password :").append(cursor.getString(2)).append("\n");
            buffer.append("Admin :").append(cursor.getString(3)).append("\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }

    private void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }




    private void goToHomePage() {
        if (Model.getInstance().checkPasswordCharacteristics(etRegister_password.getText().toString())){
            boolean inserted = db.insertData(etRegister_username.getText().toString(), etRegister_password.getText().toString(), "no");
            if(inserted) {
                if (Model.getInstance().getItems().size() == 0) {
                    InputStream is = getResources().openRawResource(R.raw.rat_sightings);
                    Model.getInstance().readCSV(is);
                    Toast.makeText(this,"Loaded Rat Data from CSV",Toast.LENGTH_LONG).show();
                }
                startActivity(new Intent(this, HomeActivity.class));
            }
            else {
                Toast.makeText(this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "password doesn't meet requirements", Toast.LENGTH_LONG).show();
        }
    }

    private void goToWelcomePage() {
        startActivity(new Intent(this, WelcomeActivity.class));
    }

}
