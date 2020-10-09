package com.example.divcal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.TotalActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn = (ImageButton) findViewById(R.id.portfolioId);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, portfolioActivity.class));
            }
        });

        ImageButton btn2 = (ImageButton) findViewById(R.id.totalsId);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TotalActivity.class));
            }
        });

        ImageButton btn3 = (ImageButton) findViewById(R.id.chartsId);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChartsActivity.class));
            }
        });
        ImageButton btn4 = (ImageButton) findViewById(R.id.settingsId);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

    }



    public static class contactClass {
        private static ArrayList<portfolioActivity.Stocks> contacts = new ArrayList<portfolioActivity.Stocks>();
        public static ArrayList<portfolioActivity.Stocks> getContacts(){
            return contacts;
        }
    }
}