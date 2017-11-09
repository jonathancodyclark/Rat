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

import com.example.jccla.rat.Model.DatabaseHelper;
import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.R;

import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText etRegister_username;
    EditText etRegister_password;
    Button bRegister;
    Button bCancel;
    Button bViewUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        etRegister_username = (EditText) findViewById(R.id.etRegister_username);
        etRegister_password = (EditText) findViewById(R.id.etRegister_password);
        bRegister = (Button) findViewById(R.id.bRegister_register);
        bCancel = (Button) findViewById(R.id.bRegister_cancel);
        bViewUsers = (Button) findViewById(R.id.bRegister_viewUsers);
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
            buffer.append("Id :"+ cursor.getString(0)+"\n");
            buffer.append("Username :"+ cursor.getString(1)+"\n");
            buffer.append("Password :"+ cursor.getString(2)+"\n");
            buffer.append("Admin :"+ cursor.getString(3)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void goToHomePage() {
        boolean inserted = db.insertData(etRegister_username.getText().toString(), etRegister_password.getText().toString(), "no");
        if(inserted) {
            //Toast.makeText(this, "Data Inserted, Registered", Toast.LENGTH_LONG).show();
            if (Model.getInstance().getItems().size() == 0) {
                InputStream is = getResources().openRawResource(R.raw.rat_sightings);
                Model.getInstance().readCSV(is);
                Toast.makeText(this,"Loaded Rat Data from CSV",Toast.LENGTH_LONG).show();
            }
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
    }

    private void goToWelcomePage() {
        startActivity(new Intent(this, WelcomeActivity.class));
    }

}
