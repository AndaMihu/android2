package com.example.andamariam.examproject2;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;

public class DishActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener //for the picker we need to implement OnDateSetListener
{

    CheckBox fill1;
    CheckBox fill2;
    CheckBox fill3;
//    CheckBox fill4;
//    CheckBox fill5;
//    CheckBox fill6;

    Resources resources;

    Button btnCheckOut;
    Button btnTime;
    RadioGroup radioGroup;

    Spinner sauceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        resources = getResources();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        fill1 = (CheckBox) findViewById(R.id.fill1);
        fill2 = (CheckBox) findViewById(R.id.fill2);
        fill3 = (CheckBox) findViewById(R.id.fill3);
//        fill4 = (CheckBox) findViewById(R.id.fill4);
//        fill5 = (CheckBox) findViewById(R.id.fill5);
//        fill6 = (CheckBox) findViewById(R.id.fill6);

        btnTime = (Button) findViewById(R.id.btnDelivery2);
        btnCheckOut = (Button) findViewById(R.id.btnCheckout);


        //////////Spinner for the sauce type\\\\\\\\\\


       sauceSpinner = (Spinner) findViewById(R.id.sauceSpinner);


        ArrayList<String> sauceArray = new ArrayList<String>(
                Arrays.asList(resources.getStringArray(R.array.sauce)));

        ArrayAdapter<String> strAdapter=
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,sauceArray);

        sauceSpinner.setAdapter(strAdapter);

        ///////////////Time picker\\\\\\\\\\\\\\\\\\\\

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        //////////Checkout button \\\\\\\\\\\\\\

        btnCheckOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //save the results from the checkbox, radio and spinner into a string
                Intent intent = new Intent(DishActivity.this, CheckoutActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1)
    {
        TextView textViewTime = (TextView) findViewById(R.id.textViewTime);
        String result = timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute() + " h";
        textViewTime.append(result);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dish, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.pasta)
        {
            TextView dishTitle = (TextView) findViewById(R.id.dishTitle);
            dishTitle.setText(resources.getString(R.string.ownPasta));
            radioGroup.setVisibility(View.VISIBLE);
            fill1.setVisibility(View.VISIBLE);
            fill2.setVisibility(View.VISIBLE);
            fill3.setVisibility(View.VISIBLE);
            sauceSpinner.setVisibility(View.VISIBLE);
            btnTime.setVisibility(View.VISIBLE);
            btnCheckOut.setVisibility(View.VISIBLE);

        }


        if (id == R.id.burger)
        {
            TextView dishTitle = (TextView) findViewById(R.id.dishTitle);
            dishTitle.setText(resources.getString(R.string.ownBurger));

        }

        if (id == R.id.pizza)
        {
            TextView dishTitle = (TextView) findViewById(R.id.dishTitle);
            dishTitle.setText(resources.getString(R.string.ownPizza));

        }

        return super.onOptionsItemSelected(item);


    }



}
