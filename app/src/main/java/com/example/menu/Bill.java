package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
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
        billString += "Total price = $" + getTotalPrice();
        return billString;
    }

    public double getTotalPrice()
    {
        double totalPrice = 0;
        for (int i=0; i<MainActivity.foodList.size(); i++)
        {
            totalPrice += MainActivity.foodList.get(i).getTotalPrice();
        }
        return totalPrice;
    }

    public void pay(View view)
    {
        Intent intent = new Intent(this, Payment.class);
        startActivity(intent);
    }

    public void send(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:Jesse_Xu@live.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "A new order");
        intent.putExtra(Intent.EXTRA_TEXT, getBillString());

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }

    public void addToCalendar(View view)
    {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, "Order")
                .putExtra(CalendarContract.Events.DESCRIPTION, getBillString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
