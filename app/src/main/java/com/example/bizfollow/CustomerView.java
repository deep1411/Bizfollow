package com.example.bizfollow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerView extends AppCompatActivity {
    EditText name, address, vehicle, freq;
    Button update, delete, back;
    DatabaseHelper clientDB;
    String pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        name = (EditText) findViewById(R.id.editText);
        address = (EditText) findViewById(R.id.editText2);
        vehicle = (EditText) findViewById(R.id.editText3);
        freq = (EditText) findViewById(R.id.editText4);
        update = (Button) findViewById(R.id.btnUpdate);
        delete = (Button) findViewById(R.id.btnDelete);
        back = (Button) findViewById(R.id.btnBack);
        clientDB = new DatabaseHelper(this);

        pos = getIntent().getStringExtra("POS");

        pop(pos);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = clientDB.updateData(pos,name.getText().toString(),address.getText().toString(),vehicle.getText().toString(),freq.getText().toString());
                if(isUpdated==true)
                    Toast.makeText(CustomerView.this,"DATA UPDATED",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CustomerView.this,"DATA NOT UPDATED",Toast.LENGTH_LONG).show();
                pop(pos);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = clientDB.deleteData(pos);

                if(deletedRows>0)
                    Toast.makeText(CustomerView.this,"DATA DELETED",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CustomerView.this,"DATA NOT DELETED",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
            }
        });




    }

    public void pop(String pos){
        Cursor res = clientDB.getAllData();
        if ((res.getCount() == 0)){
            //show
            showMessage("Error","Nothing is found");
            return;
        }
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            if (res.getString(0).contentEquals(pos)) {
                name.setText(res.getString(1));
                address.setText(res.getString(2));
                vehicle.setText(res.getString(3));
                freq.setText(res.getString(4));
            }
        }
    }

    public void bck(View view){
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        startActivity(intent);
    }


    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
