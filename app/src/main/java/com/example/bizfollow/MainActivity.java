package com.example.bizfollow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText usrName;
    EditText pass;
    Button login;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usrName =(EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        sharedPreferences = getSharedPreferences("USERDATA", MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input data
                String usrNm = usrName.getText().toString();
                String pasw = pass.getText().toString();

                //Edit to put data in Shared preference
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("UNAME",usrNm);
                editor.putString("UPASS",pasw);
                //apply changes to shared preference
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onClick(View view) {

    }
}
