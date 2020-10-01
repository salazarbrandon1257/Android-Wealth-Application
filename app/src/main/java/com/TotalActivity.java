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
    public double twoDecimalplaces(double num){
        return (double) Math.round(num * 100) / 100;
    }

    public String growthCalculation(String growthString, String timeFrameString, Double sum){
        double result;
        double growth = 1 + Double.parseDouble(growthString);
        int timeFrame = Integer.parseInt(timeFrameString);
        result = sum * Math.pow(growth, timeFrame);
        return Double.toString(twoDecimalplaces(result));
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
        sum = twoDecimalplaces(sum);

        portfolioValue.setText("$" + Double.toString(sum));


        Button btn3 = (Button) findViewById(R.id.calculateButtonId);
        final double finalSum = sum;
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView portfolioVal  = (TextView) findViewById(R.id.portfolioValueId);
                TextView growthValue = (TextView) findViewById(R.id.yearlyGrowthId);
                TextView timeFrameValue = (TextView) findViewById(R.id.timeFrameId);
                TextView expected = (TextView) findViewById(R.id.expectedGrowthId);
                TextView difference = (TextView) findViewById(R.id.growthDifferenceId);
                TextView percentage = (TextView) findViewById(R.id.percentageGrowthId);

                String growth = growthValue.getText().toString();
                String timeFrame = timeFrameValue.getText().toString();
                String portfolio = portfolioVal.getText().toString();
                portfolio = portfolio.substring(1);
                double newPortfolio = Double.parseDouble(portfolio);

                if ( !growth.isEmpty() && !timeFrame.isEmpty() ){
                    String growthCalc = growthCalculation(growth, timeFrame, newPortfolio);
                    expected.setText(growthCalc);
                    difference.setText("+" + Double.toString(twoDecimalplaces(Double.parseDouble(growthCalc) - finalSum)));
                    percentage.setText( "(+" + Double.toString(twoDecimalplaces(100 *( (Double.parseDouble(growthCalc) / finalSum) - 1))) + "%)"   );
                }
            }
        });
    }
}