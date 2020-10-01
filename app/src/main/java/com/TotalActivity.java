package com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divcal.MainActivity;
import com.example.divcal.R;
import com.example.divcal.StockAdapter;
import com.example.divcal.portfolioActivity;

import java.util.ArrayList;

public class TotalActivity extends AppCompatActivity {
    public String growthCalculation(String growthString, String timeFrameString, Double sum){
        double result;
        double growth = 1 + Double.parseDouble(growthString);
        int timeFrame = Integer.parseInt(timeFrameString);

        result = sum * Math.pow(growth, timeFrame);
        result= (double) Math.round(result * 100) / 100;
        return Double.toString(result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        TextView portfolioValue  = (TextView) findViewById(R.id.portfolioValueId);

        double sum = 0;
        ArrayList<portfolioActivity.Stocks> portfolioStocks = MainActivity.contactClass.getContacts();
        for(portfolioActivity.Stocks stock: portfolioStocks){
            sum += Double.parseDouble(stock.getPrice().substring(1)) * stock.getShares();
        }
        sum = (double) Math.round(sum * 100) / 100;

        portfolioValue.setText("$" + Double.toString(sum));


        Button btn3 = (Button) findViewById(R.id.calculateButtonId);
        final double finalSum = sum;
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView growthValue = (TextView) findViewById(R.id.yearlyGrowthId);
                TextView timeFrameValue = (TextView) findViewById(R.id.timeFrameId);
                TextView expected = (TextView) findViewById(R.id.expectedGrowthId);

                String growth = growthValue.getText().toString();
                String timeFrame = timeFrameValue.getText().toString();

                if ( !growth.isEmpty() && !timeFrame.isEmpty() ){
                    expected.setText(growthCalculation(growth, timeFrame, finalSum));
                }
            }
        });
    }
}