package com.example.mouhammad.mapapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    EditText etlat,etlon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.button);
        etlat = (EditText) findViewById(R.id.editText);
        etlon = (EditText) findViewById(R.id.editText2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etlon.getText().toString().equals("") || etlat.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();

                }
                else{
                    Intent i = new Intent(MainActivity.this, MapActivity.class);
                    Location.latitude = Double.parseDouble(etlat.getText().toString());
                    Location.longitude = Double.parseDouble(etlon.getText().toString());
                    startActivity(i);
                }
            }
        });
    }
}
