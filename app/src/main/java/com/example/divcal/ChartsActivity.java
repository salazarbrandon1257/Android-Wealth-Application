package com.example.divcal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import javax.xml.transform.dom.DOMLocator;

public class ChartsActivity extends AppCompatActivity {
    ArrayList<String> nameList = new ArrayList<>();
    HashMap<Double, String> stockMap = new HashMap<Double, String>();

    TextView tvR, tvPython, tvCPP, tvJava, stockName1, stockName2, stockName3, stockName4;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        tvR = findViewById(R.id.tvR);
        tvPython = findViewById(R.id.tvPython);
        tvCPP = findViewById(R.id.tvCPP);
        tvJava = findViewById(R.id.tvJava);
        pieChart = findViewById(R.id.piechart);
        stockName1 = findViewById(R.id.stockNameOneId);
        stockName2 = findViewById(R.id.stockNameTwoID);
        stockName3 = findViewById(R.id.stockNameThreeID);
        stockName4 = findViewById(R.id.stockNameFourID);

        final TextView portfolioValue  = (TextView) findViewById(R.id.portfolioValueId);

        double sum = 0;

        ArrayList<portfolioActivity.Stocks> portfolioStocks = MainActivity.contactClass.getContacts();
        for(portfolioActivity.Stocks stock: portfolioStocks){
            stockMap.put(Double.parseDouble(stock.getPrice().substring(1)) * stock.getShares(), stock.getName());

            sum += Double.parseDouble(stock.getPrice().substring(1)) * stock.getShares();
        }
        Collection<Double> values= stockMap.keySet();
        ArrayList<Double> list= new ArrayList<Double>(values);
        Collections.sort(list);

        //for (int i=0; i < 4; i++) {
        //    System.out.println(list.get(i));
        //}
        if(list.size() > 0) {
            //Toast.makeText(getApplicationContext(), list.get(0).toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), stockMap.get(list.get(list.size() - 1)), Toast.LENGTH_SHORT).show();
        }
        // Creating a method setData()
        // to set the text in text view and pie chart
        setData(list, stockMap);
    }

    private void setData(ArrayList<Double> list, HashMap<Double, String> stockMap) {
        // Set the percentage of language used
        tvR.setText(Integer.toString(40));
        tvPython.setText(Integer.toString(30));
        tvCPP.setText(Integer.toString(5));
        tvJava.setText(Integer.toString(25));
        if(stockMap.size() > 0){stockName1.setText(stockMap.get(list.get(list.size() - 1)));}
        if(stockMap.size() > 1){stockName2.setText(stockMap.get(list.get(list.size() - 2)));}
        if(stockMap.size() > 2){stockName3.setText(stockMap.get(list.get(list.size() - 3)));}
        if(stockMap.size() > 3){stockName4.setText(stockMap.get(list.get(list.size() - 4)));}

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel("lk",
                        Integer.parseInt(tvR.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Pythonkl",
                        Integer.parseInt(tvPython.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "C++",
                        Integer.parseInt(tvCPP.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Java",
                        Integer.parseInt(tvJava.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}
