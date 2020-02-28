package com.example.bizfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer extends AppCompatActivity implements View.OnClickListener {

    public static DatabaseHelper clientDB;
    EditText cName, cAdd, vehicle, fFreq;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        cName = (EditText) findViewById(R.id.cstName);
        cAdd = (EditText) findViewById(R.id.cstAddress);
        vehicle = (EditText) findViewById(R.id.cstVehicle);
        fFreq = (EditText) findViewById(R.id.followFreq);
        save = (Button) findViewById(R.id.save);

        clientDB = new DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = clientDB.insertData(cName.getText().toString(),cAdd.getText().toString(),vehicle.getText().toString(),fFreq.getText().toString());

                if(isInserted==true){
                    Toast.makeText(AddCustomer.this,"DATA INSERTED",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(AddCustomer.this, "DATA NOT INSERTED", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
