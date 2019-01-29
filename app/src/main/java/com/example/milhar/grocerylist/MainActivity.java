package com.example.milhar.grocerylist;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees=1;

    private boolean ischecked;
    private boolean ischecked2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void giveSummary(View v)
    {
        CheckBox ischeck=(CheckBox)findViewById(R.id.checkBoxState);
       ischecked=ischeck.isChecked();


        CheckBox ischeck2=(CheckBox)findViewById(R.id.checkChocolateBoxState);
        ischecked2=ischeck2.isChecked();


        int price=calculatePrice(numberOfCoffees);

        EditText user=(EditText) findViewById(R.id.username);
        if( user.getText().toString().length() == 0 )
            user.setError( "First name is required!" );
        else {
            String customer = user.getText().toString();
            customer = "Name: " + customer;
            String messageUser = "Thank You";
            displayMessage(customer + "\nWhipped Cream added : " + ischecked + "\nChocolate : " + ischecked2 + "\nTotal:$ " + price + "\n" + messageUser);
            TextView chanfeForOrder = (TextView) findViewById(R.id.myHeading);
            chanfeForOrder.setText("ORDER SUMMARY");
            chanfeForOrder.setTextSize(20);
        }
    }

    public void submit(View V)
    {

        EditText user=(EditText) findViewById(R.id.username);
        if( user.getText().toString().length() == 0 )
            user.setError( "First name is required!" );
        else {
            String customer = user.getText().toString();

            //we want to start another activity to show bill so we use intent
            Intent i = new Intent(this, OrderSummary.class);
            //we pass this and name of class we want to visit in the arguements

            //Using the putExtra Method to get the data needed in other app
            i.putExtra("coffee", "" + numberOfCoffees);

            i.putExtra("coffeePrice", "" + calculatePrice(numberOfCoffees));
            i.putExtra("customerName", customer);
            i.putExtra("whippedCream", ischecked);
            i.putExtra("chocolate", ischecked2);

            startActivity(i);

        }

    }
    public void display(int x)
    {
        TextView myview=(TextView) findViewById(R.id.quantity);
        myview.setText(""+x);
    }
    protected void displayPrice(int x)
    {
        TextView priceView=(TextView)findViewById(R.id.priceView);

        priceView.setText(NumberFormat.getCurrencyInstance().format(calculatePrice(x)));
    }
    public void  increment(View v)
    {
        if(numberOfCoffees<=9)
        {
            numberOfCoffees++;
            display(numberOfCoffees);
            displayPrice(numberOfCoffees);
        }
        else
        {
            Toast.makeText(this,"Max 10 cups allowed per user",Toast.LENGTH_SHORT).show();
        }

    }
    public void decrement(View v)
    {
        if(numberOfCoffees<=1)
        {
            Toast.makeText(this,"Min order needs to be 1 cup of Coffee",Toast.LENGTH_SHORT).show();

        }
        else {
            numberOfCoffees--;
            display(numberOfCoffees);
            displayPrice(numberOfCoffees);
        }
    }
public void displayMessage(String inp)
{
    TextView change=(TextView)findViewById(R.id.priceView);
    change.setText(inp);
}
public int calculatePrice(int x)
            {   int pricefinal=0;
                int whippedCoffee=0;
                int chocolate=0;
                pricefinal=x*5;

                if(ischecked)
                {
                    whippedCoffee=1;
                }

                if(ischecked2)
                {
                    chocolate=2;
                }


                pricefinal+=(chocolate*x)+(whippedCoffee*x);
                return pricefinal;
            }

}
