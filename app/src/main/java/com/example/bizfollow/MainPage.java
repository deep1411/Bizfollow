package com.example.bizfollow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainPage extends AppCompatActivity {

    ListView customerList;
    ArrayList<String> listItem;
    ArrayAdapter<String> adapter;
    DatabaseHelper clientDB;
    Button list, addCst, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        customerList = (ListView) findViewById(R.id.customerList);
        clientDB = new DatabaseHelper(this);

        listItem = new ArrayList<>();
        list = (Button) findViewById(R.id.button);
        addCst = (Button) findViewById(R.id.button2);
        settings  = (Button) findViewById(R.id.button3);

        viewData();


        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItem.clear();
                viewData();
            }
        });

        customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pos = Integer.toString(position + 1);
                //Toast.makeText(MainPage.this,""+pos, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CustomerView.class);
                intent.putExtra("POS",pos);
                startActivity(intent);

            }
        });


        addCst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCustomer.class);
                startActivity(intent);
            }
        });
    }

    public void viewData(){
        Cursor cursor = clientDB.getAllData();

        if(cursor.getCount() == 0)
            Toast.makeText(MainPage.this,"NO DATA",Toast.LENGTH_LONG).show();
        else
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listItem);
        customerList.setAdapter(adapter);
    }


    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
