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
        stockName2 = findViewById(R.id.stockNameTwoId);
        stockName3 = findViewById(R.id.stockNameThreeId);
        stockName4 = findViewById(R.id.stockNameFourId);

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
            Toast.makeText(getApplicationContext(), Double.toString(sum), Toast.LENGTH_SHORT).show();
        }
        // Creating a method setData()
        // to set the text in text view and pie chart
        setData(list, stockMap, sum);
    }

    private void setData(ArrayList<Double> list, HashMap<Double, String> stockMap, double sum) {
        // Set the percentage of language used
        Double valueOne;
        Double valueTwo;
        Double valueThree;
        Double valueFour;

        if(stockMap.size() > 0){
            valueOne =  new Double(list.get(list.size() - 1) * 100 / sum );
            tvR.setText( Integer.toString(valueOne.intValue()) + "%");
            stockName1.setText(stockMap.get(list.get(list.size() - 1)));
            pieChart.addPieSlice(
                    new PieModel("lk",
                            Integer.parseInt(tvR.getText().toString().substring(0, tvR.getText().toString().length() - 1)),
                            Color.parseColor("#FFA726")));
        }
        if(stockMap.size() > 1){
            valueTwo =  new Double(list.get(list.size() - 2) * 100 / sum );
            tvPython.setText(valueTwo.intValue() + "%");
            stockName2.setText(stockMap.get(list.get(list.size() - 2)));
            pieChart.addPieSlice(
                    new PieModel(
                            "Pythonkl",
                            Integer.parseInt(tvPython.getText().toString().substring(0, tvPython.getText().toString().length() - 1)),
                            Color.parseColor("#66BB6A")));
        }
        if(stockMap.size() > 2){
            valueThree =  new Double(list.get(list.size() - 3) * 100 / sum );
            tvCPP.setText(valueThree.intValue() + "%");
            stockName3.setText(stockMap.get(list.get(list.size() - 3)));
            pieChart.addPieSlice(
                    new PieModel(
                            "C++",
                            Integer.parseInt(tvCPP.getText().toString().substring(0, tvCPP.getText().toString().length() - 1)),
                            Color.parseColor("#EF5350")));
        }
        if(stockMap.size() > 3){
            valueFour =  new Double(list.get(list.size() - 4) * 100 / sum );
            tvJava.setText(valueFour.intValue() + "%");
            stockName4.setText(stockMap.get(list.get(list.size() - 4)));
            pieChart.addPieSlice(
                    new PieModel(
                            "Java",
                            Integer.parseInt(tvJava.getText().toString().substring(0, tvJava.getText().toString().length() - 1)),
                            Color.parseColor("#29B6F6")));
        }
        pieChart.startAnimation();
    }
}
