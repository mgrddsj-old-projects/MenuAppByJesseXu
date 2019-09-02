package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodList = new ArrayList<>();
        foodList.add(new Food("Sausage", 5, "Do not cut"));
        foodList.add(new Food("Lasagna", 15, "Do not cut"));
        foodList.add(new Food("Pizza", 20, "Spicy"));

        createEventListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.reset) {
            // do something here
            reset();
        }
        return super.onOptionsItemSelected(item);
    }

    public void createEventListeners()
    {
        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Order received! \nYou need to pay: $" + getTotalPrice(), Toast.LENGTH_LONG).show();
            }
        });

        RadioGroup sausage = (RadioGroup) findViewById(R.id.sausage_radio);
        sausage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case -1:
                        break;
                    case R.id.sausage_radio1:
                        foodList.get(findFood("Sausage")).setRadioOption("Cut into Pieces");
                        break;
                    case R.id.sausage_radio2:
                        foodList.get(findFood("Sausage")).setRadioOption("Do not cut");
                        break;
                }
            }
        });

        RadioGroup lasagna = (RadioGroup) findViewById(R.id.lasagna_radio);
        lasagna.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case -1:
                        break;
                    case R.id.lasagna_radio1:
                        foodList.get(findFood("Lasagna")).setRadioOption("Cut into Pieces");
                        break;
                    case R.id.lasagna_radio2:
                        foodList.get(findFood("Lasagna")).setRadioOption("Do not cut");
                        break;
                }
            }
        });

        RadioGroup pizza = (RadioGroup) findViewById(R.id.pizza_radio);
        pizza.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case -1:
                        break;
                    case R.id.sausage_radio1:
                        foodList.get(findFood("Pizza")).setRadioOption("Spicy");
                        break;
                    case R.id.sausage_radio2:
                        foodList.get(findFood("Pizza")).setRadioOption("Not Spicy");
                        break;
                }
            }
        });
    }

    public double getTotalPrice()
    {
        double totalPrice = 0;
        for (int i=0; i<foodList.size(); i++)
        {
            totalPrice += foodList.get(i).getTotalPrice();
        }

        TextView priceDisplay = (TextView)findViewById(R.id.total_price);
        priceDisplay.setText("Total: $" + totalPrice);
        return totalPrice;
    }

    public void reset()
    {
        for (int i=0; i<foodList.size(); i++)
        {
            foodList.get(i).setAmount(0);
        }
        getTotalPrice();

        TextView SausageAmount = (TextView) findViewById(R.id.sausage_amount);
        SausageAmount.setText(foodList.get(findFood("Sausage")).getAmount() + " pcs");
        TextView LasagnaAamount = (TextView) findViewById(R.id.lasagna_amount);
        LasagnaAamount.setText(foodList.get(findFood("Lasagna")).getAmount() + " pcs");
        TextView PizzaAmount = (TextView) findViewById(R.id.pizza_amount);
        PizzaAmount.setText(foodList.get(findFood("Pizza")).getAmount() + " pcs");

        Toast.makeText(getApplicationContext(), "Orders have been reset!", Toast.LENGTH_LONG).show();
    }

    public int findFood(String name)
    {
        for (int i=0; i<foodList.size(); i++)
        {
            if (foodList.get(i).getName().equals(name))
            {
                return i;
            }
        }
        return -1;
    }

    public void addSausage(View view)
    {
        foodList.get(findFood("Sausage")).addOne();

        TextView amount = (TextView) findViewById(R.id.sausage_amount);
        amount.setText(foodList.get(findFood("Sausage")).getAmount() + " pcs");

        getTotalPrice();
    }

    public void minusSausage(View view)
    {
        foodList.get(findFood("Sausage")).minusOne();

        TextView amount = (TextView) findViewById(R.id.sausage_amount);
        amount.setText(foodList.get(findFood("Sausage")).getAmount() + " pcs");

        getTotalPrice();
    }

    public void addLasagna(View view)
    {
        foodList.get(findFood("Lasagna")).addOne();

        TextView amount = (TextView) findViewById(R.id.lasagna_amount);
        amount.setText(foodList.get(findFood("Lasagna")).getAmount() + " pcs");

        getTotalPrice();
    }

    public void minusLasagna(View view)
    {
        foodList.get(findFood("Lasagna")).minusOne();

        TextView amount = (TextView) findViewById(R.id.lasagna_amount);
        amount.setText(foodList.get(findFood("Lasagna")).getAmount() + " pcs");

        getTotalPrice();
    }

    public void addPizza(View view)
    {
        foodList.get(findFood("Pizza")).addOne();

        TextView amount = (TextView) findViewById(R.id.pizza_amount);
        amount.setText(foodList.get(findFood("Pizza")).getAmount() + " pcs");

        getTotalPrice();
    }

    public void minusPizza(View view)
    {
        foodList.get(findFood("Pizza")).minusOne();

        TextView amount = (TextView) findViewById(R.id.pizza_amount);
        amount.setText(foodList.get(findFood("Pizza")).getAmount() + " pcs");

        getTotalPrice();
    }

    public void sausage_check1(View view)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.sausage_check1);
        if (checkBox.isChecked())
        {
            foodList.get(findFood("Sausage")).addSpecialRequirement("Add Pepper");
        }
        else
        {
            foodList.get(findFood("Sausage")).removeSpecialRequirement("Add Pepper");
        }
    }

    public void sausage_check2(View view)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.sausage_check2);
        if (checkBox.isChecked())
        {
            foodList.get(findFood("Sausage")).addSpecialRequirement("More Cheeeeese!");
        }
        else
        {
            foodList.get(findFood("Sausage")).removeSpecialRequirement("More Cheeeeese!");
        }
    }

    public void lasagna_check1(View view)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.lasagna_check1);
        if (checkBox.isChecked())
        {
            foodList.get(findFood("Lasagna")).addSpecialRequirement("More Cheeeeese!");
        }
        else
        {
            foodList.get(findFood("Lasagna")).removeSpecialRequirement("More Cheeeeese!");
        }
    }

    public void lasagna_check2(View view)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.lasagna_check2);
        if (checkBox.isChecked())
        {
            foodList.get(findFood("Lasagna")).addSpecialRequirement("Add Pepper");
        }
        else
        {
            foodList.get(findFood("Lasagna")).removeSpecialRequirement("Add Pepper");
        }
    }

    public void pizza_check1(View view)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.pizza_check1);
        if (checkBox.isChecked())
        {
            foodList.get(findFood("Pizza")).addSpecialRequirement("More Cheeeeese!");
        }
        else
        {
            foodList.get(findFood("Pizza")).removeSpecialRequirement("More Cheeeeese!");
        }
    }

    public void pizza_check2(View view)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.pizza_check2);
        if (checkBox.isChecked())
        {
            foodList.get(findFood("Pizza")).addSpecialRequirement("Double pepperoni");
        }
        else
        {
            foodList.get(findFood("Pizza")).removeSpecialRequirement("Double pepperoni");
        }
    }
}

class Food
{
    private String name;
    private String radioOption;
    private double price;
    private int amount;
    private ArrayList<String> specialRequirements;

    public Food(String _name, double _price, String defaultRadio)
    {
        name = _name;
        price = _price;
        amount = 0;
        specialRequirements = new ArrayList<>();
        radioOption = defaultRadio;
    }

    public String getName()
    {
        return name;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int _amount)
    {
        amount = _amount;
    }

    public void addOne()
    {
        amount++;
    }

    public void minusOne()
    {
        if (amount>0)
        {
            amount--;
        }
    }

    public double getTotalPrice()
    {
        return amount*price;
    }

    public void setRadioOption(String _radioOption)
    {
        radioOption = _radioOption;
    }

    public String getRadioOption()
    {
        return radioOption;
    }

    public void addSpecialRequirement(String _specialRequirement)
    {
        specialRequirements.add(_specialRequirement);
    }

    public void removeSpecialRequirement(String _specialRequirement)
    {
        for (int i=0; i<specialRequirements.size(); i++)
        {
            if (specialRequirements.get(i).equals(_specialRequirement))
            {
                specialRequirements.remove(i);
                break;
            }
        }
    }
}