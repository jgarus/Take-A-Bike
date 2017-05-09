package com.take_a_bikev2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by David on 5/2/2017.
 */

public class SelectActivity extends AppCompatActivity {

    static String key = ""; //
    static int value = 0; //

    Random r = new Random();
    List<Integer> values = new ArrayList<Integer>(); //ArrayList for different bikes available

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        //Number of bikes from selected station
        Bundle extras = getIntent().getExtras();
        key = extras.getString("Stations"); //Pull bike stations
        value = extras.getInt("Availability"); //Pulls station value

        //Add to arrayList/Condition for duplicate numbers
        for(int i = 0; i < Integer.valueOf(value); i++) {
            int number = r.nextInt(9000 - 1000 + 1) + 1;
            if (!values.contains(number)) {values.add(number);}
        }

        insert();
    }

    //Set arrayList to GridView
    public void insert() {

        //TextView text_view1 = (TextView) findViewById(R.id.textView_array);


        GridView grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(new ArrayAdapter<Integer>(this, R.layout.list_item, R.id.textView_array, values));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

               // View view = super.getView(parent,position,v,parent);
                // text_view1 = (TextView) view;
                //text_view1.setBackgroundResource(Color.GREEN);
                //text_view1.setBackgroundColor(Color.GREEN);
                Toast.makeText(getBaseContext(),
                        "Bike: " + (values.get(position)) + " selected",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //send to email method

    }
}
