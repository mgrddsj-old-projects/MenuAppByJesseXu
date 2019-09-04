package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Bill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        TextView billList = (TextView) findViewById(R.id.billList);
        billList.setText(getBillString());
    }

    public String getBillString()
    {
        String billString = "";
        for (int i=0; i<MainActivity.foodList.size(); i++)
        {
            if (MainActivity.foodList.get(i).getAmount() != 0)
            {
                billString += MainActivity.foodList.get(i);
            }
        }
//        billString += "Total price = $" + getTotalPrice();
        return billString;
    }

    public double getTotalPrice()
    {
        double totalPrice = 0;
        for (int i=0; i<MainActivity.foodList.size(); i++)
        {
            totalPrice += MainActivity.foodList.get(i).getTotalPrice();
        }

        TextView priceDisplay = (TextView)findViewById(R.id.total_price);
        priceDisplay.setText("Total: $" + totalPrice);
        return totalPrice;
    }
}
