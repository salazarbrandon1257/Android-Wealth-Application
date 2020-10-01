package com.example.divcal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.lang.*;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;



public class portfolioActivity<TAG> extends AppCompatActivity {
    ArrayList<Stocks> contacts = MainActivity.contactClass.getContacts();

    public static String addList(String ticker) {
        Stock stock = null;
        try {
            stock = YahooFinance.get(ticker);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigDecimal price = stock.getQuote().getPrice();
        return "$" + price.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvStocks);
        // Initialize contacts
        Toast.makeText(getApplicationContext(), "List is initialized", Toast.LENGTH_SHORT).show();
        // Create adapter passing in the sample user data
        final StockAdapter adapter = new StockAdapter(contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ImageButton btn1 = (ImageButton) findViewById(R.id.addButtonId);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText enteredName  = (EditText) findViewById(R.id.stockNameId);
                String enteredStock = enteredName.getText().toString();
                EditText enteredNum  = (EditText) findViewById(R.id.shareNumberId);
                String enteredShare = enteredNum.getText().toString();

                adapter.add(new Stocks(enteredStock, addList(enteredStock), Double.parseDouble(enteredShare)));
                Toast.makeText(getApplicationContext(), "Stock was added", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

    }


    public static class Stocks {
        private String mName;
        private String mPrice;
        private double mShares;

        public Stocks(String name, String price, double shares) {
            mName = name;
            mPrice = price;
            mShares = shares;
        }

        public String getName() {
            return mName;
        }

        public String getPrice() {
            return mPrice;
        }
        public Double getShares() {
            return mShares;
        }
    }
}

