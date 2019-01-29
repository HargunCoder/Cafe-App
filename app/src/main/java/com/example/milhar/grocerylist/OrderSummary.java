package com.example.milhar.grocerylist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class OrderSummary extends AppCompatActivity {
    String coffeesRead;
    String coffesPriceRead;
    String customername;
    boolean whippedchecker=false;
    boolean chocolateChecker=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        Bundle data=getIntent().getExtras();
        //the intent pass data as bundle so we need a bundle type of variable
        if(data==null)
        {
            return;
        }
        coffeesRead=data.getString("coffee");
        coffesPriceRead=data.getString("coffeePrice");
        customername=data.getString("customerName");

        TextView coffeeDisplay=(TextView)findViewById(R.id.coffeeDisplay);
        coffeeDisplay.setText(coffeesRead);
        TextView coffeePriceDisplay=(TextView)findViewById(R.id.coffeePriceDisplay);
        coffeePriceDisplay.setText("$"+coffesPriceRead);
        TextView addingCustomerName=(TextView)findViewById(R.id.CustomerName);
        addingCustomerName.setText("Customer Name: "+customername);

    }
    public void returnToMain(View v)
    {
        Intent i2=new Intent(this,MainActivity.class);
        startActivity(i2);
    }
    public void message(View v)
    {
        //three things needed for making a toast message
        //context of the application
        //   Context con=getApplicationContext();
        //the text message
       //     String text="Payment done.Thank You!";
        // the duration for the toast. It returns a properly initialized Toast object
        //    int dur=Toast.LENGTH_LONG;
        //   Toast myMessage=Toast.makeText(con,text,dur);
        //to change default position of toast(bottom right corner) we use
        //toastname.gravity(int,int,int)
        //int-the Gravity,int,int-x and y offset
       //        myMessage.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,0,0);
        //  myMessage.show();
        //also we can You can also chain your methods and avoid holding on to the Toast object, like this:

        //Toast.makeText(context, text, dur).show();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //adding data
        String address[]={"hargun3896@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,address);

        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffe Order For "+customername);
        intent.putExtra(Intent.EXTRA_TEXT,"Number Of coffees Ordered:"+coffeesRead+"/nTotal Price:"+coffesPriceRead );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    }



