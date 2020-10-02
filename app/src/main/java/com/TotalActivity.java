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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public String yearlyInvestCalculation(String growthString, String timeFrameString, Double sum1, String yearly){
        BigDecimal result;
        BigDecimal sum = BigDecimal.valueOf(sum1);
        BigDecimal growth = BigDecimal.valueOf(1 + Double.parseDouble(growthString));
        BigDecimal yearlyInvest = BigDecimal.valueOf(Double.parseDouble(yearly));
        int timeFrame = Integer.parseInt(timeFrameString);
        if (sum1 == 0){
            Toast.makeText(getApplicationContext(), "No Sum", Toast.LENGTH_SHORT).show();
            result = yearlyInvest.multiply(growth.pow( timeFrame + 1).subtract(growth).divide(growth.subtract(BigDecimal.valueOf(1)),4, RoundingMode.CEILING));
        }else{
            Toast.makeText(getApplicationContext(), "Sum Exists", Toast.LENGTH_SHORT).show();
            result = yearlyInvest.multiply(growth.pow(timeFrame + 1).subtract(growth)).divide(growth.subtract(BigDecimal.valueOf(1)),2, RoundingMode.CEILING);
            Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
            BigDecimal initial = growth.pow(timeFrame).multiply(sum);
            result = result.add(initial);

        }
        return result.setScale(2, RoundingMode.CEILING).toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        final TextView portfolioValue  = (TextView) findViewById(R.id.portfolioValueId);

        double sum = 0;
        ArrayList<portfolioActivity.Stocks> portfolioStocks = MainActivity.contactClass.getContacts();
        for(portfolioActivity.Stocks stock: portfolioStocks){
            sum += Double.parseDouble(stock.getPrice().substring(1)) * stock.getShares();
        }
        sum = twoDecimalplaces(sum);

        portfolioValue.setText("$" + Double.toString(sum));


        Button btn3 = (Button) findViewById(R.id.calculateButtonId);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView portfolioVal  = (TextView) findViewById(R.id.portfolioValueId);
                TextView growthValue = (TextView) findViewById(R.id.yearlyGrowthId);
                TextView timeFrameValue = (TextView) findViewById(R.id.timeFrameId);
                TextView yearlyInvestValue = (TextView) findViewById(R.id.yearlyInvestId);
                TextView expected = (TextView) findViewById(R.id.expectedGrowthId);
                TextView difference = (TextView) findViewById(R.id.growthDifferenceId);
                TextView percentage = (TextView) findViewById(R.id.percentageGrowthId);

                String growth = growthValue.getText().toString();
                String timeFrame = timeFrameValue.getText().toString();
                String yearlyInvest = yearlyInvestValue.getText().toString();
                String portfolio = portfolioVal.getText().toString();
                portfolio = portfolio.substring(1);
                double newPortfolio = Double.parseDouble(portfolio);

                if ( !growth.isEmpty() && !timeFrame.isEmpty() && yearlyInvest.isEmpty() ){
                    // String growthCalc = growthCalculation(growth, timeFrame, newPortfolio);
                    String investCalc = yearlyInvestCalculation(growth, timeFrame, newPortfolio, "0");
                    expected.setText(investCalc);
                    difference.setText("+" + Double.toString(twoDecimalplaces(Double.parseDouble(investCalc) - newPortfolio)));
                    percentage.setText( "(+" + Double.toString(twoDecimalplaces(100 *( (Double.parseDouble(investCalc) / newPortfolio) - 1))) + "%)"   );
                }else if(!growth.isEmpty() && !timeFrame.isEmpty() && !yearlyInvest.isEmpty() ){
                    String investCalc = yearlyInvestCalculation(growth, timeFrame, newPortfolio, yearlyInvest);
                    expected.setText(investCalc);
                    difference.setText("+" + Double.toString(twoDecimalplaces(Double.parseDouble(investCalc) - newPortfolio)));
                    percentage.setText( "(+" + Double.toString(twoDecimalplaces(100 *( (Double.parseDouble(investCalc) / newPortfolio) - 1))) + "%)"   );
                }
            }
        });
        ImageButton btn4 = (ImageButton) findViewById(R.id.refreshId);
        final double finalSum = sum;
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                portfolioValue.setText("$" + Double.toString(finalSum));
            }
        });
    }
}