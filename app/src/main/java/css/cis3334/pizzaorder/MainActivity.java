package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    TextView txtPizzasOrdered;
    Spinner spinnerToppings;
    PizzaOrderInterface pizza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the object reference

        pizza = new PizzaOrder(this);
        int a = 2;
        // Set up our radio buttons
        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);
        rbSmall.setText("Small Pizza: $" + pizza.getPrice(Pizza.pizzaSize.SMALL));
        rbMedium.setText("Medium Pizza: $" + pizza.getPrice(Pizza.pizzaSize.MEDIUM));
        rbLarge.setText("Large Pizza: $" + pizza.getPrice(Pizza.pizzaSize.LARGE));



        // Set up the Check Boxes
        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        // Set up the TextViews
        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);
        txtPizzasOrdered = (TextView) findViewById(R.id.textViewPizzasOrdered);
        // Set up the Spinner
        spinnerToppings = (Spinner) findViewById(R.id.spinnerToppings);






    }

    @Override
    public void updateOrderStatusInView(String orderStatus) {

        txtStatus.setText("Order Status: " + orderStatus); //Deliver option is included
    }

    public void onClickOrder(View view) {

        pizza.setDelivery(chkbxDelivery.isChecked());

        // The user checks what kind of pizza(Size) they want
        String size = "medium";
        Boolean extraCheese = false;

        if (rbSmall.isChecked()){
            size = "small";
        }
        if (rbMedium.isChecked()){
            size = "medium";
        }
        if (rbLarge.isChecked()){
            size = "large";
        }

        if (chkbxCheese.isChecked()){
            extraCheese = true;
        }

        // ****** For the Assignment, students need to add code here to get information from the UI widgets...

        String orderDescription = "No orders yet";

        //Modified so the data is dynamic
        orderDescription = pizza.OrderPizza(spinnerToppings.getSelectedItem().toString(), size.toString(), extraCheese );

        txtTotal.setText(pizza.getTotalBill().toString());



        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+orderDescription , Toast.LENGTH_LONG).show();
        // add this pizza to the textview the lists the pizzas
        txtPizzasOrdered.append(orderDescription+"\n");

    }
}
